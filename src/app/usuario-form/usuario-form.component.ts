import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.css'
})
export default class UsuarioFormComponent {
  private fb = inject(FormBuilder);
  form = this.fb.group({
    nombre:["", Validators.required],
    apellidoPaterno:["", Validators.required],
    apellidoMaterno:["", Validators.required], 
    correoElectronico:["", Validators.required],
    password:[]

  });

  create(){
    console.log(this.form.value);
  }

  


}
