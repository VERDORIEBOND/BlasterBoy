package com.ictm2c3.blasterboy;

public class ArduData
{
    double angle;
    boolean jump;
    int currentJumps = 0;
    private static ArduData single_instance = null;

    public static ArduData getInstance()
    {
        if (single_instance == null)
            single_instance = new ArduData();

        return single_instance;
    }

    public boolean isJump() {
        return jump;
    }

    public int getCurrentJumps() {
        return currentJumps;
    }

    public void addCurrentJumps() {
        currentJumps++;
    }
    public void setCurrentJumps(int currentJumps) {
        this.currentJumps = currentJumps;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle / 2.84166666667;
    }
}
