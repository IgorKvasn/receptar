import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

const MAX_RATING = 5;

@Component({
  selector: 'app-recipe-rating',
  templateUrl: './recipe-rating.component.html',
  styleUrls: ['./recipe-rating.component.scss']
})
export class RecipeRatingComponent implements OnInit {

  @Input() rating: number;

  @Input() editable: boolean;

  @Output() onRatingChanged = new EventEmitter();

  hoverState = false;

  hoverRating: number = 0;

  ratingArray: number[] = [];

  constructor() {
    this.ratingArray = [...Array(MAX_RATING).keys()];
  }

  ngOnInit(): void {
  }

  get ratingStars(): number[] {
    return this.ratingArray.slice(0, this.rating);
  }

  get emptyStars(): number[] {
    return this.ratingArray.slice(this.rating);
  }

  starClicked(newRating: number): void {
    console.log(newRating);
  }

  mouseEnter(rating: number): void {
    this.hoverRating = rating;
  }

  mouseLeave(): void {
    this.hoverRating = 0;
  }

  mouseEnterRating(): void {
    if (this.editable === true) {
      this.hoverState = true;
    }
  }

  mouseLeaveRating(): void {
    this.hoverState = false;
  }
}
