// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1  
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Lot class for lots on the Monopoly board

public class Lot extends Property
{
	protected boolean[] isHouse;	        // if the owner own's 1-4 amount of houses
	protected int[] house;                  // the rent from house's 1-4 in $
	protected String color;				    // the color of the lot
	protected String housingStatus;			// how many houses/hotel are on the lot
	protected int improve;				    // cost to improve lot in $
	protected int hotel;				    // the rent from a hotel in $
		
	public Lot()
	// POST: a default Lot Location is created with price, location, all houses,
	//       improvement cost all to zero, and owner, name, and color is set to an empty
	//       string and isOwned and all isHouse is set to false
	{
		super();
		house = new int[5];
		isHouse = new boolean[5];
		
		for(int i = 0; i < isHouse.length; i++)  // set all isHouse's to false, because no
		{                                        //   houses are on property when bought
			isHouse[i] = false;
			house[i] = 0;
		}
		
		improve = 0;
		color = "";
	}
	
	public Lot(int location, String name, int price, String color, int rent, int houseOne,
			int houseTwo, int houseThree, int houseFour, int hotel, int improve)
	// PRE:  location must be >= 0 and <= 39, name must be initialized, price >=0 and in $, all the
	//       houses >= 0 and in $, hotel >= 0 and in $ and improve >=0 and in $
	// POST: sets the Lot's location, name, price, base rent(house[0]), house[1-4], color,
	//       improve to respective parameters and isHouse[] and isHotel to false by default
	{		
		super(location, name, price);
		house = new int[5];
		isHouse = new boolean[5];
		this.house[0] = rent;
		this.house[1] = houseOne;
		this.house[2] = houseTwo;
		this.house[3] = houseThree;
		this.house[4] = houseFour;
		this.hotel = hotel;
		this.improve = improve;
		this.color = color;
		
		for(int i = 0; i < isHouse.length; i++)  // there are no houses on this property, yet
		{
			isHouse[i] = false;
		}
	}
	
	public void setOwner(PlayerClass owner)
	// PRE:  owner must be initialized
	// POST: assigns owner to owner to the Lot and isOwned set to true
	{
		this.owner = owner;
		isOwned = true;                          // now there is an owner for this lot
	}
	
	public int getRent()
	// PRE:  the array isHouse[] must be initialized in $
	// POST: returns the rent of the Lot depending upon the house/hotel status in $
	{
		if( isHouse[0] )                         // if one house is on lot
		{                                        // return appropriate rent
			return house[1];
		}
		else if( isHouse[1] )                    // if two houses are on lot
		{                                        // return appropriate rent
			return house[2];
		}
		else if( isHouse[2] )                    // if three houses are on lot
		{                                        // return appropriate rent
			return house[3];
		}
		else if( isHouse[3] )                    // if four houses are on lot
		{                                        // return appropriate rent
			return house[4];
		}
		else if( isHouse[4] )                       // if there is a hotel on the lot
		{                                        // return appropriate rent
			return hotel;
		}
		else                                     // if there are not hotels or houses on lot
		{                                        // return the base rent
			return house[0];
		}
	}
	
	//public String[] addHousesMenu(PlayerClass player)
	{
	    
	}
	
	public boolean isFullyImproved()
	// POST: returns true if lot is fully improved, false if lot is not fully improved
	{
		if(isHouse[4])
			return true;
		else
			return false;
	}
	
