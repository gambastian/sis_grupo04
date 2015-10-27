package controllers;

import static play.mvc.Results.*;
import static play.mvc.Controller.*;

import com.fasterxml.jackson.databind.JsonNode;
import models.Appointment;
import models.Doctor;
import models.Insurance;
import models.Speciality;
import persistence.IScheduleDao;
import persistence.impl.ScheduleDaoImpl;
import play.libs.Json;
import play.mvc.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Medical Schedules controller
 *
 * @author Sebastian Gamba Pinilla
 */
public class ScheduleController {

    private static final Random RANDOM = new Random();
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

    /**
     * Stores an appointment's information
     * @return
     */
    public Result scheduleAppointment(){

        JsonNode appointmentPayload = request().body().asJson();
        if(appointmentPayload == null){
            return badRequest("Expecting json appointment information");
        }

        final String doctorId = appointmentPayload.get("doctorId").textValue();
        if(doctorId != null){
            final Doctor doctor = Doctor.find.byId(doctorId);
            if(doctor == null){
                return internalServerError("Doctor " + doctorId + " don't exist");
            }

            final String specialityId = appointmentPayload.get("specialityId").textValue();
            if(specialityId != null){
                final Speciality speciality = Speciality.find.byId(specialityId);
                if(speciality == null){
                    return internalServerError("Speciality " + specialityId + " don't exist");
                }

                final String insuranceId = appointmentPayload.get("insuranceId").textValue();
                if(insuranceId != null){
                    final Insurance insurance = Insurance.find.byId(insuranceId);
                    if(insurance  == null){
                        return internalServerError("Insurance " + specialityId + " don't exist");
                    }

                    // Schedules the appointment
                    final String appointmentId = appointmentPayload.get("appointmentId").textValue();
                    final String patient = appointmentPayload.get("patient").textValue();
                    final String address = appointmentPayload.get("address").textValue();
                    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        final Date time = format.parse(appointmentPayload.get("time").textValue());
                        //(String id, String patientId, Doctor doctor, Speciality speciality, String address, Date time, Insurance insurance)
                        final Appointment appointment = new Appointment(appointmentId,patient,doctor,speciality,address,time,insurance);
                        scheduleDao.scheduleAppointment(appointment);
                    } catch (ParseException e) {
                        return badRequest("Wrong date format. Should be yyyy-MM-dd HH:mm:ss");
                    }
                }else{
                    return badRequest("Expecting a insurance in the request");
                }
            }else{
                return badRequest("Expecting a speciality in the request");
            }
        }else{
            return badRequest("Expecting a doctor in the request");
        }
        return ok("Appointment succesfuly scheduled");
    }

    public Result fill(){

        final String[] insurances = {"Compensar", "Coomeva", "Saludcoop", "Famisanar"};
        List<Insurance> insurancesList = new ArrayList<>(insurances.length);
        for(int i = 0; i < insurances.length; i++){
            String id = Integer.valueOf(i+1).toString();
            Insurance insurance = new Insurance(id,insurances[i]);
            insurance.save();
            insurancesList.add(insurance);
        }

        final String[] specialities = {"GENERAL_PRACTICE", "PSYCOLOGY", "PAEDIATRICS", "CARDIOLOGY", "ONCOLOGY"};
        List<Speciality> specialityList = new ArrayList<>(specialities.length);
        for(int i = 0; i < specialities.length; i++){
            String id = Integer.valueOf(i+1).toString();
            Speciality speciality = new Speciality(id,specialities[i]);
            speciality.save();
            specialityList.add(speciality);
        }

        final String[] doctors = {"Hugo", "Paco", "Luis", "Andres", "Lukas", "Paula", "Ana", "Julio"};
        List<Doctor> doctorList = new ArrayList<>(doctors.length);
        for(int i = 0; i < doctors.length; i++){
            final Integer randomPhone = (RANDOM.nextInt(9) + 1) * 1111111;
            String id = Integer.valueOf(i+1).toString();
            Doctor doctor = new Doctor(id, doctors[i], getRandomSpecialityList(specialityList), getRandomInsuranceList(insurancesList), randomPhone, "Address", "email");
            doctor.save();
            doctorList.add(doctor);
        }


        return ok("Database filled");
    }

    /**
     * Generates a random list of specialities
     * @param specialities
     * @return
     */
    private List<Speciality> getRandomSpecialityList(List<Speciality> specialities){
        final Integer times = RANDOM.nextInt(specialities.size());
        if(times != 0) {
            List<Speciality> randomSpecialityList = new ArrayList<>(times);
            for (int i = 0; i < times; i++){
                final Integer index = RANDOM.nextInt(specialities.size());
                if(!randomSpecialityList.contains(specialities.get(index))){
                    randomSpecialityList.add(specialities.get(index));
                }
            }
            if(!randomSpecialityList.isEmpty()){
                return randomSpecialityList;
            }
        }
        return new ArrayList<>();
    }

    /**
     * Generates a random list of insurances
     * @param insurances
     * @return
     */
    private List<Insurance> getRandomInsuranceList(List<Insurance> insurances){
        final Integer times = RANDOM.nextInt(insurances.size());
        if(times != 0) {
            List<Insurance> randomInsuranceList = new ArrayList<>(times);
            for (int i = 0; i < times; i++){
                final Integer index = RANDOM.nextInt(insurances.size());
                if(!randomInsuranceList.contains(insurances.get(index))){
                    randomInsuranceList.add(insurances.get(index));
                }
            }
            if(!randomInsuranceList.isEmpty()){
                return randomInsuranceList;
            }
        }
        return new ArrayList<>();
    }

}
