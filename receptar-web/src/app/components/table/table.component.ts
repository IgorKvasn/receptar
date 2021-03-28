import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewEncapsulation} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {AutoUnsubscribe} from 'take-while-alive';

export interface TableColumn {
  label: string;
  width?: string;
  cssClass?: string;
}

export interface TableMetadata {
  hasNextPage: boolean;
  hasPreviousPage: boolean;
  totalPages: number;
  totalRows: number;
}

@AutoUnsubscribe()
@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TableComponent implements OnInit {

  @Input()
  columns: TableColumn[];

  @Input()
  rows: any[];

  @Input()
  rowTemplate: TemplateRef<any>;

  @Input()
  meta: BehaviorSubject<TableMetadata | null>;

  @Input()
  pagination: boolean = false;

  @Output()
  onRowClicked = new EventEmitter();

  @Output()
  onPageChanged = new EventEmitter();

  currentPage: number = 0;

  pagesArray: number[];

  constructor() {
  }

  ngOnInit(): void {
  }

  rowClicked(row: any): void {
    this.onRowClicked.emit(row);
  }

  nextPage() {
    if (this.meta.getValue()!.hasNextPage) {
      this.currentPage++;
      this.onPageChanged.emit(this.currentPage);
    }
  }

  previousPage() {
    if (this.meta.getValue()!.hasPreviousPage) {
      this.currentPage--;
      this.onPageChanged.emit(this.currentPage);
    }
  }
}


