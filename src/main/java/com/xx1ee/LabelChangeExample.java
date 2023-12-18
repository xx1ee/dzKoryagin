package com.xx1ee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelChangeExample extends JFrame {
    private JLabel label;
    private JLabel label1 = new JLabel();
    private JTextField jTextField = new JTextField();
    private GermanConverter2 germanConverter;

    public LabelChangeExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Введите слово на немецком");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        jTextField.setColumns(25);
        jTextField.setFont(new Font("Arial", Font.PLAIN, 30));
        add(label);
        add(jTextField);
        JButton button = new JButton("OK");
        label1.setFont(new Font("Arial", Font.PLAIN, 30));
        add(label1);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                germanConverter = new GermanConverter2(jTextField.getText());
                label1.setText(germanConverter.ApplyButton_Click());
            }
        });
        add(button);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LabelChangeExample();
            }
        });
    }
}
