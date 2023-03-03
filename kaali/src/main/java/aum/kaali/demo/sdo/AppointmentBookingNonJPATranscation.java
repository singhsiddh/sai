package aum.kaali.demo.sdo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;

import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.bo.ServicExecutive;
import aum.kaali.demo.bo.SlotReservationData;

@Repository
public class AppointmentBookingNonJPATranscation {
	@Autowired
	MongoClient mongoClient;

	// @Autowired
	// MongoDatabase database ;
	/*
	 * date , slotid,totalbooking,BookedServiceman
	 * 
	 */
	/*
	 * db.collection.aggregate([ { $match: { _id: INPUT_USER_ID } }, { $group: {
	 * _id: { year: { $year: "$date_started" }, month: { $month: "$date_started" }
	 * }, total_cost_month: { $sum: "$total_cost" } } } ])
	 * 
	 */
//hinmt :https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/aggregation/
	public Integer getTotalAvailavleSlotByYearAndMonth(int yearIn, int monthIn) {
	
		MongoDatabase db = mongoClient.getDatabase("local");// TODO remove hardcoded value
		 MongoCollection<org.bson.Document> collection = db.getCollection("AppointmentSlotData");

		 collection.aggregate(
				    Arrays.asList(
				    		//Aggregates.addFields(fields),
				    		Aggregates.match(Filters.eq("company","abc")),
				    		Aggregates.match(Filters.eq("date","2023-02-24")),
				    //		Aggregates.match(Filters.exp((new Document()).append("$in", Arrays.asList(new Document().append("$month", "$date"),monthIn)))),
					        Aggregates.group("$_id",  Accumulators.sum("sum_slot", "$available")),
					        Aggregates.project(
				            Projections.fields(
				                Projections.excludeId(),
				                Projections.include("date","slotId")/*,
				                Projections.computed(
				                    "firstCategory",
				                    new Document("$arrayElemAt", Arrays.asList("$categories", 0))
				                )*/
				            )
				        )
					      //  .and(DateOperators.Month.monthOf("date")).as("month")// end of project
				        
				        
				    )
				).forEach(doc -> System.out.println(doc.toJson()));
		 
		 
		 

	        // Gets the student with the highest average in the class.
	   /*     AggregateIterable<Document> results = collection.aggregate(Arrays.asList(Aggregates.unwind("$scores"),
	                Aggregates.group("$_id", Accumulators.avg("average", "$scores.score")),
	                Aggregates.sort(Sorts.descending("average")), Aggregates.limit(1)));
	                
	                */
		//UpdateResult result = db.getCollection("AppointmentSlotData").updateMany(searchQuery, setQuery);

		return 0;
	}

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

		int reserved = currentData.getReserved() + transactionData.getTotalReservation();

		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("available", newAvailability);
		updateFields.append("dateStamp", (new Date()).getTime());
		updateFields.append("reserved", reserved);
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);
		// col.update(searchQuery, setQuery);

		System.out.println("newAvailability" + newAvailability);

		MongoDatabase db = mongoClient.getDatabase("local");// TODO remove hardcoded value

		UpdateResult result = db.getCollection("AppointmentSlotData").updateMany(searchQuery, setQuery);
		System.out.println(" Result for update =" + result + " count" + result.getModifiedCount());
		if (result.getModifiedCount() <= 0) {
			return false;
		}
		return true;
	}
}