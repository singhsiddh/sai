package com.example.exception;

import java.util.List;

public class DemoServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DemoServiceException() {
		super();
	}
/*	enum ERROR_TYPE{
	 FATAL=1;
		int RUNTIME=2;
		int BUSINESS=0;
	}*/
	private int errorType;
	private String debugMessage;
	private String tracePath="";
	private String corelationId="";
	private boolean isLogged =false;
	private String endPointURL="";
	private List<String> businessData;//Error errro later
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	public String getDebugMessage() {
		return debugMessage;
	}
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	public String getTracePath() {
		return tracePath;
	}
	public void setTracePath(String tracePath) {
		this.tracePath = tracePath;
	}
	public String getCorelationId() {
		return corelationId;
	}
	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public String getEndPointURL() {
		return endPointURL;
	}
	public void setEndPointURL(String endPointURL) {
		this.endPointURL = endPointURL;
	}
	public List<String> getBusinessData() {
		return businessData;
	}
	public void setBusinessData(List<String> businessData) {
		this.businessData = businessData;
	}
	@Override
	public String toString() {
		return "DemoServiceException [errorType=" + errorType + ", debugMessage=" + debugMessage + ", tracePath="
				+ tracePath + ", corelationId=" + corelationId + ", isLogged=" + isLogged + ", endPointURL="
				+ endPointURL + ", businessData=" + businessData + "]";
	}
	
	
	
	
}
