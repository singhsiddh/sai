package aum.kaali.demo.sdo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.bo.ServicExecutive;
import aum.kaali.demo.bo.SlotReservationData;

@Repository
public class AppointmentBookingTranscation {
	@Autowired
	MongoClient mongoClient;

	// @Autowired
	// MongoDatabase database ;
	/*
	 * date , slotid,totalbooking,BookedServiceman
	 */
	public Boolean bookAppointment(AppointmentSlotData currentData, SlotReservationData transactionData) {
		/*
		 * private Integer totalAvailability=1; //@OneToMany(targetEntity=Student.class,
		 * mappedBy="college", fetch=FetchType.EAGER) private List<ServicExecutive>
		 * serviceGuys;// list size =total availability private List<ServicExecutive>
		 * reservServiceGuys;
		 * 
		 * private int reserved; private int available;// ==totalAvailability-reserved
		 */
		// First Query for AppointmentSlotData
		/*
		 * if( currentData.getAvailable() - transactionData.getTotalReservation() < 0) {
		 * return false; }
		 */
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("date", currentData.getDate());
		searchQuery.append("slotId", currentData.getSlotId());
		searchQuery.append("dateStamp", currentData.getDateStamp());// Optimistic Lock
		// greater than slected number
		System.out.println("searchQuery" + searchQuery);
int newAvailability = currentData.getAvailable() - transactionData.getTotalReservation();
		
		int reserved = currentData.getReserved()+transactionData.getTotalReservation();
		
		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("available", newAvailability);
		updateFields.append("dateStamp", (new Date()).getTime());
		updateFields.append("reserved", reserved);
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);
		//col.update(searchQuery, setQuery);
		
		
		System.out.println( "newAvailability"+newAvailability);
		
		MongoDatabase db = mongoClient.getDatabase("local");// TODO remove hardcoded value

		UpdateResult result = db.getCollection("AppointmentSlotData").updateMany(searchQuery, setQuery);
		System.out.println(" Result for update =" + result + " count" + result.getModifiedCount());
		if (result.getModifiedCount() <= 0) {
			return false;
		}
		return true;
	}
}
