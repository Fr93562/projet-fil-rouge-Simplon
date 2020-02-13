import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionQuestionsComponent } from './gestion-questions.component';

describe('GestionQuestionsComponent', () => {
  let component: GestionQuestionsComponent;
  let fixture: ComponentFixture<GestionQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
