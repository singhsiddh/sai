package aum.kaali.demo.sdo;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.SlotReservationData;

//slotMetaDataAdd
public interface SlotTransactioRepository extends MongoRepository<SlotReservationData, String> {
	
	public SlotReservationData findBySlotDateAndSlotId(Date date);

	 // public SlotDataIn findByFirstName(String firstName);
	 // public List<SlotDataIn> findByLastName(String lastName);

	
}
