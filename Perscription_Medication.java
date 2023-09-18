package application;

public class Perscription_Medication {

	private int Perscription_Medication_id;
	private String Pemail;
	private int Patient_age;
	private String Medicine_name;
	private String Patient_name;
	private String Date_Of_Visit;
	private int Treatment_id;
	private String Tnotes;
	
	
	public Perscription_Medication() {
		
	}
    public Perscription_Medication(int Perscription_Medication_id, String Pemail,int Patient_age ,String Medicine_name,String Patient_name ,String Date_Of_Visit,int Treatment_id,String Tnotes) {
		this.Perscription_Medication_id=Perscription_Medication_id;
    	this.Pemail=Pemail;
		this.Patient_age=Patient_age;
		this.Medicine_name=Medicine_name;
		this.Patient_name=Patient_name;
	    this.Date_Of_Visit=Date_Of_Visit;
	    this.Treatment_id= Treatment_id;
	    this.Tnotes=Tnotes;
		
	}
	
	
	public int getPerscription_Medication_id() {
		return Perscription_Medication_id;
	}
	public void setPerscription_Medication_id(int perscription_Medication_id) {
		Perscription_Medication_id = perscription_Medication_id;
	}
	public String getPemail() {
		return Pemail;
	}
	public void setPemail(String pemail) {
		Pemail = pemail;
	}
	public int getPatient_age() {
		return Patient_age;
	}
	public void setPatient_age(int patient_age) {
		Patient_age = patient_age;
	}
	public String getMedicine_name() {
		return Medicine_name;
	}
	public void setMedicine_name(String medicine_name) {
		Medicine_name = medicine_name;
	}
	public String getPatient_name() {
		return Patient_name;
	}
	public void setPatient_name(String patient_name) {
		Patient_name = patient_name;
	}
	public String getDate_Of_Visit() {
		return Date_Of_Visit;
	}
	public void setDate_Of_Visit(String date_Of_Visit) {
		Date_Of_Visit = date_Of_Visit;
	}
	
	public int getTreatment_id() {
		return Treatment_id;
	}
	public void setTreatment_id(int treatment_id) {
		Treatment_id = treatment_id;
	}
	public String getTnotes() {
		return Tnotes;
	}
	public void setTnotes(String tnotes) {
		Tnotes = tnotes;
	}
	
	
	
	
}