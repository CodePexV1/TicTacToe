public class Board {
    public static final int SIZE = 3;
    public char[][] board = new char[SIZE][SIZE];

    public Board() {
        reset();
    }

    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean makeMove(int row, int col, char player) {
        if (board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public boolean hasWon(char player) {

        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
}
