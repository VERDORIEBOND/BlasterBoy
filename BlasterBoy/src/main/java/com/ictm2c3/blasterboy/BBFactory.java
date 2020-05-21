package com.ictm2c3.blasterboy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.ictm2c3.blasterboy.BBApp.TILE_SIZE;
import static com.ictm2c3.blasterboy.EntityType.*;

public class BBFactory implements EntityFactory {

    //Background properties
    @Spawns("BG")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .at(0, 0)
                .view(new Rectangle(1200, 1200, Color.LIGHTBLUE))
                .zIndex(-1)
                .build();
    }

    //Wall properties
    @Spawns("w")
    public Entity newWall(SpawnData data) {
        return FXGL.entityBuilder()
                .type(wall)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(80, 80)))
                .view(new Rectangle(80, 80, Color.GRAY.saturate()))
                .with(new PhysicsComponent())

                .build();
    }
    //Player properties
    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.addGroundSensor((new HitBox("GROUND_SENSOR", new Point2D(10, 80), BoundingShape.box(50, -10))));
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(1.0f));

        return FXGL.entityBuilder()
                .type(player)
                .from(data)
                .viewWithBBox(texture("player.png",80,80))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }
    @Spawns("Gun")
    public Entity newGun(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder()
                .type(gun)
                .from(data)
                .viewWithBBox(texture("gun.png",80,80))
                .with(new GunComponent())
                .build();
    }



}
