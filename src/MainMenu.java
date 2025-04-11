import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("🎮 Tic Tac Toe - Main Menu");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("🎯 Tic Tac Toe", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(new Color(50, 50, 255));
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(createButton("🎮 Play", e -> {
            String name = JOptionPane.showInputDialog(this, "Enter your name:");
            if (name != null && !name.isEmpty()) {
                dispose();
                new Main(name);
            }
        }));

        buttonPanel.add(createButton("📊 Scores", e -> ScoreBoard.showScores(this)));

        buttonPanel.add(createButton("ℹ️ Info", e -> {
            JOptionPane.showMessageDialog(this,
                    "Tic Tac Toe\n\nMade with ❤️ in Java\n\nPlay vs AI and have fun!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }));

        buttonPanel.add(createButton("⚙️ Settings", e -> {
            JOptionPane.showMessageDialog(this,
                    "Settings feature coming soon!",
                    "Settings", JOptionPane.INFORMATION_MESSAGE);
        }));

        buttonPanel.add(createButton("💻 GitHub", e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/CodePexV1"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to open GitHub!");
            }
        }));

        add(buttonPanel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }



    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setBackground(new Color(240, 240, 255));
        button.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 255), 2));
        button.addActionListener(action);
        return button;
    }

    public static void saveScore(String name, String result) {
        ScoreBoard.addScore(name, result);
    }
}
