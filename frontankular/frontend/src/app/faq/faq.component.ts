import { Component, OnInit } from '@angular/core';
import { Faq } from '../shared/models/faq';
import { FaqService } from '../shared/services/faq.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  faqs: Faq[];
  test: Faq;

  constructor(private faqService: FaqService) { }

  ngOnInit() {
    this.getFaqList();
  }

  getFaqList() {
    this.faqService.getFaqsList()
        .subscribe(faqs => this.faqs = faqs);
  }

  postFaq()  {
    console.log('hello');
    this.test = new Faq();
    this.test.id = 0;
    this.test.priority = 1;
    this.test.question = 'test';
    this.test.response = 'ca marche?';
    console.log(this.test);
    this.faqService.createFaq(this.test).subscribe();
  }
}
