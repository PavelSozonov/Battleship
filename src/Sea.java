/**
 * The class contains information about the playing field Player
 * 
 * import java.io.Serializable;
 * @author user
 *
 */

import java.io.Serializable;

public class Sea implements Serializable {
	
	public Cell[][] field = new Cell[Const.FIELDSIZE][Const.FIELDSIZE];
	public String name; // Name of playing filed
	
	public Sea(String name) {
		this.name = name;
		for (int i = 0; i < Const.FIELDSIZE; i++) {
			for (int j = 0; j < Const.FIELDSIZE; j++) {
				field[i][j] = Cell.Empty;
			}
		}
	}
	
	public enum Line {
		A, B, C, D, E, F, G, H, I, J
	}
	
	public enum Direction {
		Right, Bottom
	}
	
	public enum Cell {
		Deck, Unkonwn, Wounded, Empty, Border, Miss 
	}
	
	public void setCell(Line line, int column, Cell cell) {
		field[line.ordinal()][column] = cell;		
	}
	
	public void setShip(Ship ship) {
		for (int i = 0; i < ship.getDeckCount(); i++) {
			int line = ship.getDeckLine(i).ordinal();
			int column = ship.getDeckColumn(i);
			field[line][column] = Cell.Deck;
		}
	}
	
	public static void delBorder(Sea sea) {
		for (int i = 0; i < Const.FIELDSIZE; i++) {
			for (int j = 0; j < Const.FIELDSIZE; j++) {
				if (sea.field[i][j] == Cell.Border) {
					sea.field[i][j] = Cell.Empty;
				}
			}
		}
	}

	public void delBorder() {
		for (int i = 0; i < Const.FIELDSIZE; i++) {
			for (int j = 0; j < Const.FIELDSIZE; j++) {
				if (field[i][j] == Cell.Border) {
					field[i][j] = Cell.Empty;
				}
			}
		}
	}

	// Auxiliary function for the method setBorder(Ship ship), if the cell is equal to the Empty changes its Border
	private void f(int line, int column) {
		// Checking of the existence of the coordinate
		if ((line >= 0) && (line < Const.FIELDSIZE ) && (column >= 0) && (column < Const.FIELDSIZE)) { 
			if (field[line][column] == Cell.Empty) field[line][column] = Cell.Border; 
		}
	}
	
	// The function sets the value of Border around the ship
	public void setBorder(Ship ship) {
		
		for (int i = 0; i < ship.getDeckCount(); i++) {
			
			int line = ship.getDeckLine(i).ordinal() - 1;
			int column = ship.getDeckColumn(i) - 1;
			f(line, column); 

			line = ship.getDeckLine(i).ordinal() - 1;
			column = ship.getDeckColumn(i);
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal() - 1;
			column = ship.getDeckColumn(i) + 1;
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal();
			column = ship.getDeckColumn(i) - 1;
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal();
			column = ship.getDeckColumn(i) + 1;
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal() + 1;
			column = ship.getDeckColumn(i) - 1;
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal() + 1;
			column = ship.getDeckColumn(i);
			f(line, column);
			
			line = ship.getDeckLine(i).ordinal() + 1;
			column = ship.getDeckColumn(i) + 1;
			f(line, column);
		}
	}

	public Cell getCell(Line line, int column) {
		return field[line.ordinal()][column];
	}
	
}
