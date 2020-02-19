import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Faq } from '../models/faq';
import { RequestService } from './request.service';
import { DataService } from './data.service';

/**
 * gere les requêtes faq avec l'Api
 * 
 * @author : camille
 */
@Injectable({
  providedIn: 'root'
})
export class FaqService extends RequestService {

  url = this.data.baseUrl + '/faq';

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  /**
   * Récupere la liste des Faqs
   */
  getFaqsList(): Observable<any> {
    return this.getTrivialCode(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'une FAQ
   * @param newFaq Faq a ajouter
   */
  createFaq(newFaq: Faq): Observable<any> {
    console.log(this.postTrivialCode(this.url, newFaq));
    return this.postTrivialCode(this.url, newFaq);
  }

  /**
   * Envoi une requete pour la suppression d'une FAQ
   * @param delFaq Faq a supprimer
   */
  deleteFaq(delFaq: Faq): Observable<any> {
    return this.deleteTrivialCode(this.url, delFaq);
  }

  /**
   * Envoi une requete pour la mise a d'une FAQ
   * @param newFaq Faq a mettre a jour
   */
  updateFaq(newFaq: Faq): Observable<any> {
    return this.postTrivialCode(this.url, newFaq);
  }

}
