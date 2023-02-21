package aum.kaali.demo.sdo;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotMetadata;
import aum.kaali.demo.bo.SlotReservationData;

//slotMetaDataAdd
public interface SlotMetadataRepository extends MongoRepository<SlotMetadata, String> {
	
	public SlotMetadata findBySlotDate(Date date);

	 // public SlotDataIn findByFirstName(String firstName);
	 // public List<SlotDataIn> findByLastName(String lastName);

	
}
