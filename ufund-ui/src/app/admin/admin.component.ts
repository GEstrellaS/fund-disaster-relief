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

  constructor(private needService: NeedService, 
    private messageService: MessageService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  onSelect(need: Need): void {
    this.selectedNeed = need;
    this.messageService.add(`AdminComponent: 
    Selected need name=${need.name}`);
  }

  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }

  onSubmit(): void {
    if (this.isNewNeedValid()) {
      const newNeed: Need = { ...this.newNeed };
      this.needService.addNeed(newNeed).subscribe((addedNeed) => {
      console.log('Need added:', addedNeed);
      this.newNeed = { name: '', cost: 0, currentQuantity: 0, requiredQuantity: 0, type: '' };

      this.getNeeds();
      });
    }else {
    console.log('Invalid new need. Please make sure all values are non-negative.');
   }
  }
  
  isNewNeedValid(): boolean {
    return (
      this.newNeed.cost >= 0 &&
      this.newNeed.currentQuantity >= 0 &&
      this.newNeed.requiredQuantity >= 0 && this.newNeed.requiredQuantity > this.newNeed.currentQuantity
    );
  }

}