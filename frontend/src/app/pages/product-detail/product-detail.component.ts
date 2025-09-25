import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../core/model/Product';


@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [],
  templateUrl: './product-detail.component.html',
})
export class ProductDetailComponent {
  private route = inject(ActivatedRoute);
  private http = inject(HttpClient);

  product: Product | null = null;

  constructor() {
    const slug = this.route.snapshot.paramMap.get('productName')!;
    const originalName = this.slugToName(slug);

    this.http
      .get<Product>(`http://localhost:8080/api/products/`, {
        params: { name: originalName },
      })
      .subscribe(product => {
        this.product = product;
      });
  }

  private slugToName(slug: string): string {
    return slug
      .split('-')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }

}
