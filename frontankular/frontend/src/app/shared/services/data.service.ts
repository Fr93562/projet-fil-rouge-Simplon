import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  baseUrl = 'http://localhost:8080';
  tokenUser = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJFbG9kaWUsRWxvZGllIn0.mDNH910OR4--19eiD6oMOUCbxF_nzQuQcJL_prwrtcY';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      token: this.tokenUser
    })
  };

  constructor() { }
}
