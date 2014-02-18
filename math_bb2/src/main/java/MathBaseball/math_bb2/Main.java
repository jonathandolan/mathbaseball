package MathBaseball.math_bb2;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Febo on 2/9/14.
 */
public class Main extends JFrame {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;


    public Main() throws IOException {
        super("Math Baseball");

        setSize(WIDTH, HEIGHT);
        setBackground(Color.WHITE);

        Baseball b = new Baseball();
        getContentPane().add(b.$$$getRootComponent$$$());
        b.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Main(JComponent jC){
        super("Math Baseball");

        setSize(WIDTH, HEIGHT);
        setBackground(Color.WHITE);

        getContentPane().add(jC);
        jC.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }

}
