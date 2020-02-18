import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Language } from 'src/app/shared/models/language';
import { LanguageService } from 'src/app/shared/services/language.service';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/services/user.service';


/**
 * Gère la création d'un user 
 * Utilise language service et user service
 */
@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  public user: User;
  public form: FormGroup;
  public languages : Language[];
  public create : boolean = false;

  constructor(private userService: UserService, private languageService : LanguageService) { }

  ngOnInit() {

    this.languageService.getLanguagesList().subscribe(languages => this.languages = languages);

    this.form = new FormGroup({
        email: new FormControl(),
        password: new FormControl(),
        username: new FormControl(),
        langage: new FormControl()
    });
  }

  /**
   * permet la création d'un user
   * récupère les données du formulaire et envoie l'objet au service user
   * @param form : données récupérées de l'html
   */
  postUser(form: FormGroup) {

    this.user = new User();
    this.create = true;

    this.user.username = form.controls['username'].value;
    this.user.email = form.controls['email'].value;
    this.user.password = form.controls['password'].value;
    this.user.ranking = 0;
    this.user.dateInscription = new Date();
    this.user.typeUser = null;

    let lang: Array<string>;
    let postLangages: Language[] = [];
    lang = form.controls['langage'].value;
    lang.forEach(langage => {
      this.languages.forEach(element => {
        if (langage === element.language){
          postLangages.push(element);
        }
      });
    });
    this.user.langage = postLangages;

    this.userService.createUser(this.user).subscribe();
  }
}
