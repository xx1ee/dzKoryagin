package com.xx1ee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelChangeExample extends JFrame {
    private JLabel label;
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel("Начало");
    private JLabel label3 = new JLabel("Конец");
    private JLabel label4 = new JLabel();
    private JTextField jTextField = new JTextField();
    private JTextField jTextField1 = new JTextField();
    private JTextField jTextField2 = new JTextField();
    private WordsConverter germanConverter;

    public LabelChangeExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Введите фразу");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        jTextField.setColumns(35);
        jTextField1.setColumns(3);
        jTextField2.setColumns(3);
        jTextField.setFont(new Font("Arial", Font.PLAIN, 25));
        jTextField1.setFont(new Font("Arial", Font.PLAIN, 25));
        jTextField2.setFont(new Font("Arial", Font.PLAIN, 25));
        add(label);
        add(jTextField);
        add(label2);
        add(jTextField1);
        add(label3);
        add(jTextField2);
        JButton button = new JButton("OK");
        label4.setVerticalAlignment(SwingConstants.CENTER);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Arial", Font.PLAIN, 30));
        label1.setFont(new Font("Arial", Font.PLAIN, 30));
        add(label1);
        add(label4);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                germanConverter = new WordsConverter(jTextField.getText(), Integer.parseInt(jTextField1.getText()),
                        Integer.parseInt(jTextField2.getText()));

                label4.setText(germanConverter.convert());
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