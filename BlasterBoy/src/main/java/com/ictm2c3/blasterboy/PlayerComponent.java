package com.ictm2c3.blasterboy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;


public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    public void moveLeft(int speed)
    {
        physics.setVelocityX(speed * -1);
    }

    public void moveRight(int speed)
    {
        physics.setVelocityX(speed);
    }

    public void moveUp(int speed)
    {
        physics.setVelocityY(speed * -1);
    }



}

