/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodge.and.collect.game;



import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class UserInterface implements Runnable {

private JFrame frame;
private Figure fig;
private Figure fig2;

    public UserInterface(Figure fig,Figure fig2) {
      this.fig = fig;
      this.fig2 = fig2;
    }
    
    

    @Override
    public void run() {
      this.frame = new JFrame();
      frame.setTitle("Denis' Very Fun Game                                      " + "Points: 0 ");
       frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        addListeners();

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        JLayeredPane pane = new JLayeredPane();
    DrawingBoard bord = new DrawingBoard(fig,this,fig2);
    JLabel lable = new JLabel("");
    
       
        container.add(bord);
           frame.addKeyListener(new KeyboardListener(bord,fig,fig2,frame));
           
    }

    private void addListeners() {
        
    }


}
