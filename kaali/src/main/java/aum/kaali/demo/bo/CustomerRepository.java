package aum.kaali.demo.bo;

//https://spring.io/guides/gs/accessing-data-mongodb/
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//@Repository
//@Component
public interface CustomerRepository extends MongoRepository<Customer, String> {

  public Customer findByFirstName(String firstName);
  public List<Customer> findByLastName(String lastName);

}