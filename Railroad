// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1  
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Railroad class for Railroad locations on the board

public class Railroad extends Property
{
	public Railroad()
	// POST: a default Railroad object is created with price, rent, location set to zero
	//		 and owner and name set to an empty string and isOwned set to false 
	{
		super();
	}
	
	public Railroad(int location, String name, int price)
	// PRE:  location must be between >= 0 and <= 39, name and price must be
	//       initialized in $
	// POST: sets the Railroad's location, price in $ and sets isOwned to false
	{
		super(location, name, price);
	}
	
	public void setOwner(PlayerClass owner)
	// PRE:  owner must be initialized
	// POST: assigns owner owner to the Railroad and is now owned
	{
		this.owner = owner;
		isOwned = true;
	}
	
	public int getRent()
	// PRE:  rent must be initialized in $
	// POST: returns the Railroad's rent in $
	{
		switch (owner.getRailroads())
		{
			case 1:                 // if player owns one Railroad, rent is $25
				return 25;			
			case 2:                 // if player owns two Railroads, rent is $50
				return 50;			
			case 3:                 // if player owns three Railroads, rent is $100
				return 100;	 		
			case 4:                 // if player owns four Railroads, rent is $200
				return 200;			
			default:                // just in case, if player owns no railroads, rent is $0
				return 0;
		}
	}
	
	public boolean isOwned()
	// PRE:  isOwned must be initialized
	// POST: returns if the Railroad is owned or not
	{
		if( isOwned )              // if railroad is owned, return true
			return true;
		else                       // if railroad is not owned, return false
			return false;
	}
	
	public String toString()
	// PRE:  name, location, price in $, and owner must initialized
	// POST: string representation of the Railroad's name, location, cost in $, and current owner
	{
		return name + " (" + location + ") " + "[cost: $" + 
			   price + ", owner: " + owner.getToken() + "]\n";
	}
}
