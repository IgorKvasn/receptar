import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecipesOverviewComponent} from './routes/recipes-overview/recipes-overview.component';
import {RecipesDetailComponent} from './routes/recipes-detail/recipes-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/recepty', pathMatch: 'full'},
  {
    path: 'recepty', children: [
      {path: '', component: RecipesOverviewComponent},
      {path: ':id', component: RecipesDetailComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
