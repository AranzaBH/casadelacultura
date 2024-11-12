import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TallerComponent } from './taller.component';

describe('TallerComponent', () => {
  let component: TallerComponent;
  let fixture: ComponentFixture<TallerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TallerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TallerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
