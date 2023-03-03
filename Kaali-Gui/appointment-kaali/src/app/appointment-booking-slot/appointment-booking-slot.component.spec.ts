import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentBookingSlotComponent } from './appointment-booking-slot.component';

describe('AppointmentBookingSlotComponent', () => {
  let component: AppointmentBookingSlotComponent;
  let fixture: ComponentFixture<AppointmentBookingSlotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentBookingSlotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentBookingSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
