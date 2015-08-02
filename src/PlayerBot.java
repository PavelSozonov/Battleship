/**
 * The class overrides the function of the class Player specific for bot
 */

import java.io.IOException;
import java.io.Serializable;

public class PlayerBot extends Player implements Serializable {
	
	public PlayerBot(String name) {
		super(name);
	}
	
	@Override
	/**
	 * Game's logic in this method. 
	 * Very stupid bot!!!
	 * Chooses a random coordinates, checks to repeat using the checkCell(line, column, seaOfRival).
	 * Send a request to the enemy playerRival.writeTurn(line, column), which in this cage?
	 * Fix a result in seaOfRival.  
	 */
	public void nextTurn(Player playerRival) {
		int line;
		int column;
		do {
			line = Const.RANDOM.nextInt(Const.FIELDSIZE); 
			column = Const.RANDOM.nextInt(Const.FIELDSIZE);
		} while (!checkCellIsEmpty(line, column, seaOfRival));
		
		UI.debugMsg("Line and column: " + (line + 1) + " " + (column + 1));
		// Empty cells are found (line and column), let's go
		// Send a request to the enemy, which in this cage?
		if (playerRival.writeTurn(line, column) == Sea.Cell.Wounded) {
			seaOfRival.field[line][column] = Sea.Cell.Wounded;
		} else seaOfRival.field[line][column] = Sea.Cell.Miss;
		UI.showSea(seaOfRival);
	}
	
	/**
	 * Locate ships in Sea automatically
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void locateShips() {
		int countDeck[] = new int[4]; 
		countDeck[0]= Const.DECK1;
		countDeck[1] = Const.DECK2;
		countDeck[2] = Const.DECK3;
		countDeck[3] = Const.DECK4;
		
		while ((countDeck[0] + countDeck[1] + countDeck[2] + countDeck[3]) > 0) {
			UI.shipsLeft(countDeck[0], countDeck[1], countDeck[2], countDeck[3]);
			try {
				Ship ship = UI.askForLocationShipRandom(this);
				// Check how many x-deck ships unallocated
				if (countDeck[ship.getDeckCount() - 1] > 0) {
					// Check for empty position of first deck (left, top deck of ship)
					if (checkEmpty(ship)) {
						countDeck[ship.getDeckCount() - 1]--;
						sea.setShip(ship);
						sea.setBorder(ship);
						ships[countShips] = ship;
						countShips++;
					} else UI.badLocation();
				} else UI.goodLocation(ship.getDeckCount());
				UI.showSea(sea); // DEBUG
			} catch (IncorrectLocationException e) {
				UI.badLocation();
			}
		}
		sea.delBorder();
	}

}