//---------------------------------------------------------------------------
//
//Where's The T
//MBTA prediction tracker that will display the time until the next train arrives for selected station
//
//Author: May Perriello
//Date: 05/03/2023
//Class: MET CS622
//Issues: None known
//
//Description:
//This program utilizes the MBTA API to extract next arrival 
//time for any selected station and direction and perform calculations 
// using the arrival time and the current time to display
//the time until the next train. This will all be presented in a
//javaFX application.
//
//Assumptions:
//There are disruptions in service
//

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main extends Application {
	   @Override
	   public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("MBTATracker.fxml"));

		   Scene scene = new Scene(root, 300, 275); // attach scene graph to scene
		   stage.setTitle("MBTA Tracker"); // displayed in window's title bar
		   stage.setScene(scene); // attach scene to stage
		   stage.show(); // display the stage
	   }

	   public static void main(String[] args) {
		   // create a TipCalculator object and call its start method
		   launch(args); 
	   }

		
}
 