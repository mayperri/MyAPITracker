//
// Class: MBTATrackerController
//
// Description:
// This class is the controller which provides the usability 
// for this application. 
//

package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MBTATrackerController {
	
	@FXML
    private ChoiceBox<String> choiceBoxDirection;

    @FXML
    private ChoiceBox<String> choiceBoxStation;
    
    @FXML
    private TextField countdownTextField1;

    @FXML
    private TextField countdownTextField2;

    @FXML
    private TextField countdownTextField3;
  
    
    @FXML
    void updateButtonPressed(ActionEvent event) throws IOException {
    	//Retrieve the data selected by the user and assign to station and direction variables
    	String station = new String(choiceBoxStation.getValue());
    	String direction = new String(choiceBoxDirection.getValue());
    	
    	//Print the station and direction selected
    	System.out.println("Station selected is " + station);
    	System.out.println("Direction selected is " + direction);
    	System.out.println();
    	

    	//Display count down time for next upcoming train on app
    	ArrayList<String> countDownTimes = MBTATrackerAPI.mbtaAPI(StationInformation.getDirection(direction), StationInformation.getStation(station));
    	
    	countdownTextField1.setText(countDownTimes.get(0) + " minutes");
    	countdownTextField2.setText(countDownTimes.get(1) + " minutes");
    	countdownTextField3.setText(countDownTimes.get(2) + " minutes");
 
    	
    }
    
    public void initialize() {
    	//Add choicebox for station
		choiceBoxStation.getItems().add("North Quincy");
		choiceBoxStation.getItems().addAll("Porter Station", "Park Street", "JFK/UMass");
		choiceBoxStation.setValue("North Quincy");
		
		//Add choicebox for direction
		choiceBoxDirection.getItems().addAll("North", "South");
		choiceBoxDirection.setValue("North");
    }

}
