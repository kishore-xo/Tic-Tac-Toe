import java.io.IOException;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private char winner;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        winner = '-';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Current board:");
        System.out.println("\t  1 2 3");

        // Loop through the rows of the board
        for (int i = 0; i < 3; i++) {
            System.out.print("\t" + (i + 1) + " ");

            // Loop through the columns of the board
            for (int j = 0; j < 3; j++) {
                // Check if the cell is empty, and print accordingly
                if (board[i][j] == 'X') {
                    System.out.print("\u001B[33m" + board[i][j] + "\u001B[0m "); // Red color for 'X'
                } else if (board[i][j] == 'O') {
                    System.out.print("\u001B[36m" + board[i][j] + "\u001B[0m "); // Blue color for 'O'
                } else {
                    System.out.print(board[i][j] + " "); // No color for empty cells
                }
            }
            System.out.println();
        }
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error clearing screen: " + ex.getMessage());
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                winner = currentPlayer;
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                winner = currentPlayer;
                return true;
            }
         }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            winner = currentPlayer;
            return true;
        }

        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            winner = currentPlayer;
            return true;
        }

        return false;
    }

    public boolean playMove(int row, int col) {

        row--;
        col--;

        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getWinner() {
        return winner;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        boolean gameWon = false;
        boolean isDraw = false;

        game.printBoard();

        while (!gameWon && !isDraw) {
            game.clearScreen();
           
            game.printBoard();
            System.out.println("\nPlayer " + game.currentPlayer + "'s turn.");

            int row, col;

            do {
                System.out.print("Enter row (1-3) and column (1-3): ");
                row = sc.nextInt();
                col = sc.nextInt();
            } while (!game.playMove(row, col));

            gameWon = game.checkWin();
            if (!gameWon) {
                isDraw = game.isBoardFull();
                if (!isDraw) {
                    game.changePlayer();
                }
            }
        }

        game.clearScreen();
        game.printBoard();

        if (gameWon) {
            System.out.println("\nPlayer " + game.getWinner() + " wins!");
        } else if (isDraw) {
            System.out.println("It's a draw!");
        }

        sc.close();
    }
}
