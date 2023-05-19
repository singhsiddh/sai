import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppointmentBookingComponent } from './appointment-booking/appointment-booking.component';
import { RouterModule } from '@angular/router';
import {MatSelectModule} from '@angular/material/select';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppointmentBookingSlotComponent } from './appointment-booking-slot/appointment-booking-slot.component';
import { AppointmentSlotManagmentComponent } from './appointment-slot-managment/appointment-slot-managment.component';
import { ReactiveFormsModule } from '@angular/forms';
//import { AppointmentSlotManagmentComponent } from './appointment-slot-managment/appointment-slot-managment.component';


@NgModule({
  declarations: [
    AppComponent,
    AppointmentBookingComponent,
    AppointmentBookingSlotComponent,
    AppointmentSlotManagmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
    RouterModule.forRoot([
      { path: 'sai', component: AppointmentBookingComponent },
      { path: 'baba/:productId', component: AppointmentBookingSlotComponent },
      { path: 'slot', component: AppointmentSlotManagmentComponent },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
