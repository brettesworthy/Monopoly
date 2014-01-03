import java.util.Scanner;

// Programmer:  Brett Esworthy / Kyle McCormick
// Section:     1/2
// Lab:         Project 1
// Date:        February 6, 2011
// Description: The Game class that sets up the state of the game

public class Game
{
	private static final int NUM_OF_LOCATIONS = 40;   // number of locations on the board
	private static final int NUM_PLAYERS = 3;		  // amount of players
	private static BoardLocation[] location;          // locations for the game
	private PlayerClass[] player;					  // players for the game
	private int turn;								  // the player's number turn assigned
	private Dice theDice;							  // dice to be rolled
	private PlayerClass currentPlayer;                // player who's turn it is
	private int currentLocation;                      // location of the player who's turn it is
	private int actionChoice;                         // holds players choice when given options upon
	                                                  //    landing on a space
	private int houseMenuChoice;                      // holds players choice from the house menu
	private int diceRoll;                             // holds value of the diceRoll
	private boolean isGameOver;                       // controls if the game is over
	private Lot[] lotsToImprove;                      // holds lots that can have houses placed on
	                                                  //   or sold from
	
	public Game()
	// POST: a default Game is constructed, NUM_OF_LOCATION locations, NUM_PLAYERS players,
	// random first turn, and locations are set and isGameOver is set to false
	{
		player = new PlayerClass[NUM_PLAYERS];
		location = new BoardLocation[NUM_OF_LOCATIONS];
		location = this.getLocations();
		theDice = new Dice();
		turn = setFirst();
		isGameOver = false;
		setLocations();
	}
	
	public boolean isGameOver()
	//POST: returns value of IsGameOver
	{
		return isGameOver;
	}
	
	public void turn()
	// PRE:  turn must be initialized
	// POST: if not the last player in rotation, reset to first player's turn
	{
		if( turn == (NUM_PLAYERS -1) )
		{
			turn = 0;
		}
		else
		{
			turn++;
		}
	}
	
	public PlayerClass getTurn()
	// PRE:  player and turn must be initialized
	// POST: returns the current player's turn
	{
		return player[turn];
	}
	
	public int getDice()
	// PRE:  theDice must be initialized
	// POST: returns the dice roll for the player
	{
		return theDice.Roll();
	}
	
	public void setLocations()
	// PRE: location[] must be initialized
	// POST: sets the game's locations on the board
	{
		location[0] = new Corner(0, "Go");
		location[1] = new Lot(1, "MEDITERRANEAN AVE.", 60, "Dark Purple", 2, 10, 30, 90, 160, 230, 50);
		location[2] = new Card(2, "Community Chest");
		location[3] = new Lot(3, "BALTIC AVE.", 60, "Dark Purple", 4, 20, 60, 180, 320, 450, 50);
		location[4] = new Tax(4, "Income Tax");
		location[5] = new Railroad(5, "READING RAILROAD", 200);
		location[6] = new Lot(6, "ORIENTAL AVE.", 100, "Light Blue", 6, 30, 90, 270, 400, 550, 50);
		location[7] = new Card(7, "Chance");
		location[8] = new Lot(8, "VERMONT AVE.", 100, "Light Blue", 6, 30, 90, 270, 400, 550, 50);
		location[9] = new Lot(9, "CONNECTICUT AVE.", 120, "Light Blue", 8, 40, 100, 300, 450, 600, 50);
		location[10] = new Corner(10, "Jail/Just Visiting");
		location[11] = new Lot(11, "ST. CHARLES PLACE", 140, "Light Purple", 10, 50, 150, 450, 625, 750, 100);
		location[12] = new Utility(12, "ELECTRIC COMPANY", 150);
		location[13] = new Lot(13, "STATES AVE.", 140, "Light Purple", 10, 50, 150, 450, 625, 750, 100);
		location[14] = new Lot(14, "VIRGINA AVE.", 60, "Light Purple", 12, 60, 180, 500, 700, 900, 100);
		location[15] = new Railroad(15, "PENNSLYVANIA RAILROAD", 200);
		location[16] = new Lot(16, "ST. JAMES PLACE", 180, "Orange", 14, 70, 200, 550, 750, 950, 100);
		location[17] = new Card(17, "Community Chest");
		location[18] = new Lot(18, "TENNESSEE AVE.", 180, "Orange", 14, 70 , 200, 550, 750, 950, 100);
		location[19] = new Lot(19, "NEW YORK AVE.", 200, "Orange", 16, 80, 220, 600, 800, 1000, 100);
		location[20] = new Corner(20, "Free parking");
		location[21] = new Lot(21,"KENTUCKY AVE.", 220, "Red", 18, 90, 250, 700, 875, 1050, 150);
		location[22] = new Card(22, "Chance");
		location[23] = new Lot(23, "INDIANA AVE.", 220, "Red", 18, 90, 250, 700, 875, 1050, 150);
		location[24] = new Lot(24, "ILLINOIS AVE.", 240, "Red", 20, 100, 300, 750, 925, 1100, 150);
		location[25] = new Railroad(25, "B & O RAILROAD", 200);
		location[26] = new Lot(26, "ATLANTIC AVE.", 260, "Yellow", 22, 110, 330, 800, 975, 1150, 150);
		location[27] = new Lot(27, "VENTNOR AVE.", 260, "Yellow", 22, 110, 330, 800, 975, 1150, 150);
		location[28] = new Utility(28, "WATER WORKS", 150);
		location[29] = new Lot(29, "MARVIN GARDENS", 280, "Yellow", 24, 120, 360, 850, 1025, 1200, 150);
		location[30] = new Corner(30, "Go To Jail");
		location[31] = new Lot(31, "PACIFIC AVE.", 300, "Green", 26, 130, 390, 900, 1100, 1275, 200);
		location[32] = new Lot(32, "NO. CAROLINA AVE.", 300, "Green", 26, 130, 390, 900, 1100, 1275, 200);
		location[33] = new Card(33, "Community Chest");
		location[34] = new Lot(34, "PENNSYLVANIA AVE.", 320, "Green", 28, 150, 450, 1000, 1200, 1400, 200);
		location[35] = new Railroad(35, "SHORT LINE RAILROAD", 200);
		location[36] = new Card(36, "Chance");
		location[37] = new Lot(37, "PARK PLACE", 350, "Dark Blue", 35, 175, 500, 1100, 1300, 1500, 200);
		location[38] = new Tax(38, "Luxury Tax");
		location[39] = new Lot(39, "BOARDWALK", 400, "Dark Blue", 50, 200, 600, 1400, 1700, 2000, 200);
	}
	
