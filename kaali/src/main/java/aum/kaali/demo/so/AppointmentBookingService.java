package aum.kaali.demo.so;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotMetadata;

public interface AppointmentBookingService {
	public void addAppoirntment(SlotReservationData in);
	public void addAppointmrntMetaData(SlotMetadata  data);

}
