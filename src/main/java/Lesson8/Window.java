package Lesson8;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    private int SIZE;
    private JButton labels;
    private JPanel gamePanel;
    private Font font = new Font("Arial", Font.BOLD, 40); //устанавливаем размер шрифта

    public Window(int SIZE) throws IOException {
        this.SIZE = SIZE;
        setTitle("CrossZero");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 300, 300);
        setResizable(false);
        gamePanel = new JPanel();
        JButton[][] labels = new JButton[SIZE][SIZE];
        fieldBuilder(labels);
        add(gamePanel);
        setVisible(true);
    }

    public void fieldBuilder(JButton[][] labels) {
        int count = 0;
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++, count++) {
                labels[i][j] = new JButton("№" + count);
                labels[i][j].setFont(font);
                labels[i][j].setPreferredSize(new Dimension(80, 80)); //устанавливаем размер кнопок
                gamePanel.add(labels[i][j]);
            }
        }
    }


}

