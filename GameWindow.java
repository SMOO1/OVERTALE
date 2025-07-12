import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private CardLayout cardLayout; //layout manager
    private JPanel cards; //container panel
    private StartScreen startScreen; //start screen
    private GameScreen gameScreen; //game screen
    private EndScreen endScreen; //end screen
    private WorldScreen mapScreen; 

    private boolean mapScreenInitialized = false;

    private String currentPlayerName; // store player name

    public GameWindow() {
        setTitle("Overtale"); //window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close behavior
        setSize(800, 800); //window size
        setResizable(false); //fixed size

        cardLayout = new CardLayout(); //create layout
        cards = new JPanel(cardLayout); //create container

        //create screens
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        endScreen = new EndScreen(this);
        mapScreen = new WorldScreen(this, currentPlayerName);

        //add screens to container
        cards.add(startScreen, "Start");
        cards.add(gameScreen, "Game");
        cards.add(endScreen, "End");
        cards.add(mapScreen, "Map");

        add(cards); //add container to window
    }

    public void showMapScreen(String playerName) {
        this.currentPlayerName = playerName;
        
        if (!mapScreenInitialized) {
            mapScreen = new WorldScreen(this, playerName);
            cards.add(mapScreen, "Map");
            mapScreenInitialized = true;
        }
        
        cardLayout.show(cards, "Map");
        mapScreen.requestFocusInWindow();
    }

    public void showStartScreen() {
        // Reset map screen when returning to start
        if (mapScreen != null) {
            cards.remove(mapScreen);
            mapScreen = null;
            mapScreenInitialized = false;
        }
        cardLayout.show(cards, "Start");
    }

    public void showGameScreen(String playerName) {
        this.currentPlayerName = playerName; 
        gameScreen.setPlayerName(currentPlayerName); // Pass stored name
        gameScreen.startGame();
        cardLayout.show(cards, "Game");
        gameScreen.requestFocusInWindow();
    }


    public void showEndScreen(int score, String playerName) {
        endScreen.setScore(score, playerName); //set score
        cardLayout.show(cards, "End"); //show end screen
    }
    /* 
    public void showStartScreen() {
        gameScreen.resetPlayer();
        gameScreen.player.resetScore();
        cardLayout.show(cards, "Start"); //show start screen
    }

    public void showMapScreen(String playerName) {
        currentPlayerName = playerName; //store the name
        mapScreen = new WorldScreen(this, playerName); //pass name to world screen
        setMapScreen(mapScreen, currentPlayerName);
    }
    */

    public void setMapScreen(WorldScreen newMapScreen, String playerName){
        this.currentPlayerName = playerName;
        cards.remove(this.mapScreen);
        this.mapScreen = newMapScreen;
        cards.add(newMapScreen, "Map");
        cardLayout.show(cards, "Map");
        newMapScreen.requestFocusInWindow();
    }
}