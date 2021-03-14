import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RecipesOverviewComponent} from "./routes/recipes-overview/recipes-overview.component";

const routes: Routes = [
  { path: '', redirectTo: '/recepty', pathMatch: 'full' },
  { path: 'recepty', component: RecipesOverviewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
