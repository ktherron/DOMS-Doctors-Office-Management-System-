package seDOMS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntTestPatientDoctorAppointmentSchedulerAppointment {

	@Test
	@DisplayName("Test Schedule Appointment")
	public void test1() {
		AppointmentScheduler a = new AppointmentScheduler();
		Patient p = new Patient("Bob Dylan");
		Doctor d = new Doctor("Dr. Jones");
		Appointment app = a.makeAppointment(p, d, "111", "10/10/2019", "4:30");
		assertEquals(app, p.appointments.get(0));
		assertEquals(app, d.appointments.get(0));		
	}
	   @Test
	   @DisplayName("Test Patient's Cancel Appointment Method")
	   public void test2() {
	       Patient patientWithEmptyConstructor = new Patient();
	       MockAppointment appointment = new MockAppointment("");
	       patientWithEmptyConstructor.cancelAppointment(appointment);
	       assertEquals(true, appointment.isCancelled());
	   }
	   @Test
	   @DisplayName("Test Doctor's Complete Appointment Method")
	   public void test3() 
	   {
	       Doctor d = new Doctor("Bob X");
	       Appointment appointment = new Appointment();
	       d.completeAppointment(appointment);
	       assertEquals(true, appointment.isCompleted());
	   }
	   @Test
	   @DisplayName("Test Appointment's Equals Method")
	   public void test4() {
			AppointmentScheduler a = new AppointmentScheduler();
			Patient p = new Patient("Bob Dylan");
			Doctor d = new Doctor("Dr. Jones");
			Appointment app = a.makeAppointment(p, d, "111", "10/10/2019", "4:30");
			assertEquals(app, new Appointment(p, d, "111", "10/10/2019", "4:30"));
	   }
	   @Test
	   @DisplayName("Test Change Appointment")
	   public void test5() {
			AppointmentScheduler a = new AppointmentScheduler();
			Patient p = new Patient("Bob Dylan");
			Doctor d = new Doctor("Dr. Jones");
			Appointment app = a.makeAppointment(p, d, "111", "10/10/2019", "4:30");
			a.changeAppointment(app, "10/20/2020", "5:00", "777");
			assertEquals(app, new Appointment(p, d, "777", "10/20/2020", "5:00"));
	   }
}
