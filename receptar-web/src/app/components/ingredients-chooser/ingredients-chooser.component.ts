import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-ingredients-chooser',
  templateUrl: './ingredients-chooser.component.html',
  styleUrls: ['./ingredients-chooser.component.scss']
})
export class IngredientsChooserComponent implements OnInit {

  @Input()
  openedDialog: BehaviorSubject<boolean>;

  @Output()
  closeDialog = new EventEmitter();

  constructor() {
  }

  ngOnInit(): void {
  }

  actionCloseDialog() {
    this.closeDialog.emit(false);
  }
}
