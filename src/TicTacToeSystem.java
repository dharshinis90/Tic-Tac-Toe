import java.io.IOException;
import java.util.Scanner;

public class TicTacToeSystem {
    Board board = new Board(3);
    Player player1 = new Player('X');
    Player player2 = new Player('O');

    public void notifyGameOver() {
        System.out.println("GAME OVER!!");
    }

    public int[] getInput() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter co ordinates");
        int[] rowCol = new int[2];
        rowCol[0] = reader.nextInt();
        rowCol[1] = reader.nextInt();
        return rowCol;
    }
}
