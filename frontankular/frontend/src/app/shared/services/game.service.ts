import { Injectable } from '@angular/core';
import { Question } from '../models/question';
import { RequestService } from './request.service';
import { DataService } from './data.service';
import { HttpClient } from '@angular/common/http';
import { Observable, of, concat } from 'rxjs';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
/**
 * Gere les appels en base pour jouer.component
 */
export class GameService extends RequestService {

  /**
   * Attributs
   */
  listQuestions: Question[] = [];
  url = this.data.baseUrl + '/questions';

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  /**
   * Recupere la liste de questions en relation avec les langages choisi
   * Retourne un Observable contenant un tableau de Questions
   * @param listLangages  langages selectionné
   */
  private createListQuestions(listLangages: Array<string>): Observable<Question[]> {
    if (this.listQuestions.length > 0) {
      return of(this.listQuestions);
    } else {
      return concat(...listLangages.map(langage => this.getTrivialCode(this.url + '?langage=' + langage)))
        .pipe(tap(serverConcatQuestions => this.listQuestions = serverConcatQuestions));
    }
  }

  /**
   * Renvoi un Observable contenant une Questions
   * @param listLangages langages selectionné
   */
  getQuestion(listLangages: Array<string>): Observable<Question> {
     return this.createListQuestions(listLangages).pipe(
        map(list => this.selectQuestion())
      );
  }

  /**
   * Selectionne une question au hasard dans la liste
   */
  private selectQuestion(): Question {
    const dice: number = Math.floor(Math.random() * this.listQuestions.length);
    const returnQuestion: Question = this.listQuestions[dice];
    this.listQuestions.splice(dice, 1);
    return returnQuestion;
  }

  /**
   * Reinitialise le jeu
   */
  clear() {
    this.listQuestions = [];
  }
}
