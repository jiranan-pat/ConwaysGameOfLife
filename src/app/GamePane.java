package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GamePane extends JPanel implements MouseListener {

    private Cell[][] cell = new Cell[20][20];
    private boolean start = false;


    public GamePane(){
        initCells();
        this.addMouseListener(this);

    }

    public void initCells() {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                cell[x][y] = new Cell(x * 40 , y * 40);
            }
        }
    }



    public void paint(Graphics graphics) {
        super.paintComponents(graphics);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,800,820);

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                cell[x][y].paintCell(graphics);
            }
        }

        graphics.setColor(Color.GREEN);
        for (int x = 0; x < 40; x++) {
            graphics.drawLine(40 * x, 0, 40 * x, 800);
        }
        for (int y = 0; y < 40; y++) {
            graphics.drawLine(0, 40 * y, 800, 40 * y);
        }

    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX()/40;
        int y = e.getY()/40;
        cell[x][y].setAlive(true);

        System.out.println("X: "+x+ " Y: " +y);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
