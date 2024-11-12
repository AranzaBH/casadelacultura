import { Component, inject, OnInit } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [DatePipe,RouterModule],
  templateUrl: './usuario-list.component.html',
  styleUrl: './usuario-list.component.css'
})
export default class UsuarioListComponent implements OnInit{
  private usuarioService =inject(UsuarioService)

  usuario: any =[];

  ngOnInit(): void {
      this.usuarioService.list().subscribe((usuario: any) => {this.usuario = usuario;});
  }

}
