public class Player {
    private char coin;

    public Player(char coin) {
        this.coin = coin;
    }

    public boolean placeCoin(int row, int col, Board board) throws InvalidInputException {
        return board.placeCoin(row, col, coin);
    }
}
