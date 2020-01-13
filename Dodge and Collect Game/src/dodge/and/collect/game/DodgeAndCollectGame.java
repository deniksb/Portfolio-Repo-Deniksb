/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;

import java.util.Random;
import javax.swing.SwingUtilities;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public class DodgeAndCollectGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Random rand = new Random(); 
         int x =rand.nextInt(300);
         int y = rand.nextInt(300);
        Circle fig = new Circle(0,0,20);
        Box fig2 = new Box(100,100,20,20);
       UserInterface ui = new UserInterface(fig,fig2);
        SwingUtilities.invokeLater(ui);
    }
    
}
