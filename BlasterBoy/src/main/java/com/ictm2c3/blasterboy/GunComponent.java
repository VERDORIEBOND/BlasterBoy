package com.ictm2c3.blasterboy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.ictm2c3.blasterboy.BBApp.*;

public class GunComponent extends Component {

    @Override
    public void onUpdate(double tpf)
    {
        moveGun();
    }

    private void moveGun()
    {
        //Checks player location
        var player = FXGL.getGameWorld().getSingleton(EntityType.player);
        double gunX = player.getX();
        double gunY = player.getY();
        //Moves gun
        entity.setX(gunX);
        entity.setY(gunY);
        //Handles rotation
        //System.out.println(ArduData.getInstance().getAngle());
        entity.setRotation(ArduData.getInstance().getAngle() + 180);
    }
}