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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Treatment_To_PatientController implements Initializable{

	public static ArrayList<Treatment_To_Patient> data = new ArrayList<Treatment_To_Patient>(); // to store the resulted data from the query
	private ObservableList<Treatment_To_Patient> dataList; // to add data into the table view
    
    private ObservableList<Treatment_To_Patient> dataList1; // to add data into the table view
    

	


	ObservableList<String> Tables = FXCollections.observableArrayList("Patients", "Diagnosis", "Treatment","Treatment_To_Patient",
			"Appointments", "Perscription_Medication");
	
	
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "Sahar@1234";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "Clinic";
	private Connection con;
	
	 @FXML
	    private TableColumn<Treatment_To_Patient, Integer> Treatid;

	    @FXML
	    private TableView<Treatment_To_Patient> TreatmentToPatoentTable;

	    @FXML
	    private Button homepage;

	    @FXML
	    private TableColumn<Treatment_To_Patient, String> pemail;

	    @FXML
	    private ComboBox<String> tables;

	    @FXML
	    private TableColumn<Treatment_To_Patient, Integer> teethnum;
	    
	   
	    @FXML
	    private TableColumn<Treatment_To_Patient, Integer> diagnosisid;

	    
	    @FXML
	    private TextField getPemail;

	  
	    @FXML
	    private TextField getteethnum;

	    @FXML
	    private TextField gettreatid;
	    
	    @FXML
	    private Button insertTreat;
	    
	    @FXML
	    private Button refresh;
	    
	    @FXML
	    private TextField getdid;
	    

		  @FXML
		    private Button searchtretment;
		  @FXML
		    private TextField Treatment_Patient_id_search;

		    @FXML
		    private TextField Treatment_id_search;
		    @FXML
		    private TextField emailsearch;




 
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
    void setDeleteTreatment(ActionEvent event) {

    	ObservableList<Treatment_To_Patient> selectedIndices = TreatmentToPatoentTable.getSelectionModel().getSelectedItems();
        ArrayList<Treatment_To_Patient> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
        TreatmentToPatoentTable.getItems().remove(row); 
 		deleteRow(row); 
 		TreatmentToPatoentTable.refresh();});

    }
    
    private void deleteRow(Treatment_To_Patient row) {  
    		// TODO Auto-generated method stub
    		
    		try {
    			System.out.println("delete from Treatment_To_Patient where Pemail='"+row.getPemail() +"' and Did=" +row.getDid() +" and Treatment_id=" +row.getTreatment_id()+ ";");
    			connectDB();
    			ExecuteStatement("delete from Treatment_To_Patient where Pemail='"+row.getPemail() +"' and Did=" +row.getDid() +" and Treatment_id=" +row.getTreatment_id()+ ";");
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
		
		else if (tables.getValue().toString().equals("Treatment_To_Patient")) {
			try {
				Parent root_3 = FXMLLoader.load(getClass().getResource("Treatment_To_Patient.fxml"));
				Scene scene_3 = new Scene(root_3, 1370, 700);
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
	}

	

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

		diagnosisid.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Did"));
		pemail.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, String>("Pemail"));
		teethnum.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Teeth_Number"));
		Treatid.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Treatment_id"));

		try {
			dataList = getDataTreatment_To_Patient();

			TreatmentToPatoentTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insertTreat.setOnAction((ActionEvent e) -> {
			    
			 	int id=Integer.valueOf(getdid.getText());
				String email = getPemail.getText();
				int TeethNumberr = Integer.valueOf(getteethnum.getText());
		        int treatmentid=Integer.valueOf(gettreatid.getText());
				System.out.println(id + " "+ email + " "+ TeethNumberr +" "+ treatmentid);
				Treatment_To_Patient rc = new Treatment_To_Patient(email,id,TeethNumberr,treatmentid);
				//dataList1.add(rc);
				insertTreatment_To_Patient(rc);
				getdid.clear();
	        	getPemail.clear();
	        	getteethnum.clear();
	        	gettreatid.clear();
	        	
	        	
	        });
		
		 refresh.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });

		tables.setValue("Select Table");
		tables.setItems(Tables);

	}
	
	
	

     @FXML
      void stsearchtretment(ActionEvent event) throws ClassNotFoundException, SQLException {
  	   connectDB();
  	   try {
  			dataList1 =getDataSearch();
  			TreatmentToPatoentTable.setItems(dataList1);
  		} catch (ClassNotFoundException | SQLException e) {
  			// TODO Auto-generated catch block
  			//e.printStackTrace();
  		}
  	   Treatment_Patient_id_search.clear();
  	   Treatment_id_search.clear();
  	   emailsearch.clear();
  	   
      }
     public ObservableList<Treatment_To_Patient> getDataSearch() throws ClassNotFoundException, SQLException { 
		 connectDB();
			ObservableList<Treatment_To_Patient> list = FXCollections.observableArrayList();
			try {
				
				String sql1;
			
					sql1 = "Select * from Treatment_To_Patient where Did = "+Treatment_Patient_id_search.getText()+" and Treatment_id = "+Treatment_id_search.getText()+" "
							+ "and Pemail ='"+emailsearch.getText()+"';";
				
				if(Treatment_Patient_id_search.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient where Treatment_id = '"+Treatment_id_search.getText()+" and Pemail ='"+emailsearch.getText()+"';";

				}
				if(Treatment_id_search.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient where Treatment_Patient_id = "+Treatment_Patient_id_search.getText()+" and Pemail ='"+emailsearch.getText()+"';";
	
				}
				if(emailsearch.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient where Treatment_Patient_id = "+Treatment_Patient_id_search.getText()+" and Treatment_id = '"+Treatment_id_search.getText()+"';";
	
				}
				
				if(Treatment_Patient_id_search.getText().equals("") & emailsearch.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient where Treatment_id = "+Treatment_id_search.getText()+";";

				}
				if(Treatment_id_search.getText().equals("")& emailsearch.getText().equals("") ) {
					sql1 = "Select * from Treatment_To_Patient where Did = "+Treatment_Patient_id_search.getText()+";";

				}
				if(Treatment_Patient_id_search.getText().equals("") & Treatment_id_search.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient where Pemail ='"+emailsearch.getText()+"';";

				}
				
              if(Treatment_Patient_id_search.getText().equals("") & Treatment_id_search.getText().equals("") & emailsearch.getText().equals("")) {
					sql1 = "Select * from Treatment_To_Patient ;";

              }

				
					
				System.out.println(sql1);
			    Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					list.add(new Treatment_To_Patient(rs.getString("Pemail"),rs.getInt("Did"),
							rs.getInt("Teeth_Number"),rs.getInt("Treatment_id")));
				}
				} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
	
	
	public ObservableList<Treatment_To_Patient> getDataTreatment_To_Patient() throws ClassNotFoundException, SQLException {
		String SQL;
	    connectDB();

		ObservableList<Treatment_To_Patient> list = FXCollections.observableArrayList();
		
		try {
			SQL = "select * from Treatment_To_Patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				list.add(new Treatment_To_Patient(rs.getString("Pemail"),rs.getInt("Did"),
						rs.getInt("Teeth_Number"),rs.getInt("Treatment_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public void Refresh() {
		diagnosisid.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Did"));
		pemail.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, String>("Pemail"));
		teethnum.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Teeth_Number"));
		Treatid.setCellValueFactory(new PropertyValueFactory<Treatment_To_Patient, Integer>("Treatment_id"));

		try {
			dataList = getDataTreatment_To_Patient();

			TreatmentToPatoentTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void insertTreatment_To_Patient(Treatment_To_Patient rc) {
		
		try {
				System.out.println("Insert into Treatment_To_Patient (Pemail,Did,Teeth_Number,Treatment_id) values("
					
						+"'"+rc.getPemail()+"',"+
						+rc.getDid()+","
						+rc.getTeeth_Number()+","+
						rc.getTreatment_id()+")");
				
				
				connectDB();
				ExecuteStatement("Insert into Treatment_To_Patient (Pemail,Did,Teeth_Number,Treatment_id) values("
					
						+"'"+rc.getPemail()+"',"+
						+rc.getDid()+","
						+rc.getTeeth_Number()+","+
						rc.getTreatment_id()+")");
				
				
				con.close();
				System.out.println("Connection closed " + dataList.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
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
