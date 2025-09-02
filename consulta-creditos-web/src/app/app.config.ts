// src/app/app.config.ts
import { ApplicationConfig, provideZoneChangeDetection, LOCALE_ID } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

// registra locale PT-BR para DatePipe/CurrencyPipe/etc.
registerLocaleData(localePt);

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(withInterceptorsFromDi()),
    { provide: LOCALE_ID, useValue: 'pt-BR' } // <- garante formatação BRL e datas PT-BR
  ]
};
