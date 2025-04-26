export interface Category {
    id: number;
    categoryName: string;
  }
  
  export interface Brand {
    id: number;
    brandName: string;
  }
  
  export interface Promotion {
    id: number | null;
    promotional: number | null;
    promotionalStart: string|null;
    promotionalEnd: string | null;
  }
  
  export interface Product {
    id: number;
    productName: string;
    price: number;
    content: string;
    description: string;
    hrefParam: string;
    categoryId: number;
    brandId: number;
    promotions: Promotion[] | null ;
  }