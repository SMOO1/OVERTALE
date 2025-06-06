import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Boss2 extends GameObject {
    private Random random;
    private int teleportCooldown;
    private int attackCooldown;
    private ArrayList<Orb> orbs;
    private Image boss;
    private int orbSpeed = 2; 

    public Boss2(int x, int y) {
        super(x, y, 50, 50, Color.RED);
        random = new Random();
        teleportCooldown = 100;
        attackCooldown = 50;
        orbs = new ArrayList<>();
        //image to be the boss 
        ImageIcon image = new ImageIcon(getClass().getResource("winnie the pooh matrix pic.jpg"));      // load the image
        boss = image.getImage(); 
        boss = boss.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);//scale the image to boss size
    }
    //update the state of the boss, attack or teleport
    public void update() {
        if (teleportCooldown <= 0) {
            setX(random.nextInt(700));
            setY(random.nextInt(500));
            teleportCooldown = 100;
        } else {
            teleportCooldown--;
        }

        if (attackCooldown <= 0) {
            launchOrbs();
            attackCooldown=20;
        } else {
            attackCooldown--;
        }

        for (int i = orbs.size()-1; i >= 0; i--) {
            Orb orb = orbs.get(i);
            orb.update();
            if (orb.isOutOfBounds()) {
                orbs.remove(i);
            }
        }
    }

    public void draw(Graphics g) { 
        g.drawImage(boss, getX(), getY(), getWidth(), getHeight(), null);
    }

    public void launchOrbs() {
        for (int i = 0; i < 10; i++) {
            double angle = 2*Math.PI*i/10; //cakculate velocities based off of position in circle
            int velX = (int)(5*Math.cos(angle));//5 is the "base speed" of orb
            int velY = (int)(5*Math.sin(angle));
            orbs.add(new Orb(getX() + getWidth()/2, getY() + getHeight()/2, velX*orbSpeed, velY*orbSpeed));//add orb to list of orbs
        }
    }
    //list of all current orbs
    public ArrayList<Orb> getOrbs() {
        return orbs;
    }
    //remove all orbs
    public void clearOrbs() {
        orbs.clear();
    }
}