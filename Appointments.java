package application;

public class Appointments {

	private int Appointment_id;
	private String Pemail;
	private String A_Date_Of_Last_visit;
	private String A_Date_Of_Next_visit;
	private String Appointment_time;
	private int A_Number_Of_Patient_On_waiting_List;
	
	public Appointments() {
		
	}
	
    public Appointments(int Appointment_id,String Pemail,String A_Date_Of_Last_visit,String A_Date_Of_Next_visit,String Appointment_time,int A_Number_Of_Patient_On_waiting_List) {
		
    	this.Appointment_id=Appointment_id;
    	this.Pemail=Pemail;
    	this.A_Date_Of_Last_visit=A_Date_Of_Last_visit;
    	this.A_Date_Of_Next_visit=A_Date_Of_Next_visit;
    	this.Appointment_time=Appointment_time;
    	this.A_Number_Of_Patient_On_waiting_List=A_Number_Of_Patient_On_waiting_List;
    	
    	
	}

	public void setAppointment_id(int appointment_id) {
		Appointment_id = appointment_id;
	}

	public void setPemail(String pemail) {
		Pemail = pemail;
	}

	public void setA_Date_Of_Last_visit(String a_Date_Of_Last_visit) {
		A_Date_Of_Last_visit = a_Date_Of_Last_visit;
	}

	public void setA_Date_Of_Next_visit(String a_Date_Of_Next_visit) {
		A_Date_Of_Next_visit = a_Date_Of_Next_visit;
	}

	public void setAppointment_time(String appointment_time) {
		Appointment_time = appointment_time;
	}

	public void setA_Number_Of_Patient_On_waiting_List(int a_Number_Of_Patient_On_waiting_List) {
		A_Number_Of_Patient_On_waiting_List = a_Number_Of_Patient_On_waiting_List;
	}

	public int getAppointment_id() {
		return Appointment_id;
	}

	public String getPemail() {
		return Pemail;
	}

	public String getA_Date_Of_Last_visit() {
		return A_Date_Of_Last_visit;
	}

	public String getA_Date_Of_Next_visit() {
		return A_Date_Of_Next_visit;
	}

	public String getAppointment_time() {
		return Appointment_time;
	}

	public int getA_Number_Of_Patient_On_waiting_List() {
		return A_Number_Of_Patient_On_waiting_List;
	}
    
    
	
}