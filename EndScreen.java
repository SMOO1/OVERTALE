import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

public class EndScreen extends JPanel {
    private GameWindow window; //reference to main window
    private JLabel scoreLabel; //displays current score
    private JLabel nameLabel; //displays player name
    private JLabel previousScoresLabel; //displays past scores
    private String scores_file = "scores.txt"; //score file

    public EndScreen(GameWindow window) {
        this.window = window;
        setLayout(new BorderLayout()); //main layout, divide container into 5 sections
        setBackground(Color.BLACK); //black background

        //center panel for all components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); //boxlayout so that everything is aligned in a single row
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));//set border on top

        //game over label
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setForeground(Color.WHITE);
        centerPanel.add(gameOverLabel);

        //player name label
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setForeground(Color.WHITE);
        centerPanel.add(nameLabel);

        //score label
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setForeground(Color.WHITE);
        centerPanel.add(scoreLabel);

        //previous scores label
        previousScoresLabel = new JLabel();
        previousScoresLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        previousScoresLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        previousScoresLabel.setForeground(Color.WHITE);
        centerPanel.add(previousScoresLabel);

        //restart button
        JButton restartButton = new JButton("Play Again");
        restartButton.setFont(new Font("Arial", Font.BOLD, 24));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> window.showStartScreen());
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(restartButton);

        add(centerPanel, BorderLayout.CENTER); //add center panel
    }

    public void setScore(int score, String playerName) {
        scoreLabel.setText("Your score: " + score); //set score text
        nameLabel.setText("Player: " + playerName); //set name text
        saveScore(playerName, score); //save score to file
        previousScoresLabel.setText(getPreviousScores()); //show previous scores
    }

    private String getPreviousScores() {
        ArrayList<String> scores = readScores(); //get arraylist of all previous scores
        if (scores.isEmpty()) {
            return "No previous scores";
        }
    
        String result = "Previous Scores:\n";
        int start = Math.max(0, scores.size() - 3);//last 3 elements of the arraylist (newest scores) 
        for (int i = start; i < scores.size(); i++) {
            result += scores.get(i);
            if (i < scores.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
    //saving score to score.txt with error handling
    private void saveScore(String playerName, int score) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("scores.txt", true)); //append true to put new scores at end of file
            out.println(playerName+" - "+score);
            out.close();
        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    //read scores from file
    private ArrayList<String> readScores() {
        ArrayList<String> scores = new ArrayList<>();
        File file = new File(scores_file);
        
        if (!file.exists()) {
            return scores; //return empty list if no file
        }

        //read all scores from file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading scores: " + e.getMessage());
        }
        return scores;
    }
}