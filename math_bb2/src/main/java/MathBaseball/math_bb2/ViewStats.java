package MathBaseball.math_bb2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Febo on 2/19/14.
 */
public class ViewStats extends JFrame {
    static final int WIDTH = 850;
    static final int HEIGHT = 650;
    private JPanel panel1;
    private JTextArea textPane1;

    public ViewStats() {

        super("Math Baseball");

        setSize(WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        panel1 = new JPanel();
        setupUi();
        getContentPane().add(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private void setupUi(){
        textPane1 = new JTextArea();
        textPane1.setFont(new Font("HGMinchoL", textPane1.getFont().getStyle(), 22));
        textPane1.setText("View Stats tab - Further implemented in Release 2.");
        textPane1.setEditable(false);
        panel1.add(textPane1);

    }
}
