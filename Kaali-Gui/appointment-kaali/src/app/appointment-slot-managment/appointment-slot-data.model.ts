export class AppointmentSlotData {
    companyId:string="";
    company:string="";
    divisionId:string="";
    division:string="default";
    date:Date=new Date();
    slotid:number=0;
    slotSequance:number=0;
    slotStartTime:number=0.0;
    slotEndTime:number=0.0;
    availability:number=1;
    dmlFlag:number=0;//0 means query data from DB 1: insert (new data),2 update(query data modified) 3: delete(query data deleted )
}
