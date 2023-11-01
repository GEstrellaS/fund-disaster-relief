import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Need } from './need';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const needs = [
      { name: "Flashlight", price: 4.99, quantity: 2, type: "Emergency Supplies" },
      { name: "Toilet Paper",price: 4.99, quantity: 24,type: "Household" },
      { name:"Trashcans",price: 6.78,quantity: 22,type: "Household"},
      { name: "First Aid Kit", price: 19.99, quantity: 1, type: "Emergency Supplies" },
      { name: "Blankets", price: 9.99, quantity: 5, type: "Emergency Supplies" },
      { name: "Winter Jacket", price: 49.99, quantity: 1, type: "Apparel" },
      { name: "Dry Fruits", price: 0.99, quantity: 10, type: "Groceries" },
    ];
    return {needs};
  }

  // Overrides the genId method to ensure that a need always has an id.
  // If the need array is empty,
  // the method below returns the initial number (11).
  // if the need array is not empty, the method below returns the highest
  // need id + 1.
  // genId(needs: Need[]): number {
  //   return needs.length > 0 ? Math.max(...needs.map(need => needs.n)) + 1 : 11;
  // }
}