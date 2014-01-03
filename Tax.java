// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1  
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Tax class for the tax location on the monopoly board

public class Tax extends BoardLocation
{
	protected static final int AMOUNT = 200;			// amount player owes from tax location
	private int options;								// player choice for menus
	
	public Tax()
	// POST: a default Tax object is created with location set to zero
	//       and name set to any empty string
	{
		super();
	}
	
	public Tax(int location, String name)
	// PRE:  location must be >= 0 and <= 39 and name must initialized
	// POST: sets the location and name of the tax location
	{
		super(location, name);
	}
	
	public String toString()
	// PRE:  name and location must initialized
	// POST: string representation of the tax's name and location
	{
		return name + " (" + location + ")\n";
	}
	
	public String[] GetPossibleActions(PlayerClass player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//	     this space, to be used in a menu in a user interface
	{
		String[] menu = new String[1];			// menu array to be returned
		
		if(player.getMoney() > 200)             // if player's money > 200, pay the rent
		{
			options = 1;
			menu[0] = "Pay tax!";
		}
		else                                    // if player cannot afford to pay rent
		{                                       //    player goes bankrupt
			menu[0] = "Go Bankrupt";
		}
		
		return menu;
	}
	
	public String RunAction(int choice, PlayerClass player)
	// PRE:  choice must be 0 and player must be initialized
	// POST: subtracts $200 from player's money, if player does not have enough money,
	//		 player bankrupts
	{
		if (options == 1)                      // if player was given the option to pay tax
		{                                      //   deduct tax amount from players money
			player.addMoney(-AMOUNT);
		    return "You lost $" + AMOUNT + ".\n";
		}
		else                                   // if player did not have enough money
		{                                      //   and was not given the option to pay  
			player.Bankrupt();                 //   tax then the player has gone bankrupt
			return "You went Bankrupt!";
		}
	}

}
