package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotMetadata;

public interface AppointmentBookingService {
	public void addAppoirntment(SlotReservationData in);
	public SlotMetadata findBySlotDate(Date date);
	public void addAppointmrntMetaData(SlotMetadata  data);
	public SlotReservationData findBySlotDateANDSlotId(Date date,int id);
	public SlotReservationData findBySlotId(int id);
	
	public List<SlotReservationData> findAllBySlotDate(Date date);
}
