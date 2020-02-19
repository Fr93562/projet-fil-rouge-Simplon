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
  public formupdate: FormGroup;

  constructor(private categorieService: CategorieService) { }

  ngOnInit() {
    this.categorieService.getCategoryList().subscribe((categories: Categorie[]) => {
      this.categories = categories
    });

    this.form = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })
    this.formupdate = new FormGroup({
      id: new FormControl(),
      type: new FormControl()
    })

  }

  postCategory(form: FormGroup) {
    this.categorie = new Categorie();
    this.categorie.id = null;
    this.categorie.type = form.controls['type'].value;
    this.categorieService.createCategory(this.categorie).subscribe();
    this.form.reset();
  }

  dynForm(event) {
    console.log(this.categories[event.target.selectedIndex]);
    this.formupdate.controls['type'].setValue(this.categories[event.target.selectedIndex].type);
  }

  updateCategory(formupdate: FormGroup) {
    this.categorie;
    this.categorie.id = formupdate.controls['id'].value;
    this.categorie.type = formupdate.controls['type'].value;
    this.categorieService.updateCategory(this.categorie).subscribe();
    this.form.reset();
  }

  deleteCategory(form: FormGroup) {
    this.categorie;
    this.categorie.id = form.controls['id'].value;
    this.categorieService.deleteCategory(this.categorie).subscribe();
    this.form.reset();
  }

}

