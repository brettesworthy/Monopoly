// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1/2 
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Property class that is spaces on the board that are able to be owned

public abstract class Property extends BoardLocation
{
	protected int price;					// price cost for the location in $
	protected PlayerClass owner;            // the owner of the location
	protected boolean isOwned;				// if the location is owned or not
	private int options;					// players options in menu depending on
	                                        //   what options are given to player
	public Property()
	// POST: a default Property object is created with price, rent, location set to zero
	//		 and owner and name set to an empty string and isOwned set to false
	{
		super();
		price = 0;
		owner = new PlayerClass();
		isOwned = false;
	}
	public Property(int location, String name, int price)
	// PRE:  location must be between >= 0 and <= 39, name and price must
	//       be initialized
	// POST: sets the location on the board, owner of the location is Bank by default,
	//       sets the name and price, and isOwned to false
	{
		super(location, name);
		owner = new PlayerClass("Bank");
		this.price = price;
		isOwned = false;
	}
	
	public String[] GetPossibleActions(PlayerClass player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//	     this space, to be used in a menu in a user interface
	{
		String[] menu = new String[0];			         // default menu for options	
		
		if( isOwned )                                    // if the property is owned
		{			
			if(owner == player)                          // if the property is owned by player, 
			{                                            //    only option to pass
				menu = new String[1];
				options = 1;
				menu[0] = "You own this property!\nPass";
			}		
			else if(player.getMoney() > this.getRent())  // if player money is greater than 
			{                                            //    the rent, player must pay rent
				menu = new String[1];
				options = 2;
				menu[0] = "Pay rent!";
			}			
			else                                         // if player money is less than the 
			{                                            //    rent player must go bankrupt
				menu = new String[1];
				options = 3;
				menu[0] = "Go Bankrupt.";
			}
		}		
		else                                             // if the property is not owned 
		{                                                //    (owned by bank)
			if(player.getMoney() > price)                // if player money is greater than 
			{                                            //   price, player has option to buy 
				menu = new String[2];                    //   lot or to pass
				menu[0] = "Buy lot.";
				menu[1] = "Don't buy lot.";
			}			
			else                                         // if player money is less than price, 
			{                                            //   player can only pass
				menu = new String[1];
				menu[0] = "Dont't buy lot.";
			}
		}		
		return menu;
	}
	
	public String RunAction(int choice, PlayerClass player)
	// PRE:  choice must be >= 0 and <= 1 and player must be initialized 
	// POST: runs the actions chosen by the current player that is on the current
	//		 property.  FCTVAL == string representing what action has taken place
	{		
		if (options == 1)                  // if player owns the property, player passes 
		{                                  //    by the property
			return "You passed by " + this.displayName() +  "\n";
		}
		else if (options == 2)            // if player does not own the property,
		{                                 //    player pays rent and rent goes to the owner
			player.addMoney(-(this.getRent()));
			this.owner.addMoney(this.getRent());
			return "You paid rent of $" + this.getRent() + " to " + 
					this.owner.getToken() + ".\n";
		}
		
		
		else if (options == 3)           // if player does not have enough money to pay rent, 
		{                                //    player goes bankrupt
			player.Bankrupt(); 
			return "You went Bankrupt!";
		}		
		else                             // if lot is not owned
		{			
			if(choice == 0)              // if player buys lot, loses amount of money the price
			{                            //    was and property is now owned by player
				setOwner(player);
				player.buyProperties(this);
				return "You now own " + this.displayName() + ".\n";  
			}	
			else                         // player player passes by the property
			{
				return "You passed by " + this.displayName() +  "\n"; 
			}	
		}
	}
	    
	public int getPrice()
	// PRE:  price must be initialized
	// POST: returns the price of the property
	{
		return price;
	}
	
	public abstract int getRent();
	// PRE:  rent must be initialized
	// POST: returns the rent dependent upon (if applicable) house/hotel
	//       status
	
	public abstract void setOwner(PlayerClass owner);
	// PRE:  owner must be initialized
	// POST: sets the owner to the Property	
}
