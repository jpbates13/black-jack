import java.util.*;
public class BlackJackGame {
	
	public static int cardCount;
	
	private int ante;
	public int bet;
	public Deck deck;
	
	private Card firstDealerCard;
	public Card secondDealerCard;
	
	private List<Card> hand;
	public int handValue;
	public int dealerHandValue;
	public int rounds;
	public int winningRounds;
	public int losingRounds;
	public int betNum;
	public int betTotal;
	public int betAvg;
	
	public BlackJackGame(){
		rounds = 0;
		winningRounds = 0;
		losingRounds = 0;
		betNum = 0;
		betTotal = 0;
		betAvg = 0;
	}
	
	public void reset(int playerante) {
		ante = playerante;
		bet = ante;
		handValue = 0;
		cardCount = 0;
		dealerHandValue = 0;
		hand = new ArrayList<Card>();
		String[] ranks = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", 
				"jack", "queen", "king", "ace"};
		String[] suits = {"diamonds", "hearts", "clubs", "spades"};
		int[] pointValues = new int[13];
		for(int i = 0; i < 9 ; i++){
			pointValues[i] = i+2;
		}
		for(int i = 9; i < 12; i++){
			pointValues[i] = 10;
		}
		pointValues[12] = 1;
		deck = new Deck(ranks, suits, pointValues);
		deck.shuffle();
	}
	
	public void getACard(int wager){
		if(cardCount == 0){
			cardCount += 2;
			Card firstDealtCard = deck.deal();
			firstDealerCard = deck.deal();
			Card secondDealtCard = deck.deal();
			secondDealerCard = deck.deal();
			handValue = handValue + firstDealtCard.pointValue() + secondDealtCard.pointValue();
			System.out.println("You were dealt a " + firstDealtCard.toString() + " and a " + secondDealtCard.toString() + ". This brings your value to " + handValue);
			System.out.println("The dealer has two cards. The face up one is " +  firstDealerCard.toString());
			dealerHandValue = dealerHandValue + firstDealerCard.pointValue() + secondDealerCard.pointValue();
			
		}else{
			Card dealtCard = deck.deal();
			System.out.println(dealtCard.toString());
			handValue = handValue + dealtCard.pointValue();
			System.out.println("You were dealt a " + dealtCard.toString() + ". This brings your value to " + handValue);
		}	
	}
	
	public void setWager(int wager){
		bet = bet + wager;
		betNum++;
		betTotal = betTotal + bet;
		betAvg = betTotal / betNum;
	}
	
	public void adjustRounds(String outcome) {
		rounds++;
		if(outcome.equals("w")) {
			winningRounds = winningRounds +1;
		}else {
			losingRounds++;
		}
	}
	
	
}
