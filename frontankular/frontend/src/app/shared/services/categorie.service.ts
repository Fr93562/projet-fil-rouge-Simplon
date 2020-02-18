import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable, of } from 'rxjs';

import { DataService } from './data.service';
import { RequestService } from './request.service';
import { Categorie } from 'src/app/shared/models/categorie';

@Injectable({ providedIn: 'root'})
export class CategorieService extends RequestService {

  url = this.data.baseUrl + '/questions/cat';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  
  /**
   * Récupere la liste des Categories d'une question
   */
  getCategoryList(): Observable<any> {
    return this.getTrivialCode(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'une Categorie
   * @param newCategory Categorie a ajouter
   */
  createCategory(newCategory: Categorie): Observable<any> {
    return this.postTrivialCode(this.url, newCategory);
  }

  /**
   * Envoi une requete pour la suppression d'une Categorie
   * @param delCategory a supprimer
   */
  deleteCategory(delCategory: Categorie): Observable<any> {
    return this.deleteTrivialCode(this.url, delCategory);
  }

  /**
   * Envoi une requete pour la mise a jour d'une Categorie
   * @param newCategory Categorie a mettre a jour
   */
  updateCategory(newCategory: Categorie): Observable<any> {
    return this.postTrivialCode(this.url, newCategory);
  }

}


