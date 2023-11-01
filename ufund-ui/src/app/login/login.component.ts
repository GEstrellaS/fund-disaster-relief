import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

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
    // Send a GET request to the Spring Boot login endpoint
    this.http.get<User>('http://localhost:8080/users/login', {
      params: {
        username: this.username,
        password: this.password
      }
    }).subscribe((user: User) => {
      if (user) {
        if (user.isManager) {
          // Authentication successful for admin, navigate to the admin page
          this.router.navigate(['/admin']);
        } else {
          // Authentication successful for user, navigate to the home page
          this.router.navigate(['/home']);
        } 
      } else {
        alert(this.errorMessage);
      }
    });
  }
}
