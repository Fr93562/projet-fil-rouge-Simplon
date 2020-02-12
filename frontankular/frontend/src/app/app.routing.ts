import { Route, RouterModule } from '@angular/router'

import { AccueilComponent } from './accueil/accueil.component';
import { JouerComponent } from './jouer/jouer.component';
import { FaqComponent } from './faq/faq.component';
import { AdminComponent } from './admin/admin.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { CompteComponent } from './compte/compte.component';

const APP_ROUTE: Route[] = [
    { path: '', component: AccueilComponent },
    { path: 'jouer', component: JouerComponent },
    { path: 'faq', component: FaqComponent },
    { path: 'connexion', component: ConnexionComponent},
    { path: 'connexion/compte', component: CompteComponent },
    { path: 'connexion/compte/admin', component: AdminComponent}
        
];

export const AppRouting = RouterModule.forRoot(APP_ROUTE);