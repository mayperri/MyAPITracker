//
// Class: MBTATrackerAPI
//
// Description:
// This class connects the application with the MBTA API. Using
// the URL with the information from user input to pull the JSON 
// data and extracting the necessary information to calculate 
// count down time. 
//

package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;
import java.util.ArrayList;


public class MBTATrackerAPI {
	//Input : Two variables, directionId and stationId
	//Output: Number of minutes until the next train arrives
	
	public static ArrayList<String> mbtaAPI(int directionId, String stationId) throws IOException {
		// Formatting display
		String format = "%-20s%s%n";
		
		// Declare variables
		String countDown = null;
		String arrTime = null;
		String depTime = null;
		int direction = 0;
		String hours = null;
		String minutes = null;
		String seconds= null;
		
		//Initialize array for count down times
		ArrayList<String> countDownArrayList = new ArrayList<> ();
		
		
		//Determine local time an converting to String for later use. 
		LocalTime currentTime = LocalTime.now();
		System.out.printf(format, "Current time: ", currentTime);
		System.out.println();
		String currTimeForm = currentTime.toString();

		
		// Use try and catch to avoid exceptions
		try {
			// creating a url object and urlconnection object
			URL url = new URL("https://api-v3.mbta.com/predictions?page[offset]=0&page[limit]=3&filter[stop]=" + stationId + "&filter[route]=Red&filter[direction_id]=" + directionId + "&include=stop");

			URLConnection urlConnection = url.openConnection(); 

			// wrapping the urlconnection in a bufferedreader
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			StringBuilder sbuilderObj = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sbuilderObj.append(line);
			}
			
			
			// Using JSONObject parses through to convert JSON to string
			JSONObject jsonObj = new JSONObject(sbuilderObj.toString());
			JSONArray ja_data = jsonObj.getJSONArray("data");
			
			//loop to get all json objects from data json array
			for(int i=0; i<ja_data.length(); i++){
			    JSONObject jObj = ja_data.getJSONObject(i);
				//Returns the value mapped by name to string 
			    arrTime = jObj.getJSONObject("attributes").getString("arrival_time");
			    depTime = jObj.getJSONObject("attributes").getString("departure_time");
				direction = jObj.getJSONObject("attributes").getInt("direction_id");	
				
				//Parse arrival and departure time to string format
				String arrTimeForm = arrTime.substring(11, 19);
				String depTimeForm = depTime.substring(11, 19);
				
				countDown = CountDown.countdown(currTimeForm, arrTimeForm);

			    
				//Print arrival and departure time as appears in JSON
				System.out.println("MBTA API Data:");
				System.out.printf(format, "Arrival Time: ", arrTime);
				System.out.printf(format, "Departure Time: ", depTime); 
				System.out.printf(format, "Direction: ", (direction == 0 ? "South" : "North" ));
				System.out.println();
				
				//Print current time and call CountDown method and print results
				System.out.printf(format, "Countdown: ",  CountDown.countdown(currTimeForm, arrTimeForm));
				
				// Parse count down time by hours, minutes, seconds
				String[] split = countDown.split(":");
				hours = String.valueOf(split[0]);
				minutes = String.valueOf(split[1]);
				seconds = String.valueOf(split[2]);

				countDownArrayList.add(minutes);
				System.out.println();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		//return the number of minutes until the next train
		return countDownArrayList;
	}
}
