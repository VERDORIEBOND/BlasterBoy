package com.ictm2c3.blasterboy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static java.lang.Math.*;


public class PlayerComponent extends Component {

    private PhysicsComponent physics;
    private double speed = -550;

    @Override
    public void onUpdate(double tpf)
    {
        if (physics.isOnGround())
        {
            ArduData.getInstance().setCurrentJumps(0);
        }
    }

    public void launch() {
        //LaunchMath();

        double angle = ArduData.getInstance().getAngle();

        double x = speed * Math.cos(angle/180 * 3.14);
        double y = speed * Math.sin(angle/180 * 3.14);

        physics.setVelocityX(x);
        physics.setVelocityY(y);
    }
}

