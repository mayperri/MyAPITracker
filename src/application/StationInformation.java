//
// Class: StationInformation
//
// Description:
// This class takes the input of the user and sets the directionId 
// and stationId which will then be used to complete the URL for the API. 
// Methods in setStation, getStation, setDirection, getDirection. 
//

package application;

public class StationInformation {
	// Declare public strings station and direction
	public String station;
	public String direction;

	// Switch statement for the user friendly names stations to the MBTA identifiers
	public static String getStation(String station) {
		String stationID;
	     switch (station) {
	         case "North Quincy":
	             stationID = "place-nqncy";
	             break;
	         case "Porter Station":
	        	 stationID = "place-portr";
	             break;
	         case "Park Street":
	        	 stationID = "place-pktrm";
	             break;
	         case "JFK/UMASS":
	             stationID = "place-jfk";
	             break;
	         default:
	            throw new IllegalArgumentException("Invalid station: " + station);
	     }
	     return stationID;
	}

	//Set statement for station
	public void setStation(String station) {
		this.station = station;
	}

	// Switch statement for the user friendly names directions to the MBTA identifiers
	public static int getDirection(String direction) {
		int directionID;
	     switch (direction) {
	         case "North":
	             directionID = 1;
	             break;
	         case "South":
	        	 directionID = 0;
	             break;
	         default:
	            throw new IllegalArgumentException("Invalid direction: " + direction);
	     }
	     return directionID;
	}

	//Set statement for direction
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
