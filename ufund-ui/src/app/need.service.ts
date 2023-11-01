import { Injectable } from '@angular/core';
import { Need } from './need';
import { NEEDS } from './mock-needs';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})
export class NeedService {
  constructor(private messageService: MessageService) { }

  getNeeds(): Observable<Need[]> {
    const needs = of(NEEDS);
    this.messageService.add('NeedService: fetched needs');
    return needs;
  }
  getNeed(name: string): Observable<Need> {
    // For now, assume that a need with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.
    const need = NEEDS.find(h => h.name === name)!;
    this.messageService.add(`NeedService: fetched need name=${name}`);
    return of(need);
  }
}
