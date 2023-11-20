import { Injectable } from '@angular/core';
import { Need } from './need';
import { NEEDS } from './mock-needs';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class NeedService {
  private needsUrl = 'http://localhost:8080/needs'//'api/needs';  // URL to web api
  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getNeeds(): Observable<Need[]> {
 
    return this.http.get<Need[]>(this.needsUrl)
    .pipe(
      tap(_ => this.log('fetched needs')),
      catchError(this.handleError<Need[]>('getNeeds', []))
    );
  }

  getNeed(name: string): Observable<Need> {
    // For now, assume that a need with the specified `id` always exists.
    // Error handling will be added in the next step of the tutorial.
    const url = `${this.needsUrl}/${name}`;
     return this.http.get<Need>(url).pipe(
    tap(_ => this.log(`fetched need name=${name}`)),
    catchError(this.handleError<Need>(`getNeed name=${name}`))
  );

  }
  
  updateNeed(need: Need): Observable<any> {
    return this.http.put(this.needsUrl, need, this.httpOptions).pipe(
      tap(_ => this.log(`updated need name=${need.name}`)),
      catchError(this.handleError<any>('updateneed'))
    );
  }

addNeed(need: Need): Observable<Need> {
  return this.http
    .post<Need>(this.needsUrl, need, this.httpOptions)
    .pipe(
      tap((newNeed: Need) => console.log(`added need: ${newNeed.name}`)),
      catchError(this.handleError<Need>('addNeed'))
    );
}

deleteNeed(need: Need): Observable<any> {
  const url = `${this.needsUrl}/${need.name}`;

  return this.http.delete(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted need name=${need.name}`)),
      catchError(this.handleError<any>('deleteNeed'))
  );
}

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  
  /** Log a NeedService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`NeedService: ${message}`);
}
searchNeeds(term: string): Observable<Need[]> {
  const searchTerm = term.toLowerCase().trim();
  if (!searchTerm) {
    return of([]);
  }

  return this.http.get<Need[]>(this.needsUrl).pipe(
    map(needs => needs.filter(need => need.name.toLowerCase().includes(searchTerm))),
    tap(filteredNeeds => {
      const message = filteredNeeds.length > 0 ?
        `found needs matching "${term}"` :
        `no needs matching "${term}"`;
      this.log(message);
    }),
    catchError(this.handleError<Need[]>('searchNeeds', []))
  );
}
}
