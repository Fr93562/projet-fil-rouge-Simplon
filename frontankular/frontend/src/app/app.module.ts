import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AccueilComponent } from './accueil/accueil.component';
import { JouerComponent } from './jouer/jouer.component';
import { FaqComponent } from './faq/faq.component';
import { FooterComponent } from './footer/footer.component';
import { AppRouting } from './app.routing';
import { HttpClientModule }    from '@angular/common/http';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ColorDirective } from './shared/directives/color.directive';
import { AdminComponent } from './admin/admin.component';
import { CompteComponent } from './compte/compte.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { LangagesQuestionsComponent } from './admin/langages-questions/langages-questions.component';


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
    LangagesQuestionsComponent
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
