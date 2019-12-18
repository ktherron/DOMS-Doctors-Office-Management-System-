package seDOMS;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {
	//Declaring variables we need later
	static Appointment activeAppointment = null;
	static Doctor activeDoctor = null;
	static Patient activePatient = null;
	static AppointmentScheduler a = new AppointmentScheduler();
	static ArrayList<Patient> patients = new ArrayList<Patient>();
	static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	static int input = 10; 
	static String str = "";
	static Scanner s = new Scanner(System.in);
	static String dateRegex = "^[0-1]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
	static Pattern datePattern = Pattern.compile(dateRegex);
	static Matcher matcher;
	static DataController controller = new DataController(patients, doctors); 
	
	public static void main(String[] args) throws IOException {
			DataController.readData();
			
           //Initial identity prompt
			input = changeRole();
			
			//Controlling loop of Text Interface
			while (input != 0){ 
				try{
					sortAll();
					//Not a Valid Role Case
					if(!isRoleValid(input)){
						throw new InvalidInputException("Not a Valid Selection");
					}
					//Patient Identity case
					if(input  == 1){
						openPatientMenu();
					}
					//Doctor Case
					if(input  == 2){
						openDoctorMenu();
					}
					//Admin (Scheduler) Case
					if(input == 3){
						openAdminMenu();
					}
				}
				catch(Exception e){
					System.out.println(e);
					System.out.println("Sorry, that was an invalid input; take it from the top!\n");
					input = changeRole();
					
				}
			}
		}

		public static void sortAll() {
			Collections.sort(patients);
			Collections.sort(doctors);
			for(Patient p: patients) {
				Collections.sort(p.appointments);
			}
			for(Doctor d: doctors) {
				Collections.sort(d.appointments);
			}
		}
		
		public static int changeRole(){
			activePatient = null;
			activeDoctor = null;
			activeAppointment = null;
			System.out.print("Who do you want to be? Select number:\n" + "1) Patient\n" + "2) Doctor\n" + "3) Admin (Scheduler)\n");
			input = s.nextInt();
			return input;
		}
		public static void populatePatientsAndDoctors(){
			patients.add(new Patient("Patrick Stewart", "7/13/1940"));
			patients.add(new Patient("Brent Spiner", "2/2/1949"));
			patients.add(new Patient("Jonathan Frakes", "8/19/1952"));
			patients.add(new Patient("LeVar Burton", "2/16/1957"));
			patients.add(new Patient("Leonard Nimoy", "3/26//1931"));
			doctors.add(new Doctor("DeForest Kelly", "R-M 10-3" ));
			doctors.add(new Doctor("Gates McFadden", "M-F 9-5"));
		}
		
		public static boolean isRoleValid(int input) {
			if(input < 0 || input > 3) {
				return false;
			}
			else {
				return true;
			}
		}
		
		public static void openActivePatientMenu() throws InvalidInputException {
			System.out.print("You are Patient " + activePatient.getName() + ". What would you like to do?\n" + "1) Check My Appointments\n" + 
			"2) Cancel An Appointment\n" + "3) Check My Medications\n" + "4) Check My Appointments on Specific Day\n" + "5) Change Role\n");
			input = s.nextInt();
			if(input == 1 ){
				System.out.println(activePatient.checkAllAppointments());
			}
			if(input == 2){	
				cancelAppointment();
				input = 1;
			}
			if(input == 3){
				System.out.println(activePatient.checkMedications());
				input = 1;
			}
			if(input == 4) {
				checkAppointment("patient");
				input = 1;
			}
			if(input == 5){
				input = changeRole();
			}
		}
		
		public static void checkAppointment(String user) throws InvalidInputException {
			System.out.print("Enter date in this format: mm/dd/yyyy:\n");
			String date = s.next();
			matcher = datePattern.matcher(date); 
			if(!matcher.matches()){
				throw new InvalidInputException("Invalid Date Formatting");
			}
			if(user.equals("patient")) {
				System.out.println(activePatient.checkAppointments(date));
			}
			if(user.equals("doctor")) {
				System.out.println(activeDoctor.checkAppointments(date));
			}
		}
		
		
		public static void cancelAppointment() {
			System.out.println("Select appointment to cancel: \n");
			for(int i = 0; i < activePatient.appointments.size(); i ++){
				System.out.print(i+1 + ") " + activePatient.appointments.get(i) + "\n");
			}
			input = s.nextInt();
//			if(input == 0 ){break;}
			activePatient.cancelAppointment(activePatient.appointments.get(input-1));
			DataController.saveData();
		}
		
		public static void openNullPatientMenu() throws InvalidInputException{
			System.out.println("You are a Patient. Select who you are:");
			displayPatients();
			System.out.print(patients.size()+1 + ") Create new Patient\n" );
			input = s.nextInt();
//			if(input == 0 ){break;}

			//Create new patient case
			if(input == patients.size()+1){
				createPatient();
				input = 1;
		}
		//Select existing patient case
		else{
			activePatient = patients.get(input-1);
			input = 1;
			}
		}
		
		public static void displayPatients() {
			for(int i = 0; i < patients.size(); i++){
				System.out.print(i+1 + ") " + patients.get(i).getName() + "\n");
			}
		}
		
		public static void createPatient() throws InvalidInputException {
			System.out.print("Enter your name and birthdate in this format: FirstName LastName mm/dd/yyyy\n");
			String firstName = s.next(); String lastName = s.next(); String date = s.next();
			matcher = datePattern.matcher(date); 
			if(!matcher.matches()){
				throw new InvalidInputException("Invalid Date Formatting");
			}
			String name = firstName + " " + lastName;
			Patient p = new Patient(name, date);
			addPatient(p);
			DataController.saveData();
		}
		
		public static void openPatientMenu() throws InvalidInputException {
			if(activePatient == null){
				openNullPatientMenu();
			}
			//Patient is Already Selected
			else{
				openActivePatientMenu();
			}
		}

	public static void displayDoctors() {
		for(int i = 0; i < doctors.size(); i++){
			System.out.print(i+1 + ") " + doctors.get(i).getName() + " " + doctors.get(i).getSchedule() + "\n");
		}
	}
		
	public static void openNullDoctorMenu() {
		System.out.println("You are a Doctor. Select who you are:");
		displayDoctors();
		System.out.print(doctors.size()+1 + ") Create new Doctor\n" );
		input = s.nextInt();
//		if(input == 0 ){break;}

		//Create new Doctor case
		if(input == doctors.size()+1){
			createNewDoctor();
			input = 2;
	}
	//Select existing Doctor case
	else{
		activeDoctor = doctors.get(input-1);
		input = 2;
		}
	}
		
	public static void createNewDoctor() {
		System.out.print("Enter your name and schedule in this format: FirstName LastName M-F x-x\n");
		String firstName = s.next(); 
		String lastName = s.next();
		String schedule = s.nextLine();
		String name = firstName + " " + lastName;
		Doctor d = new Doctor(name, schedule);
		addDoctor(d);
		DataController.saveData();
	}
	
	
	public static void openActiveDoctorMenu() throws InvalidInputException {
		System.out.print("You are Doctor " + activeDoctor.getName() + ". What would you like to do?\n" + "1) Check My Appointments\n" + 
		"2) Begin An Appointment\n"  + "3) Check My Appointments on Specific Day\n"+ "4) Change Role\n");
		input = s.nextInt();
		if(input == 1 ){
			System.out.println(activeDoctor.checkAllAppointments());
			input = 2;
		}
		else if(input == 2){
			beginAppointment();
		}
		else if(input == 3) {
			checkAppointment("doctor");
			input = 2;
		}
		else if(input == 4){
			input = changeRole();
		}
	}
	
	public static void beginAppointment() throws InvalidInputException {
		System.out.println("Select appointment to begin: \n");
		for(int i = 0; i < activeDoctor.appointments.size(); i ++){
			System.out.print(i+1 + ") " + activeDoctor.appointments.get(i) + "\n");
		}
		input = s.nextInt();
//		if(input == 0 ){break;}
		activeAppointment = activeDoctor.appointments.get(input -1);
		if(activeAppointment.isCancelled() || activeAppointment.isCompleted()) {
			throw new InvalidInputException("This is not a valid appointment to begin; it has been completed or canceled already.");
		}
		
		activePatient = activeAppointment.getPatient();
		System.out.print("You are in an Active Appointment: \nWhat would you like to do?\n" + "1) Prescribe Medication and Complete Appointment\n"
		+ "2) Complete Appointment Only\n");
		input = s.nextInt();
//		if(input == 0 ){break;}
		if(input == 1){
			prescribeMedication();
		}
		activeDoctor.completeAppointment(activeAppointment);
		DataController.saveData();
		input = 2;
	}
	
	public static void prescribeMedication() {
		System.out.print("Enter medication to prescribe in this format: Name Dosage(int)\n");
		String name = s.next(); int dosage = s.nextInt();
		activeDoctor.prescribeMedication(activePatient, name + " " + dosage);
		System.out.print("Prescribe something else?\n" + "1)Yes\n" + "2)No\n");
		input = s.nextInt();
		if(input == 1) {
			prescribeMedication();
		}
	}
	
	
	public static void openDoctorMenu() throws InvalidInputException {
		if(activeDoctor == null){
			openNullDoctorMenu();
		}
		//Doctor is Already Selected
		else{
			openActiveDoctorMenu();
		}
	}
		
	public static void openAdminMenu() throws InvalidInputException {
		System.out.print("You are an Admin. What do you want to do?\n" + "1) Schedule an appointment\n" + "2) Change an Appointment\n"+ "3) Save Data\n" +"4) Change Role\n");
		input = s.nextInt();
//		if(input == 0 ){break;}
		if(input == 1){
			scheduleAppointment();
		}	
		if(input == 2) {
			changeAppointment();
		}
		if(input ==3) {
			DataController.saveData();
		}
		if(input == 4){
			input = changeRole();
		}
	}
	
	public static void scheduleAppointment() throws InvalidInputException {
		System.out.print("Select a Patient to Schedule: \n");
		displayPatients();
		input = s.nextInt();
//		if(input == 0 ){break;}
		activePatient = patients.get(input - 1);
		System.out.print("Select a Doctor to Schedule: \n");
		displayDoctors();
		input = s.nextInt();
//		if(input == 0 ){break;}
		activeDoctor = doctors.get(input - 1);
		System.out.print("Enter Date, Time and Room Number for Appointment in this format: mm/dd/yyyy hh:mm xxx\n");
		String date = s.next(); 
		String time = s.next();
		String room = s.next();
		matcher = datePattern.matcher(date); 
		if(!matcher.matches()){
			throw new InvalidInputException("Invalid Date Formatting");
		}
		else{
			a.makeAppointment(activePatient, activeDoctor, room, date, time);
		}
		DataController.saveData();
		input = 3;
	}
	
	public static void changeAppointment() {
		System.out.println("Which Patient's Appointment would you like to Change?\n");
		displayPatients();
		input = s.nextInt();
//		if(input == 0) {break;}
		activePatient = patients.get(input -1);
		System.out.println("Which of " + activePatient.getName() +"'s appointments would you like to change?\n");
		for(int i = 0; i < activePatient.appointments.size(); i++) {
			System.out.print(i+1 + ") " + activePatient.appointments.get(i) + "\n");
		}
		input = s.nextInt();
//		if(input == 0) {break;}
		activeAppointment = activePatient.appointments.get(input-1);
		System.out.print("Enter new date, time and room for this appointment in this format: mm/dd/yyyy h:mm xxx:\n");
		String date = s.next();
		String time = s.next();
		String room = s.next();
		System.out.print("Do you want to change the doctor for this appointment?\n" + "1) Yes\n" + "2) No\n");
		input = s.nextInt();
//		if(input == 0) {break;}
		if(input == 1) {
			System.out.println("Which Doctor is taking over this appointment?\n");
			displayDoctors();
			input = s.nextInt();
//			if(input == 0) {break;}
			activeDoctor = doctors.get(input-1);
			a.changeAppointment(activeAppointment, activeDoctor, date, time, room);
		}
		else {
			a.changeAppointment(activeAppointment, date, time, room);
		}
		DataController.saveData();
		input = 3;
	}
	
	public static void addPatient(Patient p) {
		patients.add(p);
	}
	
	public static void addDoctor(Doctor d) {
		doctors.add(d);
	}
	

}
