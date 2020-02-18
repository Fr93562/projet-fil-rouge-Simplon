import { Component, OnInit } from '@angular/core';
import { Categorie } from 'src/app/shared/models/categorie';
import { CategorieService } from 'src/app/shared/services/categorie.service';
import { FormGroup, FormControl } from '@angular/forms';


@Component({
  selector: 'app-gestion-categories',
  templateUrl: './gestion-categories.component.html',
  styleUrls: ['./gestion-categories.component.css']
})
export class GestionCategoriesComponent implements OnInit {
  public categories: Categorie[];
  public categorie: Categorie;
  public form: FormGroup;

  constructor(private categorieService: CategorieService) { }

  ngOnInit() {
    this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
      this.categories = categories
    });

    this.form = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })
  }

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

  dynForm(event) {
    console.log(this.categories[event.target.selectedIndex]);
    this.form.controls['type'].setValue(this.categories[event.target.selectedIndex].type);
  }

  updateCategory(form: FormGroup) {
    this.categorie = new Categorie();
    this.categorie.id = form.controls['id'].value;
    this.categorie.type = form.controls['type'].value;
    this.categorieService.updateCategory(this.categorie).subscribe(maj => {
      this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
        this.categories = categories
      });
    });
    this.form.reset();
  }

}

