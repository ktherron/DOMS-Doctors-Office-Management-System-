package seDOMS;

public class AppointmentSchedulerDriver {

	public static void main(String[] args) {
		AppointmentScheduler scheduler = new AppointmentScheduler();
		Patient p = new Patient("Bill x","", "7/14/1971", 1000);
		Doctor d = new Doctor("Gronk b", "M-F 8-3", 100, 10);
		Doctor f = new Doctor("Fonk s", "M-TR 5-5");
		Doctor s = new Doctor("Stonk v");
		Appointment a = scheduler.makeAppointment(p, d, "311", "9/25/2019", "3:00PM");
		p.cancelAppointment(a);

		scheduler.makeAppointment(p, s, "200", "9/25/2019", "4:00PM");
		Appointment b = scheduler.makeAppointment(p, f, "111", "9/26/2019", "8:00AM");
		d.completeAppointment(b);
		System.out.println("Output of patient's toString:");
		System.out.println(p);
		System.out.println("\nOutput of a singular appointment's toString:\n" + a);
		System.out.println("\nOutput of first doctor's toString:\n" + d);
		System.out.println("\nOutput of second doctor's toString:\n" + f);
		System.out.println("\nOutput of third doctor's toString:\n" + s);
		System.out.println(f.checkAppointments("9/26/2019"));
		System.out.println(p.checkAllAppointments());
		System.out.println(p.checkAppointments("9/25/2019"));
		System.out.println(a.isCancelled());
		System.out.println(a);

		
	}

}
