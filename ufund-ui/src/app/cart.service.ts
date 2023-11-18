import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map, tap } from 'rxjs';
import { DonationCart } from './cart'; 
import { Need } from './need';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartUrl = 'http://localhost:8080/cart'; 
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getDonationCart(username: string): Observable<DonationCart> {
    console.log('getDonationCart called with username:', username);
  
    const url = `${this.cartUrl}/${username}`;
    return this.http.get<DonationCart>(url).pipe(
      tap(cart => console.log('Server response:', cart))
    );
  }
  
  addItemToCart(username: string, need: Need): Observable<DonationCart> {
    const url = `${this.cartUrl}/${username}/add/need`;
    return this.http.post<DonationCart>(url, need, this.httpOptions);
  }

  deleteItemFromCart(username: string, need: Need): Observable<DonationCart> {
    const url = `${this.cartUrl}/${username}/delete/need`;
    return this.http.post<DonationCart>(url, need, this.httpOptions);
  }

  checkout(username: string): Observable<DonationCart> {
    const url = `${this.cartUrl}/${username}/checkout`;
    return this.http.post<DonationCart>(url, null, this.httpOptions);
  }

  incrementItemInCart(username: string, need: Need): Observable<DonationCart> {
    const url = `${this.cartUrl}/${username}/increment/need`; 
    return this.http.put<DonationCart>(url, need, this.httpOptions);
  }

  decrementItemInCart(username: string, need: Need): Observable<DonationCart> {
    const url = `${this.cartUrl}/${username}/decrement/need`; 
    return this.http.put<DonationCart>(url, need, this.httpOptions);
  }

  
}

