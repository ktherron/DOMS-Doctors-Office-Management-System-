package seDOMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DoctorTest {
	

	  
	   @Test
	   @DisplayName("Test CheckAllAppointments Method (1 Appointment)")

	   public void testCheckAllAppointments()
	   {
	       Doctor doctor = new Doctor("Wonk x");
	       MockAppointment m1 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM");
	       doctor.appointments.add(m1);
	       String result = doctor.checkAllAppointments();
	       assertEquals("All of Doctor " + doctor.getName() + "'s appointments:" + "\nPatient foo x  "
	       		+ "has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM", result);
	   }
	  
	   
	   @Test
	   @DisplayName("Test CheckAllAppointments Method w/ 2 Apps")
	  
	   public void testCheckAllAppointments2()
	   {
	      Doctor d = new Doctor("Stonk v");
	      MockAppointment m1 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM");
	      d.appointments.add(m1); 
	      MockAppointment m2 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM");
	      d.appointments.add(m2);
	      String result = d.checkAllAppointments();
	       assertEquals("All of Doctor " + d.getName() + "'s appointments:" + "\nPatient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM"
	     + "\nPatient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM", result);
	   }
	   
	   @Test
	   @DisplayName("Test CheckAppointments(date) Method")
	   public void testCheckAppointments()
	   {
		   MockAppointment m1 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM");
		   MockAppointment m2 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM");
		   m1.setDate("9/25/2019");
		   m2.setDate("9/26/2019");
		   Doctor d = new Doctor("foo x");
	       d.appointments.add(m1);
	       d.appointments.add(m2);
		   String result = d.checkAppointments("9/26/2019");
	      
	       assertEquals("All of Doctor " + d.getName() + "'s appointments on 9/26/2019" + ":"
	       + "\nPatient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM", result);
	   }
	  
	   
	   @Test
	   @DisplayName("Test toString Method")
	   public void testToString()
	   {
	       Doctor doctor = new Doctor("Wonk x", "10/11/1990", 23d, 1d);
	       String result = doctor.toString();
	       assertEquals("Doctor name: " + doctor.getName() + " Schedule: " + doctor.getSchedule() + " Wage: " + doctor.getWage() + " Hours: " + doctor.getHours(), result);
	   }

}