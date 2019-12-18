package seDOMS;

public class AppointmentScheduler {
	AppointmentScheduler(){
		
	}
	
	Appointment makeAppointment(Patient patient, Doctor doctor){
		Appointment a = new Appointment(patient, doctor);
		patient.appointments.add(a);
		doctor.appointments.add(a);
		return a;
	}
	Appointment makeAppointment(Patient patient, Doctor doctor, String room,  String date, String time) {
		Appointment a = new Appointment(patient, doctor, room, date, time);
		patient.appointments.add(a);
		doctor.appointments.add(a);
		return a;
	}
	//If the doctor also needs to change for rescheduling
	Appointment changeAppointment(Appointment app, Doctor d, String date, String time, String room) {
		Doctor old = app.getDoctor();
		old.appointments.remove(app);
		app.setDoctor(d);
		app.setDate(date);
		app.setTime(time);
		app.setRoom(room);
		d.appointments.add(app);
		return app;
	}
	//Reschedule but keep doctor the same
	Appointment changeAppointment(Appointment app, String date, String time, String room) {
		app.setDate(date);
		app.setTime(time);
		app.setRoom(room);
		return app;
	}

}
