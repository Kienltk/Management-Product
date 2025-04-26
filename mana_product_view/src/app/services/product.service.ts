import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Product, Category, Brand } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/api'; 

  constructor(private http: HttpClient) {}

 
  // getAll
  getProducts(page: number = 1, limit: number = 8, isPaginate: boolean = true, search?: string): Observable<{
    data: Product[],
    totalPage: number,
    page: number,
    limit: number,
    totalItems: number
  }> {
    let url = `${this.apiUrl}/products?isPaginate=${isPaginate}&page=${page}&limit=${limit}`;
    if (search?.trim()) {
      url += `&search=${encodeURIComponent(search)}`;
    }
  
    return this.http.get<any>(url).pipe(
      map(response => response.data)
    );
  }
  
  
  // getOne
  getProductById(id: number): Observable<Product> {
    return this.http.get<{message: string, data: Product}>(`${this.apiUrl}/products/${id}`)
      .pipe(
        map(response => response.data)
      );
  }

  // add
  addProduct(product: Product): Observable<Product> {
    return this.http.post<{message: string, data: Product}>(`${this.apiUrl}/products`, product)
      .pipe(
        map(response => response.data)
      );
  }

  // edit
  updateProduct(product: Product): Observable<Product> {
    return this.http.put<{message: string, data: Product}>(`${this.apiUrl}/products/${product.id}`, product)
      .pipe(
        map(response => response.data)
      );
  }

  // delete
  deleteProduct(id: number): Observable<void> {
    return this.http.delete<{message: string, data: void}>(`${this.apiUrl}/products/${id}`)
      .pipe(
        map(() => undefined)
      );
      
  }

  // get theo categories
  getCategories(): Observable<Category[]> {
    return this.http.get<{message: string, data: Category[]}>(`${this.apiUrl}/categories`)
      .pipe(
        map(response => response.data)
      );
  }

  // get theo brands
  getBrands(): Observable<Brand[]> {
    return this.http.get<{message: string, data: Brand[]}>(`${this.apiUrl}/brands`)
      .pipe(
        map(response => response.data)
      );
  }
  // Helper to fetch Category by ID
  getCategoryById(id: number): Observable<Category> {
    return this.http.get<{ message: string, data: Category }>(`${this.apiUrl}/categories/${id}`)
      .pipe(
        map(response => response.data)
      );
  }

  // Helper to fetch Brand by ID
   getBrandById(id: number): Observable<Brand> {
    return this.http.get<{ message: string, data: Brand }>(`${this.apiUrl}/brands/${id}`)
      .pipe(
        map(response => response.data)
      );
  }


}