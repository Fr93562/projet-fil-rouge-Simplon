import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from "@angular/router"

import { User } from '../shared/models/user';
import { AuthentificationService } from '../shared/services/authentification.service';


/**
 * gère la connexion en récupérant les données du formulaire
 * les traitants et l'envoie au service authentification 
 * 
 * @author Didax
 */
@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  // attributs du formulaire
  formUsername = new FormControl('', Validators.required);
  formPassword = new FormControl('', Validators.required);

  // attributs de data
  private username: string = "";
  private password: string = "";

  user: User = new User();

  constructor(private fb: FormBuilder, private auth: AuthentificationService, private router: Router) {

  }

  ngOnInit() {

    this.redirect();
  }

  /**
   * renvoie vers la page mon compte si l'user est connecté
   * utilisé à l'intialisation du composant
   */
  redirect() {

    if (sessionStorage.getItem('username') != "" && sessionStorage.getItem('token') != "") {

      this.router.navigate(['/connexion/compte']);
    }
  }

  /**
   * récupère les informations du formulaire et effectue une vérification
   * et le stocke dans le composant
   */
  getData() {

    this.username = this.formUsername.value;
    this.password = this.formPassword.value;
    let fake = "fake";

    if (this.password.length > 255 || this.password.length < 6 || this.username.length > 30 || this.username.length < 4) {
      this.username = fake;
      this.password = fake;
    }
  }

  /**
   * envoie les informations au service authentification
   */
  sendData() {

    this.user.username = this.username;
    this.user.password = this.password;
    this.auth.login(this.user);
  }

  /**
   * regroupe la réception et l'envoi des données
   */
  sendRequest() {

    this.getData();
    this.sendData();
    this.redirect();
  }
}

