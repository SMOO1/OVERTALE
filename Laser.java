import java.awt.*;
import java.util.ArrayList;

public class Laser extends GameObject {
    private Boss boss;
    private Player player;
    private ArrayList<Laser> lasers; 

    public Laser(int x, int y,  Boss boss, Player player) {
        super(x, y, 40, 40, Color.YELLOW);
        this.boss = boss;
        this.player = player; 
    }
    //update the position
    public void update() {
        //code
    }

    private ArrayList<Laser> makeLasers(){
        return lasers; 
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(getColor());
        g2.drawLine(getX(),getY(), player.getX(),player.getY()); 
    }
    
    public void deletelaser(){

    }
}