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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PerscriptionMedicationController implements Initializable {

	public static ArrayList<Perscription_Medication> data = new ArrayList<Perscription_Medication>(); // to store the resulted data from the query
	private ObservableList<Perscription_Medication> dataList; // to add data into the table view
	private ObservableList<Perscription_Medication> dataList1; //for search
	
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
    private TableColumn<Perscription_Medication, Integer> PS_id;
    
    @FXML
    private TableColumn<Perscription_Medication, String> med_name;

    @FXML
    private TableColumn<Perscription_Medication, Integer> patientage;

    @FXML
    private TableColumn<Perscription_Medication, String> patientname;

    @FXML
    private TableColumn<Perscription_Medication, String> pemail;

    @FXML
    private TableView<Perscription_Medication> perscTable;

    @FXML
    private TableColumn<Perscription_Medication, String> tNotes;

    @FXML
    private TableColumn<Perscription_Medication,Integer> Treatid;
    
    @FXML
    private TableColumn<Perscription_Medication, String> dateOfvisit;
    
    @FXML
    private DatePicker getDateVisit;

    @FXML
    private TextField getMedicineName;

    @FXML
    private TextField getPage;

    @FXML
    private TextField getPatientName;

    @FXML
    private TextField getPeamil;

    @FXML
    private TextField gettNotes;
    
    @FXML
    private TextField getPMid;
    
    @FXML
    private TextField gettreatID;
    
    @FXML
    private Button insert_Perscription_Medication;
    
    @FXML
    private TextField patientseacrh;
    
    @FXML
    private TextField medicinesearch;
    
    @FXML
    private TextField emailsearch;
    
    @FXML
    private DatePicker datavisitsearch;

    @FXML
    private TextField Pmsearch;
    
    @FXML
    private TextField treatsearch;
    
    @FXML
    private Button search;
    
    @FXML
    private Button refresh;
    
    

    
    @FXML
    void homepage(ActionEvent event) {

	 try {
			
			Parent root_3 = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
			Scene scene_3 = new Scene(root_3,660, 430);
			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
			st.setScene(scene_3);
			st.show();

		} catch (Exception e) {
			
		}
    }
    
    @FXML
    void setPerscriptionTable(ActionEvent event) {

    	ObservableList<Perscription_Medication> selectedIndices = perscTable.getSelectionModel().getSelectedItems();
        ArrayList<Perscription_Medication> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
        	perscTable.getItems().remove(row); 
 		deleteRow(row); 
 		perscTable.refresh();});

    }
    
    private void deleteRow(Perscription_Medication row) {  
    		// TODO Auto-generated method stub
    		
    		try {
    			System.out.println("delete from Perscription_Medication where Perscription_Medication_id="+row.getPerscription_Medication_id() + ";");
    			connectDB();
    			ExecuteStatement("delete from Perscription_Medication where Perscription_Medication_id="+"'"+row.getPerscription_Medication_id() +"'"+ ";");
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
				Scene scene_3 = new Scene(root_3, 1230, 710);
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
    			Scene scene_3 = new Scene(root_3,1370, 730);
    			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			st.setScene(scene_3);
    			st.show();

    		} catch (Exception e) {
    			
    		}

		}
	}

    @Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

    	PS_id.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Perscription_Medication_id"));
    	pemail.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Pemail"));
		patientage.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Patient_age"));
		med_name.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Medicine_name"));
		patientname.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Patient_name"));
		dateOfvisit.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Date_Of_Visit"));
		Treatid.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Treatment_id"));
		tNotes.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Tnotes"));


		try {
			dataList = getDataPerscription_Medication();

			perscTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insert_Perscription_Medication.setOnAction((ActionEvent e) -> {
			    
			 	
				int Pmid=Integer.parseInt(getPMid.getText());
				String emailpatient=getPeamil.getText();
				int P_age =  Integer.parseInt(getPage.getText());
				String medicine_name = getMedicineName.getText();
				String patient_name= getPatientName.getText();
				String date_of_visit= getDateVisit.getValue().toString();
				int treatmentID = Integer.parseInt(gettreatID.getText());
				String tnotes =  gettNotes.getText();
				System.out.println(Pmid +" "+emailpatient + " "+ P_age + " "+ medicine_name +" "+ patient_name + " "+ date_of_visit +" " + treatmentID+" "+ tnotes);
				Perscription_Medication rc = new Perscription_Medication(Pmid,emailpatient,P_age,medicine_name,patient_name,date_of_visit,treatmentID,tnotes);
				//dataList1.add(rc);
				insertPerspectionMedicine(rc);
				getPMid.clear();
				getPeamil.clear();
				getPage.clear();
				getMedicineName.clear();
				getPatientName.clear();
				getDateVisit.getValue().equals(null);
				gettreatID.clear();
				gettNotes.clear();
				
				
	        });
		
		 refresh.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });

		tables.setValue("Select Table");
		tables.setItems(Tables);

	}
    
   
    
    public void Refresh() {
    	PS_id.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Perscription_Medication_id"));
    	pemail.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Pemail"));
		patientage.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Patient_age"));
		med_name.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Medicine_name"));
		patientname.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Patient_name"));
		dateOfvisit.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Date_Of_Visit"));
		Treatid.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, Integer>("Treatment_id"));
		tNotes.setCellValueFactory(new PropertyValueFactory<Perscription_Medication, String>("Tnotes"));


		try {
			dataList = getDataPerscription_Medication();

			perscTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void setserach(ActionEvent event) throws ClassNotFoundException, SQLException {
    	connectDB();


   	 try {
			dataList1 =getDataSearch();
			perscTable.setItems(dataList1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
   	 datavisitsearch.setValue(null);		
   	 patientseacrh.clear();
   	 medicinesearch.clear();
   	 emailsearch.clear();
    }
    
    public ObservableList<Perscription_Medication> getDataSearch() throws ClassNotFoundException, SQLException { 
		 connectDB();
			ObservableList<Perscription_Medication> list = FXCollections.observableArrayList();
try {
				
				String sql1;

				sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Medicine_name = '"+medicinesearch.getText()+"' and Patient_name = '"+patientseacrh.getText()+"'and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";
				
				if(emailsearch.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Medicine_name = '"+medicinesearch.getText()+"' and Patient_name = '"+patientseacrh.getText()+"'and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
				if(medicinesearch.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Patient_name = '"+patientseacrh.getText()+"'and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
								
				if(patientseacrh.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Medicine_name = '"+medicinesearch.getText()+"' and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
				if(datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Medicine_name = '"+medicinesearch.getText()+"' and Patient_name = '"+patientseacrh.getText()+"';";

				}

				


				if(medicinesearch.getText().equals("") & patientseacrh.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "'and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
				if(medicinesearch.getText().equals("") & datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Patient_name = '"+patientseacrh.getText()+";;";

				}
	             
	            if(patientseacrh.getText().equals("")& datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "' and Medicine_name = '"+medicinesearch.getText()+"';";

				}
	           
	            
				
				if(emailsearch.getText().equals("") & medicinesearch.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Patient_name = '"+patientseacrh.getText()+"'and Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
				if(emailsearch.getText().equals("") & patientseacrh.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Medicine_name = '"+medicinesearch.getText()+"' and Date_Of_Visit like '%"+datavisitsearch.getValue()+"';";

				}
				
				if(emailsearch.getText().equals("") & datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where Medicine_name = '"+medicinesearch.getText()+"' and Patient_name = '"+patientseacrh.getText()+"';";

				}

		

				if(emailsearch.getText().equals("") & medicinesearch.getText().equals("")  & patientseacrh.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Date_Of_Visit like '%"+datavisitsearch.getValue()+"%';";

				}
				if(emailsearch.getText().equals("") & medicinesearch.getText().equals("")   & datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where Patient_name = '"+patientseacrh.getText()+"';";//1245
				}
				if(emailsearch.getText().equals("")   & patientseacrh.getText().equals("") & datavisitsearch.getValue() == null) {
					sql1 = "Select * from Perscription_Medication where  Medicine_name = '"+medicinesearch.getText()+"';";

				}
				
				if(patientseacrh.getText().equals("") & datavisitsearch.getValue() == null & medicinesearch.getText().equals("")) {
					sql1 = "Select * from Perscription_Medication where Pemail = '" + emailsearch.getText() + "';";
				}
				
				
				
				
				if(patientseacrh.getText().equals("") & datavisitsearch.getValue() == null & medicinesearch.getText().equals("") & emailsearch.getText().equals("") ) {
					sql1 = "Select * from Perscription_Medication;";
					
				}
				
				
			
				System.out.println(sql1);	 
			    Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					list.add(new Perscription_Medication(rs.getInt("Perscription_Medication_id"),rs.getString("Pemail"),rs.getInt("Patient_age"),
							rs.getString("Medicine_name"), rs.getString("Patient_name"), rs.getString("Date_Of_Visit"),rs.getInt("Treatment_id"),rs.getString("Tnotes")));
				}
				} catch (Exception e) {
				// TODO: handle exception
			}
		
			return list;
		}
    
		private void insertPerspectionMedicine(Perscription_Medication rc) {
				
				try {
						System.out.println("Insert into Perscription_Medication (Perscription_Medication_id,Pemail,Patient_age,Medicine_name,Patient_name,Date_Of_Visit,Treatment_id,Tnotes) values("
								+rc.getPerscription_Medication_id()
								+",'"+rc.getPemail()+"',"
								+rc.getPatient_age()+",'"+
								rc.getMedicine_name()+"','"
								+rc.getPatient_name()+"','"+rc.getDate_Of_Visit()+"',"+rc.getTreatment_id()+",'"+rc.getTnotes()+"')");
						
						
						connectDB();
						ExecuteStatement("Insert into Perscription_Medication (Perscription_Medication_id,Pemail,Patient_age,Medicine_name,Patient_name,Date_Of_Visit,Treatment_id,Tnotes) values("
								+rc.getPerscription_Medication_id()
								+",'"+rc.getPemail()+"',"
								+rc.getPatient_age()+",'"+
								rc.getMedicine_name()+"','"
								+rc.getPatient_name()+"','"+rc.getDate_Of_Visit()+"',"+rc.getTreatment_id()+",'"+rc.getTnotes()+"')");
						
						con.close();
						System.out.println("Connection closed " + dataList.size());
						
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
				}
			
	public ObservableList<Perscription_Medication> getDataPerscription_Medication() throws ClassNotFoundException, SQLException {
		String SQL;
	    connectDB();

		ObservableList<Perscription_Medication> list = FXCollections.observableArrayList();
		
		try {
			SQL = "select * from Perscription_Medication";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				list.add(new Perscription_Medication(rs.getInt("Perscription_Medication_id"),rs.getString("Pemail"),rs.getInt("Patient_age"),
						rs.getString("Medicine_name"), rs.getString("Patient_name"), rs.getString("Date_Of_Visit"),rs.getInt("Treatment_id"),rs.getString("Tnotes")));
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
