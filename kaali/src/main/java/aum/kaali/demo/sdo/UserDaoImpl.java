package aum.kaali.demo.sdo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import aum.kaali.demo.bo.SlotData;




@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List getUserDetails() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(SlotData.class);
		return criteria.list();
	}

}