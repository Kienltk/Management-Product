// app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ViewListComponent } from './view-list/view-list.component';
import { CreateComponent } from './create/create.component';
import { EditComponent } from './edit/edit.component';
import { DetailComponent } from './detail/detail.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'list', component: ViewListComponent },
  { path: 'products/create', component: CreateComponent },
  { path: 'products/edit/:id', component: EditComponent },
  { path: 'products/detail/:id/:hrefParam', component: DetailComponent },
  { path: '**', redirectTo: '' }, 
];
