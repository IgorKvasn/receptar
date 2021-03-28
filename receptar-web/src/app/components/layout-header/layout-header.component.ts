import {Component, OnInit} from '@angular/core';

const SIDE_MENU_WIDTH = 250; // in px

@Component({
  selector: 'app-layout-header',
  templateUrl: './layout-header.component.html',
  styleUrls: ['./layout-header.component.scss']
})
export class LayoutHeaderComponent implements OnInit {

  constructor() {
  }

  public navbarOpened = false;

  ngOnInit(): void {
  }

  onBurgerClick() {
    this.navbarOpened = !this.navbarOpened;
  }
}
