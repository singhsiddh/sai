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
		String transactionRefrenceNumber="TB" + transactionData.getSlotDate() + transactionData.getSlotId() + "-" + (new Date().getTime());
		transactionData.setReservationTrnsactionNumber(transactionRefrenceNumber
				);
		Boolean flag = transactionSDO.bookAppointment(currentData, transactionData);
		if (flag) {
			// insert in to transaction table : SloReservationData
			slotTransaction.save(transactionData);
			//SmsController sms = new SmsController();
			//sms.sendSMS(" Your transaction number "+transactionRefrenceNumber+ " slot time "+currentData.getSlotStartTime());
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
	public Integer cancleBookedAppoinmentByTransactionId(String transactionId) {
		/**
		 * 1: update status =deleted in Table: SlotReservationData 2: add availability
		 * in original master table AppointmentSlotData
		 */

		if (transactionId != null) {
			SlotReservationData data = slotTransaction.findByReservationTrnsactionNumber(transactionId);
			if (!DELETE_STATUS.equalsIgnoreCase(data.getStatus())) {
				data.setStatus(DELETE_STATUS);
				if (data.getTotalReservation() <= 0) {
					data.setTotalReservation(1);
				}
				Boolean flag = transactionSDO.updateAppoinmentOptimisticLock(data);// in case 2 ore canclation on same
																					// slot id then update would be
																					// wrong for availability hence
																					// optimistic lock is used
				if (flag) {
					slotTransaction.save(data);
				} else {
					System.out.println(" Failed to get optimistic lock in 4 attepts");
					return 0;//Throw exception with Failed to get optimistic lock in 4 attempts
				}
				/*
				 * slotData=appointmentSlotRepository.findByDateAndSlotId(data.getSlotDate(),
				 * data.getSlotId());
				 * 
				 * slotData.setAvailable(slotData.getAvailable()+data.getTotalReservation());
				 * 
				 * appointmentSlotRepository.save(slotData);
				 */
			}
		} else {
			return 0;// throw exception Invalid transaction number

		}
		return 1;
	}
}
