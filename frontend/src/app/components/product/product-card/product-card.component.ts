import { Component, computed, input } from '@angular/core';
import { Product } from '../../../core/model/Product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [],
  templateUrl: './product-card.component.html',
})
export class ProductCardComponent {

  constructor(private router: Router) { }

  product = input.required<Product>();

  productNameUrl = computed(() =>
    this.product().name.replace(/\s+/g, '-').toLowerCase()
  );

  navigateToProductDetail(): void {
    this.router.navigate(['/products', this.productNameUrl()]);
  }

}
