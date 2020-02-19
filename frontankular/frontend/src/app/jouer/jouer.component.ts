import { Component, OnInit } from '@angular/core';

import { Language } from '../shared/models/language';
import { LanguageService } from '../shared/services/language.service';
import { FormGroup, FormControl } from '@angular/forms';
import { GameService } from '../shared/services/game.service';
import { Question } from '../shared/models/question';
import { User } from '../shared/models/user';
import { UserService } from 'src/app/shared/services/user.service';

/**
 * Gere le deroulement du jeu
 */
@Component({
  selector: 'app-jouer',
  templateUrl: './jouer.component.html',
  styleUrls: ['./jouer.component.css']
})
export class JouerComponent implements OnInit {

  /**
   * Attribut
   */
  langages: Language[];
  formselectlang: FormGroup;
  gamestatement: number = 0; // defini l'état du jeu
  tours: number;
  question: Question = new Question();
  listLangages: Array<string>;
  reponses: string[];
  point: number = 0;
  useQuestions: Question[] = [];

  constructor(private languageService: LanguageService, private gameService: GameService, private userService: UserService) { }

  ngOnInit() {
    this.getLanguages();

    this.formselectlang = new FormGroup({
      selectlangage: new FormControl()
    });
  }

  /**
   * Recupere les langages
   */
  getLanguages(): void {
    this.languageService.getLanguagesList().subscribe(languages => this.langages = languages);
  }

  /**
   * Recupere les questions et engage la suite du jeu
   * Change le nombre de questions si le joueur est anonyme ou pas
   * @param formselectlang les langages a recuperer
   */
  findQuestions(formselectlang: FormGroup): void {
    this.listLangages = formselectlang.controls['selectlangage'].value;

    if (sessionStorage.getItem('username') === '' && sessionStorage.getItem('token') === '') {
      this.tours = 5;
    } else {
      this.tours = 10;
    }
    this.activeGame();
    this.gamestatement = 1;
  }

  /**
   * Deroulement du jeu
   */
  activeGame() {
    this.gameService.getQuestion(this.listLangages).subscribe(question => {
      this.question = question;
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
    });
  }

  /**
   * Fin du jeu
   * Met a jour le ranking du joueur si il est connecté
   */
  endgame() {
    if (!(sessionStorage.getItem('username') === '') && !(sessionStorage.getItem('token') === '')) {
      this.majUser();
    }
  }

  /**
   * Verifie la reponse et met à jour le score et execute le deroulement
   * @param response reponse selectionné
   */
  checkResponse(response) {
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

  /**
   * Renvoi un nombre aleatoire
   * @param length taille du tableau
   */
  random(length: number): number {
    return Math.floor(Math.random() * length);
  }

  /**
   * Recupere l'user et le met a jour
   */
  majUser() {
    this.userService.getByUsername(sessionStorage.getItem('username')).subscribe((user: User) => {
      user.ranking = this.point + user.ranking;
      this.updateUser(user);
    });
  }

  /**
   * Envoi l'utilisateur modifié en base
   * @param user utilisateur a envoyé
   */
  updateUser(user: User) {
    this.userService.updateUser(user).subscribe();
  }

  /**
   * reinitilise le jeu
   */
  clear() {
    this.gameService.clear();
    this.useQuestions = [];
    this.gamestatement = 0;
  }
}
