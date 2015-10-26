package persistence.impl;

import models.Appointment;
import persistence.IScheduleDao;

import java.util.List;

/**
 * Implementation class for IScheduleDao
 *
 * @author Sebastian Gamba Pinilla
 */
public class ScheduleDaoImpl implements IScheduleDao{

    @Override
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return (List<Appointment>) Appointment.find.where().eq("doctor.id", doctorId).findList();
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {

    }
}
