public class CalculateStatistics extends FollowLight {
	
	/* This class is used for the final calculations of the statistics.
	 * These statistics will be the output for the console at the end of the program (if wanted by the user).
	 * We initialise a local integer variable which represents our index numbers for the array.
	 * We add up all of the speedList's values at each index number until it reaches the end of the array.
	 * Following this, we divide by the size of the array list. Assuming the index is 0, we use exception handling as we know averageSpeed should be 0.
	 * We also add up all of the values at each index of the stopTime array list to the stopTime variable.
	 * At the end, we calculate travelTime after converting endTime and startTime to seconds (from milliseconds) and subtracting that value from stopTime.
	 */
	
	static long travelTime;
	static int averageSpeed = 0;
	static int stopTime = 0;

	public static void main (String args[]){
		
		int i = 0; //Placeholder local variable as array list's index
		for (i = 0; i < speedList.size(); i++)
		{
			averageSpeed += speedList.get(i); //Add up speeds in the arrayList
		}
		try
		{
			averageSpeed /= speedList.size(); //Divide by number of speeds
		}
		catch (ArithmeticException averageSpeedIs0)
		{
			averageSpeed = 0;
		}
		for (int x = 0; x < stopTimeList.size(); x++)
		{
			stopTime += stopTimeList.get(x);
		}
		travelTime = ((endTime - startTime)/1000) - stopTime; //Program duration minus how long the finch was not moving
	}
}