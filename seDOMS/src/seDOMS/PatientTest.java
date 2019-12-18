package seDOMS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PatientTest {

 
  
   @Test
   @DisplayName("Test CheckAllAppointments Method w/ 1 App")
  
   public void testCheckAllAppointments()
   {
      Patient patient = new Patient("foo x", "23-11-1990");
      MockAppointment m1 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM");
      patient.appointments.add(m1); 
      String result = patient.checkAllAppointments();
       assertEquals("All of " + patient.getName() + "'s appointments:" + "\nPatient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM", result);
   }
   
   @Test
   @DisplayName("Test CheckAllAppointments Method w/ 2 Apps")
  
   public void testCheckAllAppointments2()
   {
      Patient patient = new Patient("foo x", "23-11-1990");
      MockAppointment m1 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM");
      patient.appointments.add(m1); 
      MockAppointment m2 = new MockAppointment("Patient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM");
      patient.appointments.add(m2);
      String result = patient.checkAllAppointments();
       assertEquals("All of " + patient.getName() + "'s appointments:" + "\nPatient foo x  has an appointment with Doctor Stonk v in room 200 on 9/25/2019 at 4:00PM"
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
	   Patient patient = new Patient("foo x", "23-11-1990");
       patient.appointments.add(m1);
       patient.appointments.add(m2);
	   String result = patient.checkAppointments("9/26/2019");
      
       assertEquals("All of " + patient.getName() + "'s appointments on 9/26/2019" + ":"
       + "\nPatient foo x  has an appointment with Doctor Stonk v in room 400 on 9/26/2019 at 4:00PM", result);
   }
  

  @Test
  @DisplayName("Test Check Medications")
   public void testCheckMedications(){
	  Patient p = new Patient("foo x");
	  //p.medications.add(new Medication("Drugs", 10));
	  String result = "Drugs 10\n";
	  assertEquals(result, p.checkMedications());
   }
   
   @Test
   @DisplayName("Test toString Method")
   public void testToString()
   {
       Patient patient = new Patient("foo x", "23-11-1990");
       String result = patient.toString();
       assertEquals("Patient name: " + patient.getName() + " Birth Date: " + patient.getBirthDate() + " Balance: " + patient.getBalance(), result);
   }

  
}