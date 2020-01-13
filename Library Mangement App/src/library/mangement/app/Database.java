/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.mangement.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Denislav Berberov – 3863158 – deniksb
 * {@code 432801@student.fontys.nl}
 */
public class Database {
    
    private Map<String,String> db;
    private FileWriter writer;
    private String filename;
    private Scanner reader;
    private File filee;
    public Database(String file) throws IOException {
        this.db = new HashMap<>();
        this.filee = new File(file);
        this.filename = file;
        
        
    }
    
    public void addToLibrary(String word,String author) throws IOException{
        this.writer = new FileWriter(filename,true);
        db.put(word, author);
        
        
        writer.write("\n" + word + " " + author);
        writer.close();
        
    }
    public String searchLibrary(String input) throws FileNotFoundException{
        this.reader = new Scanner(filee);
        String buffer = input.toLowerCase();
        while(reader.hasNextLine()){
            String book = reader.nextLine();
            String lower = book.toLowerCase();
           
           if(lower.contains(buffer)){
               return book;
           }
        }
        reader.close();
        return null;
}
}
