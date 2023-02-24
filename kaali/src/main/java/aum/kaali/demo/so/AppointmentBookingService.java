package aum.kaali.demo.so;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import aum.kaali.demo.bo.SlotReservationData;
import aum.kaali.demo.bo.AppointmentMetadata;
import aum.kaali.demo.bo.AppointmentSlotData;

public interface AppointmentBookingService {
	public void addAppoirntment(SlotReservationData in);
	public void addAppointmentSlotData(AppointmentSlotData data );
	public AppointmentMetadata findBySlotDate(Date date);
	public void addAppointmrntMetaData(AppointmentMetadata  data);
	public SlotReservationData findBySlotDateANDSlotId(Date date,int id);
	public SlotReservationData findBySlotId(int id);
	
	public List<SlotReservationData> findAllBySlotDate(Date date);
	
	public List<AppointmentSlotData> findAppointmentSlotDataByDate(Date date);
	public AppointmentSlotData findAppointmentSlotDataByDateAndSlotId(Date date, Integer id);
}
