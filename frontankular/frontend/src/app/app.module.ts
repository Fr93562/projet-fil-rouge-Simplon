import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AccueilComponent } from './accueil/accueil.component';
import { JouerComponent } from './jouer/jouer.component';
import { FaqComponent } from './faq/faq.component';
import { FooterComponent } from './footer/footer.component';
import { AppRouting } from './app.routing';
import { HttpClientModule } from '@angular/common/http';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ColorDirective } from './shared/directives/color.directive';
import { AdminComponent } from './admin/admin.component';
import { CompteComponent } from './compte/compte.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { GestionFaqComponent } from './admin/gestion-faq/gestion-faq.component';
import { GestionUtilisateursComponent } from './admin/gestion-utilisateurs/gestion-utilisateurs.component';
import { GestionQuestionsComponent } from './admin/gestion-questions/gestion-questions.component';
import { GestionRessourcesComponent } from './admin/gestion-ressources/gestion-ressources.component';
import { GestionLangagesComponent } from './admin/gestion-langages/gestion-langages.component';
import { GestionTypeUtilisateurComponent } from './admin/gestion-type-utilisateur/gestion-type-utilisateur.component';
import { GestionCategoriesComponent } from './admin/gestion-categories/gestion-categories.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { RgpdComponent } from './rgpd/rgpd.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ColorDirective,
    FooterComponent,
    AccueilComponent,
    JouerComponent,
    FaqComponent,
    AdminComponent,
    CompteComponent,
    ConnexionComponent,
    GestionFaqComponent,
    GestionUtilisateursComponent,
    GestionQuestionsComponent,
    GestionRessourcesComponent,
    GestionLangagesComponent,
    GestionTypeUtilisateurComponent,
    GestionCategoriesComponent,
    InscriptionComponent,
    RgpdComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRouting,
    HttpClientModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
