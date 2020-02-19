import { Component, OnInit } from '@angular/core';

import { Language } from '../shared/models/language';
import { LanguageService } from '../shared/services/language.service';
import { FormGroup, FormControl } from '@angular/forms';
import { GameService } from '../shared/services/game.service';
import { Question } from '../shared/models/question';

@Component({
  selector: 'app-jouer',
  templateUrl: './jouer.component.html',
  styleUrls: ['./jouer.component.css']
})
export class JouerComponent implements OnInit {

  langages: Language[];
  formselectlang: FormGroup;
  gamestatement: number = 0;
  isAuth: boolean = true;
  gametitle: string;
  tours: number;
  question: Question;
  listLangages: Array<string>;
  reponses: string[];
  point: number = 0;
  useQuestions: Question[] = [];

  constructor(private languageService: LanguageService, private gameService: GameService) { }

  ngOnInit() {
    this.getLanguages();

    this.formselectlang = new FormGroup({
      selectlangage: new FormControl()
    });
  }

  getLanguages(): void {
    this.languageService.getLanguagesList().subscribe(languages => this.langages = languages);
  }

  findQuestions(formselectlang: FormGroup): void {
    this.listLangages = formselectlang.controls['selectlangage'].value;
    // TODO session storage
    if (this.isAuth) {
      this.tours = 10;
    } else {
      this.tours = 5;
    }
    this.addGameTitle();
    this.activeGame();
    this.gamestatement = 1;
  }

  addGameTitle(): void {
    if (this.isAuth) {
      this.gametitle = 'Vive Simplon';
    } else {
      this.gametitle = 'Version d\'essai';
    }
  }

  activeGame() {
    this.question = this.gameService.getQuestion(this.listLangages);
    let answerRand: string[] = [];
    this.reponses = [];
    answerRand.push(this.question.answer);
    answerRand.push(this.question.choice1);
    answerRand.push(this.question.choice2);
    answerRand.push(this.question.choice3);
    do {
      const temp = answerRand.splice(this.random(answerRand.length), 1);
      this.reponses.push(temp[0]);
    } while (answerRand.length > 0);
  }

  endgame() {
    // TODO la fin mise a jour ranking si auth
    console.log('fin du jeu');
  }

  checkResponse(response) {
    console.log(response.target.innerText);
    console.log(this.question.answer);
    if (response.target.innerText === this.question.answer) {
      this.point += 1;
    }
    this.tours -= 1;
    this.useQuestions.push(this.question);
    if (this.tours === 0) {
      this.endgame();
      this.gamestatement = 2;
    } else {
      this.activeGame();
    }
  }

  random(length: number): number {
    return Math.floor(Math.random() * length);
  }

}
