import {Component, OnInit} from '@angular/core';
import {TableColumn, TableMetadata} from '../../components/table/table.component';
import {HttpClient} from '@angular/common/http';
import {Recipe} from '../../objects/recipe';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipes-overview',
  templateUrl: './recipes-overview.component.html',
  styleUrls: ['./recipes-overview.component.scss']
})
export class RecipesOverviewComponent implements OnInit {
  public columns: TableColumn[] = [
    {
      label: 'Názov',
      cssClass: 'name-column'
    },
    {
      label: 'Dátum',
      width: '200px'
    },
    {
      label: 'Hodnotenie',
      width: '200px'
    }
  ];

  public data: Recipe[];
  public meta: TableMetadata;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.httpClient.get<{ data: Recipe[], meta: TableMetadata }>('/api/recipes?page=0').subscribe((response) => {
      this.data = response.data;
      this.meta = response.meta;
    });
  }

  onRecipeSelected(recipe: Recipe): void {
    this.router.navigateByUrl(`/recepty/${recipe.id}`);
  }
}
