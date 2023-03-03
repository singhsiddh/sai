package aum.kaali.demo.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AllSlotByMonth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String compnay;
	private String branch;
	
	private int year;
	private int month;
	Map<Date,Integer> slotCounts;
	List<AvailaibilyByDays> slotCountByDay;
	public String getCompnay() {
		return compnay;
	}
	public void setCompnay(String compnay) {
		this.compnay = compnay;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Map<Date, Integer> getSlotCounts() {
		return slotCounts;
	}
	public void setSlotCounts(Map<Date, Integer> slotCounts) {
		this.slotCounts = slotCounts;
	}
	public List<AvailaibilyByDays> getSlotCountByDay() {
		return slotCountByDay;
	}
	public void setSlotCountByDay(List<AvailaibilyByDays> slotCountByDay) {
		this.slotCountByDay = slotCountByDay;
	}
	
	
}
