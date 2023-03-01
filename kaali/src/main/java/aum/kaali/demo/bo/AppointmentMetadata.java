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
	private Date date;
	private List<AppointmentSlotData> appointmentSlotData;
	private int totalAvaibility=-1;
	
	public AppointmentMetadata() {
		
	}
	
	public AppointmentMetadata(Date slotDate, List<AppointmentSlotData> appointmentSlotData) {
		super();
		this.date = slotDate;
		this.appointmentSlotData = appointmentSlotData;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date slotDate) {
		this.date = slotDate;
	}
	public List<AppointmentSlotData> getSlotData() {
		return appointmentSlotData;
	}
	public void setSlotData(List<AppointmentSlotData> appointmentSlotData) {
		this.appointmentSlotData = appointmentSlotData;
	}
	
	public void setTotalAvailableSlots() {
		if(this.totalAvaibility==-1) {
			if(appointmentSlotData==null) {
				this.totalAvaibility=0;
			}else{
				this.totalAvaibility=0;
				for( AppointmentSlotData data:appointmentSlotData ) {
					this.totalAvaibility+=data.getAvailable();
				}
			}
		}
		
		
	}
	public int getTotalAvailableSlots() {
		this.setTotalAvailableSlots();
		return this.totalAvaibility;
		
	}
}
