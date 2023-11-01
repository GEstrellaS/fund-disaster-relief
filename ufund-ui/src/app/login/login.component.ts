import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs'; // Import throwError

interface User {
  username: string;
  password: string;
  cart: string;
  isManager: boolean;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  username: string = "";
  password: string = "";
  errorMessage: string = 'Invalid username or password.';

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    this.http.get<User>('http://localhost:8080/users/login', {
      params: {
        username: this.username,
        password: this.password
      }
    }).subscribe((user: User) => {
      if (user) {
        if (user.isManager) {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/home']);
        } 
      } else {
        alert(this.errorMessage);
      }
    },
    (error) => {
      alert(this.errorMessage);
    });
  }
}
