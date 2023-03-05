package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.AppointmentSlotData;

public interface AppointmentBookingService {
	
	public static final String DELETE_STATUS="delete";
	public static final String ACTIVE_STATUS ="active";
	public void addAppoirntment(SlotReservationData in);
	public void addAppointmentSlotData(AppointmentSlotData data );
	public AppointmentMetadata findBySlotDate(Date date);
	public void addAppointmrntMetaData(AppointmentMetadata  data);
	public SlotReservationData findBySlotDateANDSlotId(Date date,int id);
	public SlotReservationData findBySlotId(int id);
	
	public List<SlotReservationData> findAllBySlotDate(Date date);
	
	public List<AppointmentSlotData> findAppointmentSlotDataByDate(Date date);

	public List<AppointmentSlotData> findAppointmentSlotDataBetweenDates(Date dateF,Date dateT);
	public AppointmentSlotData findAppointmentSlotDataByDateAndSlotId(Date date, Integer id);
	public Boolean bookAppointment( SlotReservationData transactionData);
	public Integer cancleALLBookedAppoinmentByDateByAdmin(SlotReservationData transactionData);//soft delete make status :deleted
	public Integer cancleALLBookedAppoinmentByDateAndSlotTimeByAdmin(SlotReservationData transactionData);//soft delete make status :deleted
	public Integer cancleALLBookedAppoinmentByDateAndSlotIdByAdmin(SlotReservationData transactionData);//soft delete make status :deleted
	public Integer cancleBookedAppoinmentByTransactionId(String transactionId);//soft delete make status :deleted


}
