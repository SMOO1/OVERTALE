// OrbBoss.java

import java.util.ArrayList;

public class OrbBoss extends Boss {
    private int attackCooldown;
    private ArrayList<Orb> orbs;
    private int orbSpeed = 2;

    public OrbBoss(int x, int y) {
        super(x, y, 40, 40);
        attackCooldown = 50;
        orbs = new ArrayList<>();
    }

    @Override
    public void update() {
        super.update(); //call Boss class update method for teleportation
        
        if (attackCooldown <= 0) {
            launchOrbs();
            attackCooldown = 20;
        } else {
            attackCooldown--;
        }

        // Update all orbs
        for (int i = orbs.size()-1; i >= 0; i--) {
            Orb orb = orbs.get(i);
            orb.update();
            if (orb.isOutOfBounds()) {
                orbs.remove(i);
            }
        }
    }

    public void launchOrbs() {
        for (int i = 0; i < 10; i++) {
            double angle = 2*Math.PI*i/10;
            int velX = (int)(5*Math.cos(angle));
            int velY = (int)(5*Math.sin(angle));
            orbs.add(new Orb(getX() + getWidth()/2, getY() + getHeight()/2, velX*orbSpeed, velY*orbSpeed));
        }
    }

    public ArrayList<Orb> getOrbs() {
        return orbs;
    }

    public void clearOrbs() {
        orbs.clear();
    }
}