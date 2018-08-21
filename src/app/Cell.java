package app;

public class Cell {

    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAlive  = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void switchStatus(){
        isAlive = !isAlive;
    }
}
