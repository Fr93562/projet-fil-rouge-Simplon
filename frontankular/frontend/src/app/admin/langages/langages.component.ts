import { Component, OnInit, Input } from '@angular/core';
import { Language } from 'src/app/shared/models/language';



@Component({
  selector: 'app-langages',
  templateUrl: './langages.component.html',
  styleUrls: ['./langages.component.css']
})
export class LangagesComponent implements OnInit {
  @Input() languages: Language[];


constructor() { }

ngOnInit() {
}


}
