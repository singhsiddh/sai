package aum.kaali.demo.sdo;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.SlotReservationData;

//slotMetaDataAdd
public interface SlotMetadataRepository extends MongoRepository<AppointmentMetadata, String> {
	
	public AppointmentMetadata findBySlotDate(Date date);

	 // public SlotDataIn findByFirstName(String firstName);
	 // public List<SlotDataIn> findByLastName(String lastName);

	
}
