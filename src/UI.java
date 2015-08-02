import java.io.IOException;
import java.util.Scanner;

/**
 * Class that implements user interface
 * @author user
 *
 */
public class UI {

	/**
	 * The function displays information about player's ships and start the game
	 * @param p1 - player 1
	 * @param p2 - player 2
	 */
	public static void start(Player p1, Player p2) {
		System.out.println(">>> Start game");
		System.out.println("Player 1:");
		showSea(p1.sea);
		System.out.println("Player 2:");
		showSea(p2.sea);
		Game.start(p1, p2);
	}
	
	/**
	 * The function starts the process of alignment of the ships on the field
	 * @param p1 - player 1
	 * @param p2 - player 2
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws IncorrectLocationException
	 */
	public static void locateShips(Player p1, Player p2) throws ClassNotFoundException, IOException, IncorrectLocationException {
		System.out.println(">>> Locate ships Player 1");
		p1.locateShips();
		System.out.println(">>> Locate ships Player 2");
		p2.locateShips();
	}
	
	public static boolean newGameOrLoadFromFile() {
		Scanner in = new Scanner(System.in);
		System.out.print("New game or deserialize (n or l): ");
		String str = in.nextLine();
		if (str.equals("l")) return false; // Load
		else return true; // New game
	}
	
	// Show message when selected location of ship is inadmissible
	public static void badLocation() {
		System.out.println("Ship location is prohibited!");
	}
	
	// Show message when selected location of ship is admissible
	public static void goodLocation(int deckCount) {
		System.out.println("All " + deckCount + "-deck ships was located!");
	}
	
	public static void debugMsg (String msg) {
		System.out.println("DEBUG: " + msg);
	}
	
	public static void shipsLeft (int shipsLeft) {
		System.out.println("Left " + shipsLeft + " ships!");
	}
	
	// Show how many ships left
	public static void shipsLeft(int deck1, int deck2, int deck3, int deck4) {
		System.out.println("Remains to place ships: 1-deck " + deck1 + ", 2-deck " + deck2 + ", 3-deck " + deck3 + ", 4-deck " + deck4);
	}
	
	/**
	 * @param p - player
	 * @return ship - object of class Ship with correct information about location of new ship
	 * @throws IncorrectLocationException 
	 */
	public static Ship askForLocationShipRandom(Player p) throws IncorrectLocationException {
		Ship ship = new Ship();
		// Ask random for line 
		Sea.Line line = Sea.Line.A;
		int lineInt = Const.RANDOM.nextInt(10);
		if (lineInt == 0) line = Sea.Line.A;
		else if (lineInt == 1) line = Sea.Line.B;
		else if (lineInt == 2) line = Sea.Line.C;
		else if (lineInt == 3) line = Sea.Line.D;
		else if (lineInt == 4) line = Sea.Line.E;
		else if (lineInt == 5) line = Sea.Line.F;
		else if (lineInt == 6) line = Sea.Line.G;
		else if (lineInt == 7) line = Sea.Line.H;
		else if (lineInt == 8) line = Sea.Line.I;
		else if (lineInt == 9) line = Sea.Line.J;
		// Ask random for column
		int columnInt = Const.RANDOM.nextInt(10);
		int column = 0;
		if (columnInt == 0) column = 1;
		else if (columnInt == 1) column = 2;
		else if (columnInt == 2) column = 3;
		else if (columnInt == 3) column = 4;
		else if (columnInt == 4) column = 5;
		else if (columnInt == 5) column = 6;
		else if (columnInt == 6) column = 7;
		else if (columnInt == 7) column = 8;
		else if (columnInt == 8) column = 9;
		else if (columnInt == 9) column = 10;
		ship.setDeck(0, line, column - 1);
		// Ask random for direction
		int directionInt = Const.RANDOM.nextInt(2);
		Sea.Direction direction;
		if (directionInt == 0) direction = Sea.Direction.Right;
		else direction = Sea.Direction.Bottom;
		ship.setDirection(direction);
		// Ask random for count of deck
		int deck = Const.RANDOM.nextInt(4) + 1;
		ship.setDeckCount(deck);
		Sea.Line lines[] = Sea.Line.values();
		int indexLine = line.ordinal();
		// Filling other deck of ship (2, 3, 4)
		for (int i = 1; i < deck; i++) {
			if (ship.getDirection() == Sea.Direction.Bottom) {
				if (indexLine + 1 < 10) {
					line = lines[++indexLine];
					ship.setDeck(i, line, column - 1);
				} else {
					throw new IncorrectLocationException(); // Ship can't be locate here
				}
			} else {
				if (ship.getDirection() == Sea.Direction.Right) {
					if (column + 1 <= 10) { 
						column++;
						ship.setDeck(i, line, column - 1);
					} else {
						throw new IncorrectLocationException(); // Ship can't be locate here
					}
				} else System.out.println("Direction incorrect!");
			}
		}
		return ship;		
	}
	/**
	 * The function prompts the user for the coordinates, the location and 
	 * number of decks and a new ship if the data is valid, returns an object Ship
	 * @param p - the player whose ships need to arrange
	 * @return
	 * @throws IncorrectLocationException
	 */
	public static Ship askForLocationShip(Player p) throws IncorrectLocationException {
		Ship ship = new Ship();
		Scanner in = new Scanner(System.in);
		// Ask for line 
		System.out.print("Line coordinate of left top deck of ship, A-J: ");
		String str = in.nextLine();
		Sea.Line line = Sea.Line.A;
		if (str.toUpperCase().equals("A")) line = Sea.Line.A;
		else if (str.toUpperCase().equals("B")) line = Sea.Line.B;
		else if (str.toUpperCase().equals("C")) line = Sea.Line.C;
		else if (str.toUpperCase().equals("D")) line = Sea.Line.D;
		else if (str.toUpperCase().equals("E")) line = Sea.Line.E;
		else if (str.toUpperCase().equals("F")) line = Sea.Line.F;
		else if (str.toUpperCase().equals("G")) line = Sea.Line.G;
		else if (str.toUpperCase().equals("H")) line = Sea.Line.H;
		else if (str.toUpperCase().equals("I")) line = Sea.Line.I;
		else if (str.toUpperCase().equals("J")) line = Sea.Line.J;
		else System.out.println("Fatal error: input incorrect value of line: " + str + "\nvalue is replaced by \'A\'");
		// Ask for column
		System.out.print("Column coordinate of left top deck ship, 1-10: ");
		str = in.nextLine();
		int column = 1;
		if (str.equals("1")) column = 1;
		else if (str.equals("2")) column = 2;
		else if (str.equals("3")) column = 3;
		else if (str.equals("4")) column = 4;
		else if (str.equals("5")) column = 5;
		else if (str.equals("6")) column = 6;
		else if (str.equals("7")) column = 7;
		else if (str.equals("8")) column = 8;
		else if (str.equals("9")) column = 9;
		else if (str.equals("10")) column = 10;
		else System.out.println("Fatal error: input incorrect value of column: " + str + "\nvalue is replaced by 1");
		ship.setDeck(0, line, column - 1);
		// Ask for direction
		System.out.print("Direction of ship, Right or Bottom: ");
		str = in.nextLine();
		Sea.Direction direction = Sea.Direction.Right;
		if ((str.toLowerCase().equals("right")) || (str.toLowerCase().equals("r"))) direction = Sea.Direction.Right;
		else if ((str.toLowerCase().equals("bottom")) || (str.toLowerCase().equals("b"))) direction = Sea.Direction.Bottom;
		else System.out.println("Fatal error: input incorrect value of direction: " + str + "\nvalue is replaced by \'r\'");
		ship.setDirection(direction);
		// Ask for count of deck
		System.out.print("Count of deck, 1-4: ");
		str = in.nextLine();
		int deck = 1;
		if (str.equals("1")) deck = 1;
		else if (str.equals("2")) deck = 2;
		else if (str.equals("3")) deck = 3;
		else if (str.equals("4")) deck = 4;
		else System.out.println("Fatal error: input incorrect value of deck: " + str + "\nvalue is replaced by 1");
		ship.setDeckCount(deck);
		Sea.Line lines[] = Sea.Line.values();
		int indexLine = line.ordinal();
		// Filling other decks of ship (2, 3, 4)
		for (int i = 1; i < deck; i++) {
			if (ship.getDirection() == Sea.Direction.Bottom) {
				if (indexLine + 1 < 10) {
					line = lines[++indexLine];
					ship.setDeck(i, line, column - 1);
				} else {
					throw new IncorrectLocationException(); // Ship can't be locate here
				}
			} else {
				if (ship.getDirection() == Sea.Direction.Right) {
					if (column + 1 <= 10) { 
						column++;
						ship.setDeck(i, line, column - 1);
					} else {
						throw new IncorrectLocationException(); // Ship can't be locate here
					}
				} else System.out.println("Direction incorrect!");
			}
		}
		return ship;
	}
	
