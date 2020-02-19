import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Faq } from 'src/app/shared/models/faq';
import { FaqService } from 'src/app/shared/services/faq.service';


/**
 * Gestion des Faqs
 */
@Component({
  selector: 'app-gestion-faq',
  templateUrl: './gestion-faq.component.html',
  styleUrls: ['./gestion-faq.component.css']
})
export class GestionFaqComponent implements OnInit {
  public faqs: Faq[];
  public faq: Faq;
  public form: FormGroup;
  public formUpdate: FormGroup;

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
    this.formUpdate = new FormGroup({
      id: new FormControl(),
      priorite: new FormControl(),
      question: new FormControl(),
      reponse: new FormControl()
    })
  }


/**
 * Methode qui permet de creer une Faq
 * @param form renvoit le formulaire
 */
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


  /**
   * préremplie le formulaire de modification avec les données de l'id selectionné
   * @param event 
   */
  dynForm(event) {
    console.log(this.faqs[event.target.selectedIndex]);
    this.formUpdate.controls['priorite'].setValue(this.faqs[event.target.selectedIndex].priority);
    this.formUpdate.controls['question'].setValue(this.faqs[event.target.selectedIndex].question);
    this.formUpdate.controls['reponse'].setValue(this.faqs[event.target.selectedIndex].response);
  }


/**
 * Methode qui permet de modifier une faq
 * @param form renvoie le formulaire
 */
  updateFaq(formUpdate: FormGroup) {
    this.faq = new Faq();
    this.faq.id = formUpdate.controls['id'].value;
    this.faq.priority = formUpdate.controls['priorite'].value;
    this.faq.question = formUpdate.controls['question'].value;
    this.faq.response = formUpdate.controls['reponse'].value;
    this.faqService.updateFaq(this.faq).subscribe(maj => {
      this.faqService.getFaqsList().subscribe((faqs: Faq[]) => {
        this.faqs = faqs
      });
    });
    this.form.reset();
  }

  
  /**
   * Methode qui permet de supprimer une faq
   * @param form renvoie le formulaire
   */
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
