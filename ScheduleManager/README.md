# Schedule Manager

Proyecto para gestión de citas

## Servicios

### GetDoctorAppointments

 * Trae las citas de un doctor segun su numero de identificación
   **Ej:** GET http://localhost:9000/doctor/1/appointments

### ScheduleDoctorAppointment

 * Agenda una cita medica
   **Ej:** POST http://localhost:9000/appointment
   
   ><p>{</p>
     <p>"appointmentId" : "1",</p>
     <p>"patient" : "123456789",</p>
     <p>"doctorId" : "1",</p>
     <p>"specialityId" : "2",</p>
     <p>"address" : "Clinica palermo",</p>
     <p>"time" : "2015-12-01 08:30:00",</p>
     <p>"insuranceId" : "2"</p>
   <p>}</p>
   

## BONUS

### FillDatabase

 * Llena la base de datos con alguna informacion
   **Ej:** POST http://localhost:9000/load



