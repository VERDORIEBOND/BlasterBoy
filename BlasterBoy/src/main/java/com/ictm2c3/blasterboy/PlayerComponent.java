package com.ictm2c3.blasterboy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static java.lang.Math.*;


public class PlayerComponent extends Component {

    private PhysicsComponent physics;
    private double speed = -550;


    public void launch() {
        //LaunchMath();

        double angle = ArduData.getInstance().getAngle();

        double x = speed * Math.cos(angle/180 * 3.14);
        double y = speed * Math.sin(angle/180 * 3.14);

        physics.setVelocityX(x);
        physics.setVelocityY(y);
        /*
        System.out.println("X = " + x);
        System.out.println("Y = " + y);
        System.out.println(angle/180 * 3.14);
        System.out.println(angle);
         */
    }
}

