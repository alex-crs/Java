package Lesson8;

import Lesson4.CrossZero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AlertWindow extends JFrame {

    private Font font = new Font("Arial", Font.BOLD, 15); //устанавливаем размер шрифта
    private Label label;


    public AlertWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 300, 350, 130);
        setResizable(false);
        JPanel alertText = new JPanel();
        JPanel buttonZone = new JPanel();
        JButton buttonYes = new JButton("Да");
        JButton buttonNo = new JButton("Нет");
        buttonZone.add(buttonYes);
        buttonZone.add(buttonNo);
        alertText.setBackground(Color.lightGray);
        buttonZone.setBackground(Color.lightGray);
        label = new Label();
        label.setFont(font);
        alertText.setLayout(new BorderLayout());
        alertText.add(label, BorderLayout.CENTER);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(alertText, BorderLayout.NORTH);

        add(buttonZone, BorderLayout.SOUTH);
        buttonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        buttonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CrossZeroVisual.end = false;
                CrossZeroVisual.computerPhase = 1;
                Window.fieldReBuilder();

            }
        });
    }

    public void setLabelText(String text) {
        this.label.setText(text);
    }
}
