import { Component, OnInit } from '@angular/core';
import { Language } from 'src/app/shared/models/language';
import { LanguageService } from 'src/app/shared/services/languageService.service';


@Component({
  selector: 'app-gestion-langages',
  templateUrl: './gestion-langages.component.html',
  styleUrls: ['./gestion-langages.component.css']
})
export class GestionLangagesComponent implements OnInit {
  public languages: Language[];
  public language: Language;

  constructor(private languageService: LanguageService) { }

  ngOnInit() {
    this.languageService.getLanguages().subscribe((languages: Language[]) => {
      this.languages = languages
    });

    this.language = new Language;
  }


  addLanguage(nomLanguage: string): void {
    this.language.language = nomLanguage;
    this.languageService.addLanguage(this.language)
      .subscribe(language => {
        this.languages.push(language);
      });
  }



  deleteLanguage(nomLanguage: string): void {
    this.language.language = nomLanguage;
    this.languageService.deleteLanguage(this.language)
      .subscribe(language => {
        this.languages.push(language);
      });
  }
}

//  delete(id: number): void {
//     this.languageService.deleteLanguage(id).subscribe();
//   }
