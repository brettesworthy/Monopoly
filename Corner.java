// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1  
// Lab:         Project 1
// Date:        February 17, 2011
// Description: The Corner class locations for the monopoly board

public class Corner extends BoardLocation
{
	public Corner()
	// POST: a default Corner location is created with location set to zero
	//       and name set to any empty string
	{
		super();
	}
	
	public Corner(int location, String name)
	// PRE:  location must be between >= 0 and <= 39, and name must initialized
	// POST: Creates a Corner Location sets the corner's location, price in $ and sets isOwned
	//       to false
	{
		super(location, name);
	}
	
	@Override
	public String[] GetPossibleActions(PlayerClass player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//	     this space, to be used in a menu in a user interface
	{
		String[] menu = new String[1];
		menu[0] = "Pass.";                // give player the only option to pass
		return menu;
	}
	
	@Override
	public String toString()
	// PRE:  name and location must initialized
	// POST: returns a string representation of the corner's name and location
	{
		return name + " (" + location + ")\n";
	}
	
	public String RunAction(int choice, PlayerClass player)
	// POST: player passes by the corner objects in the game, returns string representation
	// 		   "Passed by (player name)"
	{
		return "Passed by " + this.displayName() + ".\n";
	}
}
