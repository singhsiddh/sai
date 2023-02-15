package aum.kaali.demo.bo;

import java.io.Serializable;
import java.util.List;

public class SlotData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer slotId;
	private Integer sequanceNumber;
	private Float slotStartTime;
	private Float slotEndTime;
	private Integer totalAvailability;
	private List<String> serviceGuys;// list size =total availability
	private List<String> reservServiceGuys;
	private int reserved;
	private int available;
	public Integer getSlotId() {
		return slotId;
	}
	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
	public Integer getSequanceNumber() {
		return sequanceNumber;
	}
	public void setSequanceNumber(Integer sequanceNumber) {
		this.sequanceNumber = sequanceNumber;
	}
	public Float getSlotStartTime() {
		return slotStartTime;
	}
	public void setSlotStartTime(Float slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	public Float getSlotEndTime() {
		return slotEndTime;
	}
	public void setSlotEndTime(Float slotEndTime) {
		this.slotEndTime = slotEndTime;
	}
	public Integer getTotalAvailability() {
		return totalAvailability;
	}
	public void setTotalAvailability(Integer totalAvailability) {
		this.totalAvailability = totalAvailability;
	}
	public List<String> getServiceGuys() {
		return serviceGuys;
	}
	public void setServiceGuys(List<String> serviceGuys) {
		this.serviceGuys = serviceGuys;
	}
	public List<String> getReservServiceGuys() {
		return reservServiceGuys;
	}
	public void setReservServiceGuys(List<String> reservServiceGuys) {
		this.reservServiceGuys = reservServiceGuys;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	

}
