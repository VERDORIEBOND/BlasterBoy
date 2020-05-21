package com.ictm2c3.blasterboy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenuInterface extends BBApp
{
    private JPanel mainPannel;
    private JButton exitButton;
    private JButton startButton;
    private JLabel startField;
    private static JFrame frame = new JFrame("MainInput");

    public mainMenuInterface() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(ardu.getInstance().connectArduino())
                {
                    mainMenuInterface.super.startGame();
                    System.out.println("The arduino is connected");
                    frame.remove(frame);
                }
                else {
                    System.out.println("You need to connect an Arduino first");
                    startField.updateUI();
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }




    public void startMenu()
    {
        frame.setContentPane(new mainMenuInterface().mainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}