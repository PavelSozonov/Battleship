/**
 * The class implements the functional conservation in the file 
 * information about the players and restore from file
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
	
	/**
	 * The function save to file object with type PlayerHuman
	 * @param obj - object PlayerHuman
	 * @param i - supplement to the file name
	 * @throws IOException
	 */
	public static void saveHuman(Object obj, int i) throws IOException {
		FileOutputStream fos = new FileOutputStream(i + "tempHuman.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	
	/**
	 * The function save to file object with type PlayerBot
	 * @param obj - object PlayerBot
	 * @param i - supplement to the file name
	 * @throws IOException
	 */
	public static void saveBot(Object obj, int i) throws IOException {
		FileOutputStream fos = new FileOutputStream(i + "tempBot.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	/**
	 * The function loads from file object with type PlayerHuman and returns it
	 * @param i - supplement to the file name
	 * @throws IOException
	 */
	public static PlayerHuman loadPlayerHuman(int i) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(i + "tempHuman.out");
		ObjectInputStream oin = new ObjectInputStream(fis);
		PlayerHuman player = (PlayerHuman) oin.readObject();
		return player;
	}

	/**
	 * The function loads from file object with type PlayerBot and returns it
	 * @param i - supplement to the file name
	 * @throws IOException
	 */
	public static PlayerBot loadPlayerBot(int i) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(i + "tempBot.out");
		ObjectInputStream oin = new ObjectInputStream(fis);
		PlayerBot player = (PlayerBot) oin.readObject();
		return player;
	}
}
