import {Component, Input, OnInit} from '@angular/core';

const MAX_RATING = 5;

@Component({
  selector: 'app-recipe-rating',
  templateUrl: './recipe-rating.component.html',
  styleUrls: ['./recipe-rating.component.scss']
})
export class RecipeRatingComponent implements OnInit {

  @Input() rating: number;

  constructor() {
  }

  ngOnInit(): void {
  }

  get ratingStars(): number[] {
    return Array(this.rating).fill(1);
  }

  get emptyStars(): number[] {
    return Array(MAX_RATING - this.rating).fill(2);
  }

}
