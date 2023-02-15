package aum.kaali.demo.se;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aum.kaali.demo.bo.Employee;
import aum.kaali.demo.bo.SloReservationData;
import aum.kaali.demo.bo.SlotData;
import aum.kaali.demo.bo.SlotDataIn;

@RestController
@RequestMapping("/kaali/appointmnet")
public class AppointmentBookingRest {
	 @GetMapping("/employees")
	  List<Employee> all() {
	    return null;
	  }
	  // end::get-aggregate-root[]
	 @GetMapping("/sai")
	  public String allSai() {
	    return "Jai sai baba ki";
	  }
	  @PostMapping("/employees")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
	    return newEmployee;
	  }
	  
	  public void slotDataAdd(SlotDataIn in) {
		  
	  }
	  
	  public SlotDataIn slotDataModify(SlotDataIn in) {
		  return in;
	  }
 
	  public Integer slotDataDelete(SlotDataIn in) {
		  return 0;
	  }
	  
	  public Boolean reserveSlot(SloReservationData in) {
		  return true;
	  }
	  
	  public SloReservationData reserveSlotUpdate(SloReservationData in) {
		  return in;
	  }
	  public Boolean slotMetedataClone(Date date) {
		  return true;
	  }
	  
	  public List<SlotData> getSlotData(Date date){
		  return null;
	  }
	  public List<SlotData> getSlotDataByServiceGuy(Date date){
		  return null;
	  }
}
