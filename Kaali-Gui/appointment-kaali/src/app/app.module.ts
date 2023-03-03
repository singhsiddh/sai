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

@NgModule({
  declarations: [
    AppComponent,
    AppointmentBookingComponent,
    AppointmentBookingSlotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    RouterModule.forRoot([
      { path: 'sai', component: AppointmentBookingComponent },
      { path: 'baba/:productId', component: AppointmentBookingSlotComponent },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
