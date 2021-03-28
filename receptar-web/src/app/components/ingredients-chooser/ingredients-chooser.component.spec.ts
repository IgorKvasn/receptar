import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientsChooserComponent } from './ingredients-chooser.component';

describe('IngredientsChooserComponent', () => {
  let component: IngredientsChooserComponent;
  let fixture: ComponentFixture<IngredientsChooserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngredientsChooserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientsChooserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
