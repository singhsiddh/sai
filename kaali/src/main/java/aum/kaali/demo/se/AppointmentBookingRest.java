package aum.kaali.demo.se;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aum.kaali.demo.bo.Employee;

@RestController
public class AppointmentBookingRest {
	 @GetMapping("/employees")
	  List<Employee> all() {
	    return null;
	  }
	  // end::get-aggregate-root[]
	 @GetMapping("/sai")
	  public String allSai() {
	    return "Jai sai baba ki";
	  }
	  @PostMapping("/employees")
	  Employee newEmployee(@RequestBody Employee newEmployee) {
	    return newEmployee;
	  }
}
