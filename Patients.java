package application;

public class Patients {
	private String Pname;
	private String Pgender;
	private String Pemail;
	private int P_age;
	private String Pmedicalstate;
	
	
	public Patients(String Pname,String Pgender,String Pemail,int P_age,String Pmedicalstate) {
		this.Pname=Pname;
		this.Pemail=Pemail;
		this.Pgender=Pgender;
		this.P_age=P_age;
		this.Pmedicalstate=Pmedicalstate;
	}


	public String getPname() {
		return Pname;
	}


	public void setPname(String pname) {
		Pname = pname;
	}


	public String getPgender() {
		return Pgender;
	}


	public void setPgender(String pgender) {
		Pgender = pgender;
	}


	public String getPemail() {
		return Pemail;
	}


	public void setPemail(String pemail) {
		Pemail = pemail;
	}


	public int getP_age() {
		return P_age;
	}


	public void setP_age(int p_age) {
		P_age = p_age;
	}




	public String getPmedicalstate() {
		return Pmedicalstate;
	}


	public void setPmedicalstate(String pmedicalstate) {
		Pmedicalstate = pmedicalstate;
	}
	
	
	

}
