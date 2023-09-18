package application;

public class Treatment_To_Patient {
	
	
	private String Pemail;
	private int Did;
	private int Teeth_Number;
	private int Treatment_id;
	
	
	public Treatment_To_Patient(String pemail, int Did,int teeth_Number, int treatment_id) {
		this.Pemail = pemail;
		this.Did=Did;
		this.Teeth_Number = teeth_Number;
		this.Treatment_id = treatment_id;
	}


	


	public int getDid() {
		return Did;
	}





	public void setDid(int did) {
		Did = did;
	}





	public String getPemail() {
		return Pemail;
	}


	public void setPemail(String pemail) {
		Pemail = pemail;
	}


	public int getTeeth_Number() {
		return Teeth_Number;
	}


	public void setTeeth_Number(int teeth_Number) {
		Teeth_Number = teeth_Number;
	}


	public int getTreatment_id() {
		return Treatment_id;
	}


	public void setTreatment_id(int treatment_id) {
		Treatment_id = treatment_id;
	}



}
