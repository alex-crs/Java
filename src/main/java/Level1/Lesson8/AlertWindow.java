package Level1.Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                CrossZeroVisual.end = false;
                CrossZeroVisual.turnPC=false;
                CrossZeroVisual.computerPhase = 1;
                Window.fieldReBuilder();
                dispose();
            }
        });
    }

    public void setLabelText(String text) {
        this.label.setText(text);
    }
}
