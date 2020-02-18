import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable, of } from 'rxjs';
import { DataService } from './data.service';

import { RequestService } from './request.service';
import { Language } from 'src/app/shared/models/language';

/**
 * gere les requêtes langage avec l'Api
 * 
 * @author : elodie
 */
@Injectable({ providedIn: 'root' })
export class LanguageService extends RequestService {

  url = this.data.baseUrl + '/langages';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  
  /**
   * Récupere la liste des Langages
   */
  getLanguagesList(): Observable<any> {
    return this.getTrivialCode(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'un langage
   * @param newLanguage Langage a ajouter
   */
  createLangage(newLanguage: Language): Observable<any> {
    return this.postTrivialCode(this.url, newLanguage);
  }

  /**
   * Envoi une requete pour la suppression d'un langage
   * @param delLangage a supprimer
   */
  deleteLanguage(delLanguage: Language): Observable<any> {
    return this.deleteTrivialCode(this.url, delLanguage);
  }

  /**
   * Envoi une requete pour la mise a jour d'un langage
   * @param newLanguage Langage a mettre a jour
   */
  updateLanguage(newLanguage: Language): Observable<any> {
    return this.postTrivialCode(this.url, newLanguage);
  }

}

