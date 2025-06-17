// Code Done all by Harrell the Horrid

import javax.swing.*;
import java.awt.*;

public class OverPlayer 
{
    private int x;
    private int y;
    int hitpoints;
    int damage;
    char[][] gameMap;
    private JFrame screen;
    OverPlayer(int x, int y, char[][] map, JFrame screen)
    {
        this.screen = screen;
        this.x = x;
        this.y = y;
        gameMap = map;
    }
    private void hitBoss()
    {
        System.out.println("Boss Killed");
    }
    private void enterHatch()
    {
        WorldScreen je = new WorldScreen(screen);
    }
    public void moveN()
    {
        if(y == 0 || gameMap[y-1][x] == '*') return;
        if(gameMap[y-1][x] == 'B') hitBoss();
        else if(gameMap[y-1][x] == 'H') enterHatch();


        gameMap[y][x] = 'f';
        y--;
        gameMap[y][x] = 'P';
    }
    public void moveE()
    {
        if(x == Math2D.length(gameMap) || gameMap[y][x+1] == '*') return;
        if(gameMap[y][x+1] == 'B') hitBoss();
        else if(gameMap[y][x+1] == 'H') enterHatch();

        gameMap[y][x] = 'f';
        x++;
        gameMap[y][x] = 'P';
    }
    public void moveS()
    {
        if(y == gameMap.length || gameMap[y+1][x] == '*') return;
        if(gameMap[y+1][x] == 'B') hitBoss();
        else if(gameMap[y+1][x] == 'H') enterHatch();

        gameMap[y][x] = 'f';
        y++;
        gameMap[y][x] = 'P';
    }
    public void moveW()
    {
        if(x == 0 || gameMap[y][x-1] == '*') return;
        if(gameMap[y][x-1] == 'B') hitBoss();
        else if(gameMap[y][x-1] == 'H') enterHatch();

        gameMap[y][x] = 'f';
        x--;
        gameMap[y][x] = 'P';
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}
