import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AccueilComponent } from './accueil/accueil.component';
import { JouerComponent } from './jouer/jouer.component';
import { CompteComponent } from './compte/compte.component';
import { FaqComponent } from './faq/faq.component';
import { FooterComponent } from './footer/footer.component';
import { AppRouting } from './app.routing';
import { HttpClientModule }    from '@angular/common/http';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ColorDirective } from './shared/directives/color.directive';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    ColorDirective,
    FooterComponent,
    AccueilComponent,
    JouerComponent,
    CompteComponent,
    FaqComponent,
    
   
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
