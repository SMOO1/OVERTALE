import java.awt.*;

public class Player extends GameObject {
    private int speed = 7;
    private boolean up, down, left, right;

    public Player(int x, int y) {
        super(x, y, 30, 30, Color.BLUE);
    }

    public void update() {
        int newX = getX();
        int newY = getY();
        
        if(up) 
            newY -= speed;
        if(down)
            newY += speed;
        if(left)
            newX -= speed;
        if(right)
            newX += speed;

        //keep player within screen
        newX = Math.max(0, Math.min(newX, 765));
        newY = Math.max(0, Math.min(newY, 540));

        setX(newX);
        setY(newY);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }


    //setters
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down){
        this.down = down;
    }
    public void setLeft(boolean left){
        this.left = left;
    }
    public void setRight(boolean right){
        this.right = right;
    }
}