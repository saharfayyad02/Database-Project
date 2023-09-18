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

public class DiagnosisController implements Initializable  {
	
	public static ArrayList<Diagnosis> data = new ArrayList<Diagnosis>(); // to store the resulted data from the query
	private ObservableList<Diagnosis> dataList; // to add data into the table view
	private ObservableList<Diagnosis> dataList1;

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
    private TableColumn<Diagnosis, Integer> did;

    @FXML
    private TableColumn<Diagnosis, String> dtype;
    
    @FXML
    private TableView<Diagnosis> DiagnosisTable;
    
    @FXML
    private TextField getDid;

    @FXML
    private TextField getDtype;
    
    @FXML
    private Button insert_diagnosis;
    
    @FXML
    private TextField didsearch;
  @FXML
    private TextField dtypesearch;
  
   @FXML
    private Button searchdignosis;
   
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
    void setDeleteDiagnosis(ActionEvent event) {

    	ObservableList<Diagnosis> selectedIndices = DiagnosisTable.getSelectionModel().getSelectedItems();
        ArrayList<Diagnosis> rows = new ArrayList<>(selectedIndices);
        rows.forEach(row -> {
        DiagnosisTable.getItems().remove(row); 
 		deleteRow(row); 
 		DiagnosisTable.refresh();});

    }
    
    private void deleteRow(Diagnosis row) {  
    		// TODO Auto-generated method stub
    		
    		try {
    			System.out.println("delete from Diagnosis where Did="+row.getDid() + ";");
    			connectDB();
    			ExecuteStatement("delete from Diagnosis where Did="+row.getDid() + ";");
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

		did.setCellValueFactory(new PropertyValueFactory<Diagnosis, Integer>("Did"));
		dtype.setCellValueFactory(new PropertyValueFactory<Diagnosis, String>("Dtype"));
		

		try {
			dataList = getDataDiagnosis();

			DiagnosisTable.setItems(dataList);
			// connectDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insert_diagnosis.setOnAction((ActionEvent e) -> {
			    
			 	int id=Integer.valueOf(getDid.getText());
				String dtype = getDtype.getText();
			
			
				System.out.println(id + " "+ dtype);
				Diagnosis rc = new Diagnosis(id,dtype);
				//dataList1.add(rc);
				insertDiagnosis(rc);
				getDid.clear();
				getDtype.clear();
				
			
	        	
	        });
		
		
		 refresh.setOnAction((ActionEvent e) -> {        	 
	        	Refresh();
	        });

		tables.setValue("Select Table");
		tables.setItems(Tables);

	}
	
	
	   @FXML
	    void setsearchdiagnosis(ActionEvent event) throws ClassNotFoundException, SQLException {
		   connectDB();

	    	 try {
	 			dataList1 =getDataSearch();
	 			DiagnosisTable.setItems(dataList1);
	 		} catch (ClassNotFoundException | SQLException e) {
	 			// TODO Auto-generated catch block
	 			//e.printStackTrace();
	 		}

	    	
	    }
	   
	   public ObservableList<Diagnosis> getDataSearch() throws ClassNotFoundException, SQLException { 
			 connectDB();
				ObservableList<Diagnosis> list = FXCollections.observableArrayList();
				try {
					
					String sql1;
				//	searchapp.getValue();
				//	if(search_box.getText().matches("[0-9]+")) {
						sql1 = "Select * from Diagnosis where Did =" + didsearch.getText() + " and Dtype = '"+dtypesearch.getText()+"';";
						
						String[] split = dtypesearch.getText().split(" ");
						if(split.length == 1) {
								sql1 = "Select * from Diagnosis where Dtype like '%" + split[0] + "%' and Did ="+didsearch.getText()+";";
							}
						if(didsearch.getText().equals("")) {
							
							sql1 = "Select * from Diagnosis where Dtype = '"+dtypesearch.getText()+"';";
							String[] split1 = dtypesearch.getText().split(" ");
							if(split.length == 1) {
									sql1 = "Select * from Diagnosis where Dtype like '%" + split1[0] + "%';";
								}

						}
						if(dtypesearch.getText().equals("")) {
							sql1 = "Select * from Diagnosis where Did =" + didsearch.getText() + ";";

						}
						if(dtypesearch.getText().equals("") & didsearch.getText().equals("")) {
							sql1 = "Select * from Diagnosis ;";
	
						}

						 	 
				    Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql1);
					while (rs.next()) {
						list.add(new Diagnosis(rs.getInt("Did"),rs.getString("Dtype")));
					}
					} catch (Exception e) {
					// TODO: handle exception
				}
			
				return list;
			}
	   
	   public void Refresh() {

			did.setCellValueFactory(new PropertyValueFactory<Diagnosis, Integer>("Did"));
			dtype.setCellValueFactory(new PropertyValueFactory<Diagnosis, String>("Dtype"));
			

			try {
				dataList = getDataDiagnosis();

				DiagnosisTable.setItems(dataList);
				// connectDB();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	
private void insertDiagnosis(Diagnosis rc) {
		
		try {
				System.out.println("Insert into Diagnosis (Did,Dtype) values("
						+rc.getDid()+",'"
						+rc.getDtype()+"')");
				
				
				connectDB();
				ExecuteStatement("Insert into Diagnosis (Did,Dtype) values("
						+rc.getDid()+",'"
						+rc.getDtype()+"')");
				
				con.close();
				System.out.println("Connection closed " + dataList.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
	
	public ObservableList<Diagnosis> getDataDiagnosis() throws ClassNotFoundException, SQLException {
		String SQL;
	    connectDB();

		ObservableList<Diagnosis> list = FXCollections.observableArrayList();
		
		try {
			SQL = "select * from Diagnosis";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				list.add(new Diagnosis(rs.getInt("Did"),rs.getString("Dtype")));
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