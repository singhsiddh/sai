package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.so.ServiceDemo;
@RestController
@RequestMapping("/sai")
public class RestControllerDemo {
	@Autowired
	ServiceDemo service ;
	   @GetMapping("*")
	    public String getSpanishGreetingById() {
	        return "Welcome sai*...."; // list index starts with 0 but we prefer to start on 1
	    }
	   @GetMapping("/om")
	    public Address getSpanishGreetingById1() {
		   Address ad = new Address();
		   ad.setHousenumber(1212);
	        return service.getAddess(); // list index starts with 0 but we prefer to start on 1
	    }
}
