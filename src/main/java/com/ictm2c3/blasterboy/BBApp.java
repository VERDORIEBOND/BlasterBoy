package com.ictm2c3.blasterboy;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Orientation;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BBApp extends GameApplication {

    public static final int TILE_SIZE = 80;

    private Entity player;
    private Entity gun;
    private PlayerComponent playerComponent;
    private GunComponent gunComponent;
    private final int speed = 100;

    private static BBApp single_instance = null;


    public static void main(String[] args)
    {
        mainMenuInterface mainMenu = new mainMenuInterface();
        mainMenu.startMenu();
    }

    public static BBApp getInstance()
    {
        if (single_instance == null)
            single_instance = new BBApp();

        return single_instance;
    }

    @Override
    protected void initSettings(GameSettings gameSettings)
    {
        gameSettings.setWidth(1200);
        gameSettings.setHeight(1200);
        gameSettings.setTitle("BlasterBoy");
        gameSettings.setVersion("1.0");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars)
    {
        vars.put("Ammo", 2);
    }

    public Entity getPlayer() {
        return player;
    }


    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Launch") {
            @Override
            protected void onActionBegin()
            {
                player.getComponent(PlayerComponent.class).launch();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initUI() {
        Text uiAmmo = new Text("Ammo: ");
        uiAmmo.setFont(Font.font(50));
        uiAmmo.setTranslateX(getAppWidth() / 2 - 125);
        uiAmmo.setTranslateY(60);
        uiAmmo.textProperty().bind(getip("Ammo").asString("Ammo: %d"));
        addUINode(uiAmmo);
    }


    @Override
    protected void initGame() {
        //Initializes the EntityFactory
        getGameWorld().addEntityFactory(new BBFactory());

        //Spawns in the level
        Level level = getAssetLoader().loadLevel("0.txt", new TextLevelLoader(80, 80, '0'));
        getGameWorld().setLevel(level);
        //Spawns in background
        getGameWorld().spawn("background");;

        //Spawns in player
        player = spawn("Player", 560, 5500);
        playerComponent = player.getComponent(PlayerComponent.class);

        Thread.UncaughtExceptionHandler h = (th, ex) -> System.out.println("Uncaught exception: " + ex);

        Thread t = new Thread(() -> {
            while (true) {
                double angleRaw = ardu.getInstance().getPotmeter();
                if (angleRaw >= 0)
                {
                    ArduData.getInstance().setAngle(angleRaw);
                } else {
                    System.out.println("potmeter is invalid: " + angleRaw);

                }
                //Button
                ArduData.getInstance().setJump(ardu.getInstance().getButton());
            }
        });
        t.setUncaughtExceptionHandler(h);
        t.start();


        //Spawns in gun
        gun = spawn("Gun",player.getX(),player.getY());
        gunComponent = gun.getComponent(GunComponent.class);

        //Camera
        getGameScene().getViewport().setBounds(0, 0, 1200, 5600);
        getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
        getGameScene().getViewport().setLazy(true);
    }

    public void startGame()
    {
        String[] args = new String[] {""};

        launch(args);
    }
}