import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LangagesQuestionsComponent } from './langages-questions.component';

describe('LangagesQuestionsComponent', () => {
  let component: LangagesQuestionsComponent;
  let fixture: ComponentFixture<LangagesQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LangagesQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LangagesQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
