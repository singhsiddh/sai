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
  slotdata:SlotData[]= [];
  ngOnInit() {
console.log(" service in slot component i s");
console.log(this.service);//singlton class so what i ssaved in previos hould availble in next too
let dt = new Date(this.service.year,this.service.month-1,parseInt(""+this.service.dayStr,10));
let blog = { "date": dt, "slotDate":dt, "slotId": "2" };
console.log("date"+dt);
this.service.postBlog(blog, "kaali/appointmnet/findAppointmentSlotDataByDate").subscribe({
  next: (num) => {
    console.log("Slot data .....................");
    console.log(num);
    this.slotdata=<SlotData[]>num;
  },
  error: (err) => { console.error(err) },
  complete: () => {
    console.log("response in complete");
    //.. this.router.navigate(['viewblogs'])
  }
});
}

slotBooking(date:Date,slotId:number){
console.log(" date"+date+" slotId="+slotId);
}
}
export class SlotData{
  date ?:Date=new Date();
  slotId ?:number=0;
  slotStartTime ?: number;
  available?: number=0;
slotEndTime ?:number =0;
}