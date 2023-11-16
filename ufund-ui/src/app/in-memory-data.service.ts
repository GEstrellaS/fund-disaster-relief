import { Injectable } from '@angular/core';
//import { InMemoryDbService } from 'angular-in-memory-web-api';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService {
  createDb() {
    const needs = [
      { name: "Flashlight", cost: 4.99, quantity: 2, type: "Emergency Supplies" },
      { name: "Toilet Paper",cost: 4.99, quantity: 24,type: "Household" },
      { name:"Trashcans",cost: 6.78,quantity: 22,type: "Household"},
      { name: "First Aid Kit", cost: 19.99, quantity: 1, type: "Emergency Supplies" },
      { name: "Blankets", cost: 9.99, quantity: 5, type: "Emergency Supplies" },
      { name: "Winter Jacket", cost: 49.99, quantity: 1, type: "Apparel" },
      { name: "Dry Fruits", cost: 0.99, quantity: 10, type: "Groceries" },
    ];
    return {needs};
  }
}