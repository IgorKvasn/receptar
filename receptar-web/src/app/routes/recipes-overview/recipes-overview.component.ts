import {Component, OnInit} from '@angular/core';
import {TableColumn, TableMetadata} from '../../components/table/table.component';
import {HttpClient} from '@angular/common/http';
import {Recipe} from '../../objects/recipe';

@Component({
  selector: 'app-recipes-overview',
  templateUrl: './recipes-overview.component.html',
  styleUrls: ['./recipes-overview.component.scss']
})
export class RecipesOverviewComponent implements OnInit {
  public columns: TableColumn[] = [
    {
      columnCode: 'name',
      label: 'Názov'
    },
    {
      columnCode: 'createDate',
      label: 'Dátum'
    },
    {
      columnCode: 'rating',
      label: 'Hodnotenie'
    }
  ];

  public data: Recipe[];
  public meta: TableMetadata;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.httpClient.get<{data: Recipe[], meta: TableMetadata} >('/api/recipes?page=0').subscribe((response)=>{
      this.data = response.data;
      this.meta = response.meta;
    });
  }

}
