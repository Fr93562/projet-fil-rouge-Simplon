import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { baseUrl } from './baseUrl';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Faq } from '../models/faq';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  httpOptions: any;
  tokenUser = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJDYW1pbGxlLENhbWlsbGUifQ.SjfrzVEz84enwBPJGXxdge0IcYiQg6GljcakY2BKLGI';
  /*httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };*/
  url = baseUrl + '/faq';


  constructor(private http: HttpClient) { }

  /**
   * Récupere la liste des Faqs
   */
  getFaqsList(): Observable<any> {
    return this.http.get<Faq[]>(this.url);
  }

  /**
   * Envoi une requete pour l'ajout d'une FAQ
   * @param newFaq Faq a ajouter
   */
  createFaq(newFaq: Faq): Observable<any> {
    return this.http.post<any>(this.url, newFaq, this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json',
      token: this.tokenUser})
    });
  }

  deleteFaq(delFaq: Faq): Observable<any> {
    return this.http.request('delete', this.url, {body: delFaq, headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                                                token: this.tokenUser })});
  }

  updateFaq(newFaq: Faq): Observable<any> {
    return this.http.put<any>(this.url, newFaq, this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json',
      token: this.tokenUser})
    });
  }

}
