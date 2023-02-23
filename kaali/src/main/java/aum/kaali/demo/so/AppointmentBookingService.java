package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentMetadata;

public interface AppointmentBookingService {
	public void addAppoirntment(SlotReservationData in);
	public AppointmentMetadata findBySlotDate(Date date);
	public void addAppointmrntMetaData(AppointmentMetadata  data);
	public SlotReservationData findBySlotDateANDSlotId(Date date,int id);
	public SlotReservationData findBySlotId(int id);
	
	public List<SlotReservationData> findAllBySlotDate(Date date);
}
