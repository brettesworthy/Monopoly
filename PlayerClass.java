// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1/2
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The player class for the Monopoly project

public class PlayerClass 
{
	protected Property[] playerProperties;              // array of properties
														//   that the player owns
	protected final String playerToken;                 // player's token for game (ex: racecar)
	protected int playerMoney;                          // player's money in $
	protected int playerPosition;                       // player's position (0-39)
	protected int numOfProperties;			    		// number of properties owned by the player
	protected int numOfRailroads;                       // number of railroads owned by the player
	protected int numOfUtilities;                       // number of utilities owned by the player
	protected int numOfLots;                            // number of lots owned by the player
		
	public PlayerClass()
	//POST:  Creates a default player with a blank token, playerMoney, playerPosition,
	//          numOfRailroads, numofUtilities, numOfProperties, and numOfLots set to 0.
	//          Also creates an empty array playerProperties array for holding player's 
	//          properties
	{
		this.playerToken = "";
		playerMoney = 0;
		playerPosition = 0;
		numOfRailroads = 0;
		numOfUtilities = 0;
		numOfProperties = 0;
		numOfLots = 0;
		playerProperties = new Property[28];
	}
	
	public PlayerClass(String playerToken)
	// PRE:  playerToken must be initialized
	// POST: sets the playerMoney to $1500, playerPosition to zero and
	//       the player's railroads and utilities to zero
	{
		this.playerToken = playerToken;
		playerMoney = 1500;
		playerPosition = 0;
		numOfRailroads = 0;
		numOfUtilities = 0;
		numOfProperties = 0;
		numOfLots = 0;
		playerProperties = new Property[28];
	}
	
	public void buyProperties(BoardLocation property)
	// PRE:  property must be a valid property on the Monopoly board
	// POST: sets the property to the owner
	{
		
		if(property instanceof Lot)                 // if property is of type Lot, 
		{                                           //    sets the Lot
			setLots((Lot)property);
		}
		else if (property instanceof Railroad)      // if property is of type RailRoad, 
		{                                           //    sets the Railroad
			setRailroads((Railroad)property);       
		}
		else                                        // if property is of type Utility, 
		{                                           //    sets the Utility
			setUtilities((Utility)property);        
		}
		
		addMoney(-((Property)property).getPrice());	// subtracts player's money from the 
	}                                               //    price of the property bought
	
	public void setRailroads(Railroad railroad)
	// PRE:  playerRailroads must be >= 0 and <= 4
	// POST: sets the the amount of railroads the player owns
	{
		playerProperties[numOfProperties] = railroad;
		numOfRailroads++;
		numOfProperties++;
	}
	
	public void setUtilities(Utility utility)
	// PRE:  playerUtilities must be >= 0 and <= 2
	// POST: sets the amount of utilities the player owns
	{
		playerProperties[numOfProperties] = utility;
		numOfUtilities++;
		numOfProperties++;
	}
	
	public void setLots(Lot lot)
	// PRE:  index must be >= 0 and playerProprties must be initialized
	// POST: sets the player's property set by the index
	{
		playerProperties[numOfProperties] = lot;
		numOfProperties++;
		numOfLots++;
	}
	
	public void addMoney(int playerMoney)
	// PRE:  playerMoney must be in $ and initialized
	// POST: adds playerMoney to the player's current money
	{
		this.playerMoney += playerMoney;
	}
	
	public void Bankrupt()
	// POST: bankrupts player and sets playerMoney to $0
	{
		playerMoney = 0;
	}
	
	public int getRailroads()
	// POST: returns the amount of railroads the player owns
	{
		return numOfRailroads;
	}
	
	public int getUtilities()
	// POST: returns the amount of utilities the player owns
	{
		return numOfUtilities;
	}
	
	public int getLots()
	// POST: returns the amount of utilities the player owns
	{
		return numOfLots;
	}
	
	public int getImprovedLots()
	// POST: returns the amount of lots player owns that are improved
	{
		int improvedLots = 0;           // holds number of improved lots
		
		for( int i = 0; i < numOfProperties; i++)
		{
			                            // if property is a lot and is improved
			if(playerProperties[i] instanceof Lot &&((Lot)playerProperties[i]).isImproved())
				improvedLots++;
		}
		
		return improvedLots;
	}
	
	public String getProperties()
	// PRE: playerProperties must be initialized
	// POST: returns the array of properties the player owns
	{
		String output;                                // holds String to be returned
		    
		output = "";                                  // initialize blank string
		
		if(numOfProperties == 0)                      // if player does not own any properties
		{
			output = "no properties";
		}		    
		else                                          // if player does own properties
		{
			for(int i = 0; i < numOfProperties; i++)  // populate output with list of properties
			{                                         //    owned by the player
				output += playerProperties[i].displayName();
				
				if( i != numOfProperties - 1)         // if this is not the last property,
				{                                     //   place a comma
					output += ", ";
				}
				else                                  // if this is the last property
				{                                     //   place a period
					output += ". ";	
				} 
			}
		
		}  
		return output;
	}
	
	public Lot[] buyOrSellHouses (boolean isBuy)
	{
		Lot[] availLots; 
		int numOfAvailLots = 0;
		
		if(isBuy)
		{
			availLots = new Lot[numOfLots];
			
			for (int i = 0; i < numOfProperties; i++)
			{
				if(playerProperties[i] instanceof Lot && !(((Lot)playerProperties[i]).isFullyImproved())
						&& (playerMoney > ((Lot)playerProperties[i]).getImprove()))
				{
					availLots[numOfAvailLots] = (Lot)playerProperties[i];
					numOfAvailLots++;
				}
			}
		}
		else
		{
			availLots = new Lot[this.getImprovedLots()];
			
			for (int i = 0; i < numOfProperties; i++)
			{
				if(playerProperties[i] instanceof Lot && ((Lot)playerProperties[i]).isImproved())
				{
					availLots[numOfAvailLots] = (Lot)playerProperties[i]; 
					numOfAvailLots++;
				}
			}
		}
		
		return availLots;
	}
	
	public void movePlayer(int newAddress)
	// PRE:  newAddress < 0 and <= 12
	// POST: sets the playerPosition to newAddress
	{
		playerPosition = newAddress;
	}
	
	public int getPosition()
	// PRE:  playerPosition must be initialized
	// POST: returns the player's current position
	{
		return playerPosition;
	}
	
	public int getMoney()
	// PRE:  playerMoney must be initialized
	// POST: returns the player's current money
	{
		return playerMoney;
	}
	
	public String getToken()
	// PRE:  playerToken must be initialized
	// POST: returns the player's token
	{
		return playerToken;
	}
	
	@Override
	public String toString()
	// PRE:  playerMoney, playerPositon, playerProperties, playerRailroads,
	//       playerUtilities, and playerToken must be initialized
	// POST: string representation of the player's money, current position,
	//       what properties are owned, the amount of railroads and utilities
	//       owned and the player's token
	{
		return "Player with token: " + getToken() + " is at position: " + 
				((playerPosition == 0)? "Go" : playerPosition) + " and has $" + 
				playerMoney + "\nAlso owns " + numOfRailroads + " railroads, " + 
				numOfUtilities + " utilities, and " + getProperties();
	}	
}
