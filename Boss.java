// Boss.java
import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Boss extends GameObject {
    private Random random;
    private int teleportCooldown;
    private Image boss;

    public Boss(int x, int y, int width, int height) {
        super(x, y, width, height, Color.RED);
        random = new Random();
        teleportCooldown = 100;
        //image to be the boss 
        ImageIcon image = new ImageIcon(getClass().getResource("winnie the pooh matrix pic.jpg"));
        boss = image.getImage(); 
        boss = boss.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    }

    public void update() {
        if (teleportCooldown <= 0) {
            setX(random.nextInt(700));
            setY(random.nextInt(700));
            teleportCooldown = 100;
        } else {
            teleportCooldown--;
        }
    }

    public void draw(Graphics g) { 
        g.drawImage(boss, getX(), getY(), getWidth(), getHeight(), null);
    }
}