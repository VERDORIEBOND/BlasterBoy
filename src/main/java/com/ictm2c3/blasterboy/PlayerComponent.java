package com.ictm2c3.blasterboy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import static com.almasb.fxgl.dsl.FXGL.set;
import static java.lang.Math.*;


public class PlayerComponent extends Component {

    private PhysicsComponent physics;
    private double speed = -550;
    private static final int DURATION = 200;
    private long activatedAt = Long.MAX_VALUE;

    public void activate() {
        activatedAt = System.currentTimeMillis();
    }

    public boolean isActive() {
        long activeFor = System.currentTimeMillis() - activatedAt;

        return activeFor >= 0 && activeFor <= DURATION;
    }

    @Override
    public void onUpdate(double tpf)
    {

        if (!isActive() && physics.isOnGround())
        {
            System.out.println("test");
            ArduData.getInstance().setCurrentJumps(0);
            activate();
            set("Ammo", 2);
        }
        else if (physics.isOnGround())
        {
            activate();
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

