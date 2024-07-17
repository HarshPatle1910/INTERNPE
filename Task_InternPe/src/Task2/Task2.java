/*
 * Rock Paper Scissor Game
 */

package Task2;

import java.util.Scanner;
import java.util.Random;

public class Task2 {

	public static void main(String[] args) {
		
		//Creating objects
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		//Variables
		String[] choices = {"rock","paper","scissors"}; 
		String pc_choice = "";
		String user_choice = "";
		int user_score = 0;
		int pc_score = 0;
		int pc_choice_number;
		int round = 1;
		
		System.out.println("\t\t\tWelcome to Rock Paper Scissor Game\n");
		System.out.println("You have 5 rounds");
		
		while(round<=5) {
			pc_choice_number = rd.nextInt(3);
			pc_choice = choices[pc_choice_number];
			
			System.out.println("\nReady to play. This is round " + round);
			
			System.out.println("To exit the game, type - 'exit'");
			System.out.print("Enter your move(rock, paper, scissor): ");
			user_choice = sc.nextLine().toLowerCase();
			
			if (user_choice.equals("exit")) {
				break;
			}
			
			if(!user_choice.equals(choices[0]) && !user_choice.equals(choices[1]) && !user_choice.equals(choices[2])) {
				System.out.println("Invalid move! Please try again.");
				continue;
			}
			
			if (user_choice.equals(pc_choice)) {
				System.out.println("You both choosed " + user_choice + ", it's tie.");
			}
			else if((user_choice.equals("rock") && pc_choice.equals("scissors")) ||
                    (user_choice.equals("paper") && pc_choice.equals("rock")) ||
                    (user_choice.equals("scissors") && pc_choice.equals("paper"))) {
				user_score++;
				System.out.println("You win this round.");
			}
			else {
				pc_score++;
				System.out.println("Computer win this round.");
			}
			round++;
		}
		
		if(user_choice.equals("exit")) {
			System.out.println("Thanks for playing");
		}
		else {
			if(user_score==pc_score) {
				System.out.println("This game is tied, you both scored: " + pc_score);
			}
			else if (user_score>pc_score) {
				System.out.println("Congratulations! you win this game");
				System.out.println("Your score: " + user_score + " and Computer score: " + pc_score);
			}
			else {
				System.out.println("Oops! you lose this game");
				System.out.println("Your score: " + user_score + " and Computer score: " + pc_score);
			}	
		}
		
		sc.close();
	}

}
