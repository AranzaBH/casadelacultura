import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearTallerComponent } from './crear-taller.component';

describe('CrearTallerComponent', () => {
  let component: CrearTallerComponent;
  let fixture: ComponentFixture<CrearTallerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrearTallerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearTallerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
