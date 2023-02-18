package aum.kaali.demo.so;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aum.kaali.demo.bo.SloReservationData;
import aum.kaali.demo.bo.SlotDataIn;
import aum.kaali.demo.sdo.AppointmentRepository;
@Service
public class AppointmentBookingServiceImpl implements AppointmentBookingService {
	  @Autowired
	  private AppointmentRepository repository;

	@Override
	public void add(SloReservationData in) {
		repository.save(in);// TODO Auto-generated method stub
		
	}

}
