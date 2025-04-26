import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Brand, Category, Product } from '../models/product.model';
import { ProductService } from '../services/product.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class DetailComponent implements OnInit {
  product: Product | null = null;
  categories: Category[] = [];
   brands: Brand[] = [];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? +idParam : null;
    if (id) {
      this.loadProduct(id);
    }
    this.loadBrands();
    this.loadCategories();
  }
  
  goToList(): void {
    this.router.navigate(['/list']);
  }
  loadBrands(): void {
    this.productService.getBrands().subscribe(data => {
      this.brands = data;
    });
  }
  
  loadCategories(): void {
    this.productService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }
  

  loadProduct(id: number): void {
    this.productService.getProductById(id).subscribe(product => {
      this.product = product;
    });
  }
  getBrandNameById(id: number): string {
    const brand = this.brands.find(b => b.id === id);
    return brand ? brand.brandName : 'Unknown Brand';
  }
  
  getCategoryNameById(id: number): string {
    const category = this.categories.find(c => c.id === id);
    return category ? category.categoryName : 'Unknown Category';
  }
  
}
