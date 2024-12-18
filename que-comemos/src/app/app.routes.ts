import { Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { RegistroComponent } from './components/registro/registro.component';
import { ClienteRegisterComponent } from './components/registro/cliente/cliente.component';
import { AdminRegisterComponent } from './components/registro/admin/admin.component';
import { ResponsableRegisterComponent } from './components/registro/responsable/responsable.component';
import { ExitoComponent } from './components/registro/exito/exito.component';
import { LoginComponent } from './components/login/login.component';
import { ComidaComponent } from './components/comida/comida.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'registro/cliente', component: ClienteRegisterComponent },
  { path: 'registro/responsable', component: ResponsableRegisterComponent },
  { path: 'registro/admin', component: AdminRegisterComponent },
  { path: 'registro/exito', component: ExitoComponent },
  { path: 'login', component: LoginComponent },
  { path: 'comidas', component: ComidaComponent },
  { path: '**', pathMatch: 'full', redirectTo: '' },
];
