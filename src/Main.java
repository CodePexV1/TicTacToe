import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private final Board board = new Board();
    private final AI ai = new AI();
    private final String playerName;

    public Main(String name) {
        this.playerName = name;

        setTitle("🎮 Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton button = new JButton("");
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                int row = i, col = j;

                button.addActionListener(e -> {
                    if (board.makeMove(row, col, 'X')) {
                        button.setText("X");
                        button.setEnabled(false);

                        if (checkEnd('X')) return;

                        int[] aiMove = ai.findBestMove(board);
                        board.makeMove(aiMove[0], aiMove[1], 'O');
                        buttons[aiMove[0]][aiMove[1]].setText("O");
                        buttons[aiMove[0]][aiMove[1]].setEnabled(false);

                        checkEnd('O');
                    }
                });

                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private boolean checkEnd(char player) {
        if (board.hasWon(player)) {
            String result = player == 'X' ? "Win" : "Lose";
            JOptionPane.showMessageDialog(this, playerName + " " + result + "s!");
            MainMenu.saveScore(playerName, result);
            resetGame();
            return true;
        } else if (board.isFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            MainMenu.saveScore(playerName, "Draw");
            resetGame();
            return true;
        }
        return false;
    }

    private void resetGame() {
        board.reset();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}
