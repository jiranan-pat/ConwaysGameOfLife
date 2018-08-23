package app;

import java.awt.*;

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
        this.isAlive = alive;
    }

    public void switchStatus(){
        isAlive = !isAlive;
    }

    public void paintCell(Graphics graphics) {
        if(isAlive) {
            graphics.setColor(Color.DARK_GRAY);
        } else if (!isAlive) {
            graphics.setColor(Color.WHITE);
        }

        graphics.fillRect(x,y,40,40);
    }
}
