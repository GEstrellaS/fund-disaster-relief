import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
}

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})

export class ShoppingCartComponent {
  cart: CartItem[] = [];
  errorMessage: string = 'Item not found in cart.';
  user: any;

  constructor(private router: Router, private http: HttpClient) {}

  // Method to add an item to the shopping cart
  addItemToCart(item: CartItem) {
    const existingItem = this.cart.find((cartItem) => cartItem.id === item.id);

    if (existingItem) {
      existingItem.quantity += 1;
    } else {
      this.cart.push({ ...item, quantity: 1 });
    }
  }

  // Method to remove an item from the shopping cart
  removeItemFromCart(item: CartItem) {
    const index = this.cart.findIndex((cartItem) => cartItem.id === item.id);

    if (index !== -1) {
      if (this.cart[index].quantity > 1) {
        this.cart[index].quantity -= 1;
      } else {
        this.cart.splice(index, 1);
      }
    } else {
      alert(this.errorMessage);
    }
  }

  // Method to navigate to the checkout or payment page
  goToCheckout() {
    this.router.navigate(['/checkout']); 
  }
}
