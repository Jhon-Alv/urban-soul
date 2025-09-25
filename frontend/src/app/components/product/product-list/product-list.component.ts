import { Component, inject } from '@angular/core';
import { ProductCardComponent } from '../product-card/product-card.component';
import { ProductService } from '../../../core/services/product.service';
import { Product } from '../../../core/model/Product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [ProductCardComponent],
  templateUrl: './product-list.component.html',
})
export class ProductListComponent {

  _productService = inject(ProductService);

  productList: Product[] = [];

  products$ = this._productService.getProducts();

  constructor() {
    this.products$.subscribe(products => {
      this.productList = products;
    });
  }



}
