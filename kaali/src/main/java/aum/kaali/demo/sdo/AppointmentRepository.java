package aum.kaali.demo.sdo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.Customer;
import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotMetadata;

public interface AppointmentRepository extends MongoRepository<SlotReservationData, String> {

	 // public SlotDataIn findByFirstName(String firstName);
	 // public List<SlotDataIn> findByLastName(String lastName);

	}

