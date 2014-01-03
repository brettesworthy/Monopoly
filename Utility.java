// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1  
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Utility class for the utility locations on the board

public class Utility extends Property
{
	private int diceRoll;           // holds value of dice for determining rent
	
	public Utility()
	// POST: a default Utility object is created with price, rent, location set to zero
	//		 and owner and name set to an empty string and isOwned set to false 
	{
		super();
	}
	
	public Utility(int location, String name, int price)
	// PRE:  location must be between >= 0 and <= 39, name and price in $ must be
	//       initialized
	// POST: sets the Utility's location, price in $ and sets isOwned to false
	{
		super(location, name, price);
	}
	
	public void setOwner(PlayerClass owner)
	// PRE:  owner must be initialized
	// POST: assigns owner owner to the Utility and is now owned
	{
		this.owner = owner;
		isOwned = true;
	}
	
	public void setDiceRoll(int diceRoll)
	// PRE:  diceRoll must be >= 2 and <= 12
	// POST: sets the dice roll that was rolled on the utility
	{
		this.diceRoll = diceRoll;
	}
	
	@Override
	public int getRent()
	// PRE:  rent must be initialized in $
	// POST: returns the Utility's rent in $
	{
		
		if(owner.getUtilities() == 1)  // if owner owns one utility, 
		{                              //   player pays rent 4 times the diceRoll
			return diceRoll * 4;
		}
		
		
		else                           // if owner owns two utilities, player 
		{                              //    pays rent 4 times the diceRoll
			return diceRoll * 10;
		}
	}
		
	public String toString()
	// PRE:  name, location, price, and owner must initialized
	// POST: string representation of the Utility's name, location, 
	//       cost in $, and current owner
	{
		return name + " (" + location + ") " + "[cost: $" + 
		price + ", owner: " + owner.getToken() + "]\n";
	}
}
