import java.awt.Color;

public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    //getter methods
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Color getColor() {
        return color;
    }

    //setter methods
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    //method to check collision with other object
    public boolean collidesWith(GameObject other) {
        return getX() < other.getX() + other.getWidth() && getX()+ getWidth()>other.getX() && getY()<other.getY() + other.getHeight() && 
        getY() + getHeight() > other.getY();
    }
}