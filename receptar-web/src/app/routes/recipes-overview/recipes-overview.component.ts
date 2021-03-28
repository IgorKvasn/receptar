import {Component, OnInit} from '@angular/core';
import {TableColumn, TableMetadata} from '../../components/table/table.component';
import {HttpClient} from '@angular/common/http';
import {Recipe} from '../../objects/recipe';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {DialogService} from '../../services/dialog.service';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import { skip } from 'rxjs/operators';

@Component({
  selector: 'app-recipes-overview',
  templateUrl: './recipes-overview.component.html',
  styleUrls: ['./recipes-overview.component.scss']
})
@UntilDestroy()
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
  public meta: BehaviorSubject<TableMetadata | null> = new BehaviorSubject<TableMetadata | null>(null);

  filterShown = false;
  openedIngrediendsDialog = new BehaviorSubject(false);

  constructor(private httpClient: HttpClient, private router: Router, private dialogService: DialogService) {
    this.openedIngrediendsDialog.pipe(skip(1)).pipe(untilDestroyed(this)).subscribe((value) => {
      if (value) {
        this.dialogService.dialogOpened();
      } else {
        this.dialogService.dialogClosed();
      }
    });
  }

  ngOnInit(): void {
    this.fetchPage(0);
  }

  onRecipeSelected(recipe: Recipe): void {
    this.router.navigateByUrl(`/recepty/${recipe.id}`);
  }

  fetchPage(page: number) {
    this.httpClient.get<{ data: Recipe[], meta: TableMetadata }>(`/api/recipes?page=${page}&pageSize=2`).subscribe((response) => {
      this.data = response.data;
      this.meta.next(response.meta);
    });
  }

  openIngrediendsDialog() {
    this.openedIngrediendsDialog.next(!this.openedIngrediendsDialog.value);
  }

  closeIngrediendsDialog() {
    this.openedIngrediendsDialog.next(false);
  }
}
