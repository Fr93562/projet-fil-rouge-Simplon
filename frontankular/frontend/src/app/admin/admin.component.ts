import { Component, OnInit } from '@angular/core';
import { Language } from '../shared/models/language';
import { LanguageService } from '../shared/services/languageService.service';
import { LangagesQuestionsComponent } from './langages-questions/langages-questions.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
 
  languages: Language[];
  language: Language = new Language();


  constructor(private languageService : LanguageService) { }

  ngOnInit() {
    this.getLanguages();
  }

getLanguages(): void {
  
  this.languageService.getLanguages().subscribe(languages => this.languages = languages );
  
}

add(libelle: string): void {
  this.language.language = libelle; 
  if (!libelle) { return; }
  this.languageService.addLanguage(this.language)
    .subscribe(language => {
      this.languages.push(language);
    });
}

delete(language: Language): void {
  this.languageService.deleteLanguage(language).subscribe();
}


}
