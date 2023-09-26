/*
 * Mastermind
 * Jan 20, 2020
 */

package assignment2;

import java.util.Scanner;

public class Driver {



    public static void main(String[] args) {
        // Use this for your testing.  We will not be calling this method.

        GameConfiguration config = new GameConfiguration(12, new String[]{"B","G","O","P","R","Y"}, 5);
        SecretCodeGenerator generator = new SecretCodeGenerator(config);
        start(true, config, generator);
    }

    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
        // TODO: complete this method
		// We will call this method from our JUnit test cases.
        Scanner in = new Scanner(System.in);
        String newGame = "Y";
        System.out.println("Welcome to Mastermind.");

        while(newGame.equals("Y")){
            System.out.println("Do you want to play a new game? (Y/N): ");
            newGame = in.next();

            if(newGame.equals("Y")){
                Game game = new Game(isTesting, config, generator);
                game.runGame(in);
            }
        }
    }
}
