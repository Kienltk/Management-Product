import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Brand, Category, Product, Promotion } from '../models/product.model';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Toast, ToastrModule, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-view-list',
  imports: [CommonModule, RouterModule, FormsModule,ToastrModule],
  templateUrl: './view-list.component.html',
  styleUrls: ['./view-list.component.css'],
  standalone: true
})
export class ViewListComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];
  brands: Brand[] = [];
  search: string = "";
  currentPage: number = 1;
  limit: number = 8;
  totalPages: number = 1;
  totalItems: number = 0;

  constructor(private productService: ProductService, private router: Router ,private toastr: ToastrService) {}

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
    this.loadBrands();
  }

  onSearch(): void {
    this.currentPage = 1; // Reset về trang 1 khi tìm kiếm
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts(this.currentPage, this.limit, true, this.search).subscribe(res => {
      console.log('Products data:', res); 
      this.products = res.data;
      this.totalPages = res.totalPage;
      this.totalItems = res.totalItems;
      this.currentPage = res.page;
    }
  );
  }
  getPageNumbers(): number[] {
    const pageNumbers = [];
    const maxPagesToShow = 7;
    
    if (this.totalPages <= maxPagesToShow) {
      for (let i = 1; i <= this.totalPages; i++) {
        pageNumbers.push(i);
      }
    } else {
      const half = Math.floor(maxPagesToShow / 2);
      let start = this.currentPage - half;
      let end = this.currentPage + half;
  
      if (start < 1) {
        start = 1;
        end = maxPagesToShow;
      } else if (end > this.totalPages) {
        end = this.totalPages;
        start = this.totalPages - maxPagesToShow + 1;
      }
  
      for (let i = start; i <= end; i++) {
        pageNumbers.push(i);
      }
    }
  
    return pageNumbers;
  }
  
  
  goToPage(page: number): void {
    if (page !== this.currentPage) {
      this.currentPage = page;
      this.loadProducts();
    }
  }
  
  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.loadProducts();
    }
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.loadProducts();
    }
  }

  loadCategories(): void {
    this.productService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  loadBrands(): void {
    this.productService.getBrands().subscribe(data => {
      this.brands = data;
    });
  }

  getCategoryById(id: number): string {
    const category = this.categories.find(c => c.id === id);
    return category ? category.categoryName : 'Unknown Category';
  }

  getBrandById(id: number): string {
    const brand = this.brands.find(b => b.id === id);
    return brand ? brand.brandName : 'Unknown Brand';
  }

  viewDetails(id: number,hrefParam:string): void {
    this.router.navigate(['/products/detail', id,hrefParam]);
  }

  addProduct(): void {
    this.router.navigate(['/products/create']);
  }

  editProduct(id: number): void {
    this.router.navigate(['/products/edit', id]);
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.loadProducts();
        this.toastr.success("Delete product successfully!")
      });
    }
  }
}