import java.awt.*;

public class Projectile extends Orb {

     public Projectile(int x, int y, int velX, int velY) {
        super(x, y, velX, velY);
        this.setColor(Color.GREEN);
    }

}