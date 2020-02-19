import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { TypeUser } from 'src/app/shared/models/typeUser';
import { TypeUserService } from 'src/app/shared/services/type-user.service';


/**
 * Gestion des types d'utilisateurs
 */
@Component({
  selector: 'app-gestion-type-utilisateur',
  templateUrl: './gestion-type-utilisateur.component.html',
  styleUrls: ['./gestion-type-utilisateur.component.css']
})
export class GestionTypeUtilisateurComponent implements OnInit {

  public typeUsers: TypeUser[];
  public typeUser: TypeUser;
  public form: FormGroup;
  public formUpdate: FormGroup;

  constructor(private typeUserService: TypeUserService) { }

  ngOnInit() {
    this.typeUserService.getTypeUserList().subscribe((typeUsers: TypeUser[]) => {
      this.typeUsers = typeUsers
    });

    this.form = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })

    this.formUpdate = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })
  }

  /**
 * Methode qui permet de creer un type d'utilisateur
 * @param form renvoie le formulaire
 */
  postTypeUser(form: FormGroup) {
    this.typeUser = new TypeUser();
    this.typeUser.id = null;
    this.typeUser.type = form.controls['type'].value;
    this.typeUserService.createTypeUser(this.typeUser).subscribe(maj => {
      this.typeUserService.getTypeUserList().subscribe((typeUsers: TypeUser[]) => {
        this.typeUsers = typeUsers
      });
    });
    this.form.reset();
  }

  dynForm(event) {
    console.log(this.typeUsers[event.target.selectedIndex]);
    this.formUpdate.controls['type'].setValue(this.typeUsers[event.target.selectedIndex].type);
  }


   /**
 * Methode qui permet de modifier un type d'utilisateur
 * @param form renvoie le formulaire
 */
  updateTypeUser(form: FormGroup) {
    this.typeUser = new TypeUser();
    this.typeUser.id = this.formUpdate.controls['id'].value;
    this.typeUser.type = this.formUpdate.controls['type'].value;
    this.typeUserService.updateTypeUser(this.typeUser).subscribe(maj => {
      this.typeUserService.getTypeUserList().subscribe((typeUsers: TypeUser[]) => {
        this.typeUsers = typeUsers
      });
    });
    this.form.reset();
  }
}

