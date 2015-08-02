import java.util.Random;

public class Const {

	// The size of each side of the playing field
	public final static int FIELDSIZE = 10;

	// Common generator random numbers
	public final static Random RANDOM = new Random();

	// Ships count
	public final static int DECK1 = 4;
	public final static int DECK2 = 3;
	public final static int DECK3 = 2;
	public final static int DECK4 = 1;
	public final static int COUNTSHIPS = DECK1 + DECK2 + DECK3 + DECK4;
	
}
