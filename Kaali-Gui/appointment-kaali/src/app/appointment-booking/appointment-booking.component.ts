import { NumberFormatStyle } from '@angular/common';
import { Component } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { withLatestFrom } from 'rxjs';
import { ConfigServiceService } from '../config-service.service';
@Component({
  selector: 'app-appointment-booking',
  templateUrl: './appointment-booking.component.html',
  styleUrls: ['./appointment-booking.component.css']
})
export class AppointmentBookingComponent {
  constructor(private service: ConfigServiceService, private router: Router) { }
  bmonth: number = 2;
  byear: number = 2023;
  currMonth: number = 1;
  currYear: number = 2000;
  nextYear: number = 2001;
  bookingMonthData: any[] = [];
  months: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  monthObj: { "name": String, "id": Number }[] = [{ "name": "Jan", "id": 1 }, { "name": "Feb", "id": 2 }, { "name": "Mar", "id": 3 }, { "name": "Apr", "id": 4 }, { "name": "May", "id": 5 }, { "name": "Jun", "id": 6 }, { "name": "Jul", "id": 7 }, { "name": "Aug", "id": 8 }, { "name": "Sep", "id": 9 }, { "name": "Oct", "id": 10 }, { "name": "Nov", "id": 11 }, { "name": "Dec", "id": 12 }];
  monthObjCurr: { "name": String, "id": Number }[] = [];
  monthAvaibility: MonthAvaibility = {};
  selectOption(ee: String) {
    console.log(ee);
  }

  callSlotComponent(ind: any) {
    this.service.month = this.bmonth;
    this.service.year = this.byear;
    this.service.dayStr = ind;
    console.log("ind....." + ind);
    this.router.navigate(['/baba/1']);
  }
  ngOnInit() {
    let currDate = new Date();
    this.currMonth = currDate.getMonth() + 1;
    this.currYear = currDate.getFullYear();
    this.byear = this.currYear;
   
    this.nextYear=this.currYear+1;
    this.bmonth= this.currMonth;
    for(let i=this.currMonth,j=0; i <=12;i++,j++){
  this.monthObjCurr[j]=this.monthObj[i-1];
    }
    // Get current month & year slot data
    this.callMonthWiseAvailability();
  }

  /**
   * This method call service to get moth wise availability at particular date
   */
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

  /**
   * This method will tell which date in particular months has available slot for booking
   */
  callMonthWiseAvailability() {
    let blog = { "month": this.bmonth, "year": this.byear };
    try {
      this.service.postBlog(blog, "kaali/appointmnet/findAllSlotByMonth").subscribe({
        next: (num) => {
          console.log("monthlyAvailability=");
          console.log(num);


          this.monthAvaibility = num;
          console.log(this.monthAvaibility.company);
          console.log("slotCountByDay=");
          console.log(
            this.monthAvaibility.slotCountByDay);
          /* this.monthAvaibility.slotCountByDay?.forEach( (element) => {
             console.log(element);
             if(element.availaibilty){
               console.log("111day.......sai="+element.availaibilty+" 11value...sai ="+element["availaibilty"]);
               
             }
             console.log("00day.......sai"+element.day+" 00value...sai "+element["availaibilty"]);
            
         
           })
           ;*/

        },
        error: (err) => {
          console.error(err)
          this.fillData();
        },
        complete: () => {
          console.log("response in complete");
          //.. this.router.navigate(['viewblogs'])
          this.fillData();
        }
      });
    } catch (e) {
      console.log(e);
    }
  }
  roles: { id: number, name: string }[] = [];
  roles2: { id: number, name: string }[] = [{ "id": 1, "name": "admin" }, { "id": 2, "name": "user" }];
  fillData() {
    //this.callMonthWiseAvailability();

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
    let availabilityData = 0;
    for (let i = 0; i < 6; i++) {
      availabilityData = 0;
      let iArr: DaysData[] = [];
      for (let j = 1; j <= 7; j++) {
        daydata = new DaysData();
        if (i == 0 && j < firstDay) {
          //console.log("E "+i+"j ="+j);
          daydata.day = "..";
          daydata.availaibilty = 0;
          //..iArr.push(daydata);
          iArr.push({ "day": "..", "availaibilty": 0 });
        } else {




          if (dayNumber > totalDays) {
            daydata.day = "..";
            daydata.availaibilty = 0;
            //..iArr.push(daydata);
            iArr.push({ "day": "..", "availaibilty": 0 });

          } else {
            daydata.day = ".." + dayNumber;
            daydata.availaibilty = 0;
            // iArr.push(daydata);

            //  if(this.monthAvaibility.slotCountByDay?.day && ""+dayNumber==this.monthAvaibility.slotCountByDay?.day ){
            //   availabilityData=this.monthAvaibility.slotCountByDay?.availaibilty;
            //  }
            console.log()
            //iArr.push({"day":".."+dayNumber,"availability":availabilityData});
            availabilityData = this.getAvailability(dayNumber);
            iArr.push({ "day": "" + dayNumber, "availaibilty": availabilityData });
            if (availabilityData != 0) {
              console.log("non zero availabilty data");
              console.log(iArr);
            }
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

  /**
   * This method is used to fill in particultar claender days what is avail;abilty 
   * @param day 
   * @returns 
   */
  getAvailability(day: number): number {
    //if(this.monthAvaibility && this.monthAvaibility.slotCountByDay){
    // console.log(" Inside getAvailability call sai baba.........>>>>>");
    // console.log(this.monthAvaibility.slotCountByDay);
    // console.log(this.monthAvaibility);
    console.log(" Inside getAvailability call sai baba.........<<<<<<");
    let dataArray: DaysData[] = [];

    let availaibilty99: number = 0;

    this.monthAvaibility.slotCountByDay?.forEach((element) => {

      //console.log("Inside loop 99999");
      //console.log(element);
      let daySTr = "" + day;
      //console.log("99..day.......sai"+element.day+" 99value...sai "+element.availaibilty +" daystr"+daySTr);


      if (daySTr == element.day) {
        availaibilty99 = element.availaibilty || 0;
        console.log(" matched...99... for " + daySTr);
      } else {

      }
    })
    if (availaibilty99 > 0) {
      console.log(" matched...99... for  = " + availaibilty99);
    }

    return availaibilty99;
  }

}
class DaysData {

  day?: string = "";
  availaibilty?: number = 0;
  availability1?: string = "";
}

class MonthAvaibility {
  day?: string = "";
  company?: string = "";
  //availability?: number=0;
  slotCountByDay?: DaysData[] = []

}
