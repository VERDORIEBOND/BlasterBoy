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
        return this.ardu1.serialRead();
    }

    // private constructor restricted to this class itself
    private ardu()
    {
        this.ardu1 = new Arduino("COM4");
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
        int output = -1;
        if (getAnswer('p').startsWith("ptm"))
        {
            if (getAnswer('p').length()>=3)
            {
                output = Integer.parseInt(getAnswer('p').substring(3).trim());
            }
        }
        return output;
    }

    public boolean getButton()
    {
        boolean returnAnswer = false;
        if (getAnswer('b').startsWith("btn"))
        {
            returnAnswer = getAnswer('b').endsWith("1");
        }

        if (hasJumped && !returnAnswer)
        {
            hasJumped = false;
        }
        else if(!hasJumped && returnAnswer)
        {
            hasJumped = true;
        }
        return hasJumped;
    }
}