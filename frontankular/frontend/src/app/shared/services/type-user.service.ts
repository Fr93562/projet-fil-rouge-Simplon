import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DataService } from './data.service';
import { RequestService } from './request.service';
import { typeUser } from '../models/typeUser';



@Injectable({
  providedIn: 'root'
})
export class TypeUserService extends RequestService{
  url = this.data.baseUrl + '/users/type';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  
  /**
   * Récupere la liste des types d'utilisateurs
   */
  getTypeUserList(): Observable<any> {
    return this.getTrivialCode(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'un type d'utilisateur
   * @param newType type a ajouter
   */
  createTypeUser(newType: typeUser): Observable<any> {
    return this.postTrivialCode(this.url, newType);
  }


  /**
   * Envoi une requete pour la mise a jour d'un type d'utilisateur
   * @param newType type a mettre a jour
   */
  updateTypeUser(newType: typeUser): Observable<any> {
    return this.postTrivialCode(this.url, newType);
  }

  /**
   * Envoi une requete pour la suppression d'un type d'utilisateur
   * @param delType type à supprimer
   */
  deleteTypeUser(delType: typeUser): Observable<any> {
    return this.deleteTrivialCode(this.url, delType);
  }
}



