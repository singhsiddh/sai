package aum.kaali.demo.so;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.SlotMetadata;
import aum.kaali.demo.sdo.AppointmentRepository;
import aum.kaali.demo.sdo.SlotMetadataRepository;
@Service
public class AppointmentBookingServiceImpl implements AppointmentBookingService {
	  @Autowired
	  private AppointmentRepository appointmentRepository;
	  @Autowired
	  private SlotMetadataRepository slotRepository; 

	@Override
	public void addAppoirntment(SlotReservationData in) {
		appointmentRepository.save(in);
		
	}

	@Override
	public void addAppointmrntMetaData(SlotMetadata data) {
		slotRepository.save(data);
		
	}

}
