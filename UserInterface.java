import java.util.Scanner;

public class UserInterface extends FollowLight {
	
	/* The code for the user input/output for the console user interface is contained here.
	 * Once the method is called, it will create a new instance of the Scanner.
	 * The variable 'viewStatistics' is set to the input from the next line in the console.
	 * Depending on if this equals 'YES' or 'yes', the system will print the statistics and terminate...
	 * Or it will just terminate.
	 */
	
	public static void main (String args[]){
		viewStatistics();
	}
	
	public static void viewStatistics()
	{
		String viewStatistics;
		Scanner user_input = new Scanner(System.in); //Initialise scanner
		System.out.println("Would you like to view the statistics (YES/NO)?");
		viewStatistics = user_input.next(); //Whatever the next input in the console is
		if (viewStatistics.equals("YES") || viewStatistics.equals("yes"))
		{
			System.out.println("Average Speed = " + CalculateStatistics.averageSpeed); //Calls the variable from the other class
			System.out.println("Total Travel Time = " + CalculateStatistics.travelTime + " seconds");
			System.out.println("Total Stop Time = " + CalculateStatistics.stopTime + " seconds");
			System.out.println("Number of stops = " + numberOfStops);
			user_input.close(); //Terminate the scanner
			playerFinch.quit(); //Terminate the Finch
		}
		else
		{
			user_input.close();
			playerFinch.quit();
		}
	}
}