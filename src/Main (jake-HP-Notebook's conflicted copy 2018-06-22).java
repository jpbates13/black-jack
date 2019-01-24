import java.util.*;


public class Main {
	private static Scanner reader = new Scanner(System.in);
	public static void main(String args[]){
		printInstructions();
		int[] amounts = getAmounts();
		playBlackJack(amounts[0], amounts[1]);
		System.out.println("Game over!");
	}
	
	public static int[] getAmounts(){
		int[] amounts = new int[2];
		while(amounts[0] >= amounts [1]){
			System.out.println("Enter the ante amount for each round: ");
			amounts[0] = Integer.parseInt(reader.nextLine());
			System.out.println("Enter the total amount of money you have.");
			amounts[1]= Integer.parseInt(reader.nextLine());
			if(amounts[0] >= amounts [1]){
				System.out.println("The ante cannot be greater than the total amount of money!");
			}
		}
		return amounts;
	}
	
	public static void playBlackJack(int ante, int total){
		while(total - ante > 0){
			BlackJackGame game = new BlackJackGame(ante);
			String userInput = "";
			int wager = 0;
			while(!(userInput.equals("check")) && game.handValue <= 21){
				while(wager <= 0 || total - (ante + wager) < 0){
					System.out.println("You have " + (total - ante) + " money left. Enter a wager ");
					wager = Integer.parseInt(reader.nextLine());
					if((wager <= 0 || total - (game.bet + wager) < 0)){
						System.out.println("Invalid betting amount");
					}else{
						game.setWager(wager);
					}
				}
				game.getACard(wager);
				userInput = "";
				if(game.handValue <= 21){
					while(!(userInput.equalsIgnoreCase("check") || userInput.equalsIgnoreCase("hit") || userInput.equals("hit me"))){
						System.out.println("New card? ");
						userInput = reader.nextLine();
						if(!(userInput.equalsIgnoreCase("check") || userInput.equalsIgnoreCase("hit") || userInput.equals("hit me"))){
							System.out.println("Invalid option");
						}
					}
				}else{
					System.out.println("Your hand value is too large at " + game.handValue);
				}
			
			}
			
			if(game.dealerHandValue == 21){
				System.out.println("You lose! The dealer had a value of " + game.dealerHandValue + " with the second card being " + game.secondDealerCard.toString());
				System.out.println("Your hand value was " + game.handValue);
				total = total - (game.bet);
				System.out.println("You have " + total + " money left");
			}else if (game.handValue == 21){
				System.out.println("You win! The dealer had a value of " + game.dealerHandValue + " with the second card being " + game.secondDealerCard.toString());
				System.out.println("Your hand value was " + game.handValue);
				total = total + (game.bet);

				System.out.println("You have " + total + " money left");
			}else if((21 - game.handValue < 21 - game.dealerHandValue) && (21 -game.handValue >= 0)){
				System.out.println("You win! The dealer had a value of " + game.dealerHandValue + " with the second card being " + game.secondDealerCard.toString());
				System.out.println("Your hand value was " + game.handValue);
				total = total + (game.bet);
				System.out.println("You have " + total + " money left");
			}else{
				System.out.println("You lose! The dealer had a value of " + game.dealerHandValue + " with the second card being " + game.secondDealerCard.toString());
				System.out.println("Your hand value was " + game.handValue);
				total = total - (game.bet);
				System.out.println("You have " + total + " money left");
			}
		
		}
	}
	
	public static void printInstructions(){
		System.out.println("Welcome to Black Jack!");
		System.out.println("Here is how to play: ");
		System.out.println("In the first hand the dealer and you both get two cards. You can see one of the dealer's cards.");
		System.out.println("Your goal is to get as close to 21 (without going over) by asking for a new card one at a time.");
		System.out.println("Ask for a new card by saying \"hit\" or \"hit me.\" Once you think you are as close as you cen get you can say \"check.\"");
		System.out.println("If you go over 21 you automatically lose. If you are closer than the dealer to 21 then you win. If you both have 21 then the dealer wins.");
		System.out.println("In this version of the game aces are worth one, all face cards are with 10 and numerical cards are equal to their numerical value.");
		System.out.println("There are no splits in this version (you only get one hand at a time).");
		System.out.println("The game will continue to play until the amount of money you specified at the beginning has been depleted.");
		System.out.println("The ante amount is automatically taken away from your total at the start of each game. You only get the ante back if you win.");
		System.out.println("Good Luck! (Press enter to continue).");
		reader.nextLine();
	}
	
}