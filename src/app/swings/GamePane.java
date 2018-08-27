package app.swings;

import app.Cell;
import app.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GamePane extends JPanel {

    private Game game = new Game();

    public GamePane() {
        this.addMouseListener(new MouseAction());
        this.addKeyListener(new KeyAction());
    }

    public void startGame() {

        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                do {
                    System.out.println("start!!!!  " + i++);
                    game.gameLogic();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                } while (!game.isStable());
                System.out.println("game is stable");
            }
        }).start();

    }

    public void paint(Graphics graphics) {
        super.paintComponents(graphics);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 820);

        for (Cell c : game.getCurrentAliveCellList())
            c.paintCell(graphics);

        graphics.setColor(Color.GREEN);
        for (int x = 0; x < 40; x++) {
            graphics.drawLine(40 * x, 0, 40 * x, 800);
        }
        for (int y = 0; y < 40; y++) {
            graphics.drawLine(0, 40 * y, 800, 40 * y);
        }

    }

    class KeyAction implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                startGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    class MouseAction implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / 40;
            int y = e.getY() / 40;
            game.initialCellDeadOrAlive(x, y);
            repaint();
            System.out.println("X: " + x + " Y: " + y);
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
}
