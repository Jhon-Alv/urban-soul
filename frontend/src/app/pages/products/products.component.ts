import { Component } from '@angular/core';
import { ProductListComponent } from "../../components/product/product-list/product-list.component";

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ProductListComponent, ProductListComponent],
  templateUrl: './products.component.html',
})
export class ProductsComponent {

}
