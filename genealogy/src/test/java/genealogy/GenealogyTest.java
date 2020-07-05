/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genealogy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.*;

/**
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class GenealogyTest {

    Genealogy gen = new Genealogy();


    /**
     * Test with javax.swing.JButton.
     */
    @Test
    public void genealogyJButton() throws ClassNotFoundException {
        //TODO test with JButton
        //feels kinda weird hardcoding it like this but it works i guess
        String expected = "\njava.lang.Object  \n" +
                "java.awt.Component implements java.awt.image.ImageObserver java.awt.MenuContainer java.io.Serializable    \n" +
                "java.awt.Container      \n" +
                "javax.swing.JComponent implements java.io.Serializable javax.swing.TransferHandler$HasGetTransferHandler        \n" +
                "javax.swing.AbstractButton implements java.awt.ItemSelectable javax.swing.SwingConstants          \n" +
                "javax.swing.JButton implements javax.accessibility.Accessible";

        assertThat(gen.getAncestry(JButton.class.getName())).isEqualTo(expected);
//        fail( "method genealogyJButton reached end. You know what to do." );
    }

    /**
     * Test with self, genealogy.Genealogy.
     */
    @Test
    public void testSelf() throws ClassNotFoundException {
        String expected = "\n" +
                "java.lang.Object  \n" +
                "genealogy.Genealogy";

        assertThat(gen.getAncestry(Genealogy.class.getName())).isEqualTo(expected);
//        fail( "method testSelf reached end. You know what to do." );
    }

    @Test
    public void testMain() {
        String[] args = {"genealogy.Student"};
        assertThatCode(() -> {
            Genealogy.main(args);
        }).doesNotThrowAnyException();
//        fail( "method testMain reached end. You know what to do." );
    }

    /**
     * This test is for coverage of the main method.
     * To make it a 'meaningfull' test, we ensure that application does
     * not throw an exception.
     */
    @Test
    public void testMainExceptionsCaught() {
        String[] args = {"genealogy.NoStudent"};
        assertThatCode(() -> {
            Genealogy.main(args);
        }).doesNotThrowAnyException();
        ;
//        fail( "method testMain reached end. You know what to do." );
    }

    /**
     * This test is for coverage of the main method.
     * To make it a 'meaningfull' test, we ensure that application does
     * not throw an exception.
     */
    @Test
    public void noArgsNoException() {
        assertThatCode(() -> {
            Genealogy.main(new String[0]);
        }).doesNotThrowAnyException();
//        fail( "method noArgsNoException reached end. You know what to do." );
    }
}
