import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ScoreBoard {
    private static final String SCORE_FILE = "scores.txt";
    private static final ArrayList<String> scores = new ArrayList<>();

    static {
        loadScores();
    }


    public static void addScore(String name, String result) {
        String scoreEntry = name + ": " + result;
        scores.add(scoreEntry);
        saveScores();
    }


    private static void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {

            System.out.println("No previous scores found.");
        }
    }


    private static void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            for (String score : scores) {
                writer.write(score);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void showScores(JFrame parent) {
        if (scores.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No games played yet!");
        } else {
            StringBuilder sb = new StringBuilder("📊 Score History:\n\n");
            for (String s : scores) sb.append(s).append("\n");
            JOptionPane.showMessageDialog(parent, sb.toString());
        }
    }
}
