package application;

import java.sql.Connection;


import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;


public class AppointmentsController implements Initializable{
	
	ObservableList<String> Tables = FXCollections.observableArrayList("Patients", "Diagnosis", "Treatment",
			"Appointments", "Perscription_Medication","Treatment_To_Patient");
	
   private ObservableList<Appointments> dataList; // to add data into the table view
   private ObservableList<Appointments> dataList1;
   public static ArrayList<Appointments> data = new ArrayList<>(); // to store the resulted data from the query
   public static ArrayList<Appointments> dataReserve = new ArrayList<>(); // to store the resulted data from the query


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
    private TableColumn<Appointments, String> AdateLastVisit;

    @FXML
    private TableColumn<Appointments, String> AdateNextVisit;

    @FXML
    private TableColumn<Appointments, String> Pemail;

    @FXML
    private TableColumn<Appointments, Integer> Aid;

    @FXML
    private TableColumn<Appointments, Integer> AnumberWaiting;

    @FXML
    private TableColumn<Appointments, String> Atime;
    
	@FXML
    private TableView<Appointments> appoinTable;
	

	@FXML
    private Button DeleteAppointment;
	
	  @FXML
	    private TextField getAid;

	    @FXML
	    private DatePicker getCurrentDate;

	    @FXML
	    private DatePicker getLastDate;

	    @FXML
	    private TextField getPemail;

	    @FXML
	    private TextField getTime;

	    @FXML
	    private TextField getnumberWaiting;
	
	  @FXML
	    private Button insert_appointment;
	  
	  @FXML
	    private Button updateData;
	  

	    @FXML
	    private TextField text; //text for time

	  
	  @FXML
	    private Button search; //id for button 

	    @FXML
	    private DatePicker searchapp; // A_Date_Of_Last_visit

	    @FXML
	    private DatePicker serachforcurrent;//A_Date_Of_Next_visit
	    
	    @FXML
		 private TextField Pemailsrch;

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
	    void setDeleteAppointment(ActionEvent event) {
	    	
	        ObservableList<Appointments> selectedIndices = appoinTable.getSelectionModel().getSelectedItems();
//	        if(selectedIndices.size() != 0 ) {
//	        	Appointments row = data.get(selectedIndices.get(0)); // get the selected row
//	            deleteRow(row);
//	            dataList.remove(selectedIndices.get(0));
//	            //  MStableView.refresh(); // update the tale view
//	            appoinTable.getItems().removeAll(appoinTable.getSelectionModel().getSelectedItem()); // remove all selected tables
//	        }
	        ArrayList<Appointments> rows = new ArrayList<>(selectedIndices);
	        rows.forEach(row -> {
       		appoinTable.getItems().remove(row); 
       		deleteRow(row); 
       		appoinTable.refresh();});

	    }
	    
	    private void deleteRow(Appointments row) {

	    	
	    
	    		// TODO Auto-generated method stub
	    		
	    		try {
	    			System.out.println("delete from Appointments where Appointment_id="+row.getAppointment_id() + ";");
	    			connectDB();
	    			ExecuteStatement("delete from Appointments where Appointment_id="+row.getAppointment_id() + ";");
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
		
		Aid.setCellValueFactory(new PropertyValueFactory<Appointments,Integer>("Appointment_id"));
    	Pemail.setCellValueFactory(new PropertyValueFactory<Appointments, String>("Pemail"));
    	AdateLastVisit.setCellValueFactory(new PropertyValueFactory<Appointments, String>("A_Date_Of_Last_visit"));
    	AdateNextVisit.setCellValueFactory(new PropertyValueFactory<Appointments, String>("A_Date_Of_Next_visit"));
    	Atime.setCellValueFactory(new PropertyValueFactory<Appointments, String>("Appointment_time"));
    	AnumberWaiting.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("A_Number_Of_Patient_On_waiting_List"));
    	
    	
		 try {
			dataList =getDataStudent();
			
			appoinTable.setItems(dataList);
			 //connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 insert_appointment.setOnAction((ActionEvent e) -> {
			    
			 	int id=Integer.valueOf(getAid.getText());
				String email = getPemail.getText();
				String lastDate = getLastDate.getValue().toString();
				String currentDate= getCurrentDate.getValue().toString();
				String time= getTime.getText();
				int numberWaiting =  Integer.parseInt(getnumberWaiting.getText());
				System.out.println(id + " "+ email + " "+ lastDate +" "+ currentDate + " "+ time +" " + numberWaiting);
				Appointments rc = new Appointments(id,email,lastDate,currentDate,time,numberWaiting);
				//dataList1.add(rc);
	        	insertAppointemnt(rc);
	        	getAid.clear();
	        	getPemail.clear();
	        	getTime.clear();
	        	getnumberWaiting.clear();
	        	getLastDate.getValue().equals(null);
	        	getCurrentDate.getValue().equals(null);
	        	
	        	
	        	
	        });
		 
