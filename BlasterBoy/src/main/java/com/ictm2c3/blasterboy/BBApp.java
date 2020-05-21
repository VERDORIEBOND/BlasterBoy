package com.ictm2c3.blasterboy;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.ui.FontType;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BBApp extends GameApplication {

    public static final int TILE_SIZE = 80;

    private Entity player;
    private Entity gun;
    private PlayerComponent playerComponent;
    private GunComponent gunComponent;
    private final int speed = 100;
    Text ammoLeft = new Text();

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
        gameSettings.setVersion("0.1");
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
                initUI();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initUI()
    {
            super.initUI();
    }

    public void addExampleFontUI(int x, int y, int ammo)
    {
        Font fontUI = getUIFactoryService().newFont(FontType.UI, 14.0);

        Text text = new Text("This is UI_FONT: Ammo " + ( 2 - ammo));
        text.setFont(fontUI);

        addUINode(text, x, y);
    }

    public void changeAmmo(int ammo)
    {
        ammoLeft.setText("Ammo: " + (2 - ammo));
    }

    @Override
    protected void initGame() {
        //Initializes the EntityFactory
        getGameWorld().addEntityFactory(new BBFactory());

        //Spawns in the level
        Level level = getAssetLoader().loadLevel("0.txt", new TextLevelLoader(80, 80, '0'));
        getGameWorld().setLevel(level);
        //Spawns in background
        getGameWorld().spawn("BG");

        //Spawns in player
        player = spawn("Player", getAppWidth() / 2 - 40, getAppHeight() - 160);
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
    }

    public void startGame()
    {
        String[] args = new String[] {""};
        launch(args);
    }

    public void changeSpeed()
    {
       int speed = ardu.getInstance().getPotmeter() / 2046;
        System.out.println(speed);

    }
}