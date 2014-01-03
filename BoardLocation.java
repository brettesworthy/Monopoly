// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1 / 2
// Lab:         Project 1
// Date:        February 17, 2011
// Description: The BoardLocation class that handles spaces on the board.  Parent class to Card, Corner,
//                 Property, and Tax

public abstract class BoardLocation 
{
	protected final int location;	    // the location on the board (0-39)
	protected String name;				// name of the location (ex: "Boardwalk")

	public BoardLocation()
	// POST: a default BoardLocation is created with location set to zero
	//       and name set to any empty string
	{
		location = 0;
		name = "";
	}
	public BoardLocation(int location, String name)
	// PRE: location must be >= 0 and <= 39, and name must initialized
	// POST: sets the location to location and name to name on the board
	{
		this.location = location;
		this.name = name;
	}

	public String displayName()
	// POST: FCTVAL == name
	{
		return name;
	}
	
	public static String move(PlayerClass player, int diceRoll)
	// PRE:  diceRoll must be >= 0 and <= 39
	// POST: dice is rolled for player and moves the player to the appropriate location
	//		 and if player passes Go, he/she collects $200.  FCTVAL == 
	{		
		if(diceRoll + player.getPosition() < 40 )  // if player is not rounding go, move
		{                                          //   player accordingly
			player.movePlayer(player.getPosition() + diceRoll);
			return player.getToken() + " moved to " + Game.displayLocation(player.getPosition());
		}		
		else                                       // if player is rounding Go, give $200
		{                                          //   and move player accordingly
			player.movePlayer(diceRoll - (39 - player.getPosition()) - 1);
			player.addMoney(200);
			return player.getToken() + " passed Go and collected $200!\n" + "and is now on " +
			       Game.displayLocation(player.getPosition());
		}
	}

	public abstract String[] GetPossibleActions(PlayerClass player);
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//	     this space, to be used in a menu in a user interface
	
	public abstract String RunAction(int choice, PlayerClass player);
	// PRE:  choice > 0, player is initialized
	// POST: performs an action depending on what choice the player made.
	//          FCTVAL == string explaining what action was performed
}
