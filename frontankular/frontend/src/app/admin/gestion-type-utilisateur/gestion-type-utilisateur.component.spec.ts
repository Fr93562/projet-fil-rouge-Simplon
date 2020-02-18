import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionTypeUtilisateurComponent } from './gestion-type-utilisateur.component';

describe('GestionTypeUtilisateurComponent', () => {
  let component: GestionTypeUtilisateurComponent;
  let fixture: ComponentFixture<GestionTypeUtilisateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionTypeUtilisateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionTypeUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
