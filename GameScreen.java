import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel implements ActionListener {
    private GameWindow window; //reference to main window
    private Timer timer; //game loop timer
    public Player player; //player object
    private OrbBoss orbBoss; //boss object
    private LaserBoss laserBoss; 
    private LaserBoss laserBoss2; 
    private HorridHarrell harrell; 
    private String playerName; //player name
    private boolean gameRunning = false; //game state flag

    private DialogBox bossDialogue;
    private Timer dialogueTimer;
    private String bossDialogueText = "";
    private boolean dialogueActive = false;

    private Portal exitPortal; //exit portal variables
    private boolean portalSpawned = false;
    private Random random = new Random();


    public GameScreen(GameWindow window) {
        this.window = window;
        setBackground(Color.BLACK); //black background

        player = new Player(400, 300);
        //diologue box
        bossDialogue = new DialogBox("");
        bossDialogue.setBounds(50, 700, 700, 80); // Position at bottom
        bossDialogue.setVisible(false);
        add(bossDialogue);
    
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameRunning && !dialogueActive) {
                    player.launchProjectile(e.getX(), e.getY());
                }
            }
        });
    
        timer = new Timer(16, this); //60fps game loop
    }

    public void startGame() {
        resetPlayer();
        
        gameRunning = true; //set game state

        int bossNum = (int) (Math.random()*3);

        
        orbBoss = null; 
        harrell = null; 
        laserBoss = null;
        laserBoss2=null;

        switch(bossNum){
            case 0:
                orbBoss = new OrbBoss(200, 200); 
                bossDialogueText = "IT IS ME........       KYLE THE COWARDLYYYYYYYY.";
                break;
            case 1:
                laserBoss = new LaserBoss(200, 200, player);
                laserBoss2 = new LaserBoss(200, 400, player);
                bossDialogueText = "IT IS US. WE ARE BOTH -----SASHA THE SINISTER-----. AND YES WE ARE TWINS. DID WE MENTION THAT WE ARE TWINS??";
                break;
            case 2: 
                harrell = new HorridHarrell(100,200);
                bossDialogueText = "yo.....    its me... HORRID HARRELL!!!!! ALSO DID I MENTION THAT MY NAME SOUNDS LIKE HERRO???!!!!";
                break;
        }
        startDialogue();
        timer.start(); //start game loop
        portalSpawned = false;
        exitPortal = null;
    }

    public void resetPlayer() {
        //only reset position/state, not score
        player.setX(400);
        player.setY(300);
        player.clearProjectile();
        player.setUp(false);
        player.setDown(false);
        player.setLeft(false);
        player.setRight(false);
    }


    private void startDialogue() {
        dialogueActive = true;
        bossDialogue.setText(bossDialogueText);
        bossDialogue.printBox();
        
        //set timer to deactivate dialogue
        dialogueTimer = new Timer(50, e -> {
            //access the public texttimer
            if (!bossDialogue.texttimer.isRunning()) {
                dialogueActive = false;
                dialogueTimer.stop();
                
                //hide dialogue after 3 seconds
                Timer hideTimer = new Timer(3000, ev -> {
                    bossDialogue.hideBox();
                });
                //hideTimer.setRepeats(false);
                hideTimer.start();
            }
        });
        dialogueTimer.start();
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
            orbBoss = null; 
            harrell = null; 
            laserBoss = null;
            laserBoss2=null;

            return; //skip if game is over
        }
        //update game objects
        player.update();
        if (!dialogueActive) {
            handleProjectileCollisions();
            checkBossStates();
            
            //only update alive bosses
            if (orbBoss != null && orbBoss.getHp() > 0) {
                orbBoss.update();
                checkOrbCollisions();
            }
            else if (laserBoss != null || laserBoss2 != null) {
                if (laserBoss != null && laserBoss.getHp() > 0) {
                    laserBoss.update();
                    checkLaserCollisions(laserBoss);
                }
                if (laserBoss2 != null && laserBoss2.getHp() > 0) {
                    laserBoss2.update();
                    checkLaserCollisions(laserBoss2);
                }
            }
            else if (harrell != null && harrell.getHp() > 0) {
                harrell.update();
                checkHarrellCollision();
            }
            
            //check portal collision
            if (portalSpawned && player.collidesWith(exitPortal)) {
                window.showMapScreen(playerName);
                return;
            }
            
        }
        repaint();//redraw screen
    }

    private void spawnExitPortal() {
        int x = random.nextInt(700); // random x and y position
        int y = random.nextInt(500); 
        exitPortal = new Portal(x, y, 50, 50, Color.WHITE);
    }
    
    //collision checking for each boss type
    private void checkOrbCollisions() {
        ArrayList<Orb> orbs = orbBoss.getOrbs();
        for (int i = orbs.size()-1; i >= 0; i--) {
            Orb orb = orbs.get(i);
            if (player.collidesWith(orb)) {
                gameOver();
                return;
            }
        }
    }
    
    private void checkLaserCollisions(LaserBoss boss) {
        ArrayList<Laser> lasers = boss.getLasers();
        for (int i = lasers.size()-1; i >= 0; i--) {
            Laser laser = lasers.get(i);
            if (laser.isActive() && laser.collidesWith(player)) {
                gameOver();
                return;
            }
        }
    }
    
    private void checkHarrellCollision() {
        if (harrell.collidesWith(player)) {
            gameOver();
        }
    }
    
    private void gameOver() {
        gameRunning = false;
        timer.stop();
        window.showEndScreen(player.getScore(), playerName);
    }

    private void handleProjectileCollisions() {
        ArrayList<Projectile> projectiles = player.getProjectiles();
        
        if (orbBoss != null) {
            for (int i = projectiles.size() - 1; i >= 0; i--) {
                Projectile p = projectiles.get(i);
                if (orbBoss.collidesWith(p)) {
                    orbBoss.takeDamage(10);
                    player.addScore(10); // 10 points per hit
                    projectiles.remove(i);
                }
            }
        } 
        else if (harrell != null) {
            for (int i = projectiles.size() - 1; i >= 0; i--) {
                Projectile p = projectiles.get(i);
                if (harrell.collidesWith(p)) {
                    harrell.takeDamage(10);
                    player.addScore(10);
                    projectiles.remove(i);
                }
            }
        }
        else {
            // Handle laser bosses separately
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            boolean hit = false;
            
            if (laserBoss != null && laserBoss.collidesWith(p)) {
                laserBoss.takeDamage(10);
                player.addScore(10);
                hit = true;
            }
            
            if (!hit && laserBoss2 != null && laserBoss2.collidesWith(p)) {
                laserBoss2.takeDamage(10);
                player.addScore(10);
                hit = true;
            }
            
            if (hit) {
                projectiles.remove(i);
            }
        }
        } 
    }

    private void checkBossStates() {
        // Check if all bosses are defeated
        boolean allDefeated = false;
        
        if (orbBoss != null && orbBoss.getHp() <= 0) {
            orbBoss.getOrbs().clear();
            orbBoss = null;
            allDefeated = true;
        } 
        else if ((laserBoss != null && laserBoss.getHp() <= 0) || (laserBoss2 != null && laserBoss2.getHp() <= 0)) {
            if (laserBoss != null && laserBoss.getHp() <= 0){
                laserBoss.getLasers().clear();
                laserBoss = null; 
            } 
            if (laserBoss2 != null && laserBoss2.getHp() <= 0){
                laserBoss2.getLasers().clear();
                laserBoss2 = null;
            } 
            allDefeated = (laserBoss == null && laserBoss2 == null);


        } 
        else if (harrell != null && harrell.getHp() <= 0) {
            harrell = null;
            allDefeated = true;
        }
        
        // Spawn exit portal if all bosses defeated
        if (allDefeated && !portalSpawned) {
            spawnExitPortal();
            portalSpawned = true;
        }
    }

    @Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw game objects
    player.draw(g);
    drawBossHp(g);  // Draw HP bars first

    // Draw orb boss if exists
    if (orbBoss != null) {
        orbBoss.draw(g);
        for (Orb orb : orbBoss.getOrbs()) {
            orb.draw(g);
        }
    } 
    // Draw laser bosses if at least one exists
    else if (laserBoss != null || laserBoss2 != null) {
        if (laserBoss != null) {
            laserBoss.draw(g);
            for (Laser laser : laserBoss.getLasers()) {
                laser.draw(g);
            }
        }
        if (laserBoss2 != null) {
            laserBoss2.draw(g);
            for (Laser laser : laserBoss2.getLasers()) {
                laser.draw(g);
            }
        }
    } 
    // Draw Harrell if exists
    else if (harrell != null) {
        harrell.draw(g);
    }

    // Draw portal if spawned
    if (portalSpawned) {
        int px = exitPortal.getX();
        int py = exitPortal.getY(); 
        int pwidth = exitPortal.getWidth();
        int pheight = exitPortal.getHeight();

        g.setColor(exitPortal.getColor());
        g.fillRect(px, py, pwidth, pheight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("ESCAPE", exitPortal.getX(), exitPortal.getY() - 5);
    }
    
    // Draw score info
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    g.drawString("Score: " + player.getScore(), 20, 30);
    g.drawString("Player: " + playerName, 20, 60);
}

