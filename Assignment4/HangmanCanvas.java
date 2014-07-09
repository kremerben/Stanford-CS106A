/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	/** Resets the display so that only the scaffold appears */ 
	
	public GObject[] addParts;
	public GLabel guessWord;
	public GLabel guessLetter;
	public String guessedLetters = "";
	public int wrongGuess = 0;
	public GOval drawHead;
	public GLine drawBody;
	public GLine drawLeftArm1;
	public GLine drawLeftArm2;
	public GLine drawRightArm1;
	public GLine drawRightArm2;
	public GLine drawLeftLeg1;
	public GLine drawLeftLeg2;
	public GLine drawRightLeg1;
	public GLine drawRightLeg2;
	public GLine drawLeftFoot;
	public GLine drawRightFoot;
	
	public void reset() {
		CENTERLINE = getWidth()/2; 
		TOP_OF_SCAFFOLD = (getHeight() - SCAFFOLD_HEIGHT - 100)/2; 
		TOP_OF_HEAD = TOP_OF_SCAFFOLD + ROPE_LENGTH;
		TOP_OF_HEAD = TOP_OF_SCAFFOLD + ROPE_LENGTH; 
		TOP_OF_BODY = TOP_OF_HEAD + 2*HEAD_RADIUS;
		TOP_OF_ARMS = TOP_OF_BODY + ARM_OFFSET_FROM_HEAD;
		TOP_OF_LEGS = TOP_OF_BODY + BODY_LENGTH;
		TOP_OF_FEET = TOP_OF_LEGS + LEG_LENGTH;

		GLine drawScaffold = new GLine(CENTERLINE-BEAM_LENGTH,TOP_OF_SCAFFOLD,CENTERLINE-BEAM_LENGTH,TOP_OF_SCAFFOLD+SCAFFOLD_HEIGHT);
		GLine drawBeam = new GLine(CENTERLINE-BEAM_LENGTH,TOP_OF_SCAFFOLD,CENTERLINE,TOP_OF_SCAFFOLD);
		GLine drawRope = new GLine(CENTERLINE,TOP_OF_SCAFFOLD,CENTERLINE,TOP_OF_HEAD);

		add(drawScaffold);
		add(drawBeam);
		add(drawRope);
		
		drawHead = new GOval(CENTERLINE-HEAD_RADIUS,TOP_OF_HEAD,2*HEAD_RADIUS,2*HEAD_RADIUS);
		drawBody = new GLine(CENTERLINE,TOP_OF_BODY,CENTERLINE,TOP_OF_LEGS);
		drawLeftArm1 = new GLine(CENTERLINE,TOP_OF_ARMS,CENTERLINE-UPPER_ARM_LENGTH,TOP_OF_ARMS);
		drawLeftArm2 = new GLine(CENTERLINE-UPPER_ARM_LENGTH,TOP_OF_ARMS,CENTERLINE-UPPER_ARM_LENGTH,TOP_OF_ARMS + LOWER_ARM_LENGTH);
		drawRightArm1 = new GLine(CENTERLINE,TOP_OF_ARMS,CENTERLINE+UPPER_ARM_LENGTH,TOP_OF_ARMS);
		drawRightArm2 = new GLine(CENTERLINE+UPPER_ARM_LENGTH,TOP_OF_ARMS,CENTERLINE+UPPER_ARM_LENGTH,TOP_OF_ARMS + LOWER_ARM_LENGTH);
		drawLeftLeg1 = new GLine(CENTERLINE,TOP_OF_LEGS,CENTERLINE - HIP_WIDTH,TOP_OF_LEGS);
		drawLeftLeg2 = new GLine(CENTERLINE - HIP_WIDTH,TOP_OF_LEGS,CENTERLINE - HIP_WIDTH,TOP_OF_LEGS + LEG_LENGTH);
		drawRightLeg1 = new GLine(CENTERLINE,TOP_OF_LEGS,CENTERLINE + HIP_WIDTH,TOP_OF_LEGS);
		drawRightLeg2 = new GLine(CENTERLINE + HIP_WIDTH,TOP_OF_LEGS,CENTERLINE + HIP_WIDTH,TOP_OF_LEGS + LEG_LENGTH);
		drawLeftFoot = new GLine(CENTERLINE - HIP_WIDTH,TOP_OF_FEET,CENTERLINE - HIP_WIDTH - FOOT_LENGTH,TOP_OF_FEET);
		drawRightFoot = new GLine(CENTERLINE + HIP_WIDTH,TOP_OF_FEET,CENTERLINE + HIP_WIDTH + FOOT_LENGTH,TOP_OF_FEET);
		
		   guessWord = new GLabel("");
		   add(guessWord,50,TOP_OF_FEET + 50);
		   guessLetter = new GLabel("");
		   add(guessLetter,50,TOP_OF_FEET + 70 + guessWord.getHeight());

   }

	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game. The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens. 
	 * */
   public void displayWord(String word) {
	  // display word with dashes
	   guessWord.setLabel(word);
   }

   /**
    * Updates the display to correspond to an incorrect guess by the
    * user. Calling this method causes the next body part to appear
    * on the scaffold and adds the letter to the list of incorrect
    * guesses that appears at the bottom of the window. 
    * */
   public void noteIncorrectGuess(char letter) { 
	   /* You fill this in */
	   //add letter to label
	   guessedLetters += "" + letter;
	   guessLetter.setLabel(guessedLetters);
	   //add body part
	   switch(wrongGuess) {
	   case 0: add(drawHead); break;
	   case 1: add(drawBody); break;
	   case 2: add(drawLeftArm1); add(drawLeftArm2); break;
	   case 3: add(drawRightArm1); add(drawRightArm2); break;
	   case 4: add(drawLeftLeg1); add(drawLeftLeg2); break;
	   case 5: add(drawRightLeg1); add(drawRightLeg2); break;
	   case 6: add(drawLeftFoot); break;
	   case 7: add(drawRightFoot); break;
	   default: break;
	   }
	   wrongGuess++;
   }

   /* Constants for the simple version of the picture (in pixels) */ 
   private static final int SCAFFOLD_HEIGHT = 360; 
   private static final int BEAM_LENGTH = 144; 
   private static final int ROPE_LENGTH = 18; 
   private static final int HEAD_RADIUS = 36; 
   private static final int BODY_LENGTH = 144; 
   private static final int ARM_OFFSET_FROM_HEAD = 28;
   private static final int UPPER_ARM_LENGTH = 72; 
   private static final int LOWER_ARM_LENGTH = 44; 
   private static final int HIP_WIDTH = 36; 
   private static final int LEG_LENGTH = 108; 
   private static final int FOOT_LENGTH = 28;

   private double CENTERLINE;  
   private double TOP_OF_SCAFFOLD; //(getHeight() - SCAFFOLD_HEIGHT)/2; 
   private double TOP_OF_HEAD; // = TOP_OF_SCAFFOLD + ROPE_LENGTH; 
   private double TOP_OF_BODY; // = TOP_OF_HEAD + 2*HEAD_RADIUS;
   private double TOP_OF_ARMS; // = TOP_OF_BODY + ARM_OFFSET_FROM_HEAD;
   private double TOP_OF_LEGS; // = TOP_OF_BODY + BODY_LENGTH;
   private double TOP_OF_FEET; // = TOP_OF_LEGS + LEG_LENGTH;

}