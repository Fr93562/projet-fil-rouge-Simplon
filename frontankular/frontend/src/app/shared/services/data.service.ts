import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseUrl = 'http://localhost:8080';
  tokenUser = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJDYW1pbGxlLENhbWlsbGUifQ.SjfrzVEz84enwBPJGXxdge0IcYiQg6GljcakY2BKLGI';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      token: this.tokenUser
    })
  };

  constructor() { }
}
