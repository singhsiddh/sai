package aum.kaali.demo.bo;

import java.util.Date;
import java.util.List;

public class BookingMetadata {
	private Date bokkingDate;
	private List<AppointmentSlotData> slots;
	
	public Date getBokkingDate() {
		return bokkingDate;
	}
	public void setBokkingDate(Date bokkingDate) {
		this.bokkingDate = bokkingDate;
	}
	public List<AppointmentSlotData> getSlots() {
		return slots;
	}
	public void setSlots(List<AppointmentSlotData> slots) {
		this.slots = slots;
	}
	
	

}
