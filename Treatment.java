package application;

public class Treatment {
	
	private int Treatment_id;
	private int Did;
	private String Ttype;
	private double Treatment_Cost_Paid_Amount;
	private double Treatment_Cost_Remaining_Amount;
	
	
	
	public Treatment(int Treatment_id,int Did,String Ttype,double Treatment_Cost_Paid_Amount,double Treatment_Cost_Remaining_Amount) {
		this.Treatment_id= Treatment_id;
		this.Did=Did;
		this.Ttype=Ttype;
		this.Treatment_Cost_Paid_Amount=Treatment_Cost_Paid_Amount;
		this.Treatment_Cost_Remaining_Amount=Treatment_Cost_Remaining_Amount;
		
	}


	public int getDid() {
		return Did;
	}



	public void setDid(int did) {
		Did = did;
	}


	public int getTreatment_id() {
		return Treatment_id;
	}


	public void setTreatment_id(int treatment_id) {
		Treatment_id = treatment_id;
	}

	public String getTtype() {
		return Ttype;
	}



	public void setTtype(String ttype) {
		Ttype = ttype;
	}



	public double getTreatment_Cost_Paid_Amount() {
		return Treatment_Cost_Paid_Amount;
	}



	public void setTreatment_Cost_Paid_Amount(double treatment_Cost_Paid_Amount) {
		Treatment_Cost_Paid_Amount = treatment_Cost_Paid_Amount;
	}



	public double getTreatment_Cost_Remaining_Amount() {
		return Treatment_Cost_Remaining_Amount;
	}



	public void setTreatment_Cost_Remaining_Amount(double treatment_Cost_Remaining_Amount) {
		Treatment_Cost_Remaining_Amount = treatment_Cost_Remaining_Amount;
	}
	
	
	

}
