
public class Card {
	
	//Values for suits
	public static final int CLUBS = 0;
	public static final int SPADES= 1;
	public static final int HEARTS = 2;
	public static final int DIAMONDS = 3;
	
	//Values for non-numerics
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	
	private int suit;
	private int value;
	
	public Card() {
		suit = 0;
		value = 1;
	}
	
	//Constructor
	public Card(int cardSuit, int cardValue) {
		suit = cardSuit;
		value = cardValue;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String PrintSuit() {
		String cardSuit;
		getSuit();
		
		switch (suit) {
		case CLUBS: cardSuit = "Clubs";
		break;
		case SPADES: cardSuit = "Spades";
		break;
		case HEARTS: cardSuit = "Hearts";
		break;
		case DIAMONDS: cardSuit = "Diamonds";
		break;
		default: cardSuit = "error";
		}
		
		return cardSuit;
	}
	
	public String PrintValue() {
		String cardValue;
		getValue();
		
		switch (value) {
		case ACE: cardValue = "Ace";
		break;
		case JACK: cardValue = "Jack";
		break;
		case QUEEN: cardValue = "Queen";
		break;
		case KING: cardValue = "King";
		break;
		default: cardValue = "" + value;
		}
		
		return cardValue;
	}
	
	public String toString() {
		String cardSuit = PrintSuit();
		String cardValue = PrintValue();
		
		return cardSuit + ", " + cardValue;
	}
}
