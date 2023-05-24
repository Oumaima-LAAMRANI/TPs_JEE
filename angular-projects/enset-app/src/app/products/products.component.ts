import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit{
  products : Array<any> =[];
  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get<Array<any>>("http://localhost:8089/products")
      .subscribe({
        next: data =>{
          this.products=data
        },
        error: err=>{
          console.log(err);
        }
      });
  }



  handleCheckProduct(product:any) {
    product.checked=!product.checked;
  }
}
