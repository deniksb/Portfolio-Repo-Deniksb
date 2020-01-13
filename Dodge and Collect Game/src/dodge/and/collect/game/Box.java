/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public class Box extends Figure{
    private int diameter;
    private int diameter2;

    public Box(int x, int y,int diameter,int diameter2) {
        super(x, y);
        this.diameter = diameter;
        this.diameter2 = diameter2;
    }
          

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
    graphics.fillRect(x, y, diameter,diameter2);
    }
}
