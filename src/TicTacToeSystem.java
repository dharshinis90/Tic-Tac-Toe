import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeSystem {
    static Board board = new Board(3);
    static boolean isGameOver = false;
    static String currentPlayer;
    static String winner = "No one";

    public static void main(String[] args) throws IOException {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        boolean isPlayer2TurnOver = true;
        boolean isPlayer1TurnOver = false;
        board.setTicTacToeSystem(new TicTacToeSystem());
        boolean isFirstMove = true;
        while (!isGameOver) {
            try {
                if (isPlayer2TurnOver || isFirstMove) {
                    System.out.println("Player1's turn");
                    int[] input1 = getInput();
                    currentPlayer = "Player1";
                    isPlayer1TurnOver = player1.placeCoin(input1[0], input1[1], board);
                    isFirstMove = false;
                    printBoard();
                }
                if (isPlayer1TurnOver && !isGameOver) {
                    System.out.println("Player2's turn");
                    int[] input2 = getInput();
                    currentPlayer = "Player2";
                    player2.placeCoin(input2[0], input2[1], board);
                    printBoard();
                }
            } catch (InvalidInputException e) {
                System.out.println("Please try other co ordinates.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter inputs in valid format.");
            } catch (IOException e) {
                System.out.println("Please try again.");
            }
        }
        System.out.println("GAME OVER!!");
        System.out.println(winner + " has won the game!");
    }

    public void notifyGameOver() {
        isGameOver = true;
    }

    public void notifyPlayerWon() {
        isGameOver = true;
        winner = currentPlayer;
    }

    private static int[] getInput() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter co ordinates");
        int[] rowCol = new int[2];
        rowCol[0] = reader.nextInt();
        rowCol[1] = reader.nextInt();
        return rowCol;
    }

    private static void printBoard() {
        for (int i = 0; i < board.cells.length + 1; i++) {
            for (int j = 0; j < board.cells.length + 1; j++) {
                if (j > 0 && i == 0)
                    System.out.print(j - 1);
                else if (j == 0 && i == 0)
                    System.out.print(" ");
                else if (i > 0 && j == 0)
                    System.out.print(i - 1);
                else if (j > 0 && i > 0) {
                    if (board.cells[i - 1][j - 1] == '\0')
                        System.out.print(" ");
                    else
                        System.out.print(board.cells[i - 1][j - 1]);
                }
            }
            System.out.println();
        }
    }
}
