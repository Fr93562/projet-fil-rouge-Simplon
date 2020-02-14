import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { tap, catchError } from 'rxjs/operators';
import { RequestService } from './request.service';
import { DataService } from './data.service';

/**
 * Gère l'authentification, la connexion et la déconnexion
 */
@Injectable({
  providedIn: 'root'
})
export class AuthentificationService extends RequestService 
{
  dataReponse: string[];


  url = this.dataService.baseUrl + '/login';

  constructor(public http: HttpClient, public data: DataService) 
  { 
    super(http,data);
  }

  /**
   * demande et stocke le token/username
   */
  public login (user: User)
  {
    let response: any = this.postAuthentification(user);
    console.log(response.subscribe(dataReponse2 => this.dataReponse = dataReponse2));
    console.log(this.dataReponse);
  }

  /**
   * supprime le token/username
   */
  public logout()
  {

  }

  /**
   * envoi une requete POST à l'api
   */
  private postAuthentification (user: User): Observable<any>
  {
    return this.postTrivialCode(this.url, user)
  }


}
