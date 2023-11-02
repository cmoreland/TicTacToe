import java.util.Scanner;

public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if ((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void display() {
        System.out.println("-------------");
        for (int row = 0; row < ROW; row++) {
            System.out.print("| ");
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            currentPlayer = "X";

            display();

            while (true) {
                int rowMove = SafeInput.getRangedInt(in, "Enter the row (1 - 3)", 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(in, "Enter the column (1 - 3)", 1, 3) - 1;

                if (isValidMove(rowMove, colMove)) {
                    board[rowMove][colMove] = currentPlayer;
                    display();

                    if (isWin(currentPlayer)) {
                        System.out.println(currentPlayer + " wins!");
                        break;
                    } else if (isTie()) {
                        System.out.println("It's a tie!");
                        break;
                    }

                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                } else {
                    System.out.println("Invalid move. The cell is already occupied.");
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Play again? (yes/no)");
        } while (playAgain);
    }
}
