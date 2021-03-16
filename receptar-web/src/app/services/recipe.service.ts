import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Recipe} from '../objects/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private httpClient: HttpClient) { }

  getRecipe(id: string): Observable<Recipe> {
    return this.httpClient.get<Recipe>(`/api/recipes/${id}`);
  }
}
