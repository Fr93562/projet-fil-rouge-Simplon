import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { DataService } from './data.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';


/**
 * Service qui gère les interactions de user avec l'api Rest
 */
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private dataService: DataService) { }

  url = this.dataService.baseUrl + '/users';
  list = null;
  user = null;

  /**
   * Récupere la liste des users
   */
  getList(): Observable<any> {

    console.log(this.list);
    return this.http.get<User[]>(this.url);
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
}
