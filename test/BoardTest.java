import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BoardTest {
    @Test
    public void shouldPlaceCoinXAtGivenRowAndCol() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(1, 1, 'X');
        assertEquals(board.boardArray[1][1], 'X');
    }

    @Test
    public void shouldPlaceCoinOAtGivenRownAndCol() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(1, 1, 'O');
        assertEquals(board.boardArray[1][1], 'O');
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowExceptionWhenRowAndColOutOfBounds() throws InvalidInputException {
        Board board = new Board(1);
        board.placeCoin(1, 1, 'X');
    }

    @Test(expected = InvalidInputException.class)
    public void shouldPlaceCoinThrowExceptionWhenCoinAlreadyPresent() throws InvalidInputException {
        Board board = new Board(2);
        board.placeCoin(1, 1, 'X');
        board.placeCoin(1, 1, 'O');
    }

    @Test
    public void shouldIsGameOverReturnTrueIfColIsCrossed() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(0, 0, 'X');
        board.placeCoin(0, 1, 'O');
        board.placeCoin(1, 0, 'X');
        board.placeCoin(2, 2, 'O');
        board.placeCoin(2, 0, 'X');
        assertTrue(board.isGameOver(2, 0, 'X'));

    }

    @Test
    public void shouldIsGameOverReturnTrueIfRowIsCrossed() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(1, 0, 'X');
        board.placeCoin(2, 1, 'O');
        board.placeCoin(1, 1, 'X');
        board.placeCoin(2, 2, 'O');
        board.placeCoin(1, 2, 'X');
        assertTrue(board.isGameOver(1, 0, 'X'));
    }

    @Test
    public void shouldIsGameOverReturnTrueIfLeftDiagonalCrossed() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(0, 0, 'X');
        board.placeCoin(2, 1, 'O');
        board.placeCoin(1, 1, 'X');
        board.placeCoin(1, 2, 'O');
        board.placeCoin(2, 2, 'X');
        assertTrue(board.isGameOver(2, 2, 'X'));
    }

    @Test
    public void shouldIsGameOverReturnTrueIfRightDiagonalCrossed() throws InvalidInputException {
        Board board = new Board(3);
        board.placeCoin(1, 0, 'X');
        board.placeCoin(0, 2, 'O');
        board.placeCoin(2, 1, 'X');
        board.placeCoin(1, 1, 'O');
        board.placeCoin(1, 2, 'X');
        board.placeCoin(2, 0, 'O');
        assertTrue(board.isGameOver(2, 0, 'O'));
    }


    @Test
    public void shouldNotifyTicTacToeSystemWhenGameOver() throws InvalidInputException {
        TicTacToeSystem ticTacToeSystem = mock(TicTacToeSystem.class);
        //ticTacToeSystem.notifyGameOver();
        Board board = new Board(3);
        board.setTicTacToeSystem(ticTacToeSystem);
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        player1.placeCoin(0, 0, board);
        player1.placeCoin(0, 1, board);
        player1.placeCoin(0, 2, board);
        verify(ticTacToeSystem).notifyGameOver();
    }

    @Test
    public void shouldNotifyTicTacToeIfBoardIsFull() throws InvalidInputException {
        TicTacToeSystem ticTacToeSystem = mock(TicTacToeSystem.class);

        Board board = new Board(3);
        board.setTicTacToeSystem(ticTacToeSystem);

        Player player1 = new Player('X');
        Player player2 = new Player('O');

        player2.placeCoin(0, 1, board);
        player1.placeCoin(0, 0, board);
        player2.placeCoin(0, 2, board);
        player1.placeCoin(1, 1, board);
        player2.placeCoin(1, 0, board);
        player1.placeCoin(1, 2, board);
        player2.placeCoin(2, 1, board);
        player1.placeCoin(2, 0, board);
        player2.placeCoin(2, 2, board);

        verify(ticTacToeSystem).notifyGameOver();
    }

    @Test
    public void shouldNotNotifyTicTacToeIfBoardNotFull() throws InvalidInputException {
        TicTacToeSystem ticTacToeSystem = mock(TicTacToeSystem.class);

        Board board = new Board(3);
        board.setTicTacToeSystem(ticTacToeSystem);

        Player player1 = new Player('X');
        Player player2 = new Player('O');

        player1.placeCoin(0, 0, board);
        player2.placeCoin(0, 1, board);
        player1.placeCoin(1, 1, board);
        player2.placeCoin(0, 2, board);
        player1.placeCoin(1, 2, board);
        player2.placeCoin(1, 0, board);
        player1.placeCoin(2, 0, board);
        player2.placeCoin(2, 1, board);

        verify(ticTacToeSystem, never()).notifyGameOver();
    }

}
