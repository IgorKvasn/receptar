import {Ingredient} from './ingredient';

export interface Recipe {
  name: string;
  description: string;
  ingredients: Ingredient[];
  tags: string[];
  createDate?: number;
  rating: number;
  id?: string;
}
