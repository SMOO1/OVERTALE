import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogBox extends JComponent implements ActionListener {
    private String fullString;
    private String updatedString = "";
    public Timer texttimer;
    private JTextArea textArea;
    int i = 0;

    public DialogBox(String text) {
        this.fullString = text;

        setLayout(new BorderLayout());
        setOpaque(false);
        setVisible(false);
        setBounds(50, 700, 700, 80); // Position at bottom

        // text area with wrapping
        textArea = new JTextArea();
        textArea.setLineWrap(true); //line wrapping
        textArea.setWrapStyleWord(true); //wrap at word boundaries
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(new Color(0, 0, 0)); //black ting
        textArea.setFont(new Font("Arial", Font.BOLD, 18));
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        textArea.setOpaque(true);
        
        add(textArea, BorderLayout.CENTER);
        texttimer = new Timer(40, this);
    }

    public void printBox() {
        setVisible(true);
        texttimer.start();
    }

    public void hideBox() {
        setVisible(false);
    }
    
    public void setText(String text) {
        this.fullString = text;
        updatedString = "";
        i = 0;
        textArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (i < fullString.length()) {
            updatedString += fullString.charAt(i);
            textArea.setText(updatedString);
            i++;
        } else {
            texttimer.stop();
        }
    }
}