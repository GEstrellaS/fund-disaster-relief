import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { NeedService } from '../need.service'; // Import NeedService
import { Announcement } from '../announcement';
import { AnnouncementsService } from '../announcements.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  needs: Need[] = [];
  announcements: Announcement[] = [];
  selectedNeed?: Need;

  constructor(
    private cartService: CartService,
    private userService: UserService,
    private announcementService: AnnouncementsService,
    private needService: NeedService // Inject NeedService
  ) { }


  ngOnInit(): void {
    this.getNeeds();
    this.getAnnouncements();
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

  getAnnouncements(): void {
    this.announcementService.getAnnouncements().subscribe(announcements => {
      this.announcements = announcements.filter(announcement => announcement.id);
    });
  }
}

