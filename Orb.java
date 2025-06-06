import java.awt.*;

public class Orb extends GameObject {
    private int velX, velY;

    public Orb(int x, int y, int velX, int velY) {
        super(x, y, 40, 40, Color.ORANGE);
        this.velX = velX;
        this.velY = velY;
    }
    //update the position of the orb
    public void update() {
        setX(getX() + velX);
        setY(getY() + velY);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
    //method to check when orb is out of bounds
    public boolean isOutOfBounds() {
        return getX() < -20 || getX() > 800 || getY() < -20 || getY() > 600;
    }
}