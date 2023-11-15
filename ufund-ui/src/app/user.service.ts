
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private usernameSubject = new BehaviorSubject<string | null>(localStorage.getItem('username'));
  username$ = this.usernameSubject.asObservable();
  

  setUsername(username: string): void {
    localStorage.setItem('username', username); 
    this.usernameSubject.next(username);
  }

}

