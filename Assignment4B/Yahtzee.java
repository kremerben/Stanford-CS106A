/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.HashMap;
import java.util.Map;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		playerScores = new int[nPlayers][4];
		playedCat = new int[nPlayers][N_CATEGORIES];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
			playerScores[i][0] = 0; //upper
			playerScores[i][1] = 0; //lower
			playerScores[i][2] = 0; //bonus
			playerScores[i][3] = 0; //total
			for (int j = 0; j < N_CATEGORIES; j++){
				playedCat[i][j] = 1;
			}
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		/* You fill this in! */
		for (int i = 1; i <= N_SCORING_CATEGORIES; i++) {
			for (int player = 0; player < nPlayers; player++) {
				display.printMessage(playerNames[player] + "'s turn!, Click the \"Roll Dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(player);
				dice = randomDiceRoll();
				
				for (int m = 0; m < 2; m++) {
				display.displayDice(dice);
				display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
				display.waitForPlayerToSelectDice();
				for (int k = 0; k < N_DICE; k++) {
					if (display.isDieSelected(k)) {
						dice[k] = randomSingleDieRoll();
					}	
				}
				}
				display.displayDice(dice);
				display.printMessage("Select a category for this roll");
				int category = display.waitForPlayerToSelectCategory();
				// check if category is already filled
				while (isCategoryUsed(player,category)) {
					display.printMessage("You already picked that category. Please choose another category.");
					category = display.waitForPlayerToSelectCategory();
				}
				playedCat[player][category] = 0;
				
				int score = getScore(category);
				display.updateScorecard(category, player, score);
				updatePlayerScores(category, player, score);
//				playerScores[player][0] += score;
				
				
				int upperScore = playerScores[player][0];
				int lowerScore = playerScores[player][1];
				int bonusScore = playerScores[player][2];
				int totalScore = playerScores[player][3];
				
				display.updateScorecard(UPPER_SCORE, player, upperScore);
				display.updateScorecard(UPPER_BONUS, player, bonusScore);
				display.updateScorecard(LOWER_SCORE, player, lowerScore);
				display.updateScorecard(TOTAL, player, totalScore);
			}
		}
		
		//tally total scores and announce winner
		int winner = findWinner();
		int winnerScore = playerScores[winner][3];
		display.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of "+ winnerScore);
	}
	
	private boolean isCategoryUsed(int player, int category) {
		if (playedCat[player][category] == 0) {
			return true;
		}
		playedCat[player][category] = 0;
		return false;
	}
	
	private void updatePlayerScores(int category, int player, int score) {
		if (category < 6) {
			playerScores[player][0] += score;
		} else {
			playerScores[player][1] += score;
		}
		if (playerScores[player][0] >= 63) {
			playerScores[player][2] = 35;
		}
		playerScores[player][3] = playerScores[player][0] + playerScores[player][1] + playerScores[player][2];

	}

	private int getScore(int categoryNum) {		
		if (categoryNum < 0 || categoryNum > N_CATEGORIES) {
			return 0;
		}
		if (categoryNum < 6) {
			int scorable = 0;
			for (int i = 0; i < N_DICE; i++){
				if (dice[i] == categoryNum + 1) {
					scorable++;
				}
			}
			return scorable * (categoryNum + 1);
		}
		if (categoryNum == 14) {
			return totalDice();
		}
		if (categoryNum == 8 || categoryNum == 9) {
			if (checkCategory(categoryNum)) {
				return totalDice();
			} 
		}
		if (categoryNum == 13 && checkCategory(categoryNum)) {
			return 50;
		} 
		if (categoryNum == 10 && checkCategory(categoryNum)) {
			return 25;
		}
		
		if (categoryNum == 11 && checkCategory(categoryNum)) {
			return 30;
		}
		if (categoryNum == 12 && checkCategory(categoryNum)) {
			return 40;
		}		
		return 0;
	}

	private boolean checkCategory(int category) {
		Map<Integer, Integer> dieTotal = new HashMap<Integer, Integer>();
		for (int i = 0; i < N_DICE; i++){
			if (dieTotal.get(dice[i]) == null) {
				dieTotal.put(dice[i], 1);
			} else {
				dieTotal.put(dice[i], (dieTotal.get(dice[i]) + 1));
			}
		}
		if (category == 8 || category == 9 || category == 13) {
			//check if any are 3 or 4 or 5 as needed
			if (category == 8 && (dieTotal.containsValue(3) || dieTotal.containsValue(4) || dieTotal.containsValue(5))){
					return true;
			} else if (category == 9 && (dieTotal.containsValue(4) ||dieTotal.containsValue(5))) {
				return true;
			} else if (category == 13 && dieTotal.containsValue(5)){
				return true;
			}
		}
		// category 10
		if (category == 10 && dieTotal.containsValue(2) && dieTotal.containsValue(3)) {
			return true;
		}
		// categories 11, 12
		if (category == 11 || category == 12) {
			if (!(dieTotal.containsKey(3) && dieTotal.containsKey(4))) {
				return false;
			}
			if (category == 11) {
				if (dieTotal.containsKey(1) && dieTotal.containsKey(2)) {
					return true;
				}
				if (dieTotal.containsKey(2) && dieTotal.containsKey(5)) {
					return true;
				}
				if (dieTotal.containsKey(5) && dieTotal.containsKey(6)) {
					return true;
				}
			}
			if (category == 12) {
				if (dieTotal.containsKey(1) && dieTotal.containsKey(2)  && dieTotal.containsKey(5)) {
					return true;
				}
				if (dieTotal.containsKey(2) && dieTotal.containsKey(5)  && dieTotal.containsKey(6)) {
					return true;
				}
			}
		}	
		return false;
	}

	private int findWinner() {
		int win = 0;
		for (int i = 1; i < nPlayers; i++){
			if (playerScores[win][3] < playerScores[i][3]) {
				win = i;
			}	
		}
		return win;
	}
	
	
	private int totalDice() {
		int total = 0;
		for (int i = 0; i < N_DICE; i++) {
			total += dice[i];
		}
		return total;
	}
	
	private int[] randomDiceRoll() {
		int[] roll = {randomSingleDieRoll(), randomSingleDieRoll(), randomSingleDieRoll(), randomSingleDieRoll(), randomSingleDieRoll()};
		return roll;
	}
	private int randomSingleDieRoll() {
		return rgen.nextInt(1,6);
	}
		
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private int[][] playerScores;
	private int[][] playedCat;
	private YahtzeeDisplay display;
	private int[] dice;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	
}
