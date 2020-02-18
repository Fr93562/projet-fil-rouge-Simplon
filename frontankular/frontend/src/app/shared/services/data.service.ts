import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

/**
 * stocke les données utilisées par les autres services
 * 
 * @author : camille
 */
@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseUrl = 'http://localhost:8080';
  tokenUser = '';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      token: sessionStorage.getItem('token')
    })
  };

  constructor() { }
}
