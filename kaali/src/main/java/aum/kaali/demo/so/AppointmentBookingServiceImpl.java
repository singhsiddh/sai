package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

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

	@Override
	public SlotReservationData findBySlotDateANDSlotId(Date date, int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.findBySlotDateAndSlotId(date, id);
	}

	@Override
	public List<SlotReservationData> findAllBySlotDate(Date date) {
		// TODO Auto-generated method stub
		return appointmentRepository.findAllBySlotDate(date);
	}

	@Override
	public SlotReservationData findBySlotId(int id){
		// TODO Auto-generated method stub
		return  appointmentRepository.findBySlotId(id) ;
	}

	@Override
	public SlotMetadata findBySlotDate(Date date) {
		// TODO Auto-generated method stub
		return slotRepository.findBySlotDate(date);
	}

}