	public void setPlayer(String[] token)
	// PRE:  token[] must be an array of monopoly tokens
	// POST: sets the player's tokens
	{
		for( int i = 0; i < NUM_PLAYERS; i++)
		{
			player[i] = new PlayerClass(token[i]);
		}
	}
	
	public boolean isBankrupt()
	//POST: FCTVAL == true if a player is bankrupt, false if 
	{
		for(int i = 0; i < NUM_PLAYERS; i++)
		{
			if(player[i].getMoney() <= 0)
				return true;
		}
		return false;
	}
	
	public String getStatus()
	// PRE:  Player must be initialized
	// POST: returns the player's statuses
	{
		String output = "";
		System.out.println("");
		
		for( int i = 0; i < NUM_PLAYERS; i++)
		{
			output += player[i].toString() + "\n\n";
		}
		return output;
	}
	
	public int setFirst()
	// POST: random pick for player to go first
	{
		return (int)(NUM_PLAYERS * Math.random());
	}
	
	public BoardLocation[] getLocations()
	// PRE:  location must be initialized
	// POST: returns the board's locations
	{
		return location;
	}
	
	public String DisplayLocations()
	// PRE:  location must be initialized
	// POST: displays the board's locations
	{
		String output;
		output = "";
		
		for (int i = 0; i < NUM_OF_LOCATIONS; i++)
		{
			output += location[i].toString() + "\n";
		}
		return output;
	}
	
	public static String displayLocation(int address)
	// PRE:  address >= 0 and < 40
	// POST: returns the string of the name of the location at address
	{
		return location[address].displayName();
	}
	
	public static String[] houseMenu()
	// POST: returns a menu array giving the player the option to buy or sell houses/hotels
	{
		String[] menu = new String[2];
		
		menu[0] = "Buy Houses/Hotels";
		menu[1] = "Sell Houses/Hotels";
		
		return menu;
	}
	
	public void fastGame()
	// PRE:  Player[] must be initialized and Property must be set
	// POST: sets a faster/demo version of the game by giving initial properties
	{
		player[0].buyProperties(location[1]);
		player[0].buyProperties(location[3]);
		player[0].buyProperties(location[11]);
		player[1].buyProperties(location[6]);
		player[1].buyProperties(location[9]);
		player[1].buyProperties(location[15]);
		player[2].buyProperties(location[13]);
		player[2].buyProperties(location[8]);
		player[2].buyProperties(location[12]);
	}	
	
	public int RunActionsMenu(String[] actions)
	// PRE:  Each element of actions contains a sentence telling a user a possible option.
	// POST: A menu has been displayed to the console listing each action and the user has been
	//       prompted to choose one (with error handling).  actions[FCTVAL] is the action chosen.
	{
		int choice;                                      	// # of action chosen, corresponds to
													        //    index in actions
		Scanner input = new Scanner(System.in);          	// for reading from console
		
		for(int i = 0; i < actions.length; i++)             // show choices
		{
			System.out.printf("%d. %s\n", i, actions[i]);
		}
		
		do                                                  // read in, require valid choice
		{
			System.out.print("\nEnter number of your choice: ");
			choice = input.nextInt();
		} while(choice >= actions.length || choice < 0);
		
		return choice;
	}
	
