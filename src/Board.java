public class Board {
    public char[][] cells;
    private int size;
    private TicTacToeSystem ticTacToeSystem;
    private int squaresWithCoins = 0;

    public Board(int size) {
        this.size = size;
        cells = new char[size][size];
    }

    public void setTicTacToeSystem(TicTacToeSystem ticTacToeSystem) {
        this.ticTacToeSystem = ticTacToeSystem;
    }

    public boolean placeCoin(int row, int col, char coinType) throws InvalidInputException {
        if (row >= size || col >= size) {
            throw new InvalidInputException();
        }
        if (cells[row][col] != '\0') {
            throw new InvalidInputException();
        }
        cells[row][col] = coinType;
        squaresWithCoins++;
        if (isGameOver(row, col, coinType)) {
            if (ticTacToeSystem != null) {
                ticTacToeSystem.notifyPlayerWon();
            }
        }
        if (isBoardFull()) {
            if (ticTacToeSystem != null) {
                ticTacToeSystem.notifyGameOver();
            }
        }
        return true;
    }

    public boolean isGameOver(int row, int col, char coinType) {
        return (isRowOrColumnCrossed(row, col, coinType)
                || isRightDiagonalCrossed(coinType)
                || isLeftDiagonalCrossed(coinType));
    }

    public boolean isBoardFull() {
        return (squaresWithCoins == size * size);
    }

    private boolean isLeftDiagonalCrossed(char coinType) {
        int leftDiagonalCount = 0;
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (cells[i][j] == coinType) {
                {
                    leftDiagonalCount++;
                }
            }
        }
        return (leftDiagonalCount == size);
    }

    private boolean isRightDiagonalCrossed(char coinType) {
        int rightDiagonalCount = 0;
        for (int i = 0, j = size - 1; i < size; i++, j--) {
            if (cells[i][j] == coinType) {
                rightDiagonalCount++;
            }
        }
        return (rightDiagonalCount == size);
    }

    private boolean isRowOrColumnCrossed(int row, int col, char coinType) {
        int rowCount = 0;
        int colCount = 0;
        for (int i = 0; i < size; i++) {
            if (cells[row][i] == coinType) {
                rowCount++;
            }
            if (cells[i][col] == coinType) {
                colCount++;
            }
        }
        return (rowCount == size || colCount == size);
    }
}
