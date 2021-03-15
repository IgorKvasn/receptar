import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutHeaderComponent } from './components/layout-header/layout-header.component';
import { RecipesOverviewComponent } from './routes/recipes-overview/recipes-overview.component';
import { TableComponent } from './components/table/table.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RecipeRatingComponent } from './components/recipe-rating/recipe-rating.component';
import { RecipesDetailComponent } from './routes/recipes-detail/recipes-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutHeaderComponent,
    RecipesOverviewComponent,
    TableComponent,
    RecipeRatingComponent,
    RecipesDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
