import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Ressource } from 'src/app/shared/models/ressource';
import { RessourceService } from 'src/app/shared/services/ressource.service';



/**
 * Gestion des ressources
 */
@Component({
  selector: 'app-gestion-ressources',
  templateUrl: './gestion-ressources.component.html',
  styleUrls: ['./gestion-ressources.component.css']
})
export class GestionRessourcesComponent implements OnInit {
  public ressources: Ressource[];
  public ressource: Ressource;
  public form: FormGroup;
  public formUpdate: FormGroup;

  constructor(private ressourceService: RessourceService) { }

  ngOnInit() {
    this.ressourceService.getLinkList().subscribe((ressources: Ressource[]) => {
      this.ressources = ressources
    });

    this.form = new FormGroup({
      id: new FormControl(),
      text: new FormControl(),
      link: new FormControl()
    })
    this.formUpdate = new FormGroup({
      id: new FormControl(),
      text: new FormControl(),
      link: new FormControl()
    })
   
  }

   /**
 * Methode qui permet de creer une ressource
 * @param form renvoie le formulaire
 */
  postLink(form: FormGroup) {
    this.ressource = new Ressource();
    this.ressource.id = null;
    this.ressource.text = form.controls['text'].value;
    this.ressource.link = form.controls['link'].value;
    this.ressourceService.createLink(this.ressource).subscribe(maj => {
      this.ressourceService.getLinkList().subscribe((ressources: Ressource[]) => {
        this.ressources = ressources
      });
    });
    this.form.reset();
  }


   /**
  * préremplie le formulaire de modification avec les données de l'id selectionné
  * @param event 
  */
  dynForm(event) {
    this.formUpdate.controls['text'].setValue(this.ressources[event.target.selectedIndex].text);
    this.formUpdate.controls['link'].setValue(this.ressources[event.target.selectedIndex].link);
  }

  
   /**
 * Methode qui permet de modifier une ressource
 * @param form renvoie le formulaire
 */
  updateLink(formUpdate: FormGroup) {
    this.ressource = new Ressource();
    this.ressource.id = formUpdate.controls['id'].value;
    this.ressource.text = formUpdate.controls['text'].value;
    this.ressource.link = formUpdate.controls['link'].value;
    this.ressourceService.updateLink(this.ressource).subscribe(maj => {
      this.ressourceService.getLinkList().subscribe((ressources: Ressource[]) => {
        this.ressources = ressources
      });
    });
    this.form.reset();
  }

   /**
 * Methode qui permet de supprimer une ressource
 * @param form renvoie le formulaire
 */
  deleteLink(form: FormGroup) {
    this.ressource = new Ressource();
    this.ressource.id = form.controls['id'].value;
    this.ressourceService.deleteLink(this.ressource).subscribe(maj => {
      this.ressourceService.getLinkList().subscribe((ressources: Ressource[]) => {
        this.ressources = ressources
      });
    });
    this.form.reset();
  }
}
