package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientController implements Initializable {

	public static ArrayList<Patients> data = new ArrayList<Patients>(); // to store the resulted data from the query
	private ObservableList<Patients> dataList; // to add data into the table view
	private ObservableList<Patients> dataList1; // for search 
	private ObservableList<Patients> dataList2; //for join query 
	private ObservableList<Patients> dataList3;  //for join query 
	private ObservableList<Patients> dataList4;  //for join query 
	private ObservableList<Patients> dataList5; //for join query

	ObservableList<String> Tables = FXCollections.observableArrayList("Patients", "Diagnosis", "Treatment",
			"Appointments", "Perscription_Medication","Treatment_To_Patient");

	
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "Sahar@1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Clinic";
	private Connection con;
   
	@FXML
	private Button homepage;

	@FXML
	private ComboBox<String> tables;

	
	@FXML
	private TextField gender;

	@FXML
	private TextField page;

	@FXML
	private TextField pemail;

	@FXML
	private TextField pname;

	@FXML
	private TextField namesearch;

	@FXML
	private TextField medicalstate;
	
	@FXML
	private TextField getmedicalstate;

	@FXML
	private TextField emailsearch;
	
	 @FXML
	    private Button seachPatient;

	@FXML
	private TableView<Patients> patientTable;

	@FXML
	private TableColumn<Patients, String> pgender1;

	@FXML
	private TableColumn<Patients, String> pmedical;


	@FXML
	private TableColumn<Patients, Integer> page1;

	@FXML
	private TableColumn<Patients, String> pname1;
	
	@FXML
	private TableColumn<Patients, String> pemail1;
	
	@FXML
	private Button deletepatient;
	
	  @FXML
	    private Button insert_patient;
	  
	  @FXML
	    private Button refresh;
	  
	    @FXML
	    private Button info;
	    
	    @FXML
	    private DatePicker dateNext;
	    
	    @FXML
	    private TextField numOnWaiting;
	    
	    @FXML
	    private Button infoPm;

	    @FXML
	    private TextField diagnosistype;
	    
	    @FXML
	    private TextField piadcostt;
	    
	    @FXML
	    private TextField remaincost;
	    
	    @FXML
	    private TextField treattype;
	    
	    @FXML
	    private TextField medicineName;
	    
	    @FXML
	    private Button PatinetTreatmentDiagnosis;
	    
	    @FXML
	    private Button avgage;

        @FXML
        private PieChart pieChartt;


	

	@FXML
	void homepage(ActionEvent event) {

		try {

			Parent root_3 = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
			Scene scene_3 = new Scene(root_3, 660, 430);
			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
			st.setScene(scene_3);
			st.show();

		} catch (Exception e) {

		}
	}
	
	  @FXML
	    void setDeletePatient(ActionEvent event) {

		  ObservableList<Patients> selectedIndices = patientTable.getSelectionModel().getSelectedItems();
	        ArrayList<Patients> rows = new ArrayList<>(selectedIndices);
	        rows.forEach(row -> {
	        patientTable.getItems().remove(row); 
     		deleteRow(row); 
     		patientTable.refresh();});

	    }
	    
	    private void deleteRow(Patients row) {  
	    		// TODO Auto-generated method stub
	    		
	    		try {
	    			System.out.println("delete from Patients where Pemail="+row.getPemail() + ";");
	    			connectDB();
	    			ExecuteStatement("delete from Patients where Pemail="+"'"+row.getPemail()+"'"+ ";");
	    			con.close();
	    			System.out.println("Connection closed");
	    			
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			} catch (ClassNotFoundException e) {
	    				e.printStackTrace();
	    			}
	   
	     }
	    
	    public void ExecuteStatement(String SQL) throws SQLException {

			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(SQL);
				stmt.close();
							 
			}
			catch(SQLException s) {
				s.printStackTrace();
				System.out.println("SQL statement is not executed!");
				  
			}			
			
		}

	@FXML
	void Choose(ActionEvent event) {
		if (tables.getValue().toString().equals("Appointments")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
				Scene scene_3 = new Scene(root_3, 1117, 700);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene_3);
				st.show();

			} catch (Exception e) {

			}

		}

		else if (tables.getValue().toString().equals("Patients")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("Patient.fxml"));
				Scene scene_3 = new Scene(root_3, 1230, 700);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene_3);
				st.show();

			} catch (Exception e) {

			}

		} else if (tables.getValue().toString().equals("Treatment")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("Treatment.fxml"));
				Scene scene_3 = new Scene(root_3, 940, 700);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene_3);
				st.show();

			} catch (Exception e) {

			}

		}

		else if (tables.getValue().toString().equals("Perscription_Medication")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("PerscriptionMedication.fxml"));
				Scene scene_3 = new Scene(root_3, 1330, 700);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene_3);
				st.show();

			} catch (Exception e) {

			}

		}

		else if (tables.getValue().toString().equals("Diagnosis")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("Diagnosis.fxml"));
				Scene scene_3 = new Scene(root_3, 800, 700);
				Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
				st.setScene(scene_3);
				st.show();

			} catch (Exception e) {

			}

		}
		else if(tables.getValue().toString().equals("Treatment_To_Patient")) {
			try {
    			Parent root_3 = FXMLLoader.load(getClass().getResource("Treatment_To_Patient.fxml"));
    			Scene scene_3 = new Scene(root_3,755, 730);
    			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			st.setScene(scene_3);
    			st.show();

    		} catch (Exception e) {
    			
    		}

		}
	}

	

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

		pname1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pname"));
		pgender1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pgender"));
		pemail1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pemail"));
		page1.setCellValueFactory(new PropertyValueFactory<Patients, Integer>("P_age"));
		pmedical.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pmedicalstate"));

		try {
			dataList = getDataPatient();

			patientTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insert_patient.setOnAction((ActionEvent e) -> {
			    
			 	String name=pname.getText();
				String email = pemail.getText();
				String Pgender = gender.getText();
				int age=Integer.parseInt( page.getText());
				String state =  getmedicalstate.getText();
				System.out.println(name + " "+ email + " "+ Pgender +" "+ age +" " + state);
				Patients rc = new Patients(name,Pgender,email,age,state);
				//dataList.add(rc);
				insertPatient(rc);
	        	pname.clear();
	        	pemail.clear();
	        	page.clear();
	        	gender.clear();
	        	getmedicalstate.clear();
	        	
	        	
	        });
		
		
	        refresh.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });

		tables.setValue("Select Table");
		tables.setItems(Tables);
		
		
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
		         new PieChart.Data("Female", 60),
		         new PieChart.Data("Male", 40));
		
		  pieChartt.setData(data);
	      //Setting the other properties
	      pieChartt.setTitle("Female Vs Male");


	}
	
	@FXML
    void infopatient(ActionEvent event) throws ClassNotFoundException, SQLException {
		connectDB();
		 try {
				dataList2 =PatientAppointmentInfo();
				patientTable.setItems(dataList2);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 
		 dateNext.setValue(null);
		 numOnWaiting.clear();
    }
	
	 @FXML
	    void Averageage(ActionEvent event) throws ClassNotFoundException, SQLException {
		 connectDB();
		 try {
				dataList4 =AgeavgInfo();
				patientTable.setItems(dataList4);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		 
	    }
	
	  @FXML
	    void infopatientMedication(ActionEvent event) throws ClassNotFoundException, SQLException {
		  connectDB();
			 try {
					dataList3 =PatientMedicationInfo();
					patientTable.setItems(dataList3);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 medicineName.clear();
	    }
	  
	   @FXML
	    void InfoPatinetTreatmentDiagnosis(ActionEvent event) throws ClassNotFoundException, SQLException {
		   connectDB();
			 try {
					dataList4 =PatientTratmentDiagnosisInfo();
					patientTable.setItems(dataList4);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 
			 treattype.clear();
			 diagnosistype.clear();
			 remaincost.clear();
			 piadcostt.clear();
			 
	    }

	
	@FXML
    void searchforpaitent(ActionEvent event) throws ClassNotFoundException, SQLException {
    	connectDB();

   	 try {
			dataList1 =getDataSearch();
			patientTable.setItems(dataList1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
   	medicalstate.clear();
   	namesearch.clear();
   	emailsearch.clear();

    }
	
	public ObservableList<Patients> PatientMedicationInfo() throws ClassNotFoundException, SQLException {
		
		connectDB();
		ObservableList<Patients> list = FXCollections.observableArrayList();
		try {
			String SQL ;
			SQL="select * from Patients p,Perscription_Medication pm where p.Pemail=pm.Pemail and pm.Medicine_name ='" +medicineName.getText()+"'";
			System.out.println(SQL);
			if(medicineName.getText().equals("")) {
				SQL="select * from Patients p";
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
						rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				//PATIENTNAME=rs.getString("Pname");
				//System.out.println(PATIENTNAME);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
public ObservableList<Patients> AgeavgInfo() throws ClassNotFoundException, SQLException {
		
		connectDB();
		ObservableList<Patients> list = FXCollections.observableArrayList();
		try {
			String SQL ;
			SQL="select * from Patients p where p.P_age > (select AVG(P_age) from Patients)";
			System.out.println(SQL);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
						rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				//PATIENTNAME=rs.getString("Pname");
				//System.out.println(PATIENTNAME);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
public ObservableList<Patients> PatientTratmentDiagnosisInfo() throws ClassNotFoundException, SQLException {
		
		connectDB();
		ObservableList<Patients> list = FXCollections.observableArrayList();
		try {
			String SQL ;
			SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
					+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()+"' and d.Dtype='" +diagnosistype.getText()+"'"
							+ "and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+" and t.Treatment_Cost_Remaining_Amount="+
							remaincost.getText();
			if(treattype.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did  and d.Dtype='" +diagnosistype.getText()+"'"
								+ "and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+" and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			if(diagnosistype.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Treatment_To_Patient tp,Diagnosis d where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()
								+ "' and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+" and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			if(	remaincost.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()+"' and d.Dtype='" +diagnosistype.getText()+"'"
								+ "and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+"";
			}
			if(piadcostt.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()+"' and d.Dtype='" +diagnosistype.getText()+"'"
							+" and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			if(treattype.getText().equals("") && diagnosistype.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id"
								+ " and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+" and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			if(treattype.getText().equals("") && remaincost.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did  and d.Dtype='" +diagnosistype.getText()+"'"
								+ "and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+"";
			}
			
			if(treattype.getText().equals("") && piadcostt.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and d.Dtype='" +diagnosistype.getText()+"'"
								+" and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			
			if(diagnosistype.getText().equals("") &&  remaincost.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()
								+ "' and t.Treatment_Cost_Paid_Amount="+piadcostt.getText()+"";
			}
			
			if(diagnosistype.getText().equals("") &&  piadcostt.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()
							+"' and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			
			if(piadcostt.getText().equals("") && remaincost.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()+"' and d.Dtype='" +diagnosistype.getText()+"'";
								
			}
			if(diagnosistype.getText().equals("") && treattype.getText().equals("") && remaincost.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did"
								+ " and t.Treatment_Cost_Paid_Amount="+piadcostt.getText();
			}
			if(diagnosistype.getText().equals("") && treattype.getText().equals("") && piadcostt.getText().equals("")) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Treatment_Cost_Remaining_Amount="+
								remaincost.getText();
			}
			if(piadcostt.getText().equals("") && remaincost.getText().equals("") && diagnosistype.getText().equals("") ) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did and t.Ttype ='" +treattype.getText()+"'";
			}
			if(piadcostt.getText().equals("") && remaincost.getText().equals("") && treattype.getText().equals("") ) {
				SQL="select * from Patients p,Treatment t,Diagnosis d,Treatment_To_Patient tp where p.Pemail=tp.Pemail and "
						+ " t.Treatment_id = tp.Treatment_id and d.Did=tp.Did  and d.Dtype='" +diagnosistype.getText()+"'";
			}
			if(piadcostt.getText().equals("") && remaincost.getText().equals("") && treattype.getText().equals("") && diagnosistype.getText().equals("") ) {
				SQL="select * from Patients p";
			}
			
			
			
			System.out.println(SQL);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
						rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				//PATIENTNAME=rs.getString("Pname");
				//System.out.println(PATIENTNAME);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ObservableList<Patients> PatientAppointmentInfo() throws ClassNotFoundException, SQLException {
		connectDB();
		ObservableList<Patients> list = FXCollections.observableArrayList();
		//String PATIENTNAME=null;
		try {
			String SQL ;
			 SQL = "select * from Patients p,Appointments a where p.Pemail=a.Pemail and a.A_Date_Of_Next_visit ='" + dateNext.getValue() + "' and a.A_Number_Of_Patient_On_waiting_List =" + numOnWaiting.getText()+";"; 
			System.out.println(SQL);
			if(dateNext.getValue()==null) {
			 SQL = "select * from Patients p,Appointments a where p.Pemail=a.Pemail  and a.A_Number_Of_Patient_On_waiting_List =" + numOnWaiting.getText()+";"; 

			}
			if(numOnWaiting.getText().equals("")) {
				 SQL = "select * from Patients p,Appointments a where p.Pemail=a.Pemail and a.A_Date_Of_Next_visit ='" + dateNext.getValue() +"';"; 
			}
			if(dateNext.getValue()==null && numOnWaiting.getText().equals("")) {
				SQL = "select * from Patients p";
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
						rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				//PATIENTNAME=rs.getString("Pname");
				//System.out.println(PATIENTNAME);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return list;
		
	}
	public void Refresh() {
		pname1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pname"));
		pgender1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pgender"));
		pemail1.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pemail"));
		page1.setCellValueFactory(new PropertyValueFactory<Patients, Integer>("P_age"));
		pmedical.setCellValueFactory(new PropertyValueFactory<Patients, String>("Pmedicalstate"));

		try {
			dataList = getDataPatient();

			patientTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public ObservableList<Patients> getDataSearch() throws ClassNotFoundException, SQLException { 
		 connectDB();
			ObservableList<Patients> list = FXCollections.observableArrayList();
			try {
				
				String sql1;

					sql1 = "Select * from Patients where Pname = '" + namesearch.getText()+ "' and Pemail = '"+emailsearch.getText()+"' and Pmedicalstate = '"+medicalstate.getText()+"';";
					if(namesearch.getText().equals("")) {
						sql1 = "Select * from Patients where Pemail = '"+emailsearch.getText()+"' and Pmedicalstate = '"+medicalstate.getText()+"';";
					
					}
					if(medicalstate.getText().equals("")) {
						sql1 = "Select * from Patients where Pemail = '"+emailsearch.getText()+"' and Pname = '" + namesearch.getText() + "';";
					
					}
					if(emailsearch.getText().equals("")) {
						sql1 = "Select * from Patients where Pname = '" + namesearch.getText() + "'and Pmedicalstate = '"+medicalstate.getText()+"';";

					}
					if(namesearch.getText().equals("") & emailsearch.getText().equals("")) {
						sql1 = "Select * from Patients where Pmedicalstate ='"+medicalstate.getText()+"' ;";
					}
					if(namesearch.getText().equals("") & medicalstate.getText().equals("")) {
						sql1 = "Select * from Patients where  Pemail = '"+emailsearch.getText()+"';";

					}
					if(medicalstate.getText().equals("") & emailsearch.getText().equals("")) {
						sql1 = "Select * from Patients where Pname = '" + namesearch.getText() + "';";
					}
					if(medicalstate.getText().equals("") & emailsearch.getText().equals("") & namesearch.getText().equals("")) {
						sql1 = "Select * from Patients";
					}

					System.out.println(sql1);
			    Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
							rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				}
				} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
	 
	
	

	
		private void insertPatient(Patients rc) {
		
		try {
				System.out.println("Insert into Patients (Pname,Pgender,Pemail,P_agePmedicalstate) values("+"'"
						+rc.getPname()+"','"
						+rc.getPgender()+"',"
						+"'"+ rc.getPemail() +"',"
						+rc.getP_age()
						+",'"+rc.getPmedicalstate()+"'"+")");
				
				
				connectDB();
				ExecuteStatement("Insert into Patients (Pname,Pgender,Pemail,P_age,Pmedicalstate) values("+"'"
						+rc.getPname()+"','"
						+rc.getPgender()+"',"
						+"'"+ rc.getPemail() +"',"
						+rc.getP_age()
						+",'"+rc.getPmedicalstate()+"'"+")");
				
				
			

				con.close();
				System.out.println("Connection closed " + dataList.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
		
		public ObservableList<Patients> getDataPatient() throws ClassNotFoundException, SQLException {
			String SQL;
		    connectDB();

			ObservableList<Patients> list = FXCollections.observableArrayList();
			
			try {
				SQL = "select * from Patients";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				
				while (rs.next()) {
					list.add(new Patients(rs.getString("Pname"),rs.getString("Pgender"),
							rs.getString("Pemail"), rs.getInt("P_age"),rs.getString("Pmedicalstate")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			return list;
		}
	
	private void connectDB() throws ClassNotFoundException, SQLException {
		
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");
	
		con = DriverManager.getConnection (dbURL, p);

	}

}
