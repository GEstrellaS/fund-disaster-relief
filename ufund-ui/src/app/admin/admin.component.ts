import { Component } from '@angular/core';
import { Need } from '../need';
import { NEEDS } from '../mock-needs';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent {
  needs: Need[] = NEEDS;
  selectedNeed?: Need;
  hideDetails = false;

  toggleHideDetails() {
    this.hideDetails = !this.hideDetails;
  }

  onSelect(need: Need): void {
  this.selectedNeed = need;
  this.hideDetails = false;
}
}
