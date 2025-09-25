export interface Product {
    name: string;
    description: string;
    size: string;
    color: string;
    price: number;
    stock: number;
    status: 'active' | 'inactive';
    urlImage: string;
}