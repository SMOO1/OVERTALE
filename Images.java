// Code Done all by Harrell the Horrid

import javax.swing.*;
import java.awt.*;

class WallImage extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.fillRect(0, 0, 100, 100);
    }   
}
class PlayerImage extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
        
    }   
}
class FloorImage extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 100);
        
    }   
}

class BossImage extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 100, 100);
        
    }   
}
class HatchImage extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.PINK);
        g.fillRect(0, 0, 100, 100);
        
    }   
}

class Error extends JPanel
{
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawString("Error", 0, 0);
        
    }   
}

