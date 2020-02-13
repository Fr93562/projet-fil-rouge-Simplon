import { Component, OnInit } from '@angular/core';
import { User } from '../shared/models/user';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {
  
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {

    this.getLadder();
  }

  getLadder() {

    this.userService.getList().subscribe( users => this.users = users);
  }

}
