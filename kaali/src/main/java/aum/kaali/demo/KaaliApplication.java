package aum.kaali.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import aum.kaali.demo.bo.Customer;
import aum.kaali.demo.bo.CustomerRepository;

@SpringBootApplication
@EnableMongoRepositories
public class KaaliApplication implements CommandLineRunner{

	  @Autowired
	  private CustomerRepository repository;
	public static void main(String[] args) {
		System.out.println("jai ma Kaali");
		try {
		SpringApplication.run(KaaliApplication.class, args);
		
		}catch(Throwable th) {
			th.printStackTrace();
			System.out.println("jai ma Kaali Error ");
		}
		KaaliApplication kk = new KaaliApplication ();
		
	}
	@Override
	public  void run(String... args) throws Exception {
		

		    repository.deleteAll();

		    // save a couple of customers
		    repository.save(new Customer("Alice", "Smith"));
		    repository.save(new Customer("Bob", "Smith"));

		    // fetch all customers
		    System.out.println("Customers found with findAll():");
		    System.out.println("-------------------------------");
		    for (Customer customer : repository.findAll()) {
		      System.out.println(customer);
		    }
		    System.out.println();

		    // fetch an individual customer
		    System.out.println("Customer found with findByFirstName('Alice'):");
		    System.out.println("--------------------------------");
		    System.out.println(repository.findByFirstName("Alice"));

		    System.out.println("Customers found with findByLastName('Smith'):");
		    System.out.println("--------------------------------");
		    for (Customer customer : repository.findByLastName("Smith")) {
		      System.out.println(customer);
		    }
	}

}
