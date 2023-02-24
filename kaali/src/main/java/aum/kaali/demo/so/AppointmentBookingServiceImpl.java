package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.sdo.AppointmentBookingTranscation;
import aum.kaali.demo.sdo.AppointmentRepository;
import aum.kaali.demo.sdo.AppointmentSlotMetadataRepository;
import aum.kaali.demo.sdo.SlotMetadataRepository;
import aum.kaali.demo.sdo.SlotTransactioRepository;
@Service
public class AppointmentBookingServiceImpl implements AppointmentBookingService {
	  @Autowired
	  private AppointmentRepository appointmentRepository;
	  @Autowired
	  private SlotMetadataRepository slotRepository; 
	  
	  @Autowired
	  private AppointmentSlotMetadataRepository appointmentSlotRepository; 
	  @Autowired
	  AppointmentBookingTranscation transactionSDO;
	  @Autowired
	  SlotTransactioRepository slotTransaction;

	@Override
	public void addAppoirntment(SlotReservationData in) {
		appointmentRepository.save(in);
		
	}

	@Override
	public void addAppointmrntMetaData(AppointmentMetadata data) {
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
	public AppointmentMetadata findBySlotDate(Date date) {
		// TODO Auto-generated method stub
		return slotRepository.findBySlotDate(date);
	}

	@Override
	public void addAppointmentSlotData(AppointmentSlotData data) {
		
		appointmentSlotRepository.save(data);
	}

	@Override
	public List<AppointmentSlotData> findAppointmentSlotDataByDate(Date date) {
		return appointmentSlotRepository.findAllByDate(date);
	}
	@Override
	public AppointmentSlotData findAppointmentSlotDataByDateAndSlotId(Date date,Integer id) {
		return appointmentSlotRepository.findByDateAndSlotId( date, id);
	}

	@Override
	public Boolean bookAppointment(SlotReservationData transactionData) {
		AppointmentSlotData currentData = appointmentSlotRepository.findByDateAndSlotId(transactionData.getSlotDate(), transactionData.getSlotId());
		System.out.println(("Currentdata="+currentData));
		Boolean flag=transactionSDO.bookAppointment(currentData, transactionData);
		if(flag) {
			// insert in to transaction table : SloReservationData
			slotTransaction.save(transactionData);
			System.out.println("Slot reserved ");
		}else {
			System.out.println("Warning Some has booked your slot prior your transaction completion ");
			return false; // It means some other has book that slot 
		}
		return true;	
	}
}
