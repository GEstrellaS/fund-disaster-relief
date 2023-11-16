import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { Need } from '../need';
import { NeedService } from '../need.service';



@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {
  selectedNeed?: Need;
  needs: Need[] = [];

  newNeed: Need = { name: '', cost: 0, currentQuantity: 0, requiredQuantity: 0, type: '' };


  //needs:Need[] =NEEDS
  //hideDetails = false;

  constructor(private needService: NeedService, 
    private messageService: MessageService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  onSelect(need: Need): void {
    this.selectedNeed = need;
    this.messageService.add(`AdminComponent: 
    Selected need name=${need.name}`);
    //this.hideDetails = false;
  }

  // toggleHideDetails() {
  //   this.hideDetails = !this.hideDetails;
  // }
  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }
    onSubmit(): void {
      // const name = form.value.name;
      // const cost = form.value.cost;
      // const quantity = form.value.quantity;
      // const type = form.value.type;
  
      // Create a Need object with the form values
      const newNeed: Need = { ...this.newNeed };
  
      // Call the service method to add the need
      this.needService.addNeed(newNeed).subscribe((addedNeed) => {
        // Handle the response from the backend, if needed
        console.log('Need added:', addedNeed);

        this.newNeed = { name: '', cost: 0, currentQuantity: 0, requiredQuantity: 0, type: '' };

        this.getNeeds();
      });
    }
  
    // You can implement validation and error handling here.
  

  // add(name: string, cost: number, quantity: number, type: string): void {
  //   name = name.trim();
  //   if (!name) { return; }

  //   const newNeed: Need = { name, cost, quantity, type } as Need;
  
  //   this.needService.addNeed(newNeed)
  //     .subscribe(need => {
  //       this.needs.push(need);
  //     });
  // }
}