package com.ictm2c3.blasterboy;

import arduino.*;
import java.util.Scanner;
import static java.lang.Boolean.parseBoolean;

public class ardu
{

    private static Scanner data;
    private static ardu single_instance = null;
    private final Arduino ardu1;
    private boolean hasJumped = false;

    public String getAnswer(char type)
    {
        this.ardu1.serialWrite(type);
        String answer = this.ardu1.serialRead();
        String output = "";
        if (type == 'b' && answer.startsWith("btn"))
        {
            output = answer;
        }
        else if (type == 'p' && answer.startsWith("ptm"))
        {
            output = answer;
        }
        return output;
    }

    // private constructor restricted to this class itself
    private ardu()
    {
        this.ardu1 = new Arduino("COM3");
        this.ardu1.setBaudRate(2400);
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
        int output = -1;
        String answer = getAnswer('p');
            if (answer.length()>=3)
            {
                output = Integer.parseInt(answer.substring(3).trim());
            }
        return output;
    }

    public boolean getButton()
    {
        String arduAnswer = getAnswer('b');
        boolean output = false;
        if (Integer.parseInt(arduAnswer.substring(3).trim()) == 1)
        {
            output = true;
        }
        return output;
    }
}