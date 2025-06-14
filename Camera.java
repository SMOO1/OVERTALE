// Code Done all by Harrell the Horrid

public class Camera
{
    private char[][] gameMap;
    private OverPlayer player;
    private int height = 7;
    private int length = 7;
    Output printer;

    Camera(Map map, OverPlayer play,Output print)
    {
        gameMap = map.getMap();
        player = play;
        printer = print;
    }

    private char[][] viewMap(int x, int y)
    {
        char[][] view = new char[2*height + 1][2* length + 1];

        int startX = x - length;
        int startY = y - height;

        int endX = x + length;
        int endY = y + height;
        //System.out.println("start(xy) " + startX + " " + startY + " end " + endX+ " " + endY);
        for(int i = startY; i < endY+1; i++){
            for(int j = startX; j < endX+1; j++){
                int viewY = i+ height - y;
                int viewX = j+ length - x;
                if(i < 0 || j < 0 || i >= gameMap.length || j >= Math2D.length(gameMap) ) view[viewY][viewX] = '*';
                else view[viewY][viewX] = gameMap[i][j];
            }
        }

        return view;
    }
    public void print()
    {
        int x = player.getX();
        int y = player.getY();

        printer.printScreen(viewMap(x, y));
    }
}
