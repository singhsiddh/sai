package com.example.parvatiarchana.myntra.bo;

import java.util.List;

public class SubCat {
	private String name;
	private List<SubSubCat> subsubcat;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubSubCat> getSubsubcat() {
		return subsubcat;
	}
	public void setSubsubcat(List<SubSubCat> subsubcat) {
		this.subsubcat = subsubcat;
	}
	@Override
	public String toString() {
		return "SubCat [name=" + name + ", subsubcat=" + subsubcat + "]";
	}
	

}
