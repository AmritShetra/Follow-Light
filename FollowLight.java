import java.awt.Color;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.ArrayList;

public class FollowLight {
			
	/* Creating the main variables that will be used all throughout the program.
	 * We've got some variables placed in the other class too.
	 * We initialise our Finch and two array lists which hold the averageSpeed and the stopTime.
	 * We also have endTime, startTime and numberOfStops.
	 * There's also thresholdValue which is set to 80, as it's the amount of light that we want to receive for the Finch to move.
	 */
			
	static Finch playerFinch = new Finch(); //Initialises the player Finch
	static ArrayList<Integer> speedList = new ArrayList<Integer>(); //Initialise arrayList which will contain Finch speeds
	static ArrayList<Double> stopTimeList = new ArrayList<Double>(); //How long the finch has stopped
	static long endTime = 0; //Time at which the program ends
	static long startTime = 0; //Time at which the program begins in milliseconds
	static int numberOfStops = 0; //Number of times the Finch has stopped
	static int thresholdValue = 80; //The amount of light given off by a torch
	static boolean gameOver = false;
	
	/* Main program logic: 
	 * Whilst the game isn't over and the light sensors are above the thresholdValue, the Finch will move, light up and make a sound. 
	 * It will also conduct obstacle checks, which redirect to a separate method.
	 * But when the light stops/there is an obstacle, the Finch will stop moving and redirect to the redLight() method.
	 * This then redirects to the sixSecondsCheck() method and after 6 seconds, the game is over and you get to see the statistics (if you want).
	 */
	
	public static void main (String args[]) {
		System.out.println("Welcome to Follow Light!");
		System.out.println("Hold a torch infront of the Finch and it will follow!");
		startTime = System.currentTimeMillis(); //Time at which the program begins in milliseconds
		while (!gameOver)
		{
			if (playerFinch.getLeftLightSensor() > thresholdValue | playerFinch.getRightLightSensor() > thresholdValue) //At least 1 light sensor is above thresholdValue
			{
				Movement.main(args);
			}
			
			if (playerFinch.getLeftLightSensor() < thresholdValue && playerFinch.getRightLightSensor() < thresholdValue) //If there is no light
			{
				redLight();
			}
			obstacleCheck(); //Method to check if there's an obstacle
		} //End while loop
		
		if (gameOver)
		{
			endTime = System.currentTimeMillis(); //Record the endTime of the program
			CalculateStatistics.main(args); //Method to calculate averageSpeed, travelTime and stopTime
			UserInterface.main(args); //Method to print statistics if required and end the program
		}
	} //End main logic
	
	public static void greenLight() //Occurs is light is present, turns Finch green and buzzes
	{
		playerFinch.setLED(Color.green); //Set beak colour to green
		playerFinch.buzz(200, 5000); //Make a sound
	}
			
	public static void redLight() //Occurs is light is not present, turns Finch red and stops buzzing & moving, then calls another method
	{
		playerFinch.setLED(Color.red); //Set beak colour to red
		playerFinch.setWheelVelocities(0, 0); //Stop moving
		playerFinch.buzz(0, 0); //Stop making a sound
		System.out.println("In 6 seconds, the program will terminate unless the light returns/the obstacle is moved!");
		numberOfStops += 1;
		sixSecondCheck(); //Method that checks if 6 seconds have passed whilst not moving
	}
			
	public static void obstacleCheck()
	{
		if (playerFinch.isObstacle())
		{
			redLight(); //Links to the redLight method which will in turn link to the sixSecondCheck method
		}
	}
	
	public static void sixSecondCheck() //Used two different while loops with same contents because if combined into one, the program performs less reliably
	{
		int i = numberOfStops - 1; //Used to set the index of stopTimeList to numberOfStops so it will find the current stopTime
		stopTimeList.add(i, (double) 0); //Initialise the first stopTime
		while(playerFinch.getLeftLightSensor() < thresholdValue && playerFinch.getRightLightSensor() < thresholdValue) //Whilst no light
		{
			playerFinch.sleep(500); //Sleep for half a second
			stopTimeList.set(i, stopTimeList.get(i) + 0.5); //Add half a second to the variable at index i
			System.out.println(stopTimeList.get(i));
			if (stopTimeList.get(i) >= 6) //Counts to 6 seconds
			{
				gameOver = true; //Program is over
				break; //End loop, go back to main argument
			}
		}
		while (playerFinch.isObstacle()) //Same thing as above while loop, just checking for obstacles
		{
			playerFinch.sleep(500); //Sleep for half a second
			stopTimeList.set(i, stopTimeList.get(i) + 0.5); //Add half a second to the variable at index i
			System.out.println(stopTimeList.get(i));
			if (stopTimeList.get(i) >= 6) //Counts to 6 seconds
			{
				gameOver = true; //Program is over
				break; //End loop, go back to main argument
			}
		}
	}
}