private void drawBossHp(Graphics g) {
    int barWidth = 200;
    int barHeight = 20;
    int startX = 300;
    int startY = 40;
    
    if (orbBoss != null) {
        drawHpBar(g, "Orb Boss HP", orbBoss.getHp(), 100, startX, startY, barWidth, barHeight);
    } 
    else if (laserBoss != null || laserBoss2 != null) {
        //draw HP bars for each laser boss thats still alive
        if (laserBoss != null) {
            drawHpBar(g, "Laser Boss 1 HP", laserBoss.getHp(), 60, startX, startY, barWidth, barHeight);
            startY += 50; // Move down for next bar
        }
        if (laserBoss2 != null) {
            drawHpBar(g, "Laser Boss 2 HP", laserBoss2.getHp(), 60, startX, startY, barWidth, barHeight);
        }
    } 
    else if (harrell != null) {
        drawHpBar(g, "Horrid Harrell HP", harrell.getHp(), 150, startX, startY, barWidth, barHeight);
    }
}

    private void drawHpBar(Graphics g, String label, int currentHp, int maxHp, int x, int y, int width, int height) {
        // Draw background
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        
        // Draw HP
        int hpWidth = (int) ((double) currentHp / maxHp * width);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, hpWidth, height);
        
        // Draw border and text
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.drawString(label + ": " + currentHp + "/" + maxHp, x, y - 5);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName; //set player name
    }
}