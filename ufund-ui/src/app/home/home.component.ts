import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  needs: Need[] = [];
  selectedNeed?: Need;

  constructor(private needService: NeedService) {}

  ngOnInit(): void {
    this.getNeeds();
  }

  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }

  onSelect(need: Need): void {
    this.selectedNeed = need;
  }
}
