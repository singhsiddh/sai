package aum.kaali.demo.bo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


@Document("SloReservationData")
public class SloReservationData  implements Serializable{
	/**
	 * 
	 */
	@Id
	private String rowid;
	private static final long serialVersionUID = 1L;
	private String reserverId;
	private String reserverName;
	private String reservationTrnsactionNumber;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

	@JsonFormat(pattern="yyyy/MM/dd'T'HH:mm:ss")
	private Date slotDate;
	//  @JsonFormat(pattern = "hh:mm:ss a")
		//  private LocalTime time;
	private Integer slotId;
	private Integer totalReservation=1;//1 is default
	
	
	public SloReservationData(String rowid,String reserverId, String reserverName, String reservationTrnsactionNumber, Date slotDate,
			Integer slotId, Integer totalReservation) {
		super();
		this.rowid =rowid;
		this.reserverId = reserverId;
		this.reserverName = reserverName;
		this.reservationTrnsactionNumber = reservationTrnsactionNumber;
		this.slotDate = slotDate;
		this.slotId = slotId;
		this.totalReservation = totalReservation;
	}
	public String getReserverId() {
		return reserverId;
	}
	public void setReserverId(String reserverId) {
		this.reserverId = reserverId;
	}
	public String getReserverName() {
		return reserverName;
	}
	public void setReserverName(String reserverName) {
		this.reserverName = reserverName;
	}
	public String getReservationTrnsactionNumber() {
		return reservationTrnsactionNumber;
	}
	public void setReservationTrnsactionNumber(String reservationTrnsactionNumber) {
		this.reservationTrnsactionNumber = reservationTrnsactionNumber;
	}
	public Date getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
	public Integer getSlotId() {
		return slotId;
	}
	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}
	public Integer getTotalReservation() {
		return totalReservation;
	}
	public void setTotalReservation(Integer totalReservation) {
		this.totalReservation = totalReservation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
