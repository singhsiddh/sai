package aum.kaali.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.sdo.AppointmentRepository;
import aum.kaali.demo.sdo.AppointmentSlotMetadataRepository;
import aum.kaali.demo.so.AppointmentBookingService;
@RunWith(SpringRunner.class)
@SpringBootTest
class KaaliApplicationTests {
@Autowired 
AppointmentBookingService bService;
	@Test
	void contextLoads() {
	}
	@MockBean
	private AppointmentRepository appointmentRepository;
	 
	@MockBean
	private AppointmentSlotMetadataRepository appointmentSlotRepository;
	@Test
	public void addAppoirntment(SlotReservationData in) {
		//when(appointmentRepository.save(in)).thenReturn()
		appointmentRepository.save(in);
		//assertEqual()

	}

	@Test
	public void findAppointmentSlotDataByDateAndSlotId(Date date, Integer id) {
		System.out.println( "Inside Test sai 25 May 2023");
		AppointmentSlotData data = new AppointmentSlotData();
		data.setAvailable(2);
		
		when(appointmentSlotRepository.findByDateAndSlotId(date, id)).thenReturn(data);
		AppointmentSlotData data1=	 bService.findAppointmentSlotDataByDateAndSlotId(date, id);
		 assertEquals(2,data1.getAvailable());
	}
}
