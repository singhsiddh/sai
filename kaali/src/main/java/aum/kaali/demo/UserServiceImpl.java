package aum.kaali.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aum.kaali.demo.sdo.UserDao;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

//	public List getUserDetails() {
//		return userDao.getUserDetails();
//	}

}