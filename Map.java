// Code Done all by Harrell the Horrid

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Map 
{
    private char[][] gameMap;
    private ArrayList<Node> mapList;
    private ArrayList<Room> roomList = new ArrayList<>();
    private GameWindow screen;

    Map(int height, int length)
    {
        gameMap = new char[height][length];
        Math2D.replace(gameMap, '*');

        BSP spliter = new BSP();
        //System.out.println(Math2D.length(gameMap));
        spliter.partition(new Node(gameMap, 0, 0), 0);
        mapList = spliter.mapParts;

        for(int i = 0; i < mapList.size(); i++)
        {
            Room creater = new Room(mapList.get(i).gMap());
            creater.createRoom();
            mapList.get(i).swapMap(mapList.get(i).gMap());
            roomList.add(creater);
        }

        
    }
    public void createRoom()
    {
        for(Node roomArea: mapList)
        {
            int x = roomArea.x();
            int y = roomArea.y();
            for(int i = 0; i < roomArea.height(); i++){
                for(int j = 0; j < roomArea.length(); j++){
                    //System.out.println(roomArea.gMap()[i][j]);
                    gameMap[i+y][j+x] = roomArea.gMap()[i][j];
                }
            }
        }
    }
    private void tunnelX(int xLength, int startX, int y)
    { 
        int change;
        if(xLength >= 0) 
            change = 1;
        else 
            change = -1;
        //System.out.println("suff " + xLength + " " + change);
        for(int i = 0; i < Math.abs(xLength); i++)
        {
            //System.out.println("index " + y + " " + (startX + change*i));
            gameMap[y][startX + change*i] = 'f';
        }
    }
    private void tunnelY(int yLength, int x, int startY)
    {
        int change;
        if(yLength >= 0) 
            change = 1;
        else 
            change = -1;
        for(int i = 0; i < Math.abs(yLength); i++)
        {
            gameMap[startY + change*i][x] = 'f';
        }
    }
    public void tunnel()
    {
        
        for(int i = 0; i < roomList.size();i++){
            int j;
            if(i == roomList.size() - 1)
                j = i - 1;
            else 
                j = i + 1;
            
            Room room = roomList.get(i);
            Room nextRoom = roomList.get(j);
            
            int x = room.getX() + mapList.get(i).x();
            int y = room.getY() + mapList.get(i).y();
            int x2 = nextRoom.getX() + mapList.get(j).x();
            int y2 = nextRoom.getY() + mapList.get(j).y();
            int xPath = x2 - x;
            int yPath = y2 - y;
            //System.out.println("x y " + x + " " + y);
            
            int direction = (int)(Math.random()*2);
            if(direction == 1){
                tunnelX(xPath, x, y);
                tunnelY(yPath, x + xPath, y);
                //System.out.println("room " + i + " XY cord " + y + " " + x);
            } else{
                tunnelY(yPath, x, y);
                tunnelX(xPath, x, y + yPath);
                //System.out.println("room " + i +" YX cord " + y + " " + x);
            }
            //System.out.println("nextRoom " + (i+1) + " cord " + nextRoom.getY() + " " + nextRoom.getX());
        }
    }
    public void createStuff()
    {
        for(int i = 0; i < mapList.size();i++){
            int x = mapList.get(i).x() + roomList.get(i).getX();
            int y = mapList.get(i).y() + roomList.get(i).getY();
            if(i == 0){
                gameMap[y][x] = 'P';
            }else if(i == mapList.size()-1){
                gameMap[y][x] = 'H';
            }else
                gameMap[y][x] = 'B';
        }
    }
    public char[][] getMap()
    {
        return gameMap;
    }
    public OverPlayer getPlayer(GameWindow screen, String playerName)
    {
        return new OverPlayer(roomList.get(0).getX(), roomList.get(0).getY(), gameMap, screen, playerName);
    }
    
}
