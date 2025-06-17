// Code Done all by Harrell the Horrid

import java.util.ArrayList;

public class BSP
{
    ArrayList<Node> mapParts;

    BSP()
    {
        mapParts = new ArrayList<Node>();
    }

    private Node[] split(Node map, int min, int max)
    {
        int size;
        char direction = 'y';
        //System.out.println(map.length());
        if(map.length() > map.height())
            direction = 'x';

        if(direction == 'y')
            size = map.height();
        else
            size = map.length();

        int cutPart = (int) (Math.random()*(max + min + 1)) + (size/2 - min);
        //System.out.println("cut at " + cutPart + " " + direction+ " stuff " + max + " " + min + " " + size);
        if(direction == 'x'){
            char[][][] cuts = Math2D.cutX(map.gMap(), cutPart);
            Node[] output = {new Node(cuts[0],map.x(),map.y()), new Node(cuts[1],map.x()+cutPart,map.y())}; 
            return output;

        } else {
            char[][][] cuts = Math2D.cutY(map.gMap(), cutPart);
            Node[] output = {new Node(cuts[0],map.x(),map.y()), new Node(cuts[1],map.x(),map.y()+cutPart)}; 
            return output;
        }
    }    
    public void partition(Node map, int depth)
    {
        if(depth == 4){
            mapParts.add(map);
            return;
        }
        Node[] mapParts = split(map,6,6);

        partition(mapParts[0], depth+1);
        partition(mapParts[1], depth+1);
    }
}

class Node
{
    private char[][] map;
    private int x;
    private int y;

    Node(char[][] map, int x, int y)
    {
        this.map = map;
        this.x = x;
        this.y = y;
    }

    // Getter Method 
    public char[][] gMap()
    {
        return map;
    } 
    public int length()
    {
        return Math2D.length(map);
    }
    public int height()
    {
        return map.length;
    }
    public int x()
    {
        return x;
    }
    public int y()
    {
        return y;
    }
    // Setter
    public void swapMap(char[][] newMap)
    {
        map = newMap;
    }
}
