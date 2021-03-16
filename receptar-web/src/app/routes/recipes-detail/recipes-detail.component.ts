import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RecipeService} from '../../services/recipe.service';
import {switchMap} from 'rxjs/operators';
import {Recipe} from '../../objects/recipe';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-recipes-detail',
  templateUrl: './recipes-detail.component.html',
  styleUrls: ['./recipes-detail.component.scss']
})
export class RecipesDetailComponent implements OnInit {

  recipe$: Observable<Recipe>;

  constructor(private route: ActivatedRoute, private recipeService: RecipeService) {
  }

  ngOnInit(): void {
    this.recipe$ = this.route.paramMap.pipe(
      switchMap((params) => {
        return this.recipeService.getRecipe(params.get('id')!);
      })
    );
  }

}
