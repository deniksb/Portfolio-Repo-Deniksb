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


public class KeyboardListenerBox implements KeyListener{
    
    protected Component comp;
    protected Figure fig;
    protected Figure fig2;
    

    public KeyboardListenerBox(Component comp, Figure fig,Figure circle) {
        this.comp = comp;
        this.fig = fig;
        this.fig2 = circle;
        
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Random rand = new Random();
        if(e.getKeyCode() == KeyEvent.VK_LEFT && fig.getX() == fig2.getX() && fig.getY() == fig2.getY()){
            fig.move(rand.nextInt(250), 250);
            
        }
        else  if(e.getKeyCode() == KeyEvent.VK_RIGHT && fig.getX() == fig2.getX() && fig.getY() == fig2.getY()){
            fig.move(rand.nextInt(250), 250);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP && fig.getX() == fig2.getX() && fig.getY() == fig2.getY()){
            fig.move(rand.nextInt(250), 250);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && fig.getX() == fig2.getX() && fig.getY() == fig2.getY()){
           fig.move(rand.nextInt(250), 250);
        }
        
        comp.repaint();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
