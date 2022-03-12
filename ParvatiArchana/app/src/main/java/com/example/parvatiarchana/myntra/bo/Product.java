package com.example.parvatiarchana.myntra.bo;

import java.util.List;

public class Product {
private List<Cat> cat;

public List<Cat> getCat() {
	return cat;
}

public void setCat(List<Cat> cat) {
	this.cat = cat;
}

@Override
public String toString() {
	return "Product [cat=" + cat + "]";
}


}
