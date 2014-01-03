// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1/2  
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The location driver for Monopoly


public class Monopoly 
{	
	public static void main(String[] args)
	{
		int choice;                           // holds the choice of player at the main menu                     
		Game theGame = new Game();            // creates an instance of the game
		
		theGame.SetUp();                      // sets up initial variables for the game
		
		do
		{
			theGame.NextTurn();	              // advances play to the next player if doubles
			                                  //    have not been rolled
			do
			{
				theGame.GetPlayer();          // shows the current player
				theGame.GetStatus();          // shows the status of all players
				 
				choice = theGame.RunActionsMenu(Game.MainMenu());  // 
				
				theGame.Roll();
				
				if (choice == 0)
				{
					theGame.Movement();
				}			
				else if(choice == 1)
				{
					theGame.ManageHouses();				
				}				
				else if(choice == 2)
				{
					theGame.DisplayBoard();
				}				
				else if(choice == 3)
				{
					theGame.GameOver();
					break;
				}		
			}while(choice != 0);
			
			if(!theGame.isGameOver())
			{
				theGame.LocationActions();
				theGame.CheckDoubles();
			}
			
			if(theGame.isBankrupt())
				theGame.GameOver();
			
			}while(!theGame.isGameOver());
		
		theGame.GetStatus();
		}
}
