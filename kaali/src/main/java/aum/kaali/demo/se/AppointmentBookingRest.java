package aum.kaali.demo.se;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import aum.kaali.demo.bo.Employee;
import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.so.AppointmentBookingService;

@RestController
@RequestMapping("/kaali/appointmnet")
public class AppointmentBookingRest {
	@Autowired
	private AppointmentBookingService service;
	@Autowired
	DataSource dataSource;

	@Autowired
	MongoClient mongoClient;
	//@Autowired
	//MongoDatabase database ;
		
		
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
	public void slotMetaDataAdd(@RequestBody AppointmentMetadata data) {
		service.addAppointmrntMetaData(data);
	}

	public AppointmentMetadata slotMetaDataModify(@RequestBody AppointmentMetadata in) {
		return in;
	}

	public Integer slotMetaDataDelete(@RequestBody AppointmentMetadata in) {
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

	@PostMapping("/findBySlotDate")
	public AppointmentMetadata findBySlotDate(@RequestBody AppointmentMetadata appointmentMetadata) {
		System.out.println("date ..."+appointmentMetadata.getSlotDate());
		
		AppointmentMetadata meta= service.findBySlotDate(appointmentMetadata.getSlotDate());
		System.out.println("meata ..."+meta);
		
			System.out.println("dataSource="+dataSource+" mongoClient="+mongoClient);
		
		return meta;
	}

	@PostMapping("/findBySlotDateANDSlotId")
	public SlotReservationData findBySlotDateANDSlotId(@RequestBody SlotReservationData in) {
		return service.findBySlotDateANDSlotId(in.getSlotDate(), in.getSlotId());
	}
	

	@PostMapping("/findBySlotId")
	public SlotReservationData findBySlotId(@RequestBody SlotReservationData in) {
		return service.findBySlotId( in.getSlotId());
	}

	@PostMapping("/findAllBySlotDate")
	public List<SlotReservationData> findAllBySlotDate(@RequestBody SlotReservationData in){
		return service.findAllBySlotDate(in.getSlotDate());
	}
	
	@PostMapping("/addAppointmentSlotData")
	public void addAppointmentSlotData(@RequestBody AppointmentSlotData data) {
		
		service.addAppointmentSlotData(data);
	}

	@PostMapping("/findAppointmentSlotDataByDate")
	public List<AppointmentSlotData> findAppointmentSlotDataByDate(@RequestBody AppointmentSlotData data) {
		return service.findAppointmentSlotDataByDate(data.getDate());
	}
	
	@PostMapping("/findAppointmentSlotDataByDateAndSlotId")
	public AppointmentSlotData findAppointmentSlotDataByDateAndSlotId(@RequestBody AppointmentSlotData data) {
		return service.findAppointmentSlotDataByDateAndSlotId(data.getDate(), data.getSlotId());
	}
	
	public Boolean slotMetedataClone(@RequestBody Date date) {
		return true;
	}

	public List<AppointmentSlotData> getSlotData(Date date) {
		return null;
	}

	public List<AppointmentSlotData> getSlotDataByServiceGuy(Date date) {
		return null;
	}
}
