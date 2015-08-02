/**
 * Class implementing game logic
 * @author user
 *
 */
public class Game {
	
	public static void start(Player p1, Player p2) {
		boolean flag = true;
		while (true) {	
			if (flag) {
				p1.nextTurn(p2);
				if (p2.countOfDeck == 0) break;
			} else {
				p2.nextTurn(p1);
				if (p1.countOfDeck == 0) break;
			}
			flag = !flag;
		}
	}

}
