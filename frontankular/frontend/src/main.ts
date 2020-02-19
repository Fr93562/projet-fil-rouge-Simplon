import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

// Initialise la session storage token
if(sessionStorage.getItem('token') == null) {
  
  sessionStorage.setItem('token', '');
  sessionStorage.setItem('username', '');
}