import { Component, OnInit } from '@angular/core';
import { Faq } from 'src/app/shared/models/faq';
import { FaqService } from 'src/app/shared/services/faq.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-gestion-faq',
  templateUrl: './gestion-faq.component.html',
  styleUrls: ['./gestion-faq.component.css']
})
export class GestionFaqComponent implements OnInit {
  public faqs: Faq[];
  public faq: Faq;
  public form: FormGroup;
 

  constructor(private faqService: FaqService) { }

  ngOnInit() {
    this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
      this.faqs = faqs
    });

    this.form = new FormGroup({
      id: new FormControl(),
      priorite: new FormControl(),
      question: new FormControl(),
      reponse: new FormControl()
    })

    console.log(this.form);
  }

  postFaq(form: FormGroup) {
    this.faq = new Faq();
    this.faq.id = null;
    this.faq.priority = form.controls['priorite'].value;
    this.faq.question = form.controls['question'].value;
    this.faq.response = form.controls['reponse'].value;
    this.faqService.createFaq(this.faq).subscribe(maj => {
      this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
        this.faqs = faqs
      });
    });
    this.form.reset();
  }

  dynForm(event) {
    console.log(this.faqs[event.target.selectedIndex]);
    this.form.controls['priorite'].setValue(this.faqs[event.target.selectedIndex].priority);
    this.form.controls['question'].setValue(this.faqs[event.target.selectedIndex].question);
    this.form.controls['reponse'].setValue(this.faqs[event.target.selectedIndex].response);
  }

  updateFaq(form: FormGroup) {
    this.faq = new Faq();
    this.faq.id = form.controls['id'].value;
    this.faq.priority = form.controls['priorite'].value;
    this.faq.question = form.controls['question'].value;
    this.faq.response = form.controls['reponse'].value;
    this.faqService.updateFaq(this.faq).subscribe(maj => {
      this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
        this.faqs = faqs
      });
    });
    this.form.reset();
  }

  deleteFaq(form: FormGroup) {
    this.faq = new Faq();
    this.faq.id = form.controls['id'].value;
    this.faqService.deleteFaq(this.faq).subscribe(maj => {
      this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
        this.faqs = faqs
      });
    });
    this.form.reset();
  }


  

}
