package com.company;

import java.util.Scanner;


/**
 * Main Class:  Runs the Logic of the Hi Lo Game
 * @author Brian Chalfant 2020
 * CSCI2913 Exercise 6a HILO
 */
public class Main {
    final private static Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    /**\
     * Main Method: Main Menu Gives the user an Interface to Run the game, Customize the Game, Toggle Cheat Mode and Quit
     * @param args not used
     */
    public static void main(String[] args) {
        boolean quit = false;
        int selection;
        HiLoGame hlg = new HiLoGame(); //Instantiate a New Game,
        while (!quit) {
            System.out.println();
            gameMenu();
            selection = promptUser("Enter a Menu Item: ");
            switch (selection) {
                case (1):
                    startGame(hlg);//Starts a New Game
                    break;
                case (2):
                    customizeGame(hlg);// Opens the Customization Options Menu
                    break;
                case (3):
                    toggleCheatMode(hlg);// Toggle Cheat Mode on and off
                    break;
                case (4):
                    quit = true; //Quit the Application
            }


        }


    }

    /**
     * Opens the Customization Options Menu.  Allows the user to Edit the Number Ranges, and the Number of Guesses Per Game
     * @param hlg Instantiated Game Object
     */
    public static void customizeGame(HiLoGame hlg) {
        boolean quit = false;
        while (!quit) {
            customMenu();
            int selection = promptUser("Enter a Menu Option: ");
            switch (selection) {
                case (1):
                    hlg.setMinRange(promptUser("Enter Min Range: "));
                    hlg.setMaxRange(promptUser("Enter Max Range: "));
                    if (hlg.getMinRange() > hlg.getMaxRange()) {   // The range must be valid, if it is not, set it to default values and insult the user
                        hlg.setMinRange(0);
                        hlg.setMaxRange(100);
                        System.out.println(ANSI_RED + "Min Range must be < Max Range, Geez!" + ANSI_RESET);
                    }
                    hlg.setUpperBound(hlg.getMaxRange()); //Reset the upperBound to be inline with new range
                    hlg.setLowerBound(hlg.getMinRange()); //Reset the lowerBound to be inline with new range
                    hlg.resetMagicNumber(); //Reset the MagicNumber so that it lies within the min and max
                    break;
                case (2):
                    hlg.setNumberOfGuesses(promptUser("Set Number of Guesses: "));
                    if (hlg.getNumberOfGuesses() >= 100) { //stops the user from getting excessive guesses and insults the user
                        hlg.setNumberOfGuesses(99);
                        System.out.println(ANSI_RED + "NO! I'm not that patient. (Guesses set to 99)" + ANSI_RESET);
                    }
                    hlg.setTurns(hlg.getNumberOfGuesses());
                    break;
                case (3):
                    quit = true; //go back to main menu
            }

        }
    }

    /**
     * Main Game Logic
     * @param hlg Instantiated Game Object HiLoGame
     */
    public static void startGame(HiLoGame hlg) {
        boolean giveUp = false;
        resetGame(hlg); // gets the game ready to play
        //introductions
        System.out.println("I am thinking of a number between " + ANSI_GREEN + hlg.getMinRange() + ANSI_RESET +
                " and " + ANSI_GREEN + hlg.getMaxRange() + ANSI_RESET + "");
        System.out.println("you have " + ANSI_GREEN + hlg.getNumberOfGuesses() + ANSI_RESET + " guesses");
        System.out.println("Type 'give up' to quit");
        //Main Logic Loop
        while (!hlg.isCorrectGuess() && hlg.getTurns() >= 0) {
            if (hlg.isCheatMode()) { //Cheat Mode Output
                System.out.println("Psssst...the Magic Number is " + hlg.getMagicNumber());
            }
            int guess = promptUser("Enter Your Guess: ", hlg.getMinRange(), hlg.getMaxRange());
            if (guess > 0) {
                if (guess == hlg.getMagicNumber()) {  // Winning Logic
                    System.out.println(ANSI_GREEN + "YOU WIN!!!!!!" + ANSI_RESET);
                    hlg.setTurns(hlg.getNumberOfGuesses());
                    hlg.setCorrectGuess(true);
                    break;
                } else if (guess < hlg.getMagicNumber()) {  //Guess is Lower than Magic Number
                    hlg.takeTurn();
                    if (guess <= hlg.getLowerBound()) {
                        System.out.println("C'mon!  You know better than that!, The number is greater than " + hlg.getLowerBound());
                    } else {
                        System.out.println("That is too low");
                        hlg.setLowerBound(guess);
                    }
                } else if (guess > hlg.getMagicNumber()) { //Guess is Bigger than Magic Number
                    hlg.takeTurn();
                    if (guess >= hlg.getUpperBound()) {
                        System.out.println("C'mon!  You know better than that!, The number is less than " + hlg.getUpperBound());
                    } else {
                        System.out.println("That is too high");
                        hlg.setUpperBound(guess);
                    }
                }
                if (hlg.getTurns() == 0) { //Out of Turns
                    hlg.setCorrectGuess(true);
                    System.out.println("The Correct Answer Was: " + hlg.getMagicNumber());
                    System.out.println(ANSI_GREEN + "YOU LOSE! PLAY AGAIN!" + ANSI_RESET);

                } else {
                    System.out.println("you have " + ANSI_RED + hlg.getTurns() + ANSI_RESET + " guesses");
                }
            } else { // The User is giving up
                if (guess == -2) {
                    if(hlg.getTurns() > 1){
                        System.out.println("\"Persistence Overcomes Resistance - Darth Vader\""); //Insult the User
                    }
                    giveUp = true;  //set the boolean to true, this activates logic outside the loop
                    hlg.setCorrectGuess(true); //get out of the game loop
                    break;
                }
            }
        }
        if (giveUp) { //User Gave up, give them the answer and quit the game
            //display the number and quit
            System.out.println("The Magic Number Was: " + hlg.getMagicNumber());
            hlg.setCorrectGuess(true);
        }

    }

