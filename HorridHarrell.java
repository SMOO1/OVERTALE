import java.util.Random;
import java.awt.*;


public class HorridHarrell extends Boss{
    private int acceleration = 1; 
    private int maxSpeed = 80; 
    private int attackCooldown = 20;
    private int speed;
    private Random random = new Random(); 

    private int targetX, targetY; 
    private int pauseTimer; 
    private boolean isMoving; 

    private int hp = 150;

    public HorridHarrell(int x, int y){
        super(x,y, 300,300);
        this.speed = 0;
        this.pauseTimer = 0;
        this.isMoving = false; 
        chooseNewDirection();
    }

    @Override
    public void update(){
        if(isMoving){
            //accelerate
            speed = Math.min(speed + acceleration, maxSpeed);
            
            //move toward target
            moveToward(targetX, targetY);
            
            //check if reached target
            if(Math.abs(getX() - targetX) < 20 && Math.abs(getY() - targetY) < 20){
                isMoving = false; 
                pauseTimer = attackCooldown; 
                speed = 0; 
            }
        } else {
            //handle pause cooldown
            if(pauseTimer <= 0){
                chooseNewDirection();
                isMoving = true;
            } else {
                pauseTimer--;
            }
        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(new Color(180, 50,50));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private void chooseNewDirection(){
        targetX = random.nextInt(700)+50;
        targetY = random.nextInt(500)+50;
    }

    private void moveToward(int targetX, int targetY) {
        
        double angle = Math.atan2(targetY - getY(), targetX - getX());
        setX(getX() + (int)(speed*Math.cos(angle)));
        setY(getY() + (int)(speed*Math.sin(angle)));
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }
    
    public int getHp() {
        return hp;
    }
    
}
