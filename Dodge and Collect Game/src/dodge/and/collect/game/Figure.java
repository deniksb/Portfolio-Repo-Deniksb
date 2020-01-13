/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;

import java.awt.Graphics;


public abstract class Figure {
    
    protected int x;
    protected int y;

    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Figure() {
    }
    
    
    
    
    public void move(int dx, int dy){
        
        this.x += dx;
        this.y +=dy;
    }
    
    public int getX(){
        return this.x;
    }
    
      public int getY(){
        return this.y;
    }
      
      public abstract void draw(Graphics graphics);
    
}
