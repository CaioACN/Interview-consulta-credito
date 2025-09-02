import { Routes } from '@angular/router';
import { ConsultaComponent } from './features/consulta/consulta.component';
import { HomeComponent } from './features/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'consulta', component: ConsultaComponent },
  { path: '**', redirectTo: '' }
];