package com.example.demo.so;

import org.springframework.stereotype.Service;

import com.example.demo.Address;
import com.example.exception.DemoServiceException;

@Service
public class ServiceDemo {
	public Address getAddess() {
		
		 Address ad = new Address();
		   ad.setHousenumber(1212);
		   ad.setName("Siddharth");
		   if( true) {
			   throw new DemoServiceException();
		   }
	        return ad; // list index starts with 0 but we prefer to start on 1
	    }

}
