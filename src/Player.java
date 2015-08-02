import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * Abstract class that contains the general characteristics and functions of the players 
 * @author user
 *
 */
abstract public class Player implements Serializable {
	
	public int countShips = 0; // Counter of saved (located in map) ships
	protected Ship ships[] = new Ship[Const.COUNTSHIPS]; // Array of all ships
	public int countOfDeck = 4 * Const.DECK4 + 3 * Const.DECK3 + 2 * Const.DECK2 + Const.DECK1;
	public String name;
	
	public Sea sea; // Used by setShips() for the location of ships
	public Sea seaOfRival; // Used by nextTurn() to select next turn
	
	public Player(String name) {
		this.name = name;
		sea = new Sea(name + " sea");
		seaOfRival = new Sea(name + " sea of rival"); 
	}
	
	public int getCountShips() {
		return countShips;
	}

	public void setCountShips(int countShips) {
		this.countShips = countShips;
	}

	/**
	 * Checks whether it is possible to place the ship in these cells
	 * @param ship
	 * @return
	 */
	public boolean checkEmpty(Ship ship) {
		boolean flag = true;
		for (int i = 0; i < ship.getDeckCount(); i++) {
			int line = ship.getDeckLine(i).ordinal();
			int column = ship.getDeckColumn(i);
			if (sea.field[line][column] != Sea.Cell.Empty) flag = false;
		}
		return flag;
	}
	
	/**
	 * Game's logic in this method
	 */
	public void nextTurn(Player playerRival) {

	}
	
	/**
	 * Check status of cell, Empty or not Empty
	 * @param l - line
	 * @param c - column
	 * @param sea 
	 * @return true is Empty else false
	 */
	public boolean checkCellIsEmpty(int l, int c, Sea sea) {
		if (sea.field[l][c] == Sea.Cell.Empty) return true;
		else return false;
	}
	
	/**
	 * Fixes made by an opponent step, check the number of entire decks. Return answer Wanted or Miss
	 * @param line
	 * @param column
	 * @return
	 */
	public Sea.Cell writeTurn(int line, int column) {
		if (sea.field[line][column] == Sea.Cell.Deck) {
			countOfDeck--;
			sea.field[line][column] = Sea.Cell.Wounded;
			return Sea.Cell.Wounded;
		} else {
			sea.field[line][column] = Sea.Cell.Miss;
			return Sea.Cell.Miss;
		}
	}
	
	/**
	 * Locate ships in sea
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void locateShips() throws ClassNotFoundException, IOException, IncorrectLocationException {

	}
}
