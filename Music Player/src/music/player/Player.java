/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JTextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public class Player {
    
    private String filePath;
    private InputStream music;
    JTextField edno;
    private int index;
private  AudioStream audios;
    public Player(String filePath,JTextField edno) throws FileNotFoundException, IOException {
        this.filePath = filePath;
        this.edno = edno;
        this.index = 0;
        this.music = new FileInputStream(new File(filePath));
        audios = new AudioStream(music);
    }
    
    public void playMusic() {
        try{
         
            
        AudioPlayer.player.start(audios);
           
        
        
    }catch(Exception e){
        edno.setText("CHOOSE A MUSIC FILE");
    }
    
    
    
}
    public void stopMusic(){
         AudioPlayer.player.stop(audios);
         
    }
    public void pauseMusic(){
      
    }
    
}
