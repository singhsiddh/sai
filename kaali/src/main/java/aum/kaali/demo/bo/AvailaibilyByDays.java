package aum.kaali.demo.bo;

import java.io.Serializable;

public class AvailaibilyByDays  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String day;
	private Integer availaibilty;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getAvailaibilty() {
		return availaibilty;
	}
	public void setAvailaibilty(Integer availaibilty) {
		this.availaibilty = availaibilty;
	}
	
	
}
