package seDOMS;
import java.io.*;
import java.util.*;

public class Appointment implements Comparable<Appointment>{
	private Patient patient;
	private Doctor doctor;
	private String room;
	private String time;
	private String date;
	private boolean cancelled = false;
	private boolean completed = false; 
	

// Assigns patient and doctor to null

	Appointment(){
		this.patient = null;
		this.doctor = null;
		this.room = "";
		this.time = "";
	}
	
	Appointment(Patient patient, Doctor doctor){
		this.patient = patient;
		this.doctor = doctor;
		this.room = "";
		this.time = "";
	}
	Appointment(Patient patient, Doctor doctor, String room, String date, String time){
		this.patient = patient;
		this.doctor = doctor;
		this.room = room;
		this.date = date;
		this.time = time;
	}
	
	Patient getPatient() {
		return patient;
	}
	void setPatient(Patient patient) {
		this.patient = patient;
	}
	Doctor getDoctor() {
		return doctor;
	}
	void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	String getRoom() {
		return room;
	}
	void setRoom(String room) {
		this.room = room;
	}
	String getTime() {
		return time;
	}
	void setTime(String time) {
		this.time = time;
	}
	String getDate() {
		return date;
	}
	void setDate(String date) {
		this.date = date;
	}
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean equals(Object obj) {
		Appointment a = (Appointment)obj;
		return a.getDoctor() == this.doctor && a.getPatient() == this.patient && a.getDate() == this.date && a.getTime() == this.time && a.getRoom() == this.room; 
	}
	
	public String toString() {
		String str = "Patient " + patient.getName() + " has an appointment with Doctor " + doctor.getName() + " in room " + room + " on " + date + " at " + time;
		if (this.cancelled){
			str = str.replaceFirst("has", "cancelled");
		}
		if(this.completed){
			str = str.replaceFirst("has", "completed");
		}
		return str;
	}

	@Override
	public int compareTo(Appointment o) {
		String[] dateArr = this.date.split("/");
		String[] oDateArr = o.getDate().split("/");
		int thisMonth = Integer.parseInt(dateArr[0]);
		int thisDay = Integer.parseInt(dateArr[1]);
		int thisYear = Integer.parseInt(dateArr[2]);
		int oMonth = Integer.parseInt(oDateArr[0]);
		int oDay = Integer.parseInt(oDateArr[1]);
		int oYear = Integer.parseInt(oDateArr[2]);
		int ret = thisYear - oYear;
		if (ret == 0) {
			ret = thisMonth - oMonth;
			if(ret == 0) {
				ret = thisDay - oDay;
			}
		}
		return ret;
	}	
}
