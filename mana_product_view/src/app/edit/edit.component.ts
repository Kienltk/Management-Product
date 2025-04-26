import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { Product, Brand, Category, Promotion } from '../models/product.model';
import { ToastrModule, ToastrService } from 'ngx-toastr';

declare var $: any;

@Component({
  standalone: true,
  selector: 'app-edit',
  imports: [CommonModule, FormsModule, RouterModule,    ToastrModule],
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css'],
})
export class EditComponent implements OnInit, OnDestroy {
  product: Product = {
    id: 0,
    productName: '',
    price: 0,
    description: '',
    content: '',
    hrefParam: '',
    categoryId: 0,
    brandId: 0,
    promotions: [
      {
        id: 0,
        promotional: 0,
        promotionalStart: null,
        promotionalEnd: null,
      },
    ],
  };

  categories: Category[] = [];
  brands: Brand[] = [];

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute,
   private toastr: ToastrService
  ) {}
  get firstPromotion(): Promotion {
    if (!this.product.promotions || this.product.promotions.length === 0) {
      this.product.promotions = [
        {
          id: 0,
          promotional: 0,
          promotionalStart: null,
          promotionalEnd: null,
        },
      ];
    }
    return this.product.promotions[0];
  }
  goToList(): void {
    this.router.navigate(['/list']);
  }
  ngOnInit(): void {
    this.productService.getCategories().subscribe((c) => (this.categories = c));
    this.productService.getBrands().subscribe((b) => (this.brands = b));

    const productId = Number(this.route.snapshot.paramMap.get('id'));
    if (productId) {
      this.productService.getProductById(productId).subscribe({
        next: (product) => {
          this.product = {
            ...product,
            categoryId: product.categoryId ?? null,
            brandId: product.brandId ?? null,
            promotions:
              product.promotions && product.promotions.length > 0
                ? product.promotions
                : [
                    {
                      id: 0,
                      promotional: 0,
                      promotionalStart: new Date().toISOString(),
                      promotionalEnd: null,
                    },
                  ],
          };
          this.initializeSummernote();
        },
        error: (err) => {
          console.error('Error loading product:', err);
          alert('An error occurred while loading the product!');
        },
      });
    }
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
          onChange: function (contents: string) {
            that.product.content = contents;
          },
        },
      });
      $('#content').summernote('code', this.product.content);

      // Initialize Summernote for Description
      $('#description').summernote({
        height: 600,
        placeholder: 'Enter product description...',
        callbacks: {
          onChange: function (contents: string) {
            that.product.description = contents;
          },
        },
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
    if (
      !this.product.promotions?.[0] || 
      this.product.promotions[0].promotional === 0
    ) {
      this.product.promotions = [];
    }

    this.productService.updateProduct(this.product).subscribe({
      next: () => {
        this.toastr.success('Update product successfully!');
        this.router.navigate(['/list']);
      },
      error: (err) => {
        console.error('Error updating product:', err);
        this.toastr.error('An error occurred while updating the product!');
      },
    });
  }

  resetForm(): void {
    // Reload product data to reset form
    const productId = Number(this.route.snapshot.paramMap.get('id'));
    if (productId) {
      this.productService.getProductById(productId).subscribe({
        next: (product) => {
          this.product = {
            ...product,
            categoryId: product.categoryId ?? null,
            brandId: product.brandId ?? null,
            promotions:
              product.promotions && product.promotions.length > 0
                ? product.promotions
                : [
                    {
                      id: 0,
                      promotional: 0,
                      promotionalStart: new Date().toISOString(),
                      promotionalEnd: null,
                    },
                  ],
          };
          $('#content').summernote('code', this.product.content);
          $('#description').summernote('code', this.product.description);
        },
        error: (err) => {
          console.error('Error reloading product:', err);
          alert('An error occurred while reloading the product!');
        },
      });
    }
  }
}
