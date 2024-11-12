import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baserUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class TallerService {

  constructor(private httpClient: HttpClient) { }

  // Ejemplo de método para obtener todos los talleres
  public obtenerTalleres(){
    return this.httpClient.get(`${baserUrl}/taller`);
  }

  // Ejemplo de método para añadir un taller
  public añadirTaller(taller: any) {
    return this.httpClient.post(`${baserUrl}/taller`, taller);
  }
  
    
  // Otros métodos pueden añadirse de acuerdo a tus necesidades...
}