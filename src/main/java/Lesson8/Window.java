package Lesson8;


import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    private int SIZE;
    public static JButton[][] cells;
    private static JPanel gamePanel;
    private static Font font = new Font("Arial", Font.BOLD, 60); //устанавливаем размер шрифта

    public Window(int SIZE) throws IOException {
        this.SIZE = SIZE;
        setTitle("CrossZero");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 280, 300);
        setResizable(false);
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLACK);
        cells = new JButton[SIZE][SIZE];
        fieldBuilder();
        userTurns(cells);
        add(gamePanel);
        setVisible(true);
    }

    public static void fieldBuilder() {
        int count = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++, count++) {
                cells[i][j] = new JButton(" ");
                cells[i][j].setFont(font);
                cells[i][j].setPreferredSize(new Dimension(80, 80)); //устанавливаем размер кнопок
                cells[i][j].setBackground(Color.LIGHT_GRAY);
                gamePanel.add(cells[i][j]);
            }
        }
    }
    public static void fieldReBuilder() {
        int count = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++, count++) {
                cells[i][j].setText(" ");
                cells[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    public void userTurns(JButton[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].addActionListener(new Listener(cells, i, j));
            }
        }
    }

    public static void lineWinnerBacklight(int arrayPosition, String line, boolean player) {
        //v - вертикальная линия
        //h - горизонтальная линия
        //l - левая диагональ
        //r - правая диагональ
        switch (line) {
            case "h":
                for (int i = 0; i < cells.length; i++) {
                    cells[arrayPosition][i].setBackground(player ? Color.GREEN : Color.RED);
                }
                break;
            case "v":
                for (int i = 0; i < cells.length; i++) {
                    cells[i][arrayPosition].setBackground(player ? Color.GREEN : Color.RED);
                }
                break;
            case "l":
                for (int i = 0, j = 0; i < cells.length; i++, j++) {
                    cells[i][j].setBackground(player ? Color.GREEN : Color.RED);
                }
                break;
            case "r":
                for (int i = 0, j = cells.length; j > 0; i++, j--) {
                    cells[i][j - 1].setBackground(player ? Color.GREEN : Color.RED);
                }
                break;
            default:
                break;
        }
    }

    public static class Listener implements ActionListener {
        int i, j;
        JButton[][] cells;

        public Listener(JButton[][] cells, int i, int j) {
            this.i = i;
            this.j = j;
            this.cells = cells;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (CrossZeroVisual.field[i][j].equals(".") && !CrossZeroVisual.end&&!CrossZeroVisual.turnPC) {
                cells[i][j].setText("X");
                try {
                    CrossZeroVisual.humanTurn(i, j);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                CrossZeroVisual.turnPC = true;
            }
        }
    }


}

