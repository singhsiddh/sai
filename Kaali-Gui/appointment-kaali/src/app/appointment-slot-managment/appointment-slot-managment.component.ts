import { Component } from '@angular/core';
import { AppointmentSlotData } from './appointment-slot-data.model';
import { FormGroup,FormBuilder,FormControl,Validators } from '@angular/forms';


@Component({
  selector: 'app-appointment-slot-managment',
  templateUrl: './appointment-slot-managment.component.html',
  styleUrls: ['./appointment-slot-managment.component.css']
})
export class AppointmentSlotManagmentComponent {
slotFormData:AppointmentSlotData=new AppointmentSlotData() ;

isNextDisabled=false;
slotTable:AppointmentSlotData[]=[];
constructor(private fb:FormBuilder){
 
}

slotForm=this.fb.group({
  companyId:[],
  company:[''],
  divisionId:[],
  division:["default"],
  date:['',Validators.required],
  start:[''],
  slotid:[],
  slotSequance:[],
  slotStartTime:['9',Validators.required],
  slotEndTime:[],
  availability:[1,Validators.required]
})||{};

dateChangeHandler(date: Date){
  const stringDate: string = `${date.getMonth() + 1}/${date.getDate()}/${date.getFullYear()}`;
 if( this.slotForm.get('date')){
  
 //........... this.slotForm.get('date').setValue(stringDate);
}

}


}
