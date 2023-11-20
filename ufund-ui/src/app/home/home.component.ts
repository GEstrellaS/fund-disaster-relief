import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { NeedService } from '../need.service'; // Import NeedService
import { Announcement } from '../announcement';
import { AnnouncementsService } from '../announcements.service';
import { Observable, Subject, debounceTime, distinctUntilChanged, startWith, switchMap } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  needs: Need[] = [];
  announcements: Announcement[] = [];
  selectedNeed?: Need;
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private cartService: CartService,
    private userService: UserService,
    private announcementService: AnnouncementsService,
    private needService: NeedService // Inject NeedService
  ) { }


  ngOnInit(): void {
    this.getNeeds();
    this.getAnnouncements();

    //this.needs$ = this.needService.getNeeds();
    
    this.needs$ = this.searchTerms.pipe(
      startWith(''),
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => {
        if (term.trim() === '') {

          return this.needService.getNeeds();
        } else {

          return this.needService.searchNeeds(term);
        }
      }),
    );
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
  search(term: string): void {
    this.searchTerms.next(term);
  }
}

