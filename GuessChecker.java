package assignment2;

import java.util.Arrays;

public class GuessChecker {
    private String[] validColors;
    private int guessLen;

    public GuessChecker(String[] validColors, int guessLen){
        this.validColors = validColors;
        this.guessLen = guessLen;
    }

    //return 0 if not valid guess, 1 if valid, 2 if asking to see history
    public int isValidGuess(String guess){
        String[] guessLetters = guess.split("");    // Store guess as an array
        if(guess.equals("HISTORY")) return 2;             // If "HISTORY" return 2

        if(guess.length()!= this.guessLen) return 0;      //If not valid return 0
        if(!guess.equals(guess.toUpperCase())) return 0;    //If not all uppercase, not valid
        for(int i =0; i<guess.length(); i++){
            if(!Arrays.asList(validColors).contains(guessLetters[i])){
                return 0;                                   //If not all letters are valid, not valid
            }
        }
        return 1;                                           //1 is valid


    }
}
