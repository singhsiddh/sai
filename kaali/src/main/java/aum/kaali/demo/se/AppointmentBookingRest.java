package aum.kaali.demo.se;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import aum.kaali.demo.bo.AvailaibilyByDays;
import aum.kaali.demo.bo.AllSlotByMonth;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.so.AppointmentBookingService;

@RestController
@RequestMapping("/kaali/appointmnet")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentBookingRest {
	@Autowired
	private AppointmentBookingService service;
	@Autowired
	DataSource dataSource;

	@Autowired
	MongoClient mongoClient;
	// @Autowired
	// MongoDatabase database ;

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

	@PostMapping("/findAllSlotByDate")
	public AppointmentMetadata findAllBySlotDate(@RequestBody AppointmentMetadata data) {
		System.out.println("date ..." + data.getDate());

		AppointmentMetadata meta = new AppointmentMetadata();// service.findBySlotDate(appointmentMetadata.getSlotDate());
		System.out.println("meata ..." + meta);
		meta.setDate(data.getDate());
		meta.setSlotData(service.findAppointmentSlotDataByDate(data.getDate()));
		System.out.println("dataSource=" + dataSource + " mongoClient=" + mongoClient);

		return meta;
	}

	@PostMapping("/findAllSlotByMonth")
	public AllSlotByMonth findAllSlotByMonth(@RequestBody AllSlotByMonth data) {
		int year = data.getYear();
		int month = data.getMonth();
		String dateStr = year + "-" + month + "-01";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = null;
		try {
			today = sdf.parse(dateStr);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		Date lastDayOfMonth = calendar.getTime();
List<AvailaibilyByDays> listAvaialbility = new ArrayList<>();
AvailaibilyByDays availaibilyByDays = null;
		Map<Integer, Integer> dataMap = new HashMap<Integer, Integer>();
		/*this can also achive by mango aggragartion but not expert writing in java and all aexmplae in script way*/
		List<AppointmentSlotData> list = service.findAppointmentSlotDataBetweenDates(today, lastDayOfMonth);
//System.out.println("MMMMMM slot list "+list);
		Integer day = 0;
		Integer sumOfAvailableSlot = 0;
		for (AppointmentSlotData data1 : list) {
			calendar.setTime(data1.getDate());
			day = calendar.get(Calendar.DAY_OF_MONTH);
			if (dataMap.get(day) != null) {
				sumOfAvailableSlot = sumOfAvailableSlot + dataMap.get(day)+data1.getAvailable();
				dataMap.put(day, sumOfAvailableSlot);
			} else {
				dataMap.put(day, data1.getAvailable());
			}
		}
		for (Integer name : dataMap.keySet()) {
			availaibilyByDays = new AvailaibilyByDays();
			availaibilyByDays.setAvailaibilty(dataMap.get(name));
			availaibilyByDays.setDay(""+name);
			listAvaialbility.add(availaibilyByDays);
            System.out.println("key: " + name);
	}
		data.setSlotCountByDay(listAvaialbility);
		//System.out.println("dataSource=" + dataSource + " mongoClient=" + mongoClient);

		return data;
	}

	@PostMapping("/findBySlotDate")
	public AppointmentMetadata findBySlotDate(@RequestBody AppointmentMetadata appointmentMetadata) {
		System.out.println("date ..." + appointmentMetadata.getDate());

		AppointmentMetadata meta = service.findBySlotDate(appointmentMetadata.getDate());
		System.out.println("meata ..." + meta);

		System.out.println("dataSource=" + dataSource + " mongoClient=" + mongoClient);

		return meta;
	}

	@PostMapping("/findBySlotDateANDSlotId")
	public SlotReservationData findBySlotDateANDSlotId(@RequestBody SlotReservationData in) {
		return service.findBySlotDateANDSlotId(in.getSlotDate(), in.getSlotId());
	}

	@PostMapping("/findBySlotId")
	public SlotReservationData findBySlotId(@RequestBody SlotReservationData in) {
		return service.findBySlotId(in.getSlotId());
	}

	@PostMapping("/findAllBySlotDate")
	public List<SlotReservationData> findAllBySlotDate(@RequestBody SlotReservationData in) {
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

	@PostMapping("/bookAppointment")
	public String bookAppointment(@RequestBody SlotReservationData transactionData) {
		Boolean flag = service.bookAppointment(transactionData);
		if (!flag) {
			return "Fail";
		}
		return "Sucess";
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
