// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1/2
// Lab:         Project 1
// Date:        February 17, 2011
// Description: The Dice Class for the monopoly game.  Rolls, Stores and handles dice.

public class Dice
{
	private static int first;                   // first die
	private static int second;  				// second die
		
	public Dice()
	// POST: a default Dice object is constructed with first and second dies set to zero
	{
		first = 0;
		second = 0;
	}
	
	public int Roll()
	// POST: returns the sum of first and second die
	{
		first = (int)(6 * Math.random() + 1);   // generate a random number >=1 and <=6
		second = (int)(6 * Math.random() + 1);  // generate a second random number >=1 and <=6
		return (first + second);
	}
	
	public static boolean isDouble()
	// PRE:  first and second must be initialized
	// POST: if first == second (meaning if doubles are rolled), returns true, otherwise false
	{
		if(first == second)                     // if the value of the first die = the value
		{                                       // of the second die, return true
			return true;
		}
		else                                    // if the values of the first and second die
		{                                       // are different, return false
			return false;
		}
	}
}
