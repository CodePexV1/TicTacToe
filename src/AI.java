public class AI {
    public int[] findBestMove(Board board) {

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.board[i][j] == ' ') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
