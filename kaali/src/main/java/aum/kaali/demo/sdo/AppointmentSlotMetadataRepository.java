package aum.kaali.demo.sdo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.AppointmentSlotData;

public interface AppointmentSlotMetadataRepository extends MongoRepository<AppointmentSlotData, String> {
	
	public List<AppointmentSlotData> findAllByDate(Date date); 
	public AppointmentSlotData findByDateAndSlotId(Date date,Integer id); 
}
