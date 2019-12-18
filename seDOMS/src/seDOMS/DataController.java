package seDOMS;

import java.io.IOException;
import java.util.ArrayList;

public class DataController {
	static DataWriter writer;
	static DataReader reader;
	static ArrayList<Patient> patients;
	static ArrayList<Doctor> doctors;
	
	DataController(ArrayList<Patient> patients, ArrayList<Doctor> doctors){
		DataController.patients = patients;
		DataController.doctors = doctors;
		initializeReader(patients, doctors);
		writer = new DataWriter(patients, doctors);

	}
	
	
	static void readData(){
		try {
			DataReader.readData();	
		}
		catch(Exception e) {
			System.out.println("No appointments file to read from since this is the first time you've launched the program. We'll create it for you now!");
		}
	}
	
	static void saveData()  {
		try{
			writer.saveData();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	 
	 
	 static void initializeReader(ArrayList<Patient> patients, ArrayList<Doctor> doctors){
		try {
			reader = new DataReader(patients, doctors);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
