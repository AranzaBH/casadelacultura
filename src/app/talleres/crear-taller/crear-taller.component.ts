import { Component, OnInit } from '@angular/core';
import { TallerService } from '../../services/talleres.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import  Swal  from 'sweetalert2';

@Component({
  selector: 'app-crear-taller',
  templateUrl: './crear-taller.component.html',
  styleUrls: ['./crear-taller.component.css']
})
export class CrearTallerComponent implements OnInit {
  public taller = {
    tituloTaller: '',
    descripcion: '',
    fechaInico: '',
    fechaFinal: '',
    instructor: '',
    duracion: ''
  };


  constructor(private tallerService: TallerService, private snack: MatSnackBar) {}

  ngOnInit(): void {}

  formSubmit() {
    
    this.tallerService.añadirTaller(this.taller).subscribe(
      (data) => {
        Swal.fire('Taller guardado', 'Taller registrado con éxito en el sistema', 'success');
      },
      (error) => {
        Swal.fire('Ha ocurrido un error en el sistema', '', 'error');
      }
    );
  }
}