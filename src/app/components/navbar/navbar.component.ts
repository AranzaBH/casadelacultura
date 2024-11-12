import { Component, OnInit } from '@angular/core';
import { LoginService } from './../../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn = false;
  user: any = null;
  userRole: string = '';  // Para almacenar el rol del usuario

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
    this.user = this.loginService.getUser();
    if (this.isLoggedIn) {
      this.userRole = this.loginService.getUserRole();  // Obtener el rol del usuario
    }
    
    this.loginService.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.loginService.isLoggedIn();
        this.user = this.loginService.getUser();
        if (this.isLoggedIn) {
          this.userRole = this.loginService.getUserRole();  // Obtener el rol cuando se actualice el estado de login
        }
      }
    );
  }

  public logout() {
    this.loginService.logout();
    window.location.reload();
  }

}
