package aum.kaali.demo.bo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AppointmentMetadata")
public class AppointmentMetadata implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String rowid;
	private Date slotDate;
	private List<AppointmentSlotData> appointmentSlotData;
	
	
	
	public AppointmentMetadata(Date slotDate, List<AppointmentSlotData> appointmentSlotData) {
		super();
		this.slotDate = slotDate;
		this.appointmentSlotData = appointmentSlotData;
	}
	public Date getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
	public List<AppointmentSlotData> getSlotData() {
		return appointmentSlotData;
	}
	public void setSlotData(List<AppointmentSlotData> appointmentSlotData) {
		this.appointmentSlotData = appointmentSlotData;
	}
	
	
}
