# Schedule Manager

Proyecto para gestión de citas

## Servicios

### GetDoctorAppointments

 * Trae las citas de un doctor segun su numero de identificación
   **Ej:** GET http://localhost:9000/doctor/1/appointments

### ScheduleDoctorAppointment

 * Agenda una cita medica
   **Ej:** POST http://localhost:9000/appointment
   ```
   {
     "appointmentId" : "1",
     "patient" : "123456789",
     "doctorId" : "1",
     "specialityId" : "2",
     "address" : "Clinica palermo",
     "time" : "2015-12-01 08:30:00",
     "insuranceId" : "2"
   }
   ```

## BONUS

### FillDatabase

 * Llena la base de datos con alguna informacion
   **Ej:** POST http://localhost:9000/load



