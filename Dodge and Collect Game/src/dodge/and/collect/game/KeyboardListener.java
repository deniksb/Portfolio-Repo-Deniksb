/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyboardListener implements KeyListener {

    protected Component comp;
    protected Figure fig;
    protected Figure fig2;
    protected int points;
    protected JLabel lable;
    protected int i;
    protected JFrame panel;

    public KeyboardListener(Component comp, Figure fig, Figure fig2,JFrame panel) {
        this.comp = comp;
        this.fig = fig;
        this.fig2 = fig2;
        this.points = 0;
        this.i = 10;
        this.panel = panel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        Random rand = new Random();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            fig.move(-10, 0);

            if (fig.getX() == fig2.getX() && fig.getY() == fig2.getY()) {

                while (true) {
                    int numone = rand.nextInt(30) * i;
                    int numtwo = rand.nextInt(30) * i;
                    if (fig2.getX() + numone < 550 && fig2.getX() + numone > 0 && fig2.getY() + numtwo < 550 && fig2.getY() + numtwo > 0) {
                        fig2.move(numone, numtwo);
                        System.out.println(points);
                        points++;
                        panel.setTitle("Denis' Very Fun Game                                      " + "Points: " + points);
                        comp.repaint();
                        i *= -1;
                        break;
                    }

                }
            }

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            fig.move(10, 0);

            if (fig.getX() == fig2.getX() && fig.getY() == fig2.getY()) {

                while (true) {
                    int numone = rand.nextInt(30) * i;
                    int numtwo = rand.nextInt(30) * i;
                    if (fig2.getX() + numone < 550 && fig2.getX() + numone > 0 && fig2.getY() + numtwo < 550 && fig2.getY() + numtwo > 0) {
                        fig2.move(numone, numtwo);
                        System.out.println(points);
                        points++;
                        panel.setTitle("Denis' Very Fun Game                                      " + "Points: " + points);
                        comp.repaint();
                        i *= -1;
                        break;
                    }

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            fig.move(0, -10);

            if (fig.getX() == fig2.getX() && fig.getY() == fig2.getY()) {

                while (true) {
                    int numone = rand.nextInt(30) * i;
                    int numtwo = rand.nextInt(30) * i;
                    if (fig2.getX() + numone < 550 && fig2.getX() + numone > 0 && fig2.getY() + numtwo < 550 && fig2.getY() + numtwo > 0) {
                        fig2.move(numone, numtwo);
                        System.out.println(points);
                        points++;
                        panel.setTitle("Denis' Very Fun Game                                      " + "Points: " + points);
                        comp.repaint();
                        i *= -1;
                        break;
                    }

                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            fig.move(0, 10);

            if (fig.getX() == fig2.getX() && fig.getY() == fig2.getY()) {

                while (true) {
                    int numone = rand.nextInt(30) * i;
                    int numtwo = rand.nextInt(30) * i;
                    if (fig2.getX() + numone < 550 && fig2.getX() + numone > 0 && fig2.getY() + numtwo < 550 && fig2.getY() + numtwo > 0) {
                        fig2.move(numone, numtwo);
                        System.out.println(points);
                        points++;
                        panel.setTitle("Denis' Very Fun Game                                      " + "Points: " + points);
                        comp.repaint();
                        i *= -1;
                        break;
                    }

                }
            }
        }

        comp.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
