import { Component, OnInit } from '@angular/core';
import { Language } from 'src/app/shared/models/language';
import { LanguageService } from 'src/app/shared/services/language.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-gestion-langages',
  templateUrl: './gestion-langages.component.html',
  styleUrls: ['./gestion-langages.component.css']
})
export class GestionLangagesComponent implements OnInit {
  public languages: Language[];
  public language: Language;
  public form: FormGroup;

  constructor(private languageService: LanguageService) { }

  ngOnInit() {
    this.languageService.getLanguagesList().subscribe((languages: Language[]) => {
      this.languages = languages
    });

    this.form = new FormGroup({
      id: new FormControl(),
      language: new FormControl()
    })
   
  }

  postLanguage(form: FormGroup) {
    this.language = new Language();
    this.language.id = null;
    this.language.language = form.controls['language'].value;
    this.languageService.createLangage(this.language).subscribe();
    this.form.reset();
  }

  updateLanguage(form: FormGroup) {
    this.language;
    this.language.id = form.controls['id'].value;
    this.language.language = form.controls['language'].value;
    this.languageService.updateLanguage(this.language).subscribe();
    this.form.reset();
  }


  deleteLanguage(form: FormGroup) {
    this.language;
    this.language.id = form.controls['id'].value;
    this.languageService.deleteLanguage(this.language).subscribe();
    this.form.reset();
  }

}
