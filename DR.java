package application;

public class DR {
	 private int did;
	 private String dname;
	 private int dphone;
	 private String demail;
	 private String daddress;

	 
	 public DR (int did, String dname, int dphone, String demail, String daddress) {
		 this.did = did;
		 this.dname = dname;
		 this.dphone = dphone;
		 this.demail = demail;
		 this.daddress = daddress;

	 }
	 
	 
	 
	
	public int getDid() {
		return did;
	}




	public void setDid(int did) {
		this.did = did;
	}








	public String getDname() {
		return dname;
	}




	public void setDname(String dname) {
		this.dname = dname;
	}




	public int getDphone() {
		return dphone;
	}




	public void setDphone(int dphone) {
		this.dphone = dphone;
	}




	public String getDemail() {
		return demail;
	}




	public void setDemail(String demail) {
		this.demail = demail;
	}




	public String getDaddress() {
		return daddress;
	}




	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

}
