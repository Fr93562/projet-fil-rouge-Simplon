import { Component, OnInit } from '@angular/core';
import { Faq } from 'src/app/shared/models/faq';
import { FaqService } from 'src/app/shared/services/faq.service';

@Component({
  selector: 'app-gestion-faq',
  templateUrl: './gestion-faq.component.html',
  styleUrls: ['./gestion-faq.component.css']
})
export class GestionFaqComponent implements OnInit {
  public faqs: Faq[];
  public faq: Faq;

  constructor(private faqService: FaqService) { }

  ngOnInit() {
    this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
      this.faqs = faqs
    });
  }

}
