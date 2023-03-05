package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.AppointmentSlotData;
import aum.kaali.demo.sdo.AppointmentBookingNonJPATranscation;
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
	AppointmentBookingNonJPATranscation transactionSDO;
	@Autowired
	private SlotTransactioRepository slotTransaction;
	
	

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
	public SlotReservationData findBySlotId(int id) {
		// TODO Auto-generated method stub
		return appointmentRepository.findBySlotId(id);
	}

	@Override
	public AppointmentMetadata findBySlotDate(Date date) {
		// TODO Auto-generated method stub
		return slotRepository.findByDate(date);
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
	public AppointmentSlotData findAppointmentSlotDataByDateAndSlotId(Date date, Integer id) {
		return appointmentSlotRepository.findByDateAndSlotId(date, id);
	}

	@Override
	public Boolean bookAppointment(SlotReservationData transactionData) {
		AppointmentSlotData currentData = appointmentSlotRepository.findByDateAndSlotId(transactionData.getSlotDate(),
				transactionData.getSlotId());
		System.out.println(("Currentdata=" + currentData));
		Boolean flag = transactionSDO.bookAppointment(currentData, transactionData);
		if (flag) {
			// insert in to transaction table : SloReservationData
			slotTransaction.save(transactionData);
			System.out.println("Slot reserved ");
		} else {
			System.out.println("Warning Some has booked your slot prior your transaction completion ");
			return false; // It means some other has book that slot
		}
		return true;
	}

	@Override
	public List<AppointmentSlotData> findAppointmentSlotDataBetweenDates(Date dateF, Date dateT) {
		return appointmentSlotRepository.findByDateBetween(dateF, dateT);
	}

	@Override
	public Integer cancleALLBookedAppoinmentByDateByAdmin(SlotReservationData transactionData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer cancleALLBookedAppoinmentByDateAndSlotTimeByAdmin(SlotReservationData transactionData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer cancleALLBookedAppoinmentByDateAndSlotIdByAdmin(SlotReservationData transactionData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer cancleBookedAppoinmentByTransactionId(String  transactionId) {
		/**
		 * 1: update status =deleted in Table: SlotReservationData 2: add availability
		 * in original master table AppointmentSlotData
		 */
		
		if (transactionId != null) {
			SlotReservationData data=	slotTransaction.findByReservationTrnsactionNumber(transactionId);
			if(! DELETE_STATUS.equalsIgnoreCase(data.getStatus())) {
			data.setStatus(DELETE_STATUS);
			if(data.getTotalReservation()<=0) {
				data.setTotalReservation(1);
			}
			slotTransaction.save(data);
			AppointmentSlotData slotData=appointmentSlotRepository.findByDateAndSlotId(data.getSlotDate(), data.getSlotId());
			
			slotData.setAvailable(slotData.getAvailable()+data.getTotalReservation());
			//later use optimistic lock as if same time other are also cancelin might be wron g numebr updated 
			appointmentSlotRepository.save(slotData);
			}
		} else {
			return 0;// throw exception Invalid transaction number

		}
		return 1;
	}
}
