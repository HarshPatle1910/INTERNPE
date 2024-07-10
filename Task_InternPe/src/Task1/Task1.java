package Task1;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Task1 {

    private static final int maxRange = 100;
    private static final int minRange = 1;
    private static final int maxAttempts = 10;
    static int attempts = 0;
    
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        Random rand = new Random();
        
        boolean play_again = true;
        int total_attempts = 0;
        int round = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Generated numbers are within " + minRange + " to " + maxRange);

        do {
            round++;
            attempts = 0;
            String level = getLevelChoice(scanObj);
            
            boolean win = false;

            if (level.equalsIgnoreCase("Easy")) {
                attempts = playEasyLevel(rand, scanObj, attempts, win, round);
            } else if (level.equalsIgnoreCase("Hard")) {
                attempts = playHardLevel(rand, scanObj, attempts, win, round);
            }

            total_attempts += attempts;

            System.out.println("\nDo you want to play again (Yes or No)?");
            play_again = scanObj.next().equalsIgnoreCase("Yes");
        } while (play_again);

        System.out.println("You have played " + round + " rounds with a total of " + total_attempts + " attempts.");
        System.out.println("Thanks for playing the game!");
    }

    //Method to get level choice
    private static String getLevelChoice(Scanner scanObj) {
        while (true) {
            System.out.print("Choose a level (Easy or Hard): ");
            String level = scanObj.nextLine();
            if (level.equalsIgnoreCase("Easy") || level.equalsIgnoreCase("Hard")) {
                return level;
            } 
            else {
                System.out.println("Invalid input. Please enter 'Easy' or 'Hard'.");
            }
        }
    }

    
    //Method to play Easy level
    private static int playEasyLevel(Random rand, Scanner scanObj, int attempts, boolean win, int round) {
        int generatedNumber = rand.nextInt(maxRange - minRange + 1) + minRange;
        System.out.println("You chose the easy level. You have unlimited chances to play.");

        while (!win) {
            attempts++;
            System.out.println("\n\nThis is your attempt " + attempts + " of round " + round + " : ");

            try {
                System.out.print("Enter a number to guess for the generated number: ");
                int guessedNumber = scanObj.nextInt();
                scanObj.nextLine(); // Consume newline character

                if (guessedNumber < generatedNumber) {
                    System.out.println("Guessed number " + guessedNumber + " is too low.");
                } else if (guessedNumber > generatedNumber) {
                    System.out.println("Guessed number " + guessedNumber+ " is too high.");
                } else {
                    System.out.println("Guessed number " + guessedNumber + " is correct.");
                    win = true;
                    System.out.println("Congratulations! You win the game.");
                    System.out.println("You took " + attempts + " attempts to guess the number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanObj.next(); // Consume invalid input
            }
        }
        return attempts;
    }

    //Method to play Hard level
    private static int playHardLevel(Random rand, Scanner scanObj, int attempts, boolean win, int round) {
        int generatedNumber = rand.nextInt(maxRange - minRange + 1) + minRange;
        System.out.println("You chose the hard level. You have " + maxAttempts + " attempts to play.");

        while (!win && attempts < maxAttempts) {
            attempts++;
            System.out.println("\n\nThis is your attempt " + attempts + " of round " + round + " : ");

            try {
                System.out.print("Enter a number to guess for the generated number: ");
                int guessedNumber = scanObj.nextInt();
                scanObj.nextLine(); // Consume newline character

                if (guessedNumber < generatedNumber) {
                    System.out.println("Guessed number " + guessedNumber + " is too low.");
                } else if (guessedNumber > generatedNumber) {
                    System.out.println("Guessed number " + guessedNumber + " is too high.");
                } else {
                    System.out.println("Guessed number " + guessedNumber + " is correct.");
                    win = true;
                    System.out.println("Congratulations! You win the game.");
                    System.out.println("You took " + attempts + " attempts to guess the number");
                    break; // Exit the loop after winning
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanObj.next(); // Consume invalid input
            }
        }

        if (!win) {
            System.out.println("You attempted all " + maxAttempts + " chances. You lost!");
        }
        return attempts;
    }
}