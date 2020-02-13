import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionFaqComponent } from './gestion-faq.component';

describe('GestionFaqComponent', () => {
  let component: GestionFaqComponent;
  let fixture: ComponentFixture<GestionFaqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionFaqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
