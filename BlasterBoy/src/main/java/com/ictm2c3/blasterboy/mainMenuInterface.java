package com.ictm2c3.blasterboy;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class mainMenuInterface
{
    private static Scanner data;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton refreshButton;
    private JButton confirmButton;
    private JPanel mainPannel;
    private JLabel label1;
    private JButton exitButton;
    private JButton startButton;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JLabel startField;
    private int chosenPort;
    private SerialPort mainPort;
    private static JFrame frame = new JFrame("MainInput");
    private static boolean arduConnected = false;

    public mainMenuInterface() {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getPort();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openPort();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(arduConnected)
                {
                    String[] arguments = new String[] {""};
                    BBApp.main(arguments);
                }
                else {
                    startField.setText("You need to connect an Arduino first");
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
    public void openPort()
    {
        SerialPort ports[] = SerialPort.getCommPorts();
        chosenPort = comboBox2.getSelectedIndex();

        SerialPort port = ports[chosenPort];
        if(port.openPort())
        {
            label1.setText("Succesfully opened the port, you can close this dialog.");
            arduConnected = true;
        } else {
            label1.setText("Failed to open port, did you already open it?");
        }
        label1.updateUI();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        mainPort = port;
        data = new Scanner(mainPort.getInputStream());
    }
    public void getPort()
    {
        SerialPort ports[] = SerialPort.getCommPorts();

        for(SerialPort port : ports)
        {
            comboBox2.addItem(port.getSystemPortName());
        }
        comboBox2.updateUI();
    }

    public static void main(String[] args)
    {
        SerialPort ports[] = SerialPort.getCommPorts();

        frame.setContentPane(new mainMenuInterface().mainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        while(data.hasNextLine())
        {
            int numberPot = 0;
            int numberBut1 = 0;
            String answer = "";
            try
            {
                answer = data.nextLine();
            }catch (Exception e) {}

            if(answer.substring(0, 4).equals("btn1"))
            {
                numberBut1 = Integer.parseInt(answer.substring(5));
                if (numberBut1 == 1)
                {
                    //knop 1 ingedrukt
                }
                else {
                    //knop 1 niet ingedrukt
                }
            }
            if(answer.substring(0, 4).equals("potm"))
            {
                numberPot = Integer.parseInt(answer.substring(5));
                //numberpod is de waarde van podmeter van 0 tot 1023
            }
        }

    }
}