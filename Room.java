// Code Done all by Harrell the Horrid

public class Room 
{
    private char[][] totalArea;
    private int height;
    private int length;
    private int roomHeight;
    private int roomLength;
    private int centerX;
    private int centerY;
    private int roomX;
    private int roomY;

    Room(char[][] map)
    {
        totalArea = map;
        height = totalArea.length;  //10
        length = Math2D.length(map);//18
        centerX = (int)(Math.random()*(length/4)) + (3*length/8);
        centerY = (int)(Math.random()*(height/4)) + (3*height/8);

        //centerX = length/2;
        //centerY = height/2;

        int heightCount = 2; // 5
        int lengthCount = 2;
        while(centerX + lengthCount < length - 1 && centerX - lengthCount > 1 && centerY + heightCount < height - 1 && centerY - heightCount > 1){
            int choose = (int)(Math.random()*2);
            if(choose == 0) heightCount++;
            if(choose == 1) lengthCount++;
        }
        roomLength = lengthCount*2; 
        roomHeight = heightCount*2; 
        //System.out.println("dimension " + roomLength + " " + roomHeight);
        

        roomX = centerX - lengthCount;
        roomY = centerY - heightCount;
        //System.out.println("value " +centerX);
    }

    public void createRoom()
    {
        //Math2D.replace(room, '_');
        //System.out.println(room[0][0]);
        
        for(int i = 0; i < totalArea.length; i++){
            if(!(i >= roomY && i <= roomY +roomHeight)) 
                continue;
            for(int j = 0; j < Math2D.length(totalArea); j++){
                if(j > roomX && j <= roomX + roomLength){
                    //System.out.println("X: " + j);
                    totalArea[i][j] = 'f';
                }
            }
        }
        
    }

    public int getX()
    {
        return centerX;
    }
    public int getY()
    {
        return centerY;
    }
    public char[][] getRoom()
    {
        return totalArea;
    }
}
