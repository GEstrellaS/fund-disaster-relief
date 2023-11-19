import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { NeedService } from '../need.service'; // Import NeedService


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  needs: Need[] = [];
  selectedNeed?: Need;

  constructor(
    private cartService: CartService,
    private userService: UserService,
    private needService: NeedService // Inject NeedService
  ) { }


  ngOnInit(): void {
    this.getNeeds();
  }

  onSelect(need: Need): void {
    this.selectedNeed = need;
  }

  addToCart(need: Need): void {
    this.userService.username$.subscribe(username => {
      if (username) {
        this.cartService.addItemToCart(username, need).subscribe(() => {
          console.log('Item added to cart successfully.');
        });
      } else {
        console.error('Username is null');
      }
    });
  }

  getNeeds(): void {
    this.needService.getNeeds().subscribe(needs => {
      this.needs = needs.filter(need => need.currentQuantity !== need.requiredQuantity);
    });
  }
}

