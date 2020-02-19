import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/services/user.service';
import { TypeUser} from 'src/app/shared/models/typeUser';
import { TypeUserService } from 'src/app/shared/services/type-user.service';
import { Language } from 'src/app/shared/models/language';
import { LanguageService } from 'src/app/shared/services/language.service';


/**
 * Gestion des utilisateurs
 */
@Component({
  selector: 'app-gestion-utilisateurs',
  templateUrl: './gestion-utilisateurs.component.html',
  styleUrls: ['./gestion-utilisateurs.component.css']
})
export class GestionUtilisateursComponent implements OnInit {

  public users: User[];
  public user: User;
  public typeUsers : TypeUser[];
  public languages : Language[];
  public form: FormGroup;
  public formUpdate: FormGroup;

  constructor(private userService: UserService, private typeUserService : TypeUserService, private languageService : LanguageService) { }

  ngOnInit() {
    this.userService.getList().subscribe((users: User[]) => {
      this.users = users
    });
    this.typeUserService.getTypeUserList().subscribe(typeUsers => this.typeUsers = typeUsers);
    this.languageService.getLanguagesList().subscribe(languages => this.languages = languages);

    this.form = new FormGroup({
      id: new FormControl(),
      email: new FormControl(),
      password: new FormControl(),
      username: new FormControl(),
      typeUser: new FormControl(),
      langage: new FormControl(),
    });

    this.formUpdate = new FormGroup({
      id: new FormControl(),
      email: new FormControl(),
      password: new FormControl(),
      username: new FormControl(),
      typeUser: new FormControl(),
      langage: new FormControl(),
    });
    console.log(this.form);
  }


  /**
 * Methode qui permet de creer un utilisateur
 * @param form renvoie le formulaire
 */
  postUser(form: FormGroup) {
    this.user = new User();
    this.user.ranking = 0;
    this.user.dateInscription = new Date();
    this.user.email = form.controls['email'].value;
    this.user.password = form.controls['password'].value;
    this.user.username = form.controls['username'].value;
   
    let type = new TypeUser();
    let typeString = form.controls['typeUser'].value;
    this.typeUsers.forEach(typeUser => {
      if (typeUser.type === typeString) {
        type = typeUser;
      }    
    });
    this.user.typeUser = type;
   
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

    this.userService.createUser(this.user).subscribe(maj => {
      this.userService.getList().subscribe((users: User[]) => {
        this.users = users
      });
    });
    this.form.reset();
  }

/**
 * Methode qui permet de modifier un utilisateur
 * @param form renvoie le formulaire
 */
  updateUser(formUpdate: FormGroup) {
    this.user = new User();
    this.user.id = formUpdate.controls['id'].value;
    this.user.email = formUpdate.controls['email'].value;
    this.user.password = formUpdate.controls['password'].value;
    this.user.username = formUpdate.controls['username'].value;
    
    let type = new TypeUser();
    let typeString = formUpdate.controls['typeUser'].value;
    this.typeUsers.forEach(typeUser => {
      if (typeUser.type === typeString) {
        type = typeUser;
      }    
    });
    this.user.typeUser = type;
    let lang: Array<string>;
    let postLangages: Language[] = [];
    lang = formUpdate.controls['langage'].value;
    lang.forEach(langage => {
      this.languages.forEach(element => {
        if (langage === element.language){
          postLangages.push(element);
        }
      });
    });
    this.user.langage = postLangages;

    console.log(this.user);
    this.userService.updateUser(this.user).subscribe(maj => {
      this.userService.getList().subscribe((users: User[]) => {
        this.users = users
      });
    });
    this.form.reset();
  }


  /**
  * préremplie le formulaire de modification avec les données de l'id selectionné
  * @param event 
  */
  dynForm(event) {
    console.log(this.users[event.target.selectedIndex]);
    this.formUpdate.controls['email'].setValue(this.users[event.target.selectedIndex].email);
    this.formUpdate.controls['password'].setValue(this.users[event.target.selectedIndex].password);
    this.formUpdate.controls['username'].setValue(this.users[event.target.selectedIndex].username);
    this.formUpdate.controls['typeUser'].setValue(this.users[event.target.selectedIndex].typeUser.type);
    this.formUpdate.controls['langage'].setValue(this.users[event.target.selectedIndex].langage);
  }


  /**
 * Methode qui permet de supprimer un utilisateur
 * @param form renvoie le formulaire
 */
  deleteUser(form: FormGroup) {
    this.user = new User();
    this.user.id = form.controls['id'].value;
    this.userService.deleteUser(this.user).subscribe(maj => {
      this.userService.getList().subscribe((users: User[]) => {
        this.users = users
      });
    });
    this.form.reset();
  }
}
