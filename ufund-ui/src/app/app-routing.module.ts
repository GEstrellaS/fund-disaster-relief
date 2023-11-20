import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { ShoppingCartComponent } from './shoppingcart/shoppingcart.component';
import { AdminComponent } from './admin/admin.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { AboutComponent } from './about/about.component';
import { AnnouncementComponent } from './announcement/announcement.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'about', component: AboutComponent },
  { path: 'cart', component: ShoppingCartComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'admin/announcement', component: AnnouncementComponent},
  { path: 'detail/:name', component: NeedDetailComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
