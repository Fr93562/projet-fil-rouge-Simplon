import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DataService } from './data.service';
import { RequestService } from './request.service';
import { Question } from 'src/app/shared/models/question';


@Injectable({ providedIn: 'root' })
export class QuestionService extends RequestService {

  url1 = this.data.baseUrl + '/questions';
  url2 = this.data.baseUrl + '/questions?langage=';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }


  /**
   * Récupere la liste des Questions
   */
  getQuestionsList(): Observable<any> {
    return this.getTrivialCode(this.url1);
  }

  /**
   * Envoi une requete pour l'ajout d'une question
   * @param newQuestion Question a ajouter
   */
  createQuestion(newQuestion: Question, langage: string): Observable<any> {
    console.log(newQuestion);
    return this.postTrivialCode(this.url2 + langage, newQuestion);
  }

  /**
   * Envoi une requete pour la suppression d'une Question
   * @param delQuestion Question a supprimer
   */
  deleteQuestion(delQuestion: Question): Observable<any> {
    return this.deleteTrivialCode(this.url1, delQuestion);
  }

  /**
   * Envoi une requete pour la mise a jour d'une Question
   * @param newQuestion Question a mettre a jour
   */
  updateQuestion(newQuestion: Question): Observable<any> {
    return this.postTrivialCode(this.url1, newQuestion);
  }

}
