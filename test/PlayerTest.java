import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldPlayerPlaceCoinAssigned() throws InvalidInputException {
        Board board = new Board(3);
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        player1.placeCoin(0, 0, board);
        player2.placeCoin(1, 1, board);
        assertEquals('X', board.cells[0][0]);
        assertEquals('O', board.cells[1][1]);
    }
}