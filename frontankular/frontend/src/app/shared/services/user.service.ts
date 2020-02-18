import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { DataService } from './data.service';
import { RequestService } from './request.service';
import { User } from '../models/user';


/**
 * Service qui gère les interactions de user avec l'api Rest
 */
@Injectable({providedIn: 'root'})
export class UserService extends RequestService {

  url = this.data.baseUrl + '/users';
  list = null;
  user = null;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, private data: DataService) {
    super(http, data);
   }

  /**
   * Récupere la liste des users
   */
  getList(): Observable<any> {

    console.log(this.list);
    return this.http.get<User[]>(this.url);
  }

  /**
   * Récupere un user
   */
  getByUsername(username: string): Observable<any> {

    console.log(this.list);
    return this.http.get<User>(this.url + "/?username=" + username);
  }

  /**
   * Tri la liste des users
   */
  sortList(limit: number): User[] {

    this.list = this.http.get<User[]>(this.url);
    let orderedList: User[];

    for (let i = 0; i < limit; i++) {

      orderedList.push(this.list[i]);
    }
    return orderedList;
  }

  /**
   * Méthode destinée aux tests, affiche la list dans la console
   */
  viewList() {

    this.list = this.http.get<User[]>(this.url);
    console.log(this.list);
  }

 /**
   * Envoi une requete pour l'ajout d'un utilisateur
   * @param newUser Utilisateur a ajouter
   */
  createUser(newUser: User): Observable<any> {
    return this.postTrivialCode(this.url, newUser);
  }

 /**
   * Envoi une requete pour la mise a jour d'un utilisateur
   * @param newUser Utilisateur a mettre a jour
   */
  updateUser(newUser: User): Observable<any> {
    return this.postTrivialCode(this.url, newUser);
  }

  /**
   * Envoi une requete pour la suppression d'un utilisateur
   * @param delUser Utilisateur a supprimer
   */
  deleteUser(delUser: User): Observable<any> {
    return this.deleteTrivialCode(this.url, delUser);
  }

 

}
