import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, startWith, switchMap
 } from 'rxjs/operators';


import { Need } from '../need';
import { NeedService } from '../need.service';


@Component({
  selector: 'app-need-search',
  templateUrl: './need-search.component.html',
  styleUrls: [ './need-search.component.css' ]
})
export class NeedSearchComponent implements OnInit {
  needs$!: Observable<Need[]>;
  private searchTerms = new Subject<string>();


  constructor(private needService: NeedService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.needs$ = this.needService.getNeeds();
    
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
}  
