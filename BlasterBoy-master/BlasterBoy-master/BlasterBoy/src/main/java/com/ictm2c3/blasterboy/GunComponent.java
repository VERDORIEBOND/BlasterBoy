package com.ictm2c3.blasterboy;

import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;


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
        entity.setRotation(ArduData.getInstance().getAngle() + 180);

        if (ArduData.getInstance().isJump() && ArduData.getInstance().getCurrentJumps() <= 1)
        {
            getAudioPlayer().playSound(getAssetLoader().loadSound("shotgun" + new Random().nextInt(3) + ".wav"));
            player.getComponent(PlayerComponent.class).launch();
            ArduData.getInstance().setJump(false);
            ArduData.getInstance().setCurrentJumps(ArduData.getInstance().getCurrentJumps() + 1);
            set("Ammo", 2 - ArduData.getInstance().getCurrentJumps());
        }
    }
}