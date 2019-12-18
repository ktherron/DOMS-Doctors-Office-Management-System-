package seDOMS;
import java.io.*;
import java.util.*;
public class Patient implements Comparable<Patient>{

	private String firstName;
	private String lastName;
	private String birthDate;
	private double balance;
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	ArrayList<String> medications = new ArrayList<String>();
	
	// Assigns the balanace of patient to 0
	Patient(){
		this.firstName = "";
		this.lastName = "";
		this.birthDate = "";
		this.balance = 0;
	}
	
	//Assigns the name of the patient
	Patient(String name){
		this.setName(name);;
		this.birthDate = "";
		this.balance = 0;
	}
	
	//Assigns the birthdate of the patient
	Patient(String name, String birthDate){
		this.setName(name);
		this.birthDate = birthDate;
		this.balance = 0;
	}
	
	//Assigns the firstname, lastname, birthdate, and balanace 
	Patient(String firstName, String lastName, String birthDate, double balance){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.balance = balance;
	}
	
	// Cancels an appointment
	public void cancelAppointment(Appointment a) {
		a.setCancelled(true);
	}
	
	// Checks for medications
	public String checkMedications() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < medications.size(); i++) {
			sb.append(  medications.get(i) + "\n");
		}
		String str = sb.toString();
		return str;
	}
	
	// Checks all of the appointments for a patient
	public String checkAllAppointments() {
		StringBuilder sb = new StringBuilder();
		sb.append("All of " + this.getName() + "'s appointments:");
		for(int i = 0; i < appointments.size(); i++) {
			sb.append("\n" + appointments.get(i));
		}
		String str = sb.toString();
		return str;
	}
	
	//Checks a patients appointments
	public String checkAppointments(String date){
		StringBuilder sb = new StringBuilder();
		sb.append("All of " + this.getName() + "'s appointments on " + date +  ":");
		
		for(int i = 0; i < appointments.size(); i++) {
			if(date.equals(appointments.get(i).getDate()))
			sb.append("\n" + appointments.get(i));
		}
		String str = sb.toString();
		return str;
	}
	
	// Gets the name of a patient
	public String getName() {
		return firstName + " " + lastName;
	}
	
	// Sets the name of a patient
	public void setName(String name) {
		String firstName = name.substring(0, name.indexOf(" "));
		String lastName = name.substring(name.indexOf(" ")+1);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Gets the birthdate of a patient
	public String getBirthDate(){
		return birthDate;
	}
	
	// Sets the birthdate of a patient
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	// Gets the balance for a patient
	public double getBalance() {
		return balance;
	}
	
	// Withdraws an ammount
	public void withdraw(double amount) {
		balance -= amount;
	}
	
	// Deposits and amount
	public void deposit(double amount) {
		balance += amount;
	}
	
	// Prints patients name, birthday, and balance
	public String toString() {
		String str = "Patient name: " + this.getName() + " Birth Date: " + birthDate + " Balance: " + balance ;
		return str;
	}
	public int compareTo(Patient p) {
			int ret = this.lastName.compareTo(p.lastName);
			if(ret == 0) {
				return this.firstName.compareTo(p.firstName);
			}
		return ret;
	}
	
	public boolean equals(Object o) {
		Patient other = (Patient)o;
		if(this.getName().equals(other.getName()) && this.getBirthDate().equals(other.getBirthDate())) {
			return true;
		}
		else {
			return false;
		}
	}
}
