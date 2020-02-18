import { Component, OnInit } from '@angular/core';
import { Ressource } from 'src/app/shared/models/ressource';
import { RessourceService } from 'src/app/shared/services/ressource.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-gestion-ressources',
  templateUrl: './gestion-ressources.component.html',
  styleUrls: ['./gestion-ressources.component.css']
})
export class GestionRessourcesComponent implements OnInit {
  public ressources: Ressource[];
  public ressource: Ressource;
  public form: FormGroup;

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
   
  }

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

  dynForm(event) {
    console.log(this.ressources[event.target.selectedIndex]);
    this.form.controls['text'].setValue(this.ressources[event.target.selectedIndex].text);
    this.form.controls['link'].setValue(this.ressources[event.target.selectedIndex].link);
  }

  updateLink(form: FormGroup) {
    this.ressource = new Ressource();
    this.ressource.id = form.controls['id'].value;
    this.ressource.text = form.controls['text'].value;
    this.ressource.link = form.controls['link'].value;
    this.ressourceService.updateLink(this.ressource).subscribe(maj => {
      this.ressourceService.getLinkList().subscribe((ressources: Ressource[]) => {
        this.ressources = ressources
      });
    });
    this.form.reset();
  }

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
