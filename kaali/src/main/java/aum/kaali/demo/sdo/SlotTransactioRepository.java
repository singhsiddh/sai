package aum.kaali.demo.sdo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import aum.kaali.demo.bo.SlotReservationData;

//slotMetaDataAdd
public interface SlotTransactioRepository extends MongoRepository<SlotReservationData, String> {
	
	public List<SlotReservationData> findAllBySlotDateAndSlotId(Date date);
	public List<SlotReservationData> findAllBySlotDate(Date date);
	public SlotReservationData findByReservationTrnsactionNumber(String transactionNumber);
	

	 // public SlotDataIn findByFirstName(String firstName);
	 // public List<SlotDataIn> findByLastName(String lastName);

	
}
