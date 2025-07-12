// Code Done all by Harrell the Horrid

import javax.swing.*;
import java.awt.*;

public class OverPlayer 
{
    private String playerName;
    private int x;
    private int y;
    int hitpoints;
    int damage;
    char[][] gameMap;
    private GameWindow screen;


    OverPlayer(int x, int y, char[][] map, GameWindow screen, String playerName)
    {
        this.screen = screen;
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        gameMap = map;
    }
    private void hitBoss()
    {
        screen.showGameScreen(playerName);
    }
    
    
    private void enterHatch()
    {
       // screen.showGameScreen("sds");
        WorldScreen je = new WorldScreen(screen, playerName);
        screen.setMapScreen(je, playerName);
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