    /**
     * Toggles Cheat Mode on and off
     * @param hlg Instantiated Game Object
     */
    public static void toggleCheatMode(HiLoGame hlg) {
        hlg.setCheatMode(!hlg.isCheatMode());
        if (hlg.isCheatMode()) {
            System.out.println(ANSI_RED + "Cheat Mode ENABLED" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "Cheat Mode DISABLED" + ANSI_RESET);
        }
    }

    /**
     * prints game menu
     */
    public static void gameMenu() {
        System.out.println("Hi-Lo Game Menu");
        System.out.println("=================");
        System.out.println("1) New Game");
        System.out.println("2) Customized Game Options");
        System.out.println("3) Enable or Disable Cheat Mode");
        System.out.println("4) Quit Game");
    }

    /**
     * prints customized options menu
     */
    public static void customMenu() {
        System.out.println("Hi-Lo Game Customization Menu");
        System.out.println("=============================");
        System.out.println("1) Edit Number Range (Standard is 0-100)");
        System.out.println("2) Edit number of Guesses (Standard is 6)");
        System.out.println("3) Return to Game Menu");

    }

    /**
     * Prompts the user for an integer Value, does validation so that application will no crash on bad user Input
     * this Method is Overloaded and allows for the input of min and max range values so that user can't enter an integer
     * that is out of the number guessing range.  This Method also allows for the user to enter 'give up' and not break everything.
     * @param message message passed to the user to prompt them for input
     * @param min  minimum range for user input
     * @param max maximum range for user input
     * @return int users validated input or -2 for give up.
     */
    public static int promptUser(String message, int min, int max) {
        String user_input2;
        int user_input1 = -1;
        System.out.print(message);
        try {
            if (scanner.hasNextInt()) {
                user_input1 = scanner.nextInt();
                if (user_input1 < min || user_input1 > max) {
                    throw new IllegalArgumentException();
                }
                return user_input1;
            } else if (scanner.hasNext()) {
                scanner.nextLine();
                user_input2 = scanner.nextLine();
                if (user_input2.toLowerCase().equals("give up")) {
                    return -2;
                }
            }
        } catch (Exception e) {
            while (user_input1 < min || user_input1 > max) {
                System.out.println("Invalid Entry, Please Enter a Positive Integer between " + min + " and " + max);
                scanner.nextLine();
                if (scanner.hasNextInt()) {
                    user_input1 = scanner.nextInt();
                } else if (scanner.hasNext()) {
                    user_input2 = scanner.nextLine();
                    if (user_input2.toLowerCase().equals("give up")) {
                        return -2;
                    }
                }
            }
            return user_input1;
        }

        return -1; // this will never return, it just removes the error since all other returns are out of scope
    }

    /**
     * Generic promptUser method, displays a messages prompting the user to enter an integer.  performs validation.
     * @param message message displayed to the user
     * @return int users input
     */
    public static int promptUser(String message) {

        int user_input1 = -1;
        System.out.print(message);
        try {
            user_input1 = scanner.nextInt();
            if (user_input1 < 0) {
                throw new IllegalArgumentException();
            }
            return user_input1;
        } catch (Exception e) {
            while (user_input1 < 0) {
                System.out.println("Invalid Entry, Please Enter a Positive Integer");
                scanner.nextLine();
                if (scanner.hasNextInt()) {
                    user_input1 = scanner.nextInt();
                }
            }
            return user_input1;
        }


    }

    /**
     * resets the variables so that the game can be played again.  maintains custom min/max and number of guesses.
     * @param hlg Instantiated Game Object
     */
    public static void resetGame(HiLoGame hlg) {
        hlg.setCorrectGuess(false);
        hlg.resetMagicNumber();
        hlg.setTurns(hlg.getNumberOfGuesses());
        hlg.setLowerBound(hlg.getMinRange());
        hlg.setUpperBound(hlg.getMaxRange());
    }

}
