/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;


public class DrawingBoard extends JPanel{
    
    protected Figure fig;
    protected Figure fig2;
    protected Random rand;
    protected Runnable run;
    public DrawingBoard(Figure fig,Runnable run,Figure fig2){
        this.fig = fig;
        this.fig2 = fig2;
       
        this.run = run;
    }
    
    @Override
    protected void paintComponent(Graphics g){
       
        
       
         super.paintComponent(g);
        fig.draw(g);
        fig2.draw(g);
        
        
        }
        
        
       
       

        
    }
    
    
    

