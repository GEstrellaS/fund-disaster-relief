import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = "";
  password: string = "";
  repeatPassword: string = "";

  constructor(private router: Router, private http: HttpClient) { }

  signup() {
    this.http.get<any[]>('assets/users.json').subscribe((users: any[]) => {
      const userExists = users.find(
        (u) => u.username === this.username && u.password === this.password
      );

      if (userExists) {
        alert('Username already exists. Please choose a different username.');
      } else {
        if (this.password === this.repeatPassword) {
          const newUser = { username: this.username, password: this.password };
          users.push(newUser);
          alert('Signup successful!');
          this.router.navigate(['/login']);
        } else {
          alert('Passwords do not match. Please try again.');
        }
      }
    });
  }
}
