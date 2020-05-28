package com.ictm2c3.blasterboy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
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
import static com.ictm2c3.blasterboy.EntityType.floor;

public class BBFactory implements EntityFactory {

    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .view(new ScrollingBackgroundView(texture("background.png")))
                .zIndex(-1)
                .build();
    }

    //Wall properties
    @Spawns("L")
    public Entity newLeft(SpawnData data) {
        return FXGL.entityBuilder()
                .type(left)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(TILE_SIZE, TILE_SIZE)))
                .viewWithBBox(texture("left.png",80,80))
                .with(new PhysicsComponent())
                .with (new CollidableComponent(true))
                .build();
    }

    @Spawns("R")
    public Entity newRight(SpawnData data) {
        return FXGL.entityBuilder()
                .type(right)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(TILE_SIZE, TILE_SIZE)))
                .viewWithBBox(texture("right.png",80,80))
                .with(new PhysicsComponent())
                .with (new CollidableComponent(true))
                .build();
    }

    @Spawns("F")
    public Entity newFloor(SpawnData data) {
        return FXGL.entityBuilder()
                .type(floor)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(TILE_SIZE, TILE_SIZE)))
                .viewWithBBox(texture("floor.png",80,80))
                .with(new PhysicsComponent())
                .with (new CollidableComponent(true))
                .build();
    }

    @Spawns("P")
    public Entity newPlatform(SpawnData data) {
        return FXGL.entityBuilder()
                .type(platform)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(TILE_SIZE, TILE_SIZE)))
                .viewWithBBox(texture("platform.png",80,80))
                .with(new PhysicsComponent())
                .with (new CollidableComponent(true))
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
