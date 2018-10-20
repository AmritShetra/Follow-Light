public class Movement extends FollowLight {
	
	/* All methods relating to movement are located here.
	 * This was done to reduce complexity of the main class (FollowLight).
	 * Here, the methods called are to turn the Finch left/right or alternatively, to move forwards.
	 * They all contain the relevant 'if statements' to ensure the correct method works in the program.
	 */
	
	public static void main (String args[]){
		turnLeft();
		turnRight();
		moveForwards();
	}
	
	public static void turnLeft()
	{
		if (playerFinch.getLeftLightSensor() > playerFinch.getRightLightSensor()) //If left > right
		{
			playerFinch.setWheelVelocities(0, 100);  //Move left
			greenLight(); //Call greenLight method
			speedList.add(100); //Add speeds to the arrayList
		}
	}
	
	public static void turnRight()
	{
		if (playerFinch.getRightLightSensor() > playerFinch.getLeftLightSensor()) //If right > left
		{
			playerFinch.setWheelVelocities(100, 0); //Move right
			greenLight();
			speedList.add(100);
		}
	}
	
	public static void moveForwards()
	{
		if (playerFinch.getLeftLightSensor() == playerFinch.getRightLightSensor()) //If left == right
		{
			playerFinch.setWheelVelocities(100, 100); //Move forward
			greenLight();
			speedList.add(100);
		}
	}
}