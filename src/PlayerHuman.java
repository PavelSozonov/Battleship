/**
 * The class overrides the function of the class Player specific for human
 */

import java.io.Serializable;
import java.util.Scanner;

public class PlayerHuman extends Player implements Serializable {
	
	public PlayerHuman(String name) {
		super(name);
	}

	@Override
	/**
	 * Asks the location of ships, checks can be inserted, places on the field
	 */
	public void locateShips() throws IncorrectLocationException {
		int countDeck[] = new int[4]; 
		countDeck[0]= Const.DECK1;
		countDeck[1] = Const.DECK2;
		countDeck[2] = Const.DECK3;
		countDeck[3] = Const.DECK4;
		
		while ((countDeck[0] + countDeck[1] + countDeck[2] + countDeck[3]) > 0) {
			UI.shipsLeft(countDeck[0], countDeck[1], countDeck[2], countDeck[3]);
			try {
				Ship ship = UI.askForLocationShip(this);
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
				UI.shipsLeft(countDeck[0] + countDeck[1] + countDeck[2] + countDeck[3]);
				UI.showSea(this.sea); // DEBUG
			} catch (IncorrectLocationException e) {
				UI.badLocation();
			}
		}
	}
}
