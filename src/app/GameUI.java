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
