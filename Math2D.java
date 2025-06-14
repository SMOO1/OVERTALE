// Code Done all by Harrell the Horrid

public class Math2D 
{
    public static int length(char[][] map)
    {
        //System.out.println(map.length);
        return map[0].length;
    }
    public static void print(char[][] map)
    {
        for(int i = 0; i < map.length;i++){
            for(int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    public static void replace(char[][] map, char newChar)
    {
        for(int i = 0; i < map.length;i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = newChar;
            }
        }
    }
    public static char[][][] cutX(char[][] map, int x)
    {
        if(length(map) < x){
            System.out.println("cutX error inputted x is larger that map");
            return null;
        }
        char[][] first = new char[map.length][x];
        char[][] second =  new char[map.length][length(map) - x];
        for(int ycord = 0; ycord < map.length; ycord++){
            for(int xcord = 0; xcord < map[ycord].length; xcord++){
                if(xcord < x){
                    //System.out.println("height:" + map.length + " Index:" + ycord + " " + xcord + " length: " + map[ycord].length);
                    first[ycord][xcord] = map[ycord][xcord];
                }
                else
                    second[ycord][xcord - x] = map[ycord][xcord];
            }
        }
        char[][][] list = {first, second};

        return list;
    }
    public static char[][][] cutY(char[][] map, int y)
    {
        if(map.length < y){
            System.out.println("cutY error inputted y is larger that map");
            return null;
        }

        char[][] first = new char[y][map.length];
        char[][] second =  new char[map.length - y][length(map)];
        for(int i = 0; i < map.length;i++){
            if(i < y)
                first[i] = map[i];
            else 
                second[i-y] = map[i];
        }

        char[][][] list = {first, second};

        return list;
    }
}