	public int RunHouseMenu(Lot[] lots)
	//PRE:  lots must be initialized
	//POST: A menu is displayed with all lots that player can either buy or sell houses/hotels
	//         FCTVAL == choice of user
	{
		int choice;                                      	// # of action chosen, corresponds to
        //    index in actions
		Scanner input = new Scanner(System.in);          	// for reading from console
		
		System.out.println("");
		
		for(int i = 0; i < lots.length; i++)             // show choices
		{
			System.out.printf("%d. %s\nImprovement Cost: %d\n\n", i, lots[i].displayName(), 
					lots[i].getImprove());
		}
		
		do                                                  // read in, require valid choice
		{
			System.out.print("Enter number of your choice: ");
			choice = input.nextInt();
		} while(choice >= lots.length || choice < 0);
		
		return choice;
		
	}
	
	public static String[] MainMenu()
	//POST: creates and returns an array of the main menu options
	{	
		String[] mainMenu;                      // array to hold main menu options 
		
		mainMenu = new String[4];
		mainMenu[0] = "Roll dice.";             // 1. Roll the dice
		mainMenu[1] = "Manage houses/hotel.";   // 2. Manage Hotels and Houses
		mainMenu[2] = "View Board";             // 3. View the status of the board
		mainMenu[3] = "End game.";              // 4. End the game
		
		return mainMenu;
	}
	
	public void SetUp()
	//POST: Sets up game, creating an instance of Game, initializes the board locations,
	//         sets isGameOver to false, sets the player tokens, and calls fastGame option
	{
		isGameOver = false;                           // isGameOver is initially set to false
		String[] tokens = {"Racecar", "Boot", "Hat"}; // creates 3 player tokens
		setPlayer(tokens);		                      // create 3 players with tokens
		
		fastGame();                                   // assigns properties to players for
		                                              //   a faster game play option
 	}
	
	public void NextTurn()
	//POST: changes the currentPlayer and currentLocation to the next player
	{
		currentPlayer = getTurn();             // sets currentPlayer
		currentLocation = currentPlayer.getPosition(); // sets currentPosition to the position
	}                                                  //   of the currentPlayer
	
	public void GetPlayer()
	//POST: Prints out who's turn it is
	{
		System.out.println(getStatus());       // displays status of players
	}
	
	public void GetStatus()
	//POST: Prints out the status of all players
	{
		System.out.println(currentPlayer.getToken() + "'s turn\n"); // displays who's turn it is
	}

	public void Roll()
	//POST: rolls the dice
	{
		diceRoll = getDice();                  // rolls dice
	}
	
	public void Movement()
	//POST: moves player based on dice roll
	{
		BoardLocation.move(currentPlayer, diceRoll);   // moves player based on diceRoll
		currentLocation = currentPlayer.getPosition(); // resets currentLocation to the location
	}                                                  //   that the currentPlayer is now on
	
	public void ManageHouses()
	//POST: Runs through the menu and actions of Buying and Selling houses
	{
		houseMenuChoice = RunActionsMenu(Game.houseMenu());       
		
		if( houseMenuChoice == 0 && currentPlayer.getLots() > 0)  // if player chooses to buy houses
		{
			lotsToImprove = new Lot[currentPlayer.getLots()];
			lotsToImprove = currentPlayer.buyOrSellHouses(true);
			System.out.println("\nWhich lot would you like to improve?");
			houseMenuChoice = RunHouseMenu(lotsToImprove);
			System.out.println("");
			System.out.println(lotsToImprove[houseMenuChoice].addHouses
					(currentPlayer, true) + "\n");
		}                                                          // if player chooses to sell houses
		else if(houseMenuChoice == 1 && currentPlayer.getImprovedLots() > 0)
		{
			lotsToImprove = new Lot[currentPlayer.getImprovedLots()];
			lotsToImprove = currentPlayer.buyOrSellHouses(false);
			System.out.println("\nWhich lot would you like to sell from?");
			houseMenuChoice = RunHouseMenu(lotsToImprove);
			System.out.println("");
			System.out.println(lotsToImprove[houseMenuChoice].addHouses
					(currentPlayer, false) + "\n");
		}
		else
		{
			System.out.println("\nYou cannot buy or sell any houses\n");
		}
	}
	
	public void DisplayBoard()
	//POST: Displays all Board Locations
	{
		System.out.println(DisplayLocations());
	}
	
	public void GameOver()
	//POST: Ends the game and prints out GAME OVER
	{
		isGameOver = true;
		System.out.println("\nGAME OVER!");
	}
	
	public void LocationActions()
	//POST: Displays diceRoll and new currentLocation.  Then runs through the menu and 
	//         the action that was selected
	{
		
		System.out.println("\nYou rolled a " + diceRoll);
		System.out.println("Current Location: " + location[currentLocation].displayName() + "\n");
		
		actionChoice = RunActionsMenu(location[currentLocation].GetPossibleActions(currentPlayer));
		
		System.out.println(location[currentLocation].RunAction(actionChoice, currentPlayer));
	}
	
	public void CheckDoubles()
	{
		if(!Dice.isDouble() || currentPlayer.getMoney() != 0)
			turn();
		else
			System.out.println("\nYou rolled Doubles! \nGo Again\n");
	}
}