	public boolean isImproved()
	// POST: returns true if is improved at least once, false if lot is not improved at all
	{
		if(isHouse[0])
			return true;
		else
			return false;
	}
	
	
	public String addHouses(PlayerClass player, boolean isBuy)
	//PRE:  player must be initialized, player must own all lots of that color, player
	//         must also build evenly amongst those lots
	//POST: allows player to place a house on the lot depending on how many houses are already
	//         on the lot.  FCTVAL == string representation of what action was performed
	{
		int numOfHouses = 0;                     // number of houses on lot for use in string
		
		if(isBuy)
		{
			if (player.getMoney() > improve)         // if player has enough money to improve
			{
				if ( isHouse[4] )                    // if player has a hotel on lot
				{                                    //   then return that the lot is already
					return "Fully Improved";         //   fully improved  
				} 
				else if(isHouse[3])                  // if there are 4 houses on the lot,
				{                                    //   add a hotel to the lot
					player.addMoney(-improve);
					isHouse[4] = true;
					return "Hotel placed on " + this.displayName();
				}
				else if(isHouse[2])                  // if there are 3 houses on the lot
				{                                    //   add a 4th house
					player.addMoney(-improve);
					isHouse[3] = true;
					numOfHouses = 4;
				}
				else if(isHouse[1])                  // if there are 2 houses on the lot
				{                                    //   add a 3rd house
					player.addMoney(-improve);
					isHouse[2] = true;
					numOfHouses = 3;
				}
				else if(isHouse[0])                  // if there is 1 house on the lot
				{                                    //   add a 2nd house
					player.addMoney(-improve);
					isHouse[1] = true;
					numOfHouses = 2;
				}
				else                                 // if there are no houses on the lot
				{                                    //   add a house
					player.addMoney(-improve);
					isHouse[0] = true;
					numOfHouses = 1;
				}
					return "1 House placed on " + this.displayName() + "\nCurrent number of houses: "
						+ numOfHouses;
			}
			else                                     // if player cannot afford an improvement 
			{                                        //   return string telling player
				return "You do not have enough money to improve this lot";
			}
		}
		else
		{
			if(isHouse[4])
			{
				player.addMoney(improve/2);
				isHouse[4] = false;
				return "Hotel sold for $" + (improve/2);
			}
			else if(isHouse[3])
			{
				player.addMoney(improve/2);
				isHouse[3] = false;
			}
			else if(isHouse[2])
			{
				player.addMoney(improve/2);
				isHouse[2] = false;
			}
			else if(isHouse[1])
			{
				player.addMoney(improve/2);
				isHouse[1] = false;
			}
			else
			{
				player.addMoney(improve/2);
				isHouse[0] = false;
			}
			return "House sold for $" + improve/2;
		}
	}
	
	public String housingStatus()
	// PRE:  the array isHouse[] must be initialized
	// POST: returns the status of the Lot by how many houses or hotel the player owns
	{
		if( isHouse[0] )                        // if there is one house on the lot
		{                                       // return "1 house"
			return housingStatus = "1 house";
		}
		else if( isHouse[1] )                   // if there are two houses on the lot
		{                                       // return "2 houses"
			return housingStatus = "2 houses";
		}
		else if( isHouse[2] )                   // if there are three houses on the lot
		{                                       // return "3 houses"
			return housingStatus = "3 houses";
		}
			else if( isHouse[3] )               // if there are four houses on the lot
		{                                       // return "4 houses"
				return housingStatus = "4 houses";
		}
		else if( isHouse[4] )                   // if there is a hotel on the lot
		{                                       // return "1 hotel"
			return housingStatus = "1 Hotel";
		}
		else                                    // if there are no houses or hotels on lot
		{                                       // return "unimproved"
			return housingStatus = "unimproved";
		}
	}
	
	public int getImprove()
	//POST: FCTVAL == improve
	{
		return improve;
	}
	
	@Override
	public String toString()
	// PRE:  name, location, price in $, and owner must initialized
	// POST: returns a string representation of the Lot's name, location, cost in $, current owner,
	//       house/hotel status, each house costs in $, hotel cost in $, rent in $, 
	//       and improvement cost in $
	{
		return "[" + color + "] " + name + " (" + location + ") " + "[cost: $" + 
			price + ", owner: " + owner.getToken() + ", " + housingStatus() + "]\n" + "   RENT: $" +
			house[0] + "; 1 house... $" + house[0] + "; 2 houses... $" + house[1]
			+ "; 3 houses... $" + house[2] + "; 4 houses... $" + house[3] + ";\n"
			+ "   HOTEL... $" + hotel + "; Improve for $" + improve + "\n";
	}
}
