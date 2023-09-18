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

public class TreatmentController implements Initializable {

	public static ArrayList<Treatment> data = new ArrayList<Treatment>(); // to store the resulted data from the query
	private ObservableList<Treatment> dataList; // to add data into the table view
	private ObservableList<Treatment> dataList1; 
	
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
    private TableColumn<Treatment, Double> paidamount;

    
    @FXML
    private TableColumn<Treatment, Double> remainingamount;

    @FXML
    private TableColumn<Treatment, String> typetreat;

   
    @FXML
    private TableView<Treatment> treatmentTable;
    
    @FXML
    private TableColumn<Treatment, Integer> D_id;
    
    @FXML
    private TableColumn<Treatment, Integer> Treatid;

  
    
    @FXML
    private TextField getcostpaid;

    @FXML
    private TextField getcostremain;

    @FXML
    private TextField getdid;

    @FXML
    private TextField getTID;

   
    @FXML
    private TextField getttype;
    
    @FXML
    private Button insert_treatment;
    
    @FXML
    private Button searchtret;
    
    @FXML
    private TextField type;
    
    @FXML
    private TextField treatid;

    
    @FXML
    private TextField didsearch;
    
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
    void setDeleteTreatment(ActionEvent event) {

    	ObservableList<Treatment> selectedIndices = treatmentTable.getSelectionModel().getSelectedItems();
        ArrayList<Treatment> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
        treatmentTable.getItems().remove(row); 
 		deleteRow(row); 
 		treatmentTable.refresh();});

    }
    
    private void deleteRow(Treatment row) {  
    		// TODO Auto-generated method stub
    		
    		try {
    			System.out.println("delete from Treatment where Treatment_id="+row.getTreatment_id()+ ";");
    			connectDB();
    			ExecuteStatement("delete from Treatment where Treatment_id="+row.getTreatment_id()+ ";");
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
				Scene scene_3 = new Scene(root_3,1230, 710);
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
    			Scene scene_3 = new Scene(root_3,1370, 700);
    			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
    			st.setScene(scene_3);
    			st.show();

    		} catch (Exception e) {
    			
    		}

		}
	}


    @Override
	public void initialize(java.net.URL location, ResourceBundle resources) {

    	Treatid.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("Treatment_id"));
    	D_id.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("Did"));
    	typetreat.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Ttype"));
		paidamount.setCellValueFactory(new PropertyValueFactory<Treatment, Double>("Treatment_Cost_Paid_Amount"));
		remainingamount.setCellValueFactory(new PropertyValueFactory<Treatment, Double>("Treatment_Cost_Remaining_Amount"));

		try {
			dataList = getDataTreatment();

			treatmentTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insert_treatment.setOnAction((ActionEvent e) -> {
		    
			int TID=Integer.parseInt(getTID.getText());
			int Daignosisid =  Integer.parseInt(getdid.getText());
			String TYPE= getttype.getText();
			double costpaid= Double.parseDouble(getcostpaid.getText());
			double costremain = Double.parseDouble(getcostremain.getText());
			System.out.println(TID + " "+ Daignosisid +" " + TYPE + " "+ costpaid +" " + costremain);
			Treatment rc = new Treatment(TID,Daignosisid,TYPE,costpaid,costremain);
			//dataList1.add(rc);
			insertTreatment(rc);
			getTID.clear();
			getdid.clear();
			getttype.clear();
			getcostpaid.clear();
			getcostremain.clear();
        	
        	
        });
		
		 refresh.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });
		tables.setValue("Select Table");
		tables.setItems(Tables);

	}
    

    @FXML
    void setsearchtretment(ActionEvent event) throws ClassNotFoundException, SQLException {
    	connectDB();

    	 try {
 			dataList1 =getDataSearch();
 			treatmentTable.setItems(dataList1);
 		} catch (ClassNotFoundException | SQLException e) {
 			// TODO Auto-generated catch block
 			//e.printStackTrace();
 		}
    	 didsearch.clear();
    	 type.clear();
    }
    
    public ObservableList<Treatment> getDataSearch() throws ClassNotFoundException, SQLException { 
		 connectDB();
			ObservableList<Treatment> list = FXCollections.observableArrayList();
			try {
				
				String sql1;
			
					sql1 = "Select * from Treatment where Did = "+didsearch.getText()+" and Ttype = '"+type.getText()+"';";
				
				
					if(didsearch.getText().equals("")) {
						sql1 = "Select * from Treatment where Ttype = '"+type.getText()+"';";

					}
					if(type.getText().equals("")) {
						sql1 = "Select * from Treatment where Did = "+didsearch.getText()+";";

					}
					if(type.getText().equals("") & didsearch.getText().equals("")) {
						sql1 = "Select * from Treatment;";

					}
					System.out.println(sql1);
			    Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					list.add(new Treatment(rs.getInt("Treatment_id"),rs.getInt("Did"),
							 rs.getString("Ttype"), rs.getDouble("Treatment_Cost_Paid_Amount"),rs.getDouble("Treatment_Cost_Remaining_Amount")));
				}
				} catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
    public void Refresh() {
    	Treatid.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("Treatment_id"));
    	D_id.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("Did"));
    	typetreat.setCellValueFactory(new PropertyValueFactory<Treatment, String>("Ttype"));
		paidamount.setCellValueFactory(new PropertyValueFactory<Treatment, Double>("Treatment_Cost_Paid_Amount"));
		remainingamount.setCellValueFactory(new PropertyValueFactory<Treatment, Double>("Treatment_Cost_Remaining_Amount"));

		try {
			dataList = getDataTreatment();

			treatmentTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void insertTreatment(Treatment rc){
		
		try {
				System.out.println("Insert into Treatment (Treatment_id,Did,Ttype,Treatment_Cost_Paid_Amount,Treatment_Cost_Remaining_Amount) values("
						+rc.getTreatment_id()+","
						+rc.getDid()+",'"+
						rc.getTtype()+"',"+rc.getTreatment_Cost_Paid_Amount()+","+rc.getTreatment_Cost_Remaining_Amount()+")");
				
				
				connectDB();
				ExecuteStatement("Insert into Treatment (Treatment_id,Did,Ttype,Treatment_Cost_Paid_Amount,Treatment_Cost_Remaining_Amount) values("
						+rc.getTreatment_id()+","
						+rc.getDid()+",'"+
						rc.getTtype()+"',"+rc.getTreatment_Cost_Paid_Amount()+","+rc.getTreatment_Cost_Remaining_Amount()+")");
				
				con.close();
				System.out.println("Connection closed " + dataList.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
	
	public ObservableList<Treatment> getDataTreatment() throws ClassNotFoundException, SQLException {
		String SQL;
	    connectDB();

		ObservableList<Treatment> list = FXCollections.observableArrayList();
		
		try {
			SQL = "select * from Treatment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				list.add(new Treatment(rs.getInt("Treatment_id"),rs.getInt("Did")
						, rs.getString("Ttype"), rs.getDouble("Treatment_Cost_Paid_Amount"),rs.getDouble("Treatment_Cost_Remaining_Amount")));
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
