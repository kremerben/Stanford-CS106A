/** Hangman game
 * 
 * @author kremerdesign
 *
 * this game is a console and graphical version of the 
 * popular game hangman where the player guesses the 
 * word letter-by-letter
 * 
 */

import acm.util.*;
import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;


public class Hangman extends ConsoleProgram {
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
	public void run(){
		setup();
		canvas.reset();

//		println(guessWord);
//		println(whileGuessWord);

		while(!gameOver()){
			println("The word now looks like this: " + whileGuessWord);
			canvas.displayWord(whileGuessWord);
			if (guessesLeft > 1){
				println("You have " + guessesLeft + " guesses left.");
			} else if (guessesLeft == 1){
					println("You have only one guess left.");
			}
			lastGuess = readLine("Your guess: ");
			updateGuessing();
		}
	}
		
	private void updateGuessing() {
		if (lastGuess.length() > 1) {
			println("only one character please");
			return;
		}
		lastGuess = lastGuess.trim().toUpperCase();
		lastGuessChar = lastGuess.charAt(0);
		if (!Character.isLetter(lastGuessChar)) {
			println("letters only please");
			return;
		}
		if (guessWord.indexOf(lastGuessChar) >= 0){
			for (int i = 0; i < guessWord.length(); i++) {
				if (guessWord.charAt(i) == lastGuessChar){
					whileGuessWord = whileGuessWord.substring(0, i) + lastGuessChar + whileGuessWord.substring(i+1);
				}
			}
			println("That guess is correct!");
		} else {
			println("There are no " + lastGuessChar +"'s in the word.");
			canvas.noteIncorrectGuess(lastGuessChar);
			guessesLeft--;
		}
	}
	
	private boolean gameOver(){
		if (guessesLeft > 0 && lettersRemain()) {
			return false;
		} else if (guessesLeft > 0 && !lettersRemain()) {
			canvas.displayWord(whileGuessWord);
			println("You guessed the word: " + guessWord);
			println("You win!");
		} else if (guessesLeft <= 0 && lettersRemain()){
			canvas.displayWord(guessWord);
			println("You're completely hung.");
			println("The word was: " + guessWord);
			println("You lose.");
		}
		return true;
	}
	
	private boolean lettersRemain() {
		if (whileGuessWord.indexOf("-") >= 0 ){
			return true;
		}
		return false;
	}
	
	private void setup(){
		guessWord = chooseRandomWord();
		guessWord = guessWord.trim().toUpperCase();
		println("Welcome to Hangman!");
		for (int i = 0; i < guessWord.length();i++){
			whileGuessWord += "-";
		}
	}
	
	private String chooseRandomWord() {
		println(wordLibrary.getWordCount());
		return wordLibrary.getWord(rgen.nextInt(wordLibrary.getWordCount()-1));
	}

	public static final int INITIAL_GUESSES = 8; 
	
	/* Private instance Variables */
	
	private HangmanCanvas canvas;
	private String guessWord, lastGuess; 
	private char lastGuessChar;
	public static String whileGuessWord = "";
	private int guessesLeft = INITIAL_GUESSES;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon wordLibrary = new HangmanLexicon();
	
}
