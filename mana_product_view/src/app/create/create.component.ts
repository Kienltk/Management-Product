import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Product, Brand, Category, Promotion } from '../models/product.model';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule, ToastrService } from 'ngx-toastr';

declare var $: any; // Declare jQuery for TypeScript

@Component({
  standalone: true,
  selector: 'app-create',
  imports: [CommonModule,
     FormsModule,
     RouterModule ,
    ToastrModule],
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateComponent implements OnInit, OnDestroy {
  product: Product = {
    id: 0,
    productName: '',
    price: 0,
    description: '',
    content: '',
    hrefParam: '',
    categoryId: 0,
    brandId: 0,
    promotions: [{
      id: 0,
      promotional: 0,
      promotionalStart: null,
      promotionalEnd: null
    }  ]
  };

  categories: Category[] = [];
  brands: Brand[] = [];
  get promotion(): Promotion {
    if (!this.product.promotions || this.product.promotions.length === 0) {
      // Tạo promotion mặc định nếu chưa có
      this.product.promotions = [{
        id: 0,
        promotional: 0,
        promotionalStart: null,
        promotionalEnd: null
      }];
    }
    return this.product.promotions[0];
  }
  
  
  constructor(private productService: ProductService, private router: Router, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.productService.getCategories().subscribe(c => this.categories = c);
    this.productService.getBrands().subscribe(b => this.brands = b);
    this.initializeSummernote();
    if (!this.product.promotions || this.product.promotions.length === 0) {
      this.product.promotions = [{
        id: 0,
        promotional: 0,
        promotionalStart: null,
        promotionalEnd: null
      }];
    }
  }
  goToList(): void {
    this.router.navigate(['/list']);
  }
  ngOnDestroy(): void {
    try {
      $('#content').summernote('destroy');
      $('#description').summernote('destroy');
    } catch (e) {
      console.warn('Summernote destroy failed:', e);
    }
  }

  initializeSummernote(): void {
    setTimeout(() => {
      console.log('Initializing Summernote...');
      console.log('jQuery version:', $?.fn?.jquery);
      console.log('Summernote available:', $.fn.summernote);

      const that = this;

      $('#content').summernote({
        height: 600,
        placeholder: 'Enter product specifications...',
        callbacks: {
          onChange: function(contents: string) {
            that.product.content = contents;
          }
        }
      });
      $('#content').summernote('code', this.product.content);

      $('#description').summernote({
        height: 600,
        placeholder: 'Enter product description...',
        callbacks: {
          onChange: function(contents: string) {
            that.product.description = contents;
          }
        }
      });
      $('#description').summernote('code', this.product.description);
    }, 0);
  }

  onSubmit(): void {
    this.product.content = $('#content').summernote('code');
    this.product.description = $('#description').summernote('code');

    if (!this.product.content || this.product.content === '<p><br></p>') {
      this.toastr.warning('Product specifications are required!');
      return;
    }
    if (!this.product.description || this.product.description === '<p><br></p>') {
      this.toastr.warning('Product description is required!');
      return;
    }
    if (!this.product.categoryId) {
      this.toastr.warning('Please select a category!');
      return;
    }
    if (!this.product.brandId) {
      this.toastr.warning('Please select a brand!');
      return;
    }
    const promotion = this.product.promotions?.[0];
    if (promotion && promotion.promotional === 0) {
      this.product.promotions = null;
    }
    this.productService.addProduct(this.product).subscribe({
      next: () => {
        this.toastr.success('Product added successfully!');
        this.router.navigate(['/list']);
      },
      error: (err) => {
        console.error('Error adding product:', err);
        this.toastr.error('An error occurred while adding the product!');
      }
    });
  }

  resetForm(): void {
    this.product = {
      id: 0,
      productName: '',
      price: 0,
      description: '',
      content: '',
      hrefParam: '',
      categoryId: 0,
      brandId: 0,
      promotions: [{
        id: 0,
        promotional: 0,
        promotionalStart:null ,
        promotionalEnd: null
      }]
    };

    // Clear Summernote content
    $('#content').summernote('code', '');
    $('#description').summernote('code', '');
  }
}