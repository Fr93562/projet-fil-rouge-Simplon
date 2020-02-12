import { Component, OnInit } from '@angular/core';
import { Language } from '../shared/models/language';
import { LanguageService } from '../shared/services/languageService.service';
import { LangagesComponent } from './langages/langages.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public display: boolean = false;

  languages: Language[];
  language: Language = new Language();


  constructor(private languageService: LanguageService) { }

  ngOnInit() {
    this.getLanguages();
  }

  getLanguages(): void {

    this.languageService.getLanguages().subscribe(languages => this.languages = languages);

  }


  add(language: string): void {
    language = language.trim();
    if (!language) { return; }
    this.languageService.addLanguage({ language } as Language)
      .subscribe(language => {
        this.languages.push(language);
      });
  }



  // add(libelle: string): void {
  //   this.language.language = libelle; 
  //   if (!libelle) { return; }
  //   this.languageService.addLanguage(this.language)
  //     .subscribe(language => {
  //       this.languages.push(language);
  //     });
  // }

  // delete(id: number): void {
  //   this.languageService.deleteLanguage(id).subscribe();
  // }


}
