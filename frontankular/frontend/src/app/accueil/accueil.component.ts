import { Component, OnInit } from '@angular/core';
import { User } from '../shared/models/user';
import { UserService } from '../shared/services/user.service';

/**
 * composant de la page d'accueil
 * utilise userService pour récupérer les scores / pseudo
 */
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {
  
  users: User[] ;
  userClass: User[] ;

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.getLadder();

  }

  /**
   * appelle l'userService et stocke la réponse dans l'objet users
   */
  getLadder() {

    this.userService.getList().subscribe(users => {
      this.users = users;
      this.userClass = [this.users[0],this.users[1],this.users[2],this.users[3],this.users[4]];
    });
  }

}
