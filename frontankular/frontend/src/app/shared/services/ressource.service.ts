import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DataService } from './data.service';
import { RequestService } from './request.service';
import { Ressource } from 'src/app/shared/models/ressource';

/**
 * gere les requêtes ressource avec l'Api
 * 
 * @author : elodie
 */
@Injectable({ providedIn: 'root' })
export class RessourceService extends RequestService {
  
  url = this.data.baseUrl + '/ressources';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  
  /**
   * Récupere la liste des ressources
   */
  getLinkList(): Observable<any> {
    return this.getTrivialCode(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'une ressource
   * @param newLink Ressource a ajouter
   */
  createLink(newLink: Ressource): Observable<any> {
    return this.postTrivialCode(this.url, newLink);
  }

  /**
   * Envoi une requete pour la suppression d'une ressource
   * @param delLink Ressource a supprimer
   */
  deleteLink(delLink: Ressource): Observable<any> {
    return this.deleteTrivialCode(this.url, delLink);
  }

  /**
   * Envoi une requete pour la mise a jour d'une ressource
   * @param newLink Ressource a mettre a jour
   */
  updateLink(newLink: Ressource): Observable<any> {
    return this.postTrivialCode(this.url, newLink);
  }

}
