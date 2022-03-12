package com.example.parvatiarchana.myntra.bo;

import java.util.List;

public class Cat {
private String name;

private List<SubCat> subcat;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<SubCat> getSubcat() {
	return subcat;
}

public void setSubcat(List<SubCat> subcat) {
	this.subcat = subcat;
}

@Override
public String toString() {
	return "Cat [name=" + name + ", subcat=" + subcat + "]";
}



}
