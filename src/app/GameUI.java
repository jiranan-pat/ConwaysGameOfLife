package app;

import javax.swing.*;

public class GameUI {

    private static JFrame frame;


    public GameUI() {
        frame = getFrame();
        init();

    }

    public static JFrame getFrame() {
        if (frame == null)
            frame = new JFrame("Conway's Game Of Life");
        return frame;
    }

    private void init() {
        frame.setResizable(false);
        frame.setSize(800, 820);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel(new GamePane());
    }
    public static void setPanel(JPanel pane) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.getContentPane().add(pane);
        frame.revalidate();
        pane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        pane.setVisible(true);
        pane.setFocusable(true);
        pane.requestFocusInWindow();
    }
    private void run() {
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GameUI().run();
    }
}


//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.awt.*;
//import javax.swing.*;
//
//public class GameUI extends JFrame implements Runnable, MouseListener {
//
//    private Cell[][] cell = new Cell[20][20];
//
//
//    public GameUI(String title) {
//        super(title);
//        initCells();
//        this.addMouseListener(this);
//
//    }
//
//    private void initCells() {
//        for (int x = 0; x < 20; x++) {
//            for (int y = 0; y < 20; y++) {
//                cell[x][y] = new Cell( x * 40, y * 40);
//            }
//        }
//    }
//
//    public void paint(Graphics graphics) {
//        super.paintComponents(graphics);
//
//        //bg
//        graphics.setColor(Color.WHITE);
//        graphics.fillRect(0, 0, 800, 800);
//
//        //cell
//        for (int x = 0; x < 20; x++) {
//            for (int y = 0; y < 20; y++) {
//                cell[x][y].paintCell(graphics);
//            }
//        }
//
//        //grid
//        graphics.setColor(Color.GREEN);
//        for (int x = 0; x < 40; x++) {
//            graphics.drawLine(40 * x, 0, 40 * x, 800);
//        }
//        for (int y = 0; y < 40; y++) {
//            graphics.drawLine(0, 40 * y, 800, 40 * y);
//        }
//
//    }
//
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//    }
//
//    @Override
//    public void mousePressed(MouseEvent events) {
//        int x = events.getX() / 40;
//        int y = events.getY() / 40;
//        cell[x][y].setAlive();
//
//        System.out.println("X: " + x + " Y: " + y);
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
//
//    public static void main(String[] args) {
//        GameUI game = new GameUI("Conway's Game Of Life");
//        game.setSize(800, 800);
////        new Thread(game).start();
//        game.setLocation(100,50);
//        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        game.setUndecorated(true);
//        game.setVisible(true);
//        game.requestFocusInWindow();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//
//            repaint();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, e);
//            }
//
//        }
//
//    }
//}
//
