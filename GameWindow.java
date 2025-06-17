import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private CardLayout cardLayout; //layout manager
    private JPanel cards; //container panel
    private StartScreen startScreen; //start screen
    private GameScreen gameScreen; //game screen
    private EndScreen endScreen; //end screen

    public GameWindow() {
        setTitle("Overtale"); //window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close behavior
        setSize(800, 600); //window size
        setResizable(false); //fixed size

        cardLayout = new CardLayout(); //create layout
        cards = new JPanel(cardLayout); //create container

        //create screens
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        endScreen = new EndScreen(this);

        //add screens to container
        cards.add(startScreen, "Start");
        cards.add(gameScreen, "Game");
        cards.add(endScreen, "End");

        add(cards); //add container to window
    }

    public void showGameScreen(String playerName) {
        gameScreen.setPlayerName(playerName); //set player name
        gameScreen.startGame(); //start game
        cardLayout.show(cards, "Game"); //show game screen
        gameScreen.requestFocusInWindow(); //set focus
    }

    public void showEndScreen(int score, String playerName) {
        endScreen.setScore(score, playerName); //set score
        cardLayout.show(cards, "End"); //show end screen
    }

    public void showStartScreen() {
        cardLayout.show(cards, "Start"); //show start screen
    }
}