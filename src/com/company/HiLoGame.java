package com.company;

/**
 * Constitutes the structure of a Hi-Lo Number Guessing Game
 * @author Brian Chalfant 2020
 * CSCI2913, Exercise 6a HILO
 */
public class HiLoGame {
    private int maxRange = 100;
    private int minRange = 0;
    private int magicNumber = ((int) ((Math.random() * ((maxRange - minRange) + 1)) + minRange));
    private int numberOfGuesses = 6;
    private int turns = this.getNumberOfGuesses();
    private boolean correctGuess = false;
    private boolean cheatMode = false;
    private int upperBound = Integer.MAX_VALUE;
    private int lowerBound = 0;

    /**
     * Accessor Method for turns field
     * @return int turns number of turns the player has left on their turn
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Mutator method for turns field
     * @param turns int new amount of turns for player
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }

    /**
     * Accessor method for minimum range field
     * @return int minRange lowest range for number guessing
     */
    public int getMinRange() {
        return minRange;
    }

    /**
     * Mutator method for minimum range field
     * @param minRange int new minimum range
     */
    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    /**
     * no-arg constructor
     */
    public HiLoGame() {

    }

    /**
     * Accessor Method for maximum range field
     * @return int maxRange maximum range for number guessing
     */
    public int getMaxRange() {
        return maxRange;
    }

    /**
     * Mutator Method for maximum range field
     * @param maxRange new value for maximum range
     */
    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    /**
     * Accessor Method for number of guesses field
     * the value of this field will effect the number of turns the player has each game
     * @return int Number of guesses for each game
     */
    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    /**
     * Mutator Method for number of guesses field
     * the value of this field will effect the number of turns the player has each game
     * @param numberOfGuesses int the new number of guesses each game
     */
    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    /**
     * Accessor Method for Correct Guess field
     * @return boolean has the player found the correct value of Magic Number
     */
    public boolean isCorrectGuess() {
        return correctGuess;
    }

    /**
     * Mutator Method for Correct Guess field
     * @param correctGuess boolean sets correctGuess to true or false
     */
    public void setCorrectGuess(boolean correctGuess) {
        this.correctGuess = correctGuess;
    }

    /**
     * Accessor Method for Cheat Mode
     * @return boolean is Cheat Mode on
     */
    public boolean isCheatMode() {
        return cheatMode;
    }

    /**
     * Accessor Method for Magic Number field
     * @return int the Magic Number
     */
    public int getMagicNumber() {
        return magicNumber;
    }

    /**
     * Mutator Method for the Magic Number
     * Randomly selects a Magic Number between the range of MinRange and MaxRange
     */
    public void resetMagicNumber() {
        this.magicNumber = ((int) ((Math.random() * ((maxRange - minRange) + 1)) + minRange));
    }

    /**
     * Mutator Method for Cheat Mode
     * @param cheatMode boolean Set Cheat Mode on or off.
     */
    public void setCheatMode(boolean cheatMode) {
        this.cheatMode = cheatMode;
    }

    /**
     * Accessor Method for UpperBound field
     * The initial value of UpperBound is always MaxRange, as the player guesses numbers Upperbound lowers to the
     * lowest number above the magic number.
     * @return int upperBound
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Mutator Method for UpperBound field
     * @param upperBound int set Upperbound to the lowest number discovered above the Magic Number
     */
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
    /**
     * Accessor Method for LowerBound field
     * The initial value of LowerBound is always MinRange, as the player guesses numbers lowerBound increases to the
     * highest number below the magic number.
     * @return int upperBound
     */
    public int getLowerBound() {
        return lowerBound;
    }
    /**
     * Mutator Method for LowerBound field
     * @param lowerBound int set LowerBound to the highest number discovered below the Magic Number
     */
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Decreases the players turn by 1
     */
    public void takeTurn(){
        this.turns--;
    }

}