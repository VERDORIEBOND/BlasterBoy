package com.ictm2c3.blasterboy;

import arduino.*;
import java.util.Scanner;

public class ardu
{

    private static Scanner data;
    private static ardu single_instance = null;
    private final Arduino ardu1;



    // private constructor restricted to this class itself
    private ardu()
    {
        this.ardu1 = new Arduino("COM5");
        this.ardu1.setBaudRate(9600);
    }

    // static method to create instance of Singleton class
    public static ardu getInstance()
    {
        if (single_instance == null)
            single_instance = new ardu();

        return single_instance;
    }

    public boolean connectArduino()
    {
        return this.ardu1.openConnection();
    }

    public int getPotmeter()
    {
        int numberPot = 0;
        this.ardu1.serialWrite('p');
        String answer = this.ardu1.serialRead();

        if(answer.startsWith("potm"))
        {
            numberPot = Integer.parseInt(answer.substring(5));
        }
        return numberPot;
    }
}

