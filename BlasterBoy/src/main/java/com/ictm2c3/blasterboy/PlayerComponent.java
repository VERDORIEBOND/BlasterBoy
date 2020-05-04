package com.ictm2c3.blasterboy;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    public void moveLeft() {
        physics.setVelocityX(-100);
    }

    public void moveRight() {
        physics.setVelocityX(100);
    }

    public void moveUp() {
        physics.setVelocityY(-250);
    }

}

