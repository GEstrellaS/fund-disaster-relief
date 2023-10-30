import { Component } from '@angular/core';
import { Need } from '../need';
import { NEEDS } from '../mock-needs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  needs: Need[] = NEEDS;
  selectedNeed?: Need;

  onSelect(need: Need): void {
    this.selectedNeed = need;
}
}
