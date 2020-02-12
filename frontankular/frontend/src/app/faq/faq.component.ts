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
  response: string;

  constructor(private faqService: FaqService) { }

  ngOnInit() {
    this.getFaqList();
  }

  getFaqList() {
    this.faqService.getFaqsList()
        .subscribe(faqs => this.faqs = faqs);
  }

  // a supprimé pour test
  postFaq()  {
    this.test = new Faq();
    this.test.id = 1;
    this.test.priority = 1;
    this.test.question = 'test';
    this.test.response = 'ca marche2?';
    this.faqService.updateFaq(this.test).subscribe();
  }

  deleteFaq()  {
    this.test = new Faq();
    this.test.id = 6;
    this.faqService.deleteFaq(this.test).subscribe();
  }
  // a supprimé pour test
}
