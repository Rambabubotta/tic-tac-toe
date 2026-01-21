import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);
    static String player1, player2;

    public static void main(String[] args) {
        System.out.println("===== TIC TAC TOE GAME =====");

        System.out.print("Enter Player 1 name (X): ");
        player1 = sc.nextLine();

        System.out.print("Enter Player 2 name (O): ");
        player2 = sc.nextLine();

        boolean playAgain;
        do {
            initializeBoard();
            playGame();
            System.out.print("Do you want to play again? (y/n): ");
            playAgain = sc.next().equalsIgnoreCase("y");
            sc.nextLine();
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    static void playGame() {
        char currentSymbol = 'X';
        String currentPlayer = player1;
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            System.out.println(currentPlayer + "'s turn (" + currentSymbol + ")");
            System.out.print("Enter row and column (0-2): ");
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (!isValidMove(r, c)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[r][c] = currentSymbol;

            if (checkWin(currentSymbol)) {
                printBoard();
                System.out.println("Winner is: " + currentPlayer);
                gameEnded = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("Game is a Draw!");
                gameEnded = true;
            } else {
                if (currentSymbol == 'X') {
                    currentSymbol = 'O';
                    currentPlayer = player2;
                } else {
                    currentSymbol = 'X';
                    currentPlayer = player1;
                }
            }
        }
    }

    static boolean isValidMove(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == ' ';
    }

    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) return true;
        }
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
               (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    static boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
        System.out.println();
    }
}