import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Categorie } from 'src/app/shared/models/categorie';
import { CategorieService } from 'src/app/shared/services/categorie.service';



/**
 * Gestion des categories
 */
@Component({
  selector: 'app-gestion-categories',
  templateUrl: './gestion-categories.component.html',
  styleUrls: ['./gestion-categories.component.css']
})
export class GestionCategoriesComponent implements OnInit {
  public categories: Categorie[];
  public categorie: Categorie;
  public form: FormGroup;
  public formUpdate: FormGroup;

  constructor(private categorieService: CategorieService) { }

  ngOnInit() {
    this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
      this.categories = categories
    });
    this.form = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })
    this.formUpdate = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })
  }

    /**
 * Methode qui permet de creer une categorie
 * @param form renvoie le formulaire
 */
  postCategory(form: FormGroup) {
    this.categorie = new Categorie();
    this.categorie.id = null;
    this.categorie.type = form.controls['type'].value;
    this.categorieService.createCategory(this.categorie).subscribe(maj => {
      this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
        this.categories = categories
      });
    });
    this.form.reset();
  }


   /**
  * préremplie le formulaire de modification avec les données de l'id selectionné
  * @param event 
  */
  dynForm(event) {
    this.formUpdate.controls['type'].setValue(this.categories[event.target.selectedIndex].type);
  }


  /**
 * Methode qui permet de modifier une categorie
 * @param form renvoie le formulaire
 */
  updateCategory(formUpdate: FormGroup) {
    this.categorie = new Categorie();
    this.categorie.id = formUpdate.controls['id'].value;
    this.categorie.type = formUpdate.controls['type'].value;
    this.categorieService.updateCategory(this.categorie).subscribe(maj => {
      this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
        this.categories = categories
      });
    });
    this.form.reset();
  }

}

