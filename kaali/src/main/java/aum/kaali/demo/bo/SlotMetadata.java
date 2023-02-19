package aum.kaali.demo.bo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SlotMetadata")
public class SlotMetadata implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Date slotDate;
	private List<SlotData> slotData;
	
	
	
	public SlotMetadata(Date slotDate, List<SlotData> slotData) {
		super();
		this.slotDate = slotDate;
		this.slotData = slotData;
	}
	public Date getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
	public List<SlotData> getSlotData() {
		return slotData;
	}
	public void setSlotData(List<SlotData> slotData) {
		this.slotData = slotData;
	}
	
	
}
