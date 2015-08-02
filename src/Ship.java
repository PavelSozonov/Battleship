/**
 * Class include information about a ship
 */
import java.io.Serializable;

public class Ship implements Serializable {
	
	class Deck implements Serializable {
		
		Sea.Line line;
		int column;
	}
	
	private Deck[] deck = new Deck[4];
	private Sea.Direction direction;
	private int deckCount;
	//boolean flag = true; // TODO: Convert to Exception: Used by function UI.askForLocationShip() for indicate bad location
	
	public Ship() {
		for (int i = 0; i < 4; i++) deck[i] = new Deck();
	}
	
	public Sea.Line getDeckLine (int num) {
		return deck[num].line;
	}
	
	public int getDeckColumn (int num) {
		return deck[num].column;
	}

	public void setDeck(int num, Sea.Line line, int column) {
		deck[num] = new Deck();
		deck[num].line = line;
		deck[num].column = column;
	}
	
	public enum StatusOfShip {
		Healthy, Wounded, Killed
	}

	public Sea.Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Sea.Direction direction) {
		this.direction = direction;
	}

	public int getDeckCount() {
		return deckCount;
	}

	public void setDeckCount(int deck) {
		this.deckCount = deck;
	}
	
}
	
