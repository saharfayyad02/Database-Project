package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//AppointmentsController obj=new AppointmentsController();
		    //obj.start(primaryStage);
			AnchorPane root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
			primaryStage.setTitle("Dental Clinic DataBase");
		     Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
