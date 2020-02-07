import { Route, RouterModule } from '@angular/router'

import { AccueilComponent } from './accueil/accueil.component';
import { JouerComponent } from './jouer/jouer.component';
import { CompteComponent } from './compte/compte.component';
import { FaqComponent } from './faq/faq.component';

const APP_ROUTE: Route[] = [
    { path: '', component: AccueilComponent },
    { path: 'jouer', component: JouerComponent },
    { path: 'compte', component: CompteComponent },
    { path:'faq', component: FaqComponent },
] 

export const AppRouting = RouterModule.forRoot(APP_ROUTE);