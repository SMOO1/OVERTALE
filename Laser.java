import java.awt.*;

public class Laser extends GameObject {
    private boolean isWarning;
    private boolean isLaser; 
    private int timer; 
    private int warningDuration = 40; 
    private int laserDuration = 50; 

    private Color warningColor = new Color(255,255,0,100);
    private Color laserColor = Color.RED; 

    private int endX, endY; 


    public Laser(int x, int y, int endX, int endY, int thickness) {
        super(x, y, thickness, thickness, null);
        this.isWarning = true; 
        this.isLaser = false; 
        this.timer = 0; 
        this.endX = endX;
        this.endY = endY;
    
    }
    //update the position of the orb
    public void update() {
        timer++; 

        if(isWarning && timer>=warningDuration){
            isLaser = true; 
            isWarning=false; 
            timer = 0; //reset timer
        }

        else if(isLaser && timer>=laserDuration){
            isLaser = false; 
        }
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g; 

        if(isWarning){
            g.setColor(warningColor);
        }else{
            g.setColor(laserColor);
        }
        g2d.setStroke(new BasicStroke(getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(getX(), getY(), endX, endY);
    }

    public boolean collidesWith(GameObject other) {
        if (!isLaser) return false;
    
        int thickness = getWidth();
        int x1 = getX();
        int y1 = getY();
        int x2 = endX;
        int y2 = endY;
    
        //player center point
        int playerCenterX = other.getX() + other.getWidth()/2;
        int playerCenterY = other.getY() + other.getHeight()/2;
    
        //closest point on laser line to player center
        double lineLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double t = Math.max(0, Math.min(1, 
            ((playerCenterX - x1) * (x2 - x1) + (playerCenterY - y1) * (y2 - y1)) / 
            (lineLength * lineLength)
        ));
        
        int closestX = (int)(x1 + t * (x2 - x1));
        int closestY = (int)(y1 + t * (y2 - y1));
    
        //distance from player center to closest point on laser
        double distance = Math.sqrt(Math.pow(playerCenterX - closestX, 2) + 
            Math.pow(playerCenterY - closestY, 2));
    
        //check if distance is within collision threshold
        return distance < (thickness/2 + other.getWidth()/2);
    }

    public boolean isCollidable(){
        return isLaser; 
    }

    public boolean isExpired(){
        return !isWarning && !isLaser; 
    }

    public boolean isActive(){
        return isLaser;
    }
}