	/**
	 * The function displays the playing field and its name
	 * Designations:
	 * Deck - "D"
	 * Border - "."
	 * Miss - "-"
	 * Wounded - "W"
	 * Empty - " "
	 * @param sea - link to the sea, which is necessary to display
	 */
	public static void showSea(Sea sea) {
		System.out.println(sea.name);
		System.out.println("   1 2 3 4 5 6 7 8 9 10");
		System.out.println("  ---------------------");
		Sea.Line lines[] = Sea.Line.values();
		for (int i = 0; i < 10; i++) {
			System.out.print(lines[i] + " ");
			for (int j = 0; j < 10; j++) {
				if (sea.field[i][j] == Sea.Cell.Deck) {
					System.out.print("|D");
				} else { 
					if (sea.field[i][j] == Sea.Cell.Border) {
						System.out.print("|.");
					} else {
						if (sea.field[i][j] == Sea.Cell.Miss) {
							System.out.print("|-");
						}
						else {
							if (sea.field[i][j] == Sea.Cell.Wounded) {
								System.out.print("|W");
							} else {
								System.out.print("| ");
							}
						}
					}
				}
			}
			System.out.println("|");
		}
		System.out.println("  ---------------------");
	}
	
	/**
	 * The function checks the number of remaining decks each player and 
	 * displays information about the winner, if the deck is left
	 * @param p1 - player 1
	 * @param p2 - player 2
	 * @throws GameIsNotOverException - the exception is thrown if the function is started when the game is not finished yet 
	 */
	public static void finish(Player p1, Player p2) throws GameIsNotOverException {
		int win = 0;
		if (p1.countOfDeck == 0) {
			UI.debugMsg("p1.countOfDeck = " + p1.countOfDeck + ", p2.countOfDeck = " + p2.countOfDeck);
			win = 2;
		} else {
			if (p2.countOfDeck == 0) {
				UI.debugMsg("p1.countOfDeck = " + p1.countOfDeck + ", p2.countOfDeck = " + p2.countOfDeck);
				win = 1;
			} else {
				// The game is not over
				throw new GameIsNotOverException("p1.countOfDeck = " + p1.countOfDeck + ", p2.countOfDeck = " + p2.countOfDeck);
			}
		}
		System.out.println("Player " + win + " is win!");
	}
}
