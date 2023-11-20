import { Announcement } from './announcement';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementsService {
  private needsUrl = 'http://localhost:8080/announcements'
  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

    private log(message: string) {
      this.messageService.add(`NeedService: ${message}`);
    }

    private handleError<T>(operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        console.error(error);
        this.log(`${operation} failed: ${error.message}`);
        return of(result as T);
      };
    }

    getAnnouncements(): Observable<Announcement[]> {
      return this.http.get<Announcement[]>(this.needsUrl)
      .pipe(
        tap(_ => this.log('fetched announcements')),
        catchError(this.handleError<Announcement[]>('getAnnouncements', []))
      );
    }

    addAnnouncement(announcement: Announcement): Observable<Announcement> {
      return this.http
        .post<Announcement>(this.needsUrl, announcement, this.httpOptions)
        .pipe(
          tap((newAnnouncement: Announcement) => console.log(`added announcement: ${newAnnouncement.detail}`)),
          catchError(this.handleError<Announcement>('addAnnouncement'))
        );
    }
}
