import { Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { RegistroComponent } from './components/registro/registro.component';
import { ClienteComponent } from './components/registro/cliente/cliente.component';
import { AdminComponent } from './components/registro/admin/admin.component';
import { ResponsableComponent } from './components/registro/responsable/responsable.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'registro/cliente', component: ClienteComponent },
  { path: 'registro/responsable', component: ResponsableComponent },
  { path: 'registro/admin', component: AdminComponent },
];
