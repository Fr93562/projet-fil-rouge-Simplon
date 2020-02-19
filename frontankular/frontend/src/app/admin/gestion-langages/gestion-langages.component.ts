import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Language } from 'src/app/shared/models/language';
import { LanguageService } from 'src/app/shared/services/language.service';



/**
 * Gestion des Langages
 */
@Component({
  selector: 'app-gestion-langages',
  templateUrl: './gestion-langages.component.html',
  styleUrls: ['./gestion-langages.component.css']
})
export class GestionLangagesComponent implements OnInit {
  public languages: Language[];
  public language: Language;
  public form: FormGroup;
  public formUpdate: FormGroup;


  constructor(private languageService: LanguageService) { }

  ngOnInit() {
    this.languageService.getLanguagesList().subscribe((languages: Language[]) => {
      this.languages = languages
    });

    this.form = new FormGroup({
      id: new FormControl(),
      language: new FormControl()
    })
    this.formUpdate = new FormGroup({
      id: new FormControl(),
      language: new FormControl()
    })
   
  }

  
/**
 * Methode qui permet de creer un langage
 * @param form renvoie le formulaire
 */
  postLanguage(form: FormGroup) {
    this.language = new Language();
    this.language.id = null;
    this.language.language = form.controls['language'].value;
    this.languageService.createLangage(this.language).subscribe(maj => {
      this.languageService.getLanguagesList().subscribe((languages: Language[]) => {
        this.languages = languages
      });
    });
    this.form.reset();
  }


 /**
  * préremplie le formulaire de modification avec les données de l'id selectionné
  * @param event 
  */
  dynForm(event) {
    console.log(this.languages[event.target.selectedIndex]);
    this.formUpdate.controls['language'].setValue(this.languages[event.target.selectedIndex].language);
  }


  /**
 * Methode qui permet de modifier un langage
 * @param form renvoie le formulaire
 */
  updateLanguage(formUpdate: FormGroup) {
    this.language = new Language();
    this.language.id = formUpdate.controls['id'].value;
    this.language.language = formUpdate.controls['language'].value;
    this.languageService.updateLanguage(this.language).subscribe(maj => {
      this.languageService.getLanguagesList().subscribe((languages: Language[]) => {
        this.languages = languages
      });
    });
    this.form.reset();
  }


  /**
 * Methode qui permet de supprimer un langage
 * @param form renvoie le formulaire
 */
  deleteLanguage(form: FormGroup) {
    this.language = new Language();
    this.language.id = form.controls['id'].value;
    this.languageService.deleteLanguage(this.language).subscribe(maj => {
      this.languageService.getLanguagesList().subscribe((languages: Language[]) => {
        this.languages = languages
      });
    });
    this.form.reset();
  }

}
