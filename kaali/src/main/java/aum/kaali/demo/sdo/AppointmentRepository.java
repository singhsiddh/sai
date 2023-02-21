package aum.kaali.demo.sdo;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.Customer;
import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotMetadata;

public interface AppointmentRepository extends MongoRepository<SlotReservationData, String> {

	// public SlotDataIn findByFirstName(String firstName);
	// public List<SlotDataIn> findByLastName(String lastName);

	public SlotReservationData findBySlotDateAndSlotId(Date date,int id);

	public List<SlotReservationData> findAllBySlotDate(Date date);
	public SlotReservationData findBySlotId(int id);

}

/*
 * public void updateCategoryName(String category) {
 * 
 * // Change to this new value String newCategory = "munchies";
 * 
 * // Find all the items with the category snacks List<GroceryItem> list =
 * groceryItemRepo.findAll(category);
 * 
 * list.forEach(item -> { // Update the category in each document
 * item.setCategory(newCategory); });
 * 
 * // Save all the items in database List<GroceryItem> itemsUpdated =
 * groceryItemRepo.saveAll(list);
 * 
 * if(itemsUpdated != null) System.out.println("Successfully updated " +
 * itemsUpdated.size() + " items."); }
 */
