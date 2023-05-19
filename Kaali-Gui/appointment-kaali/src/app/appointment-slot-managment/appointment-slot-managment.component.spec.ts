import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentSlotManagmentComponent } from './appointment-slot-managment.component';

describe('AppointmentSlotManagmentComponent', () => {
  let component: AppointmentSlotManagmentComponent;
  let fixture: ComponentFixture<AppointmentSlotManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentSlotManagmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentSlotManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
