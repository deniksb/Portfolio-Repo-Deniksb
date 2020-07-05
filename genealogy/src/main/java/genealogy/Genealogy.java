package genealogy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.lang.reflect.*;

/**
 * Output the class hierarchy or ancestry of a class.
 *
 * @author hom
 */
public class Genealogy {

    int c = new ArrayList<Class>().stream().mapToInt(p -> 1).sum();

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) throws ClassNotFoundException {
//        if ( args.length == 0 ) {
//            System.out.println( "genealogy.Genealogy classname"
//                    + " [[classname]...]\n"
//                    + "as in genealogy.Genealogy java.lang.String"
//            );
//        }
//
//        Genealogy gen = new Genealogy();
//        for ( String arg : args ) {
//            String sb = gen.getAncestry( arg );
//            System.out.format( "class hierarchy of [%s]%n", arg );
//            System.out.println( sb );
//        }

        Genealogy gen = new Genealogy();
        System.out.println(gen.getAncestry(Genealogy.class.getName()));
    }


    /**
     * Get the ancestry of the class or interface  with the given name.
     *
     * @param typeName to use
     * @return a string containing the type hierarchy of the type
     */
    public String getAncestry(String typeName) throws ClassNotFoundException {
        Class reflectClass = Class.forName(typeName);
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(reflectClass);
        int i = 0;
        while (i < 10) {
            try {
                classes.add(classes.get(i).getSuperclass());
            } catch (Exception e) {
                break;
            }
            i++;
        }
        StringBuilder toReturn = new StringBuilder();
        int iteration = 0;
        int t = classes.size() - 2;  //idk why it is -2 but it seems to add some extra stuff to the list
        int spaceNumber = 0;
        while (t >= 0) {
            while (spaceNumber < iteration * 2) {
                toReturn.append(" ");
                spaceNumber++;
            }
            try {
                Class currentClass = classes.get(t);
                toReturn.append("\n").append(currentClass.getName());
                Class[] interfaces = currentClass.getInterfaces();
                if (interfaces.length > 0) {
                    toReturn.append(" implements");

                    for (Class iFace : interfaces) {
                        toReturn.append(" " + iFace.getName());
                    }
                }
            } catch (Exception e) {
                break;

            }
            spaceNumber = 0;
            iteration++;
            t--;
        }

        return toReturn.toString();
    }

}
