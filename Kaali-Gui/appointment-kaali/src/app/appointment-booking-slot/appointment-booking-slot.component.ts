import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigServiceService } from '../config-service.service';

@Component({
  selector: 'app-appointment-booking-slot',
  templateUrl: './appointment-booking-slot.component.html',
  styleUrls: ['./appointment-booking-slot.component.css']
})
export class AppointmentBookingSlotComponent {
  constructor(private service: ConfigServiceService, private router: Router) { }
  ngOnInit() {
console.log(" service in slot component i s");
console.log(this.service);//singlton class so what i ssaved in previos hould availble in next too
  }
}
