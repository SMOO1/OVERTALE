import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {
    private int speed = 7;
    private boolean up, down, left, right;
    private ArrayList<Projectile> projectiles;
    private int orbSpeed = 15;
    private int score; 

    public Player(int x, int y) {
        super(x, y, 30, 30, Color.BLUE);
        projectiles = new ArrayList<>();
        score=0;
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
        newX = Math.max(0, Math.min(newX, 780));
        newY = Math.max(0, Math.min(newY, 760));

        setX(newX);
        setY(newY);

        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile proj = projectiles.get(i);
            proj.update();
            if (proj.isOutOfBounds()) {
                projectiles.remove(i);
            }
        }

    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        for (Projectile p : projectiles) {
            p.draw(g);
        }
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

    public void launchProjectile(int mouseX, int mouseY) {

        //center of player
        int playerX = getX() + getWidth() / 2;
        int playerY = getY() + getHeight() / 2;
        //distance between mouse and player
        int dx = mouseX - playerX;
        int dy = mouseY - playerY;
        //calculates the length of hypotenuse
        double length = Math.sqrt(dx * dx + dy * dy);
        //makes it a unit vector, so that it doesnt just use the distance
        double unitX = dx / length;
        double unitY = dy / length;
        //multiplies the ratio of the lengths by the speed
        int velX = (int)(unitX*orbSpeed);
        int velY = (int)(unitY*orbSpeed);
        //adds the projectile to the array, to be deleted 
        projectiles.add(new Projectile(playerX, playerY, velX, velY));
    }
    
    public void clearProjectile() {
        projectiles.clear();
    }

    public void addScore(int score){
        this.score +=score; 
    }


    //getter

    public int getScore(){
        return this.score; 
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void resetScore() {
        this.score = 0;
    }

}
