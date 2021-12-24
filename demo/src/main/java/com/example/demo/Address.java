package com.example.demo;

import java.io.Serializable;

public class Address implements Serializable{
	
	String name="..";
	int housenumber=12;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(int housenumber) {
		this.housenumber = housenumber;
	}
	@Override
	public String toString() {
		return "Address [name=" + name + ", housenumber=" + housenumber + "]";
	}
	

}
