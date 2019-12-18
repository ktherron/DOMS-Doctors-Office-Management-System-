package seDOMS;

public class MockAppointment extends Appointment{
	String mockString = "";

	public MockAppointment(String str) {
		this.mockString = str;
	}


	public String toString() {
		return mockString;
	}

}
