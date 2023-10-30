import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

interface User {
  username: string;
  password: string;
  role: string;
  email: string;
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
    this.http.get('assets/users.json').subscribe((users) => {
      const user = (users as User[]).find(
        (u) => u.username === this.username && u.password === this.password
      );
      if (user) {
        if (user.role === "admin") {
          // Authentication successful for admin, navigate to the admin page
          this.router.navigate(['/admin']);
        } else if (user.role === "user") {
          // Authentication successful for user, navigate to the home page
          this.router.navigate(['/home']);
        } else {
          alert(this.errorMessage);
        }
      } else {
        alert(this.errorMessage);
      }
    });
  }
}
