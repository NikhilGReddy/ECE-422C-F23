package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.charCount;
import static java.lang.Character.isUpperCase;

public class Game {
    private boolean isTest;
    private String masterCode;
    private int guessesLeft;


    public String[] validColors;
    public int numPegs;

    List<String> History;
    public Game(boolean isTest, GameConfiguration c, SecretCodeGenerator gen){
        this.isTest = isTest;
        this.masterCode = gen.getNewSecretCode();
        this.guessesLeft = c.guessNumber;
        this.validColors = c.colors;
        this.numPegs = c.pegNumber;
    }

    //Return an array with num black pegs at index 0, white pegs at index 1
    private int[] pegCalculator(String guess){
        String x = new String(guess); // Don't actually edit their guess
        int numRem = 0;
        List<String> copyCode = new ArrayList<>(); // Store the String as a List for easier editing
        int[] bwPegs = new int[2];
        for(int i =0; i<guess.length(); i++){
            copyCode.add(this.masterCode.charAt(i)+""); // Create the List of chars that is the String
        }
        for(int i =0; i<guess.length();i++){
            //Check for black pegs, making them unavailable by remving from list and changing in guess
            if(guess.charAt(i) ==(masterCode.charAt(i))){
                bwPegs[0]++;
                x = x.substring(0,i)+'b' + x.substring(i+1); // Make lowercase to make invalid
                copyCode.remove(i-numRem);
                numRem++;
            }
        }
        //Check for white pegs through nested for loop, ignoring if already accounted as black
        for(int i =0; i<x.length(); i++){
            if(x.charAt(i) == 'b'){ // Skip char if already accounted for
                continue;
            }
            for(int k = 0; k<copyCode.size(); k++){
                if(copyCode.get(k).equals(x.charAt(i) +"")){
                    bwPegs[1]++;
                    copyCode.remove(k);
                }
            }
        }
        //Return result as an array where index 0 = black pegs, index 1 = white pegs
        return bwPegs;

    }

    public void runGame(Scanner in){


        if(isTest){
            System.out.println("The Secret Code is: " + masterCode);
        }
        //History = List of all checks/results
        History = new ArrayList<String>();

        while(guessesLeft>0){
            String guess = "";
            System.out.println();
            System.out.println("You have " + guessesLeft + " guess(es) left.");
            System.out.println("Enter guess: ");

            guess = in.next();
            //validate result
            GuessChecker check = new GuessChecker(validColors, numPegs);

            if(check.isValidGuess(guess) == 2){
                for(String x : History){

                    System.out.println(x);
                }
            } else if(check.isValidGuess(guess) ==0){
                System.out.println("INVALID_GUESS");
            } else {
                guessesLeft--;
                int[] pegArr = pegCalculator(guess);        //Calculate Pegs
                String historyForm = guess;
                historyForm += " -> " + pegArr[0] +"b_" + pegArr[1]+ "w";
                History.add(historyForm);                   // Add to history
                System.out.println(historyForm);


                if(pegArr[0] == guess.length()){            //Check if win
                    System.out.println("You win!");
                    System.out.println();
                    break;
                } else if(guessesLeft == 0){                //If no guesses left, you lose
                    System.out.println("You lose! The pattern was " + masterCode);
                    System.out.println();
                }
            }
        }
    }

}
