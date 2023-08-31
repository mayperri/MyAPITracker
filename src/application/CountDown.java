//
// Class: CountDown
//
// Description:
// Using the extracted arrival time and the current time, 
// this class takes the difference from these two times to 
// determine the how long until the next expected train 
// will arrive. This class uses the java.time import to parse the 
// formatted time. The return from this class is ultimately
// the difference from these two extracted time. 
//

package application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CountDown {    
	//Input : two strings, arrival time and current time
	//Output: Difference between current time and arrival time
	public static String countdown(final String first, final String second)
		{
			//Use the two determined times currTime & arrTime and parses these strings with a DateTimeFormatter
		    final LocalTime currTime = LocalTime.parse(first, DateTimeFormatter.ISO_LOCAL_TIME);
		    final LocalTime arrTime = LocalTime.parse(second, DateTimeFormatter.ISO_LOCAL_TIME);

		    //Determine that the times are updated and not null and in the correct format
		    final LocalDateTime currDateTime = LocalDateTime.of(LocalDate.now(), currTime);
		    final LocalDateTime arrDateTime = LocalDateTime.of(
		        LocalDate.now().plusDays(second.equals("00:00:00") ? 1 : 0),
		        arrTime
		    );

		    //Determine the difference for hours, minutes, and time
		    final Duration diff = Duration.between(currDateTime, arrDateTime).abs();

		    //Format data to display in HH:MM:SS structure
		    return String.format(
		        "%02d:%02d:%02d",
		        diff.toDaysPart() < 1 ? diff.toHoursPart() : 24,
		        diff.toMinutesPart(),
		        diff.toSecondsPart()
		    );
		}


}
