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
    private DBWrapper dBase;

    public ViewStats(DBWrapper db) {

        super("Math Baseball");
        dBase = db;
        setSize(WIDTH, HEIGHT);
        setBackground(Color.WHITE);
        panel1 = new JPanel();
        setupUi();
        getContentPane().add(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadData();
        setVisible(true);

    }


    private void loadData(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Scores for " + MainMenu.studentUserName);
        stringBuilder.append("Score: " +dBase.getScoreForStudent(MainMenu.studentUserName));
        stringBuilder.append("Problems Attempted: " +dBase.getNumberOfQuestionsForStudent(MainMenu.studentUserName));
        textPane1.setText(stringBuilder.toString());
    }

    private void setupUi(){
        textPane1 = new JTextArea();
        textPane1.setFont(new Font("HGMinchoL", textPane1.getFont().getStyle(), 22));
        textPane1.setText("");
        textPane1.setEditable(false);
        panel1.add(textPane1);

    }
}
