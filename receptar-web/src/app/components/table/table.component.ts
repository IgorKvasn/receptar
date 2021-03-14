import {Component, Input, OnInit, TemplateRef} from '@angular/core';


export interface TableColumn {
  label: string;
  columnCode: string;
}

export interface TableMetadata {
  hasNextPage: boolean;
  hasPreviousPage: boolean;
  totalPages: number;
  totalRows: number;
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  @Input()
  columns: TableColumn[];

  @Input()
  rows: any[];

  @Input()
  rowTemplate: TemplateRef<any>;

  @Input()
  meta?: TableMetadata;

  constructor() {
  }

  ngOnInit(): void {

  }

}


