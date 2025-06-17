import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    private GameWindow window; //reference to main window
    private JTextField nameField; //name input field

    public StartScreen(GameWindow window) {
        this.window = window;

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); //transparent panel

        GridBagConstraints gbc = new GridBagConstraints();//make everything stack ontop of eachother
        gbc.gridwidth = GridBagConstraints.REMAINDER; //each component uses a full row
        gbc.insets = new Insets(10, 10, 10, 10); //padding

        //game title
        JLabel titleLabel = new JLabel("OVERTALE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        centerPanel.add(titleLabel, gbc);

        //name input
        nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel namePrompt = new JLabel("Enter your name:");
        namePrompt.setForeground(Color.WHITE);
        centerPanel.add(namePrompt, gbc);
        centerPanel.add(nameField, gbc);

        //start button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    window.showGameScreen(playerName); //start game method runs
                }
            }
        });
        centerPanel.add(startButton, gbc);

        add(centerPanel, BorderLayout.CENTER); //add center panel
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); //black background
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}