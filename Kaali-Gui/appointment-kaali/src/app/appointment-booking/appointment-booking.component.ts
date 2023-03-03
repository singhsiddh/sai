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
  monthAvaibility :MonthAvaibility= {};
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
        console.log("monthlyAvailability=");
        console.log(num);
        

        this.monthAvaibility = num;
        console.log(this.monthAvaibility.company); 
        console.log("slotCountByDay=");
        console.log(
        this.monthAvaibility.slotCountByDay);
        console.log(
          this.monthAvaibility.slotCountByDay?.[3]);
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
 let availabilityData=0;
    for (let i = 0; i < 6; i++) {
      availabilityData=0;
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

         if(this.monthAvaibility.slotCountByDay?.[1] && dayNumber==1 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[1];
         }else if(this.monthAvaibility.slotCountByDay?.[2] && dayNumber==2 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[2];
         
         
        }else if(this.monthAvaibility.slotCountByDay?.[3] && dayNumber==3 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[3];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[4] && dayNumber==4 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[4];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[5] && dayNumber==5 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[5];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[6] && dayNumber==6 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[6];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[7] && dayNumber==7 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[7];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[8] && dayNumber==8 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[8];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[9] && dayNumber==9 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[9];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[10] && dayNumber==10 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[10];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[11] && dayNumber==1 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[11];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[12] && dayNumber==12 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[12];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[13] && dayNumber==13 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[13];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[14] && dayNumber==14 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[14];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[15] && dayNumber==15 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[15];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[16] && dayNumber==16 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[16];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[17] && dayNumber==17 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[17];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[18] && dayNumber==18 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[18];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[19] && dayNumber==19 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[19];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[20] && dayNumber==20 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[20];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[21] && dayNumber==21 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[21];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[22] && dayNumber==22 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[22];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[23] && dayNumber==23 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[23];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[24] && dayNumber==24 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[24];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[25] && dayNumber==25 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[25];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[26] && dayNumber==26 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[26];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[27] && dayNumber==27 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[27];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[28] && dayNumber==28 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[28];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[29] && dayNumber==29 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[29];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[30] && dayNumber==30 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[30];
         
         }
         else if(this.monthAvaibility.slotCountByDay?.[31] && dayNumber==31 ){
          availabilityData=this.monthAvaibility.slotCountByDay?.[31];
         
         }









          iArr.push({"day":".."+dayNumber,"availability":availabilityData});
          if(availabilityData !=0){
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
}
class DaysData {
  
  day?:  string="";
  availability?: number=0;
}

class MonthAvaibility{
  day?:  string="";
  company?:  string="";
  availability?: number=0;
  slotCountByDay ?:{1:number,2:number,3:number,4:number,5:number,6:number,7:number,8:number,9:number,10:number,
    11:number,12:number,13:number,14:number,15:number,16:number,17:number,18:number,19:number,20:number,
    21:number,22:number,23:number,24:number,25:number,26:number,27:number,28:number,29:number,30:number,31:number
  
  };

}