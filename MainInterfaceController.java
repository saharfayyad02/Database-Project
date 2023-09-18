package application;

import java.sql.Connection;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MainInterfaceController extends Application{
	
	
	
	ObservableList<String> Tables = FXCollections.observableArrayList("Patients","Diagnosis","Treatment",
			"Appointments","Perscription_Medication","Treatment_To_Patient");
	
	@FXML
    private ComboBox<String> tables;
	
	  @FXML
	    void Choose(ActionEvent event) {
	    		if(tables.getValue().toString().equals("Appointments")) {
	    			try {
	        			Parent root_3 = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
	        			Scene scene_3 = new Scene(root_3,1117, 700);
	        			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        			st.setScene(scene_3);
	        			st.setResizable(false);
	        			st.show();

	        		} catch (Exception e) {
	        			
	        		}

	    		}
	    		else if(tables.getValue().toString().equals("Patients")) {
	    			try {
	        			Parent root_3 = FXMLLoader.load(getClass().getResource("Patient.fxml"));
	        			Scene scene_3 = new Scene(root_3,1230, 710);
	        			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        			st.setScene(scene_3);
	        			st.setResizable(false);
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
	    				st.setResizable(false);
	    				st.show();

	    			} catch (Exception e) {

	    			}

	    		}

	    		
	    		else if(tables.getValue().toString().equals("Treatment")) {
	    			try {
	        			Parent root_3 = FXMLLoader.load(getClass().getResource("Treatment.fxml"));
	        			Scene scene_3 = new Scene(root_3,940, 700);
	        			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        			st.setScene(scene_3);
	        			st.setResizable(false);
	        			st.show();

	        		} catch (Exception e) {
	        			
	        		}

	    		}
	    		
	    		else if(tables.getValue().toString().equals("Perscription_Medication")) {
	    			try {
	        			Parent root_3 = FXMLLoader.load(getClass().getResource("PerscriptionMedication.fxml"));
	        			Scene scene_3 = new Scene(root_3,1330, 700);
	        			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        			st.setScene(scene_3);
	        			st.setResizable(false);
	        			st.show();

	        		} catch (Exception e) {
	        			
	        		}

	    		}
	    		else if(tables.getValue().toString().equals("Treatment_To_Patient")) {
	    			try {
	        			Parent root_3 = FXMLLoader.load(getClass().getResource("Treatment_To_Patient.fxml"));
	        			Scene scene_3 = new Scene(root_3,1370,700);
	        			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
	        			st.setScene(scene_3);
	        			st.setResizable(false);
	        			st.show();

	        		} catch (Exception e) {
	        			
	        		}

	    		}
	    		
	    		
	    		
	    }
	@FXML
	private void initialize() {
    	
		tables.setValue("Select Table");
		tables.setItems(Tables);
    	 	
	}


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
	}

}
