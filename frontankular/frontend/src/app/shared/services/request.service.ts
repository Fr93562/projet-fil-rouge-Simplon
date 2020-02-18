import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(public http: HttpClient, public dataService: DataService) { }

  /**
   * Effectue une requete Get
   * @param apiUrl Endpoint à joindre
   */
  public getTrivialCode(apiUrl: string): Observable<any> {
    return this.http.get<any>(apiUrl);
  }

  /**
   * Effectue une requete Post
   * @param apiUrl Endpoint à joindre
   * @param addObject Objet a envoyer
   */
  public postTrivialCode(apiUrl: string, addObject: any): Observable<any> {
    return this.http.post<any>(apiUrl, addObject, this.dataService.httpOptions);
  }

  /**
   * Effectue une requete Delete
   * @param apiUrl Endpoint à joindre
   * @param delObject Objet a supprimer
   */
  public deleteTrivialCode(apiUrl: string, delObject: any): Observable<any> {
    return this.http.request('delete', apiUrl, {body: delObject, headers: new HttpHeaders({ 'Content-Type': 'application/json',
                                                                token: sessionStorage.getItem('token') })});
  }

  /**
   * Effectue une requete update
   * @param apiUrl Endpoint à joindre
   * @param addObject Objet a mettre a jour
   */
  public updateTrivialCode(apiUrl: string, addObject: any): Observable<any> {
    return this.http.put<any>(apiUrl, addObject, this.dataService.httpOptions);
  }
}
