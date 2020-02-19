import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { FormBuilder, FormControl, Validators} from '@angular/forms';

import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/services/user.service';
import { AuthentificationService } from '../shared/services/authentification.service';


@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css']
})
export class CompteComponent implements OnInit 
{

  public user: User = new User();
  public updateForm: boolean = false;
  public deleteForm: boolean = false;
  public scoreShow: boolean = false;
  public createBotton: boolean = false;

  update = new FormControl('',Validators.required);
  delete = new FormControl('',Validators.required);

  inscriptionDate = new Date();
  
  constructor(private userService: UserService, private router: Router, private auth : AuthentificationService, private fb: FormBuilder) { }

  ngOnInit() {

    this.redirect();
    this.getUser();
    this.openButtonAdmin();
  }

  /**
   * renvoie vers la page mon compte si l'user est connecté
   * utilisé à l'intialisation du composant
   */
  redirect(){

    if ( sessionStorage.getItem('username') == "" && sessionStorage.getItem('token') == "") {

      this.router.navigate(['/connexion']);
    }
  }
  /**
  * récupère le user associé au token
  */
  getUser() {

    this.userService.getByUsername(sessionStorage.getItem('username')).subscribe((user: User) => {
      this.user = user
    });
  }

  /**
   * appelle le service qui gère la déconnexion
   */
  logout() {
    this.createBotton = false;
    this.auth.logout();
  }

  /**
   * affiche / retire le form update
   */
  openUpdateForm() {

    if (this.updateForm == false) {

      this.updateForm = true;
    } else {

      this.updateForm = false;
    }
  }

/**
 * affiche le bouton admin en verifiant si le type d'utilisateur
   */
  openButtonAdmin(){
   
    if (sessionStorage.getItem('role') === "Administrateur")
  
    this.createBotton = true;

  }

  /**
   * récupère les données du formulaire, l'associe au user et l'envoie au service user
   */
  updateUser() 
  {
    this.user.email = this.update.value;
    this.userService.updateUser(this.user).subscribe();
  }

  /**
   * affiche / retire le form delte
   */
  openDeleteForm() 
  {
    if (this.deleteForm == false) {

      this.deleteForm = true;
    } else {

      this.deleteForm = false;
    }
  }

  deleteUser() 
  {
    this.userService.deleteUser(this.user).subscribe();
    this.logout();
  }

  /**
   * affiche / retire le ranking
   */
  openScore() {

    if (this.scoreShow == false) {

      this.scoreShow = true;
    } else {

      this.scoreShow = false;
    }
  
  }
}
