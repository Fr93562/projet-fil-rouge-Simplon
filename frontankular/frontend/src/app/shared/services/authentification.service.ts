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
    response.subscribe(loginResponse => {
      this.dataReponse = loginResponse;
      if (this.dataReponse != null) {

        if(this.dataReponse[0] != "unauthorized") {
  
          sessionStorage.setItem('username', this.dataReponse[0]);
          sessionStorage.setItem('role', this.dataReponse[1]);
          sessionStorage.setItem('token', this.dataReponse[2]);
          sessionStorage.setItem('point', "1");
          console.log("accès autorisé");
        } else {
  
          console.log("accès refusé");
        }
  
      } else {
  
        console.log("La réponse n'a pas encore été reçue");
      }
    });
  }

  /**
   * supprime la session
   */
  public logout()
  {
    sessionStorage.setItem('token', '');
    sessionStorage.setItem('username', '');  
  }

  /**
   * envoi une requete POST à l'api
   */
  private postAuthentification (user: User): Observable<any>
  {
    return this.postTrivialCode(this.url, user);
  }
}
