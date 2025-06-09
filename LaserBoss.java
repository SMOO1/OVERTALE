
import java.util.ArrayList;


public class LaserBoss extends Boss {
    private static int laserLength = 1000; 
    private int attackCooldown;
    private ArrayList<Laser> lasers;
    private Player player; 
    
    public LaserBoss(int x, int y, Player player) {
        super(x, y, 40, 40);
        attackCooldown = 50;
        this.player = player; 
        this.lasers = new ArrayList<>();
    }

    @Override
    public void update() {
        super.update(); //call Boss class update method for teleportation
        
        if (attackCooldown <= 0) {
            fireLasers();
            attackCooldown = 50; 
        } else {
            attackCooldown--;
        }

        //update all lasers
        for (int i = lasers.size()-1; i >= 0; i--) {
            Laser laser = lasers.get(i);
            laser.update();
            if (laser.isExpired()) {
                lasers.remove(i);
            }
        }
    }

    public void fireLasers() {
        int bossCenterX = getX() + getWidth()/2;
        int bossCenterY = getY() + getHeight()/2;
        int playerCenterX = player.getX() + player.getWidth()/2;
        int playerCenterY = player.getY() + player.getHeight()/2;

        //calculate angle to player
        double angle = Math.atan2(playerCenterY - bossCenterY, playerCenterX - bossCenterX);
        int degreeOffset = 40;
        //main ting laser
        int mainEndX = bossCenterX + (int)(laserLength * Math.cos(angle));
        int mainEndY = bossCenterY + (int)(laserLength * Math.sin(angle));

        //side lasers with degree offset
        int leftEndX = bossCenterX + (int)(laserLength * Math.cos(angle - Math.toRadians(degreeOffset)));
        int leftEndY = bossCenterY + (int)(laserLength * Math.sin(angle - Math.toRadians(degreeOffset)));
        
        int rightEndX = bossCenterX + (int)(laserLength * Math.cos(angle + Math.toRadians(degreeOffset)));
        int rightEndY = bossCenterY + (int)(laserLength * Math.sin(angle + Math.toRadians(degreeOffset)));
        
        //make lasers with 10 pixel thicknelss
        lasers.add(new Laser(bossCenterX, bossCenterY, mainEndX, mainEndY, 10));
        lasers.add(new Laser(bossCenterX, bossCenterY, leftEndX, leftEndY, 10));
        lasers.add(new Laser(bossCenterX, bossCenterY, rightEndX, rightEndY, 10));
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public void clearLasers() {
        lasers.clear();
    }
}