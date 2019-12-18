package seDOMS;
import java.io.*;
import java.util.*;

public class Doctor implements Comparable<Doctor>{
	private String firstName;
	private String lastName;	
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private String schedule;
	private double wage;
	private double hours;
	
	// Assigns wage and hours to 0
	Doctor(){
		this.setName("");
		this.schedule = "";
		this.wage = 0;
		this.hours = 0;
	}
	
	// Assigns doctors name, wage, and hours
	Doctor(String name){
		this.setName(name);
		this.schedule = "";
		this.wage = 0;
		this.hours = 0;
	}
	
	// Assigns doctors name and schedule
	Doctor(String name, String schedule){
		this.setName(name);
		this.schedule = schedule;
		this.wage = 0;
		this.hours = 0;
	}
	
	// Assigns doctors name, schedule, wage, and hours
	Doctor(String name, String schedule, double wage, double hours){
		this.setName(name);
		this.schedule = schedule;
		this.wage = wage;
		this.hours = hours;
	}
	// Prescribes medication to a patient
	public void prescribeMedication(Patient p, String m){
		p.medications.add(m);
	}
	
	// Completes a doctors appointment
	public void completeAppointment(Appointment a){
		a.setCompleted(true);
	}
	
	// Checks all of a doctors appointments
	public String checkAllAppointments() {
		StringBuilder sb = new StringBuilder(); 
		sb.append("All of Doctor " + this.getName() + "'s appointments:");
		for(int i = 0; i < appointments.size(); i++) {
			sb.append("\n" + appointments.get(i));
		}
		String str = sb.toString();
		return str;
	}
	
	// Checks a doctors appointment
	public String checkAppointments(String date){
		StringBuilder sb = new StringBuilder();
		sb.append("All of Doctor " + this.getName() + "'s appointments on " + date +  ":");
		for(int i = 0; i < appointments.size(); i++) {
			if(date.equals(appointments.get(i).getDate()))
				sb.append("\n" + appointments.get(i));
		}
		String str = sb.toString();
		return str;
	}
	
	// Gets the schedule for the doctor
	public String getSchedule() {
		return schedule;
	}
	
	// Sets the schedule for the doctor
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	// Gets the wage for the doctor
	public double getWage() {
		return wage;
	}
	
	//Sets the wage for the doctor
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	//Gets the hours for the doctor
	public double getHours() {
		return hours;
	}
	
	//Sets the hours for the doctor
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	//Gets the name of the doctor
	public String getName() {
		return firstName + " " + lastName;
	}
	
	//Sets the name for the doctor
	public void setName(String name) {
		String firstName = name.substring(0, name.indexOf(" "));
		String lastName = name.substring(name.indexOf(" ")+1);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//Prints a doctor, schedule, wage, date and hours
	public String toString() {
		String str = "";
		str += "Doctor name: " + this.getName() + " Schedule: " + schedule + " Wage: " + wage + " Hours: " + hours; 

		return str;
	}
	public boolean equals(Object o) {
		Doctor other = (Doctor)o;
		if(this.getName().equals(other.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public int compareTo(Doctor d) {
		int ret = this.lastName.compareTo(d.lastName);
		if(ret == 0) {
			return this.firstName.compareTo(d.firstName);
		}
		
	return ret;
	}
	
}
