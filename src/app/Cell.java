package app;

import java.awt.*;

public class Cell {

    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAlive = false;
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

    public void setAlive() {
        this.isAlive = true;
    }

    public void setDead() {
        this.isAlive = false;
    }

    public void switchDeadOrAlive() {
        isAlive = !isAlive;
    }

    public void paintCell(Graphics graphics) {
        if (isAlive) {
            graphics.setColor(Color.DARK_GRAY);
        } else {
            graphics.setColor(Color.WHITE);
        }

        graphics.fillRect(x * 40, y * 40, 40, 40);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cell){
            Cell c = (Cell) obj;
            return this.x == c.x && this.y == c.y;
        }
        return false;
    }
}
