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
@NgModule({
  declarations: [
    AppComponent,
    AppointmentBookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot([
      { path: 'sai', component: AppointmentBookingComponent },
      { path: 'baba/:productId', component: AppointmentBookingComponent },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
