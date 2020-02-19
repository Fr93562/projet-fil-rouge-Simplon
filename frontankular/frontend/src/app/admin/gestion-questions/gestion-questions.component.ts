import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Question } from 'src/app/shared/models/question';
import { QuestionService } from 'src/app/shared/services/question.service';
import { Categorie } from 'src/app/shared/models/categorie';
import { Ressource } from 'src/app/shared/models/ressource';


/**
 * Gestion des questions
 */
@Component({
  selector: 'app-gestion-questions',
  templateUrl: './gestion-questions.component.html',
  styleUrls: ['./gestion-questions.component.css']
})
export class GestionQuestionsComponent implements OnInit {

  public questions: Question[];
  public question: Question;
  public form: FormGroup;
  public formUpdate: FormGroup;

  constructor(private questionService: QuestionService) { }

  ngOnInit() {
    this.questionService.getQuestionsList().subscribe((questions: Question[]) => {
      this.questions = questions
    });

    this.form = new FormGroup({
      id: new FormControl(),
      level: new FormControl(),
      question: new FormControl(),
      answer: new FormControl(),
      choice1: new FormControl(),
      choice2: new FormControl(),
      choice3: new FormControl(),
     categorieId: new FormControl(),
     ressourceId: new FormControl(),
     langage: new FormControl()
    })
    this.formUpdate = new FormGroup({
      id: new FormControl(),
      level: new FormControl(),
      question: new FormControl(),
      answer: new FormControl(),
      choice1: new FormControl(),
      choice2: new FormControl(),
      choice3: new FormControl(),
     categorieId: new FormControl(),
     ressourceId: new FormControl(),
     langage: new FormControl()
    })
  }


   /**
 * Methode qui permet de creer une question
 * @param form renvoie le formulaire
 */
  postQuestion(form: FormGroup) {
    this.question = new Question();
    this.question.id = null;
    this.question.level = form.controls['level'].value;
    this.question.question = form.controls['question'].value;
    this.question.answer = form.controls['answer'].value;
    this.question.choice1 = form.controls['choice1'].value;
    this.question.choice2 = form.controls['choice2'].value;
    this.question.choice3 = form.controls['choice3'].value;
    let cat = new Categorie();
    cat.id = form.controls['categorieId'].value;
    this.question.categorie = cat;
    let res = new Ressource;
    res.id = form.controls['ressourceId'].value;
    this.question.ressource = res;
    this.questionService.createQuestion(this.question, form.controls['langage'].value).subscribe(maj => {
      this.questionService.getQuestionsList().subscribe((questions: Question[]) => {
        this.questions = questions
      });
    });
    this.form.reset();
  }


  dynForm(event) {
    console.log(this.questions[event.target.selectedIndex]);
    this.formUpdate.controls['level'].setValue(this.questions[event.target.selectedIndex].level);
    this.formUpdate.controls['question'].setValue(this.questions[event.target.selectedIndex].question);
    this.formUpdate.controls['answer'].setValue(this.questions[event.target.selectedIndex].answer);
    this.formUpdate.controls['choice1'].setValue(this.questions[event.target.selectedIndex].choice1);
    this.formUpdate.controls['choice2'].setValue(this.questions[event.target.selectedIndex].choice2);
    this.formUpdate.controls['choice3'].setValue(this.questions[event.target.selectedIndex].choice3);
    this.formUpdate.controls['categorieId'].setValue(this.questions[event.target.selectedIndex].categorie);
    this.formUpdate.controls['ressourceId'].setValue(this.questions[event.target.selectedIndex].ressource);
  }

   /**
 * Methode qui permet de modifier une question
 * @param form renvoie le formulaire
 */
  updateQuestion(formUpdate: FormGroup) {
    this.question = new Question();
    this.question.id = formUpdate.controls['id'].value;
    this.question.level = formUpdate.controls['level'].value;
    this.question.question = formUpdate.controls['question'].value;
    this.question.answer = formUpdate.controls['answer'].value;
    this.question.choice1 = formUpdate.controls['choice1'].value;
    this.question.choice2 = formUpdate.controls['choice2'].value;
    this.question.choice3 = formUpdate.controls['choice3'].value;
    let cat = new Categorie();
    cat.id = formUpdate.controls['categorieId'].value;
    this.question.categorie = cat;
    let res = new Ressource;
    res.id = formUpdate.controls['ressourceId'].value;
    this.question.ressource = res;
    this.questionService.updateQuestion(this.question).subscribe(maj => {
      this.questionService.getQuestionsList().subscribe((questions: Question[]) => {
        this.questions = questions
      });
    });
    this.form.reset();
  }
  

 /**
 * Methode qui permet de supprimer une question
 * @param form renvoie le formulaire
 */
  deleteQuestion(form: FormGroup) {
    this.question = new Question();
    this.question.id = form.controls['id'].value;
    this.questionService.deleteQuestion(this.question).subscribe(maj => {
      this.questionService.getQuestionsList().subscribe((questions: Question[]) => {
        this.questions = questions
      });
    });
    this.form.reset();
  }

}

