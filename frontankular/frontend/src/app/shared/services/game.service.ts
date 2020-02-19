import { Injectable } from '@angular/core';
import { Question } from '../models/question';
import { RequestService } from './request.service';
import { DataService } from './data.service';
import { HttpClient } from '@angular/common/http';
import { Observable, of, concat } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService extends RequestService {

  listQuestions: Question[] = [];
  url = this.data.baseUrl + '/questions';

  constructor(public http: HttpClient, public data: DataService) {
    super(http, data);
  }

  private createListQuestions(listLangages: Array<string>): Observable<Question[]> {
    if (this.listQuestions.length > 0) {
      return of(this.listQuestions);
    } else {
      return concat(...listLangages.map(langage => this.getTrivialCode(this.url + '?langage=' + langage)));
    }

  }

  getQuestion(listLangages: Array<string>): Question {
    if (this.listQuestions.length > 0) {
      return this.selectQuestion();
    } else {
      this.createListQuestions(listLangages).subscribe(list => {
        this.listQuestions = list;
        return this.selectQuestion();
      });
    }
  }

  private selectQuestion(): Question {
    const dice: number = Math.floor(Math.random() * this.listQuestions.length);
    const returnQuestion: Question = this.listQuestions[dice];
    this.listQuestions.splice(dice, 1);
    console.log(returnQuestion);
    return returnQuestion;
  }
}
