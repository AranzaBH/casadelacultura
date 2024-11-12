// src/app/pages/profile/profile.component.ts
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';  // Asegúrate de que LoginService esté importado

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: any = null;  // Para almacenar los datos del usuario

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.user = this.loginService.getUser(); // Obtener los datos del usuario desde el servicio
  }

  // Función para guardar los cambios (ejemplo, podrías hacer una petición a tu backend)
  saveProfile(): void {
    // Lógica para guardar cambios, como llamar a un servicio de actualización
    console.log('Perfil guardado', this.user);
    // Aquí podrías hacer un llamado a un servicio para guardar los cambios
  }
}
