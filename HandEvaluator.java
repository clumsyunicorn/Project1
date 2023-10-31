import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class HandEvaluator {
	
	ArrayList<Card> hand = new ArrayList<Card>() ;
	ArrayList<Card> deck = new ArrayList<Card>();
	
	public enum Hands{
		PAIR, THREEOFAKIND, FOUROFAKIND, STRAIGHT, FLUSH, FULLHOUSE, STRAIGHTFLUSH,
		ROYALFLUSH, HIGHCARD;
	}
	
	public ArrayList<Card> Deck() {
		Card card = new Card();
		
		//reset deck
		deck.clear();
		
		//generate 52 unique cards
		for (int s = 0; s <= 3; s++) {
			for(int v = 1; v <= 13; v++) {
				card = new Card(s, v);
				deck.add(card);
			}
		}
		
		//shuffle the deck
		Collections.shuffle(deck);
		
		return deck;
	}
	
	public Card DrawCard() {
		//check if deck is empty
		if (deck.isEmpty()) {
	        Deck();
	    }
		
		Card card = new Card();
		Random rng = new Random();
		
		//pick a random position in the deck
		int pos = rng.nextInt(deck.size());
		card = deck.get(pos);
		
		//remove card from deck
		deck.remove(pos);
		
		return card;
	}
	
	public ArrayList<Card> DrawHand(int size) {
		//check if deck is empty
		if (deck.isEmpty()) {
	        Deck();
	    }
		
		Card card = new Card();
		hand.clear();
		
		//generate specified number of cards
		for (int i = 0; i < size; i++) {
			card = DrawCard();
			hand.add(card);
		}
		
		return hand;	
	}
	
	public void PrintHand(ArrayList<Card> hand) {	
		for (Card card: hand) {
			System.out.println(card.toString());
		}
		
	}
	
	public void Deal(int runs, int size, String handtype) {
		ArrayList<Hands> hands;
		Hands handtypes = Hands.valueOf(handtype);
		double count = 0;
		double outcome;
		
		for (int i = 0; i < runs; i++) {
			Deck();
			hand = DrawHand(size);
			hands = HandType(hand);
			
			for (Hands h : hands) {
				if (h.equals(handtypes)) {
					count++;
				}
			}
		}
		outcome = count/runs;
		System.out.println("Heres the probability of getting a " + handtype + " : " + outcome);
		
	}
	
	
	
	public ArrayList<Hands> HandType(ArrayList<Card> theHand) {
		ArrayList<Hands> handType = new ArrayList<Hands>();
		
		if (evalPair(theHand)) {
			handType.add(Hands.PAIR);
		} else if (evalThreeKind(theHand)) {
			handType.add(Hands.THREEOFAKIND);
		} else if (evalFourKind(theHand)) {
			handType.add(Hands.FOUROFAKIND);
		} else if (evalStraight(theHand)) {
			handType.add(Hands.STRAIGHT);
		} else if (evalFlush(theHand)) {
			handType.add(Hands.FLUSH);
		} else if (evalFullHouse(theHand)) {
			handType.add(Hands.FULLHOUSE);
		} else if (evalStraightFlush(theHand)) {
			handType.add(Hands.STRAIGHTFLUSH);
		} else if (evalRoyalFlush(theHand)) {
			handType.add(Hands.ROYALFLUSH);
		} else {
			handType.add(Hands.HIGHCARD);
		}
		
		return handType;
	}
	
	//Two different pairs
	public boolean evalPair(ArrayList<Card> theHand) {
	    boolean pair = false;
	    int count = 0;
	    
	    //arrayList to keep track of matching cards
	    ArrayList<Integer> values = testArray(14); 
	    

	    //add up matching pairs
	    for (int i = 0; i < theHand.size(); i++) {
	        int value = theHand.get(i).getValue();
	        values.set(value, values.get(value) + 1);
	    }

	    for (int v : values) {
	        if (v == 2) {
	            count++;
	        }
	    }

	    if (count == 2) {
	        pair = true;
	    }
	    
	    return pair;
	}
	
	//Three of the same value
	public boolean evalThreeKind(ArrayList<Card> theHand) {
	    boolean pair = false;
	    
	    //arrayList to keep track of matching values
	    ArrayList<Integer> values = testArray(14);
	    		
	    //add up matching values
	    for (int i = 0; i < theHand.size(); i++) {
	        int value = theHand.get(i).getValue();
	        values.set(value, values.get(value) + 1);
	    }

	    for (int v : values) {
	        if (v == 3) {
	            pair = true;
	        }
	    }
	    
	    return pair;
	}
	
	//Four or more of the same value
	public boolean evalFourKind(ArrayList<Card> theHand) {
		boolean pair = false;
	    
	    //arrayList to keep track of matching values
	    ArrayList<Integer> values = testArray(14);

	    //add up matching values
	    for (int i = 0; i < theHand.size(); i++) {
	        int value = theHand.get(i).getValue();
	        values.set(value, values.get(value) + 1);
	    }

	    for (int v : values) {
	        if (v == 4) {
	            pair = true;
	        }
	    }
	    
	    return pair;
	}
	
	//Five consecutive cards
	public boolean evalStraight(ArrayList<Card> theHand) {
		boolean consecutive = false;
		int count = 0;
		
		//put the cards in order based on their values
		Collections.sort(theHand, new sortByValue());
		
		for (int i = 0; i < theHand.size()-1; i++) {
			if(theHand.get(i).getValue()+1 == theHand.get(i+1).getValue()) {
				count++;
			}
		}
		
		if (count >= 4) {
			consecutive = true;
		}
		return consecutive;
	}
	
	//5 of the same suit
	public boolean evalFlush(ArrayList<Card> theHand) {
		boolean flush = false;
	    
	    //arrayList to keep track of matching values
	    ArrayList<Integer> suits = testArray(5);

	    //add up matching values
	    for (int i = 0; i < theHand.size(); i++) {
	        int suit = theHand.get(i).getSuit();
	        suits.set(suit, suits.get(suit) + 1);
	    }

	    for (int s : suits) {
	        if (s == 5) {
	            flush = true;
	        }
	    }
	    
	    return flush;
	}
	
	//A pair and three of a kind
	public boolean evalFullHouse(ArrayList<Card> theHand) {
		boolean full = false;
		boolean verify2 = false;
		boolean verify3 = false;
		
		//arrayList to keep track of matching values
	    ArrayList<Integer> values = testArray(14);
	    
	    //add up matching values
	    for (int i = 0; i < theHand.size(); i++) {
	        int value = theHand.get(i).getValue();
	        values.set(value, values.get(value) + 1);
	    }
		
		/*confirm if there is a pair 
		 * and a three of a kind in the hand
		 */
		for (int v : values) {
			if (v == 2) {
				verify2 = true;
			}
			
			if (v == 3) {
				verify3 = true;
			}
		}
		
		if (verify2 && verify3) {
			full = true;
		}
		
		return full;
	}
	
	//Five of same suit, sequential rank 
	public boolean evalStraightFlush(ArrayList<Card> theHand) {
		int count = 0;
		boolean flush = false;
		
		//put the cards in order based on their values
		Collections.sort(theHand, new sortBySuit());
		
		for (int i = 0; i < theHand.size()-1; i++) {
			if(theHand.get(i).getSuit() == theHand.get(i+1).getSuit()) {
				count++;
			}
		}
		
		if(count >= 4) {
			flush = true;
		}
		
		return flush;
	}
	
	//10 and up and same suit
	public boolean evalRoyalFlush(ArrayList<Card> theHand) {
		int count = 0;
		boolean flush = false;
		
		
		
		for (int i = 0; i < theHand.size(); i++) {
			for (int j = i + 1; j < theHand.size(); j++) {
				
				//temp variables for comparing suits
				int suit1 = theHand.get(i).getSuit();
				int suit2 = theHand.get(j).getSuit();
				
				//temp variables for comparing values
				int val1 = theHand.get(i).getValue();
				int val2 = theHand.get(j).getValue();
				
				if((suit1 == suit2) && ((val1 >= 10) || (val1 == 1)) && ((val1 >= 10) || (val1 == 1))) {
					count++;
				}
			}
		}
		
		if(count == 5) {
			flush = true;
		}
		
		return flush;
	}
	
	//generate arrays for determining hand types
	public ArrayList<Integer> testArray(int size){
		
		//arrayList of specified size
	    ArrayList<Integer> test = new ArrayList<>(size); 
	    
	    //zeros as place holders
	    for (int i = 0; i <= size - 1; i++) {
	        test.add(0);
	    }
	    
	    return test;
	}
	
	//inspired by https://www.geeksforgeeks.org/collections-sort-java-examples/#
	class sortByValue implements Comparator<Card> {
		public int compare(Card x, Card y) {
			return x.getValue() - y.getValue();
		}
	}
	
	//inspired by https://www.geeksforgeeks.org/collections-sort-java-examples/#
	class sortBySuit implements Comparator<Card>{
		public int compare(Card x, Card y) {
			return x.getSuit() - y.getSuit();
		}
	}
	








}