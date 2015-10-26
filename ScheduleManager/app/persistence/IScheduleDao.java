package persistence;

import models.Appointment;
import java.util.List;

/**
 * Interface contract for Schedule persistence methods
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IScheduleDao {

    List<Appointment> getAppointmentsByDoctor(String doctorId);
    void scheduleAppointment(Appointment appointment);
}
