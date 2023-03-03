import { Component } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { ConfigServiceService } from '../config-service.service';
@Component({
  selector: 'app-appointment-booking',
  templateUrl: './appointment-booking.component.html',
  styleUrls: ['./appointment-booking.component.css']
})
export class AppointmentBookingComponent {
  constructor(private service: ConfigServiceService, private router: Router) { }
  bmonth = 2;
  byear = 2023;
  bookingMonthData:any [] = [];
  months: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  monthAvaibility = {};
  selectOption(ee: String) {
    console.log(ee);
  }
  callData() {

    let blog = { "date": "2023-02-24", "slotDate": "2023-02-24", "slotId": "2" };

    this.service.postBlog(blog, "kaali/appointmnet/findAppointmentSlotDataByDate").subscribe({
      next: (num) => {
        console.log(num);
      },
      error: (err) => { console.error(err) },
      complete: () => {
        console.log("response in complete");
        //.. this.router.navigate(['viewblogs'])
      }
    });
  }
  callMonthWiseAvailability() {
    let blog = { "month": this.bmonth, "year": this.byear };

    this.service.postBlog(blog, "kaali/appointmnet/findAllSlotByMonth").subscribe({
      next: (num) => {
        console.log(num);
        this.monthAvaibility = num;
      },
      error: (err) => { console.error(err) },
      complete: () => {
        console.log("response in complete");
        //.. this.router.navigate(['viewblogs'])
      }
    });

  }
  roles: { id: number, name: string }[] = [];
  roles2: { id: number, name: string }[] = [{"id":1,"name":"admin"},{"id":2,"name":"user"}];
  fillData() {
    this.callMonthWiseAvailability();
    this.bookingMonthData = [];
    console.log("fill data started");
    let firstDate = new Date(this.byear, this.bmonth - 1, 1);
    let lastDate = new Date(this.byear, this.bmonth, 0);
    let totalDays = lastDate.getDate();
    let firstDay = firstDate.getDay();//1 Mon , 2 : Tues ,...7:Sunday
    let dayCounter = firstDay;
    console.log("fill data started total days " + totalDays + " day count" + firstDay);
    let dayNumber = 1;

  let daydata: DaysData;
 // daydata= new DaysData();
    for (let i = 0; i < 6; i++) {
      let iArr: DaysData[] = [];
      for (let j = 1; j <= 7; j++) {
        daydata = new DaysData();
        if (i == 0 && j < firstDay) {
          //console.log("E "+i+"j ="+j);
          daydata.day="..";
          daydata.availability=0;
          //..iArr.push(daydata);
          iArr.push({"day":"..","availability":0});
        } else {




          if (dayNumber > totalDays) {
            daydata.day="..";
          daydata.availability=0;
          //..iArr.push(daydata);
          iArr.push({"day":"..","availability":0});
            
          } else {
            daydata.day=".."+dayNumber;
          daydata.availability=0;
         // iArr.push(daydata);
          iArr.push({"day":".."+dayNumber,"availability":0});
            dayNumber++;
          }
        }

      }
      this.bookingMonthData.push(iArr);
    }
    console.log("Bokking Data");
    console.log(this.bookingMonthData);
    //window.alert(this.bookingMonthData);
  }
}
class DaysData {
  
  day?:  string="";
  availability?: number=0;
}