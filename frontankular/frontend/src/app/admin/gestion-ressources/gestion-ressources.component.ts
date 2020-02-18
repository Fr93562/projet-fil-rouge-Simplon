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
  public formupdate: FormGroup;

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
    this.formupdate = new FormGroup({
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
    this.ressourceService.createLink(this.ressource).subscribe();
    this.form.reset();
  }

  dynForm(event) {
    console.log(this.ressources[event.target.selectedIndex]);
    this.formupdate.controls['text'].setValue(this.ressources[event.target.selectedIndex].text);
    this.formupdate.controls['link'].setValue(this.ressources[event.target.selectedIndex].link);
  }

  updateLink(formupdate: FormGroup) {
    this.ressource;
    this.ressource.id = formupdate.controls['id'].value;
    this.ressource.text = formupdate.controls['text'].value;
    this.ressource.link = formupdate.controls['link'].value;
    this.ressourceService.updateLink(this.ressource).subscribe();
    this.form.reset();
  }

  deleteLink(form: FormGroup) {
    this.ressource;
    this.ressource.id = form.controls['id'].value;
    this.ressourceService.deleteLink(this.ressource).subscribe();
    this.form.reset();
  }


}
