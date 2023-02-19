package aum.kaali.demo.se;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aum.kaali.demo.bo.Employee;
import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotData;
import aum.kaali.demo.bo.SlotMetadata;
import aum.kaali.demo.so.AppointmentBookingService;

@RestController
@RequestMapping("/kaali/appointmnet")
public class AppointmentBookingRest {
	@Autowired
	private AppointmentBookingService service;

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

	@PostMapping("/slotMetaDataAdd")
	public void slotMetaDataAdd(@RequestBody SlotMetadata data) {
		service.addAppointmrntMetaData(data);
	}

	public SlotMetadata slotMetaDataModify(@RequestBody SlotMetadata in) {
		return in;
	}

	public Integer slotMetaDataDelete(@RequestBody SlotMetadata in) {
		return 0;
	}

	public Boolean reserveSlot(@RequestBody SlotReservationData in) {
		return true;
	}

	@PostMapping("/slotReservation")
	public SlotReservationData reserveSlotUpdate(@RequestBody SlotReservationData in) {
		service.addAppoirntment(in);
		return in;
	}

	public Boolean slotMetedataClone(@RequestBody Date date) {
		return true;
	}

	public List<SlotData> getSlotData(Date date) {
		return null;
	}

	public List<SlotData> getSlotDataByServiceGuy(Date date) {
		return null;
	}
}
