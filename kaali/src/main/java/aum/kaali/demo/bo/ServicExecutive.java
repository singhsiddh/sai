package aum.kaali.demo.bo;

import java.io.Serializable;

public class ServicExecutive implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String executiveId;
	private String executiveName;
	
	public String getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(String executiveId) {
		this.executiveId = executiveId;
	}
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	
	

}
