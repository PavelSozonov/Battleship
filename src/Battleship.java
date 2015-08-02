import java.io.IOException;

public class Battleship {

	public static void main(String[] args) throws ClassNotFoundException, IOException, IncorrectLocationException, GameIsNotOverException {
		
		if (UI.newGameOrLoadFromFile()) {
			// New game
			PlayerBot p1 = new PlayerBot("Bot1"); // Parameter: name of player
			PlayerBot p2 = new PlayerBot("Bot2");
			UI.locateShips(p1, p2);
			// Save players
			Serialization.saveBot(p1, 1); // Parameters: player, file name
			Serialization.saveBot(p2, 2);
			// Start game
			UI.start(p1, p2);
			// Display information about the winner
			UI.finish(p1, p2);
		} else {
			// Load players
			PlayerBot p1 = Serialization.loadPlayerBot(1);
			PlayerBot p2 = Serialization.loadPlayerBot(2);
			// Start game
			UI.start(p1, p2);
			// Display information about the winner
			UI.finish(p1, p2);
		}
			
	}
	
}
