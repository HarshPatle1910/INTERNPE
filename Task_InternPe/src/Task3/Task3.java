package Task3;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Task3 {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("\t\t\t " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("\t\t\t---+---+---");
        System.out.println("\t\t\t " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("\t\t\t---+---+---");
        System.out.println("\t\t\t " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static boolean isWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }
    
    private static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') { // Fix: double quote instead of single quote
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    	System.out.println("\t\tWelcome to Tic Tac Toe Game!\n");
    	initializeBoard();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("\tEnter row (1-3): ");
            try {
                int row = sc.nextInt() - 1;
                System.out.print("\tEnter column (1-3): ");
                int col = sc.nextInt() - 1;

                if (isValidMove(row, col)) {
                    makeMove(row, col);
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
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                sc.next(); // Consume the invalid input
            }
        }
        System.out.println("Thanks to play!");
        sc.close();
    }
}
