import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameScreen extends JPanel implements ActionListener {
    private GameWindow window; //reference to main window
    private Timer timer; //game loop timer
    private Player player; //player object
    private Boss boss; //boss object
    private int score; //current score
    private String playerName; //player name
    private boolean gameRunning = false; //game state flag

    public GameScreen(GameWindow window) {
        this.window = window;
        setBackground(Color.BLACK); //black background
    
        //setup keyboard input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), true); //key pressed
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), false); // Key released
            }
        });
    
        timer = new Timer(16, this); //60fps game loop
    }

    public void startGame() {
        player = new Player(400, 300); //reset player
        boss = new Boss(200, 200); //reset boss
        score = 0; //reset score
        gameRunning = true; //set game state
        timer.start(); //start game loop
    }

    private void handleKeyPress(int keyCode, boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                player.setUp(pressed); //move up
                break;
            case KeyEvent.VK_S:
                player.setDown(pressed); //move down
                break;
            case KeyEvent.VK_A:
                player.setLeft(pressed); //move left
                break;
            case KeyEvent.VK_D:
                player.setRight(pressed); //move right
                break;
        }
    }


    //game loop 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning){
            return; //skip if game is over
        }
        //update game objects
        player.update();
        boss.update();

        //check orb collisions
        ArrayList<Orb> orbs = boss.getOrbs();
        for (int i = orbs.size()-1;i>=0; i--) {
            Orb orb = orbs.get(i);
            if (player.collidesWith(orb)) {
                gameRunning = false; //end tha game
                timer.stop(); //stop game loop
                window.showEndScreen(score, playerName); //show end screen
                return;
            }
        }

        score++; //increase score
        repaint(); //redraw screen
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw game objects
        player.draw(g);
        boss.draw(g);
        //draw orbs
        for (Orb orb : boss.getOrbs()) {
            orb.draw(g);
        }

        //draw score info
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Player: " + playerName, 20, 60);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName; //set player name
    }
}