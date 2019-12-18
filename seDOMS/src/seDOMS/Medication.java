package seDOMS;

public class Medication {
	private String name;
	private int dosage;
	
	//Assigns name and dosage to medication
	public Medication(String name, int dosage) {
		this.name = name;
		this.dosage = dosage;
	}
	
	// Gets the name of the medication
	public String getName() {
		return name;
	}
	
	//Gets the dosage of the medication
	public int getDosage() {
		return dosage;
	}

	// Prints name and dosage of medication
	public String toString(){
		String str = name + " " + dosage;
		return str;
	}


}
