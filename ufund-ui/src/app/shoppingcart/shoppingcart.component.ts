
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DonationCart } from '../cart';
import { Need } from '../need';
import { CartService } from '../cart.service';
import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { UserService } from '../user.service'; 


@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})

export class ShoppingCartComponent {
  errorMessage: string = 'Item not found in cart.';
  user: any;
  cartItems: Need[] = [];

  constructor(
    private router: Router,
    private cartService: CartService,
    private userService: UserService
  ) {
  }

  ngOnInit(){
    this.userService.username$.subscribe(username => {
      this.user = { username }; 
      this.getCart();
    });
  }

  removeItemFromCart(need: Need) {
    const username = this.user.username;
  
    this.cartService.deleteItemFromCart(username, need).subscribe({
      next: (cart) => {
        console.log('Item removed from cart:', cart);
        this.cartItems = cart.itemsInDonationCart;
      },
      error: (error) => {
        console.error('Removing item from cart failed:', error);
      }
    });
  }

  checkout() {
    const username = this.user.username;
  
    this.cartService.checkout(username).subscribe({
      next: (cart) => {
        console.log('Checkout successful:', cart);
        this.cartItems = cart.itemsInDonationCart;
      },
      error: (error) => {
        console.error('Checkout failed:', error);
      }
    });
  }

  goToHome(): void {
    this.router.navigate(['/home']);
  }

  getCart(){
    const username = this.user.username;
    
    this.cartService.getDonationCart(username).subscribe({
      next: (cart) => {
        console.log('Sucessfully retrieved cart.');
        this.cartItems = cart.itemsInDonationCart;
      },
      error: (error) => {
        console.error('Error retrieving cart', error);
      }
    });
  }
}

@NgModule({
  declarations: [
    ShoppingCartComponent,
  ],
  imports: [
    CommonModule, 
  ],
})
export class ShoppingCartModule { } 