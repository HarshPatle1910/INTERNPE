/*
 * 		CONNECT FOUR GAME
 */

package Task4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task4 {

    private static char[][] board = new char[6][7];
    private static char currentPlayer = 'X';

    private static void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("  | 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        System.out.println("===============================");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
            	if(j==0)
            		System.out.print((i+1 + " |"));
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println("\n-------------------------------");
        }
    }

    private static boolean isValidMove(int col) {
        return col >= 0 && col < 7 && board[0][col] == ' ';
    }

    private static void makeMove(int col) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = currentPlayer;
                return;
            }
        }
    }

    private static boolean isWinner() {
        // Check horizontal
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer && board[i][j + 2] == currentPlayer && board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j] == currentPlayer && board[i + 2][j] == currentPlayer && board[i + 3][j] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonal (top-left to bottom-right)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i + 1][j + 1] == currentPlayer && board[i + 2][j + 2] == currentPlayer && board[i + 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonal (bottom-left to top-right)
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i - 1][j + 1] == currentPlayer && board[i - 2][j + 2] == currentPlayer && board[i - 3][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isDraw() {
        for (int i = 0; i < 7; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("    Welcome to Connect Four!\n");
        initializeBoard();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("\nPlayer " + currentPlayer + "'s turn.");
            System.out.print("Enter column (1-7): ");
            try {
            	int col = sc.nextInt() - 1;

                if (isValidMove(col)) {
                    makeMove(col);
                    if (isWinner()) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    }
                    if (isDraw()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        break;
                    }
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                } else {
                    System.out.println("Invalid move. Try again.");
                }
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 7.\n");
                sc.next(); // Consume the invalid input
			}
        }
        System.out.println("Thanks for playing!");
        sc.close();
    }
}