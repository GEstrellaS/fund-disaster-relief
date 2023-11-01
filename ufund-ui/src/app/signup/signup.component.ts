import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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
    if (this.password !== this.repeatPassword) {
      alert('Passwords do not match. Please try again.');
      return;
    }

    const newUser = { username: this.username, password: this.password };

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    this.http.post('http://localhost:8080/users', `username=${newUser.username}&password=${newUser.password}`, httpOptions).subscribe(
      (response: any) => {
        alert('Signup successful!');
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error:', error);
        if (error.status === 409) {
          alert('Username already exists. Please choose a different username.');
        }else if(error.status === 201){
          alert('Signup successful!');
        this.router.navigate(['/login']);
        }else {
          alert('An error occurred. Please try again later.');
        }
      }
    );
  }
}
