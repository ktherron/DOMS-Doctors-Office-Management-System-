package seDOMS;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

// Reads the file appointments.txt
public class DataReader {
	static File file = new File("appointments.txt");
	static Scanner s;
	static ArrayList<Patient> patients;
	static ArrayList<Doctor> doctors;

/*	
	public static void main(String[] args) throws Exception{

	 	DataReader dr = new DataReader();
		DataReader.readData();
		for(int i = 0; i < patients.size(); i++) {
			System.out.println(patients.get(i));
			for(int j = 0; j < patients.get(i).appointments.size(); j++) {
				System.out.println(patients.get(i).appointments.get(j));
			}
		}
		for(Doctor a: doctors) {
			System.out.println(a);
		}
	}
*/
		
	static void readData(){
		String str =  s.nextLine();
		str = s.nextLine();
		readPatients(str);
		str = s.nextLine();
		readDoctors(str);
		readAppointments();
		s.close();
	}
	
	static void readPatients(String str) {
		while(!str.equals("Doctors")){
			String[] info = str.split(" ");
			String name = info[0] + " " + info[1];
			String birthdate = info[2];
			Patient p = new Patient(name, birthdate);
			patients.add(p);
			for(int i = 3; i < info.length; i++) {
				String medication = info[i].substring(0, info[i].indexOf("-")) + " " + info[i].substring(info[i].indexOf("-") + 1); 
				p.medications.add(medication);
			}
			str = s.nextLine();
		}
	}
	
	static void readDoctors(String str) {
		while(!str.equals("Appointments")) {
			String[] info = str.split(" ");
			String name = info[0] + " " + info[1];
			StringBuilder scheduleB = new StringBuilder();
			for(int i = 2; i < info.length; i++) {
				scheduleB.append(info[i] + " ");
			}
			String schedule = scheduleB.toString();
			Doctor d = new Doctor(name, schedule);
			doctors.add(d);
			str = s.nextLine();

		}
	}
	
	static void readAppointments() {
		while(s.hasNext()) {
			String str = s.nextLine();
			String[] info = str.split(" ");
			String patientName = info[0] + " " + info[1];
			String patientBirthdate = info[2];
			String doctorName = info[3] + " " + info[4];
			String appDate = info[5];
			String appTime = info[6];
			String appRoom = info[7];
			Patient p = new Patient("Dummy dummy"); Doctor d = new Doctor("Dummy dummy");
			Patient dummyP = new Patient(patientName, patientBirthdate);
			Doctor dummyD = new Doctor(doctorName);
			for(Patient patient: patients) {
				if(patient.equals(dummyP)) {
					p = patient;
					break;
				}
			}
			for(Doctor doctor: doctors) {
				if(doctor.equals(dummyD)) {
					d = doctor;
					break;
				}
			}
			Appointment app = new Appointment(p, d, appRoom, appDate, appTime);
			if(info[8].equals("true")) {
				app.setCancelled(true);
			}
			if(info[9].equals("true")){
				app.setCompleted(true);
			}
            p.appointments.add(app); d.appointments.add(app);		
		}
	}
	
	DataReader(ArrayList<Patient> patients, ArrayList<Doctor> doctors) throws FileNotFoundException{
		s = new Scanner(file);
		this.patients = patients;
		this.doctors = doctors;
	}

}