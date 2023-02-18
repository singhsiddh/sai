package aum.kaali.demo.so;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SloReservationData;
import aum.kaali.demo.bo.SlotDataIn;

public interface AppointmentBookingService {
	public void add(SloReservationData in);

}
