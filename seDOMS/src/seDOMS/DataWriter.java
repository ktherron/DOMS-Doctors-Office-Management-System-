package seDOMS;

import java.util.ArrayList;
import java.io.*;

public class DataWriter {
	ArrayList<Patient> patients;
	ArrayList<Doctor> doctors;
	
	DataWriter(ArrayList<Patient> patients, ArrayList<Doctor> doctors){
		this.patients = patients;
		this.doctors = doctors;
	}

// Writing a new file from appointments.txt
	public void saveData() throws IOException{
		File file = new File("appointments.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file, false);
		writer.append("Patients");
		for(Patient p: patients) {
			writer.append("\n" + p.getName() + " " + p.getBirthDate());
			for(int i = 0; i < p.medications.size(); i++) {
				String m = p.medications.get(i);
				writer.append(" " + m.substring(0, m.indexOf(" ")) + "-" + m.substring(m.indexOf(" ")+1));
			}

		}
		writer.append("\nDoctors");
		for(Doctor d: doctors) {
			writer.append("\n" + d.getName() + " " + d.getSchedule());
		}
		writer.append("\nAppointments");
		for(Patient p: patients) {
			for(int i = 0; i < p.appointments.size(); i++) {
				Appointment app = p.appointments.get(i);
				writer.append("\n" + p.getName() + " " + p.getBirthDate() + 
				" " +app.getDoctor().getName() + " " + app.getDate() + " " + app.getTime() + " " + app.getRoom() + " " + app.isCancelled() + " " + app.isCompleted());
			}
		}
		writer.flush();
		writer.close();
	}
    
}
