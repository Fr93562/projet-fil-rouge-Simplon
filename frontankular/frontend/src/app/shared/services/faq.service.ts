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
  createFaq(newFaq: Faq): Observable<Faq> {
    console.log(newFaq);
    console.log(this.url);
    return this.http.post<Faq>(this.url, newFaq);
  }

  deleteFaq(delFaq: Faq): Observable<any> {
    return this.http.delete(this.url + '/supprimer', this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      body: { delFaq }});
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
