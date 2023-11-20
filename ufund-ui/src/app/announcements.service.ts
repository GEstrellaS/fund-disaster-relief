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
  private announcementUrl = 'http://localhost:8080/announcements'
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
      return this.http.get<Announcement[]>(this.announcementUrl)
      .pipe(
        tap(_ => this.log('fetched announcements')),
        catchError(this.handleError<Announcement[]>('getAnnouncements', []))
      );
    }

    addAnnouncement(announcement: string): Observable<Announcement> {
      return this.http
        .post<Announcement>(this.announcementUrl, announcement, this.httpOptions)
        .pipe(
          tap((newAnnouncement: Announcement) => {
            console.log(`Added announcement: ${newAnnouncement}`);
          }),
          catchError(this.handleError<Announcement>('addAnnouncement'))
        );
    }

    deleteAnnouncement(id: number): Observable<any> {
      const url = `${this.announcementUrl}/${id}`;
    
      return this.http.delete(url, this.httpOptions)
        .pipe(
          tap(_ => console.log(`deleted announcement with id=${id}`)),
          catchError(this.handleError<any>('deleteAnnouncement'))
        );
    }
}