		 updateData.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });
		 
		 tables.setValue("Select Table");
			tables.setItems(Tables);
		
	}
	
	public void Refresh() {
		Aid.setCellValueFactory(new PropertyValueFactory<Appointments,Integer>("Appointment_id"));
    	Pemail.setCellValueFactory(new PropertyValueFactory<Appointments, String>("Pemail"));
    	AdateLastVisit.setCellValueFactory(new PropertyValueFactory<Appointments, String>("A_Date_Of_Last_visit"));
    	AdateNextVisit.setCellValueFactory(new PropertyValueFactory<Appointments, String>("A_Date_Of_Next_visit"));
    	Atime.setCellValueFactory(new PropertyValueFactory<Appointments, String>("Appointment_time"));
    	AnumberWaiting.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("A_Number_Of_Patient_On_waiting_List"));
    	
    	
		 try {
			dataList =getDataStudent();
			
			appoinTable.setItems(dataList);
			 //connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//action for search button
    @FXML
    void searchtime(ActionEvent event) throws ClassNotFoundException, SQLException {

    	connectDB();

    	 try {
 			dataList1 =getDataSearch();
 			appoinTable.setItems(dataList1);
 		} catch (ClassNotFoundException | SQLException e) {
 			// TODO Auto-generated catch block
 			//e.printStackTrace();
 		}
    		searchapp.setValue(null);
			serachforcurrent.setValue(null);
			text.clear();
			Pemailsrch.clear();
    }



 public ObservableList<Appointments> getDataSearch() throws ClassNotFoundException, SQLException { 
	 connectDB();
		ObservableList<Appointments> list = FXCollections.observableArrayList();
		try {
			
			String sql1;

				sql1 = "Select * from Appointments where Pemail = '"+Pemailsrch.getText() +"' and A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%' and Appointment_time = '"+text.getText()+"' and A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%';";

				if(text.getText().equals("")) { //true
				sql1 = "Select * from Appointments where A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%'and A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%'and Pemail = '"+Pemailsrch.getText() +"';";

			 }
				 if(searchapp.getValue() == null) { //true
					sql1 = "Select * from Appointments where Appointment_time = '"+text.getText()+"' and A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%' and Pemail = '"+Pemailsrch.getText() +"';";
				}
				 if(serachforcurrent.getValue() == null) { //true
					sql1 = "Select * from Appointments where A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%' and Appointment_time = '"+text.getText()+"'and Pemail = '"+Pemailsrch.getText() +"';";

				}
				 if(Pemailsrch.getText().equals("")) {
						sql1 = "Select * from Appointments where  A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%' and Appointment_time = '"+text.getText()+"' and A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%';";

				 }
				 
				 if(serachforcurrent.getValue()==null & searchapp.getValue() == null) { //true
					sql1 = "Select * from Appointments where Appointment_time = '"+text.getText()+"' and Pemail = '"+Pemailsrch.getText() +"';";

				}
				 if(serachforcurrent.getValue()==null & text.getText().equals("")) { //true
					sql1 = "Select * from Appointments where A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%' and Pemail = '"+Pemailsrch.getText() +"';";

				}
				 if(text.getText().equals("") & searchapp.getValue()==null) {
					sql1 = "Select * from Appointments where A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%' and Pemail = '"+Pemailsrch.getText() +"';";
					
				}
				 if(serachforcurrent.getValue()==null & Pemailsrch.getText().equals("")) {
						sql1 = "Select * from Appointments where Appointment_time = '"+text.getText()+"' and A_Date_Of_Last_visit like '%"+searchapp.getValue()+"%';";

				 }
				 
				 if(Pemailsrch.getText().equals("") & text.getText().equals("")) {
						sql1 = "Select * from Appointments where A_Date_Of_Last_visit like '%" + searchapp.getValue() + "%' and  A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%';";

				 }
				 if(Pemailsrch.getText().equals("") & searchapp.getValue() == null) {
						sql1 = "Select * from Appointments where Appointment_time = '"+text.getText()+"' and A_Date_Of_Next_visit like '%"+serachforcurrent.getValue()+"%';";

				 }
				 
				 
				 if(text.getText().equals("") & searchapp.getValue()==null & serachforcurrent.getValue()==null) {//123
						sql1 = "Select * from Appointments where  Pemail = '"+Pemailsrch.getText() +"';";
				 }
				 
				 if(text.getText().equals("") & serachforcurrent.getValue()==null & Pemailsrch.getText().equals("")) {//134
						sql1 = "Select * from Appointments where  A_Date_Of_Last_visit = '"+searchapp.getValue() +"';";
				 }
				 
				 if(text.getText().equals("") & searchapp.getValue()==null & Pemailsrch.getText().equals("")) {//234
						sql1 = "Select * from Appointments where  A_Date_Of_Next_visit = '"+serachforcurrent.getValue() +"';";
				 }
				 if( serachforcurrent.getValue()==null & searchapp.getValue()==null & Pemailsrch.getText().equals("")) {//234
						sql1 = "Select * from Appointments where  Appointment_time = '"+text.getText() +"';";
				 }
				 
				 if(text.getText().equals("") & searchapp.getValue()==null & serachforcurrent.getValue()==null & Pemailsrch.getText().equals("")) {
					 sql1 = "Select * from Appointments;" ;
				 }
				 	 
			System.out.println(sql1);	 
		    Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				list.add(new Appointments(rs.getInt("Appointment_id"),rs.getString("Pemail"),
						rs.getString("A_Date_Of_Last_visit"), rs.getString("A_Date_Of_Next_visit"), rs.getString("Appointment_time"),rs.getInt("A_Number_Of_Patient_On_waiting_List")));
			}
			} catch (Exception e) {
			// TODO: handle exception
		}
	
		return list;
	}
	private void insertAppointemnt(Appointments rc) {
		
		try {
				System.out.println("Insert into Appointments (Appointment_id,Pemail,A_Date_Of_Last_visit,A_Date_Of_Next_visit,Appointment_time,A_Number_Of_Patient_On_waiting_List) values("+
						+rc.getAppointment_id()+",'"
						+rc.getPemail()+"',"
						+"'"+ rc.getA_Date_Of_Last_visit() +"',"
						+"'"+rc.getA_Date_Of_Next_visit()+"',"
						+"'"+rc.getAppointment_time()+"',"+rc.getA_Number_Of_Patient_On_waiting_List()+")");
				
				connectDB();
				ExecuteStatement("Insert into Appointments (Appointment_id,Pemail,A_Date_Of_Last_visit,A_Date_Of_Next_visit,Appointment_time,A_Number_Of_Patient_On_waiting_List) values("+
						+rc.getAppointment_id()+",'"
						+rc.getPemail()+"',"
						+"'"+ rc.getA_Date_Of_Last_visit() +"',"
						+"'"+rc.getA_Date_Of_Next_visit()+"',"
						+"'"+rc.getAppointment_time()+"',"+rc.getA_Number_Of_Patient_On_waiting_List()+")");
			
				
				
			

				con.close();
				System.out.println("Connection closed " + dataList.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
	
	
	
		
		public ObservableList<Appointments> getDataStudent() throws ClassNotFoundException, SQLException {
			String SQL;
		    connectDB();

			ObservableList<Appointments> list = FXCollections.observableArrayList();
			
			try {
				SQL = "select * from Appointments";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				
				while (rs.next()) {
					list.add(new Appointments(rs.getInt("Appointment_id"),rs.getString("Pemail"),
							rs.getString("A_Date_Of_Last_visit"), rs.getString("A_Date_Of_Next_visit"), rs.getString("Appointment_time"),rs.getInt("A_Number_Of_Patient_On_waiting_List")));
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