package controllers;

import static play.mvc.Results.*;

import models.Appointment;
import persistence.IScheduleDao;
import persistence.impl.ScheduleDaoImpl;
import play.libs.Json;
import play.mvc.Result;
import java.util.List;

/**
 * Medical Schedules controller
 *
 * @author Sebastian Gamba Pinilla
 */
public class ScheduleController {

    private IScheduleDao scheduleDao = new ScheduleDaoImpl();

    public Result getDoctorAppointments(String doctorId){

        if(doctorId == null || doctorId.trim().isEmpty()){
            return badRequest("Invalid input parameters");
        }

        List<Appointment> appointments = scheduleDao.getAppointmentsByDoctor(doctorId);

        if(appointments == null || appointments.isEmpty()) {
            return noContent();
        }else{
            return ok(Json.toJson(appointments));
        }
    }
}
