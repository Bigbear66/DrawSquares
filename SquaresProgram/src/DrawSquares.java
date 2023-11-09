import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;


// DrawSquares.java
// by Eldin Pita
// CSCI 1302
// Lab 1
// 02/05/2020


public class DrawSquares extends JPanel {
    private ArrayList<Rectangle> rectList;

    static SecureRandom randomNum = new SecureRandom();

    private int xSpeeds[], ySpeeds[];

    public DrawSquares() {
        rectList = new ArrayList<Rectangle>();
        addMouseListener(new DotsListener());
        setBackground(Color.black);
        setPreferredSize(new Dimension(350, 250));

        xSpeeds = new int[1000];
        ySpeeds = new int[1000];

        for (int i = 0; i < 1000; i++) {
            xSpeeds[i] = randomNum.nextInt(10) + 1;
            ySpeeds[i] = randomNum.nextInt(10) + 1;
        }

        Timer timer = new Timer();
        timer.schedule(new myTimer(), 0, 100);
    }

    class myTimer extends TimerTask {

        @Override
        public void run() {

            int w = getWidth() - 20;
            int h = getHeight() - 20;

            for (int i=0; i< rectList.size(); i++) {
                Rectangle r = rectList.get(i);
                r.x += xSpeeds[i];
                if(r.x < 0) {
                    r.x = 0;
                    xSpeeds[i] *= -1;
                }
                if(r.x > w) {
                    r.x = w;
                    xSpeeds[i] *= -1;
                }
                r.y += ySpeeds[i];
                if(r.y < 0) {
                    r.y = 0;
                    ySpeeds[i] *= -1;
                }
                if(r.y > h) {
                    r.y = h;
                    ySpeeds[i] *= -1;
                }
            }

            repaint();
        }
    }



    public void paint(Graphics page) {
        super.paintComponent(page);

        page.setColor(Color.white);

        for (Rectangle r : rectList)
            page.drawRect(r.x, r.y, r.width, r.height);

        page.drawString("Count: " + rectList.size(), 5, 15);
    }

    private class DotsListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            DrawSquares.this.drawSquare(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
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

    private void drawSquare(MouseEvent event) {
        int x = event.getPoint().x;
        int y = event.getPoint().y;

        Rectangle r = new Rectangle(x, y, 20, 20);

        rectList.add(r);
        repaint();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.add(new DrawSquares());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20, 20, 550, 550);
        frame.setVisible(true);
    }
}