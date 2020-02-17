import { Component, OnInit } from '@angular/core';

import { Language } from '../shared/models/language';
import { LanguageService } from '../shared/services/language.service';

@Component({
  selector: 'app-jouer',
  templateUrl: './jouer.component.html',
  styleUrls: ['./jouer.component.css']
})
export class JouerComponent implements OnInit {
languages: Language[];


  constructor(private languageService : LanguageService) { }

  ngOnInit() {
    this.getLanguages();
  }

getLanguages(): void {
  
  this.languageService.getLanguagesList().subscribe(languages => this.languages = languages );
  
}

}
