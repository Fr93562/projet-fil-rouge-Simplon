import { Component, OnInit } from '@angular/core';
import { LanguageService } from '../shared/services/languageService.service';
import { FaqService } from '../shared/services/faq.service';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [LanguageService, FaqService]
})
export class AdminComponent implements OnInit {
  public display1: boolean = false;
  public display2: boolean = false;
 
  constructor(private languageService: LanguageService, private faqService: FaqService) { }

  ngOnInit() {
   
  }

  

}
