import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
//import { NEEDS } from '../mock-needs';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {
  selectedNeed?: Need;
  needs: Need[] = [];
  //needs:Need[] =NEEDS
  hideDetails = false;

  constructor(private needService: NeedService, 
    private messageService: MessageService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  onSelect(need: Need): void {
    this.selectedNeed = need;
    this.messageService.add(`HeroesComponent: 
    Selected need name=${need.name}`);
    this.hideDetails = false;
  }

  toggleHideDetails() {
    this.hideDetails = !this.hideDetails;
  }
  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }
}
