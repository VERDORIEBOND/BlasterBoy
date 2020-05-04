package com.ictm2c3.blasterboy;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import com.ictm2c3.blasterboy.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BBApp extends GameApplication {

    public static final int TILE_SIZE = 39;

    private Entity player;
    private Entity gun;
    private PlayerComponent playerComponent;
    private GunComponent gunComponent;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("BlasterBoy");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("moveLeft") {
            @Override
            protected void onAction() {
                playerComponent.moveLeft();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("moveRight") {
            @Override
            protected void onAction() {
                playerComponent.moveRight();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("moveUp") {
            @Override
            protected void onAction() {
                playerComponent.moveUp();
            }
        }, KeyCode.SPACE);
    }


    @Override
    protected void initGame() {
        //Initializes the EntityFactory
        getGameWorld().addEntityFactory(new BBFactory());


        //Spawns in the level
        Level level = getAssetLoader().loadLevel("0.txt", new TextLevelLoader(40, 40, '0'));
        getGameWorld().setLevel(level);
        //Spawns in background
        getGameWorld().spawn("BG");

        //Spawns in player
        player = spawn("Player", getAppWidth() / 2 - 20, getAppHeight() - 80);
        playerComponent = player.getComponent(PlayerComponent.class);

        //Spawns in gun
        gun = spawn("gun",player.getX(),player.getY());
        gunComponent = gun.getComponent(GunComponent.class);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
