package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameScreen extends ScreenAdapter {
    public static final float WORLD_TO_BOX = 0.01f;
    private final TankiChads game;
    private static Tank t1;
    private static Tank t2;
    private Body ground;
    private World world;
    private Boolean currentTurn; // true = t1, false = t2
    private float shootTimer = 0;
    private Stage stage;
    private static Projectile projectiles;

    private TextArea textArea;
    private TextButton pauseButton;
    private boolean paused = false;

    public GameScreen(TankiChads game, int id1, int id2) {
        this.game = game;
        stage = new Stage();
        textArea = new TextArea("Hello", Assets.getTextStyle(1,1,1,1));
        textArea.setPosition(Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-150);
        textArea.setSize(100, 100);
        stage.addActor(textArea);
        world = new World(new Vector2(0f, -490f * WORLD_TO_BOX), true);
        this.world.setContactListener(new MyCollisionDetection());
        System.out.println(World.getVelocityThreshold());
        t1 = new Tank(id1,  100, world,1);
        t1.getBody().setTransform(100* WORLD_TO_BOX, 30 * WORLD_TO_BOX, 0);
        t2 = new Tank(id2, 100, world,2);
        t2.getBody().setTransform(800 * WORLD_TO_BOX, 30 * WORLD_TO_BOX, 0);
        t2.getSprite().flip(true, false);
        t1.getBody().setLinearDamping(1f);
        t2.getBody().setLinearDamping(1f);
        System.out.println(t1.getBody().getPosition());
        System.out.println(t2.getBody().getPosition());
        Body ground = createGround();
        Box_factory wall_left = new Box_factory("ground", world, -50 * WORLD_TO_BOX, 0, 1, 1000, 0, 0, 0, true, false);
        Box_factory wall_right = new Box_factory("ground", world, (Gdx.graphics.getWidth())*WORLD_TO_BOX, 0, 1, 1000, 0, 0, 0, true, false);
        projectiles = null;
//        projectiles.add(new Projectile(1, 100, world, Gdx.graphics.getWidth()/2, 50,90, 0));
        currentTurn = true;
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Assets.font;
        pauseButton = new TextButton("Pause", textButtonStyle);
        pauseButton.setPosition(0, Gdx.graphics.getHeight()-50);
        pauseButton.setSize(100, 50);
        stage.addActor(pauseButton);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                paused = !paused;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (paused){
            delta = 0;
        }
        if(projectiles == null) {
//            System.out.println("projectile");
            update(delta);
        }
        world.step(delta, 6, 2);
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
//        world.step(60f, 6, 2);
        t1.getSprite().setPosition(t1.getBody().getPosition().x / WORLD_TO_BOX, t1.getBody().getPosition().y/ WORLD_TO_BOX);
        t2.getSprite().setPosition(t2.getBody().getPosition().x  / WORLD_TO_BOX, t2.getBody().getPosition().y  / WORLD_TO_BOX);
        if(projectiles != null) {
            projectiles.getSprite().setPosition(projectiles.getBody().getPosition().x / WORLD_TO_BOX, projectiles.getBody().getPosition().y / WORLD_TO_BOX);
        }
//        projectiles.getSprite().setPosition(projectiles.getBody().getPosition().x / WORLD_TO_BOX, projectiles.getBody().getPosition().y / WORLD_TO_BOX);
        stage.act( Gdx.graphics.getDeltaTime() );

        game.batch.begin();
        game.batch.draw(Assets.background, 0, -1);
        game.batch.draw(t1.getSprite(), t1.getSprite().getX(), t1.getSprite().getY());
        game.batch.draw(t2.getSprite(), t2.getSprite().getX(), t2.getSprite().getY());
        if(projectiles != null) {
            game.batch.draw(projectiles.getSprite(), projectiles.getSprite().getX(), projectiles.getSprite().getY());
        }
//        game.batch.draw(projectiles.getSprite(), projectiles.getSprite().getX(), projectiles.getSprite().getY());
//        game.batch.draw(Assets.screen_Game, 0, -1, 1280, 720);

        game.batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        world.dispose();
    }

    private void update(float delta) {
        if(delta == 0){
            return;
        }
        if(t1.getHealth() <= 0){
            game.setScreen(new GameOverScreen(game, 2));
        }
        if(t2.getHealth() <= 0){
            game.setScreen(new GameOverScreen(game, 1));
        }
        shootTimer += delta;
        if (currentTurn) {
            t1.update();
        } else {
            t2.update();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer > 0.5f) {
            shootTimer = 0;
            System.out.println("Space pressed");
            if (currentTurn) {
                projectiles = (new Projectile(30,10,world, t1.getBody().getPosition().x , t1.getBody().getPosition().y + t1.getSprite().getHeight() * WORLD_TO_BOX, t1.getAngle(), t1.getScale()));
                t1.shoot();

            } else {
                projectiles = (new Projectile(30,10,world, t2.getBody().getPosition().x +t2.getSprite().getWidth() * WORLD_TO_BOX, t2.getBody().getPosition().y + t2.getSprite().getHeight() * WORLD_TO_BOX, t2.getAngle(), t2.getScale()));
                t2.shoot();
            }
            currentTurn = !currentTurn;
        }
        StringBuilder sb = new StringBuilder();
        if(currentTurn){
//            StringBuilder sb = new StringBuilder();
            sb.append("T1: turn\n").append("Angle: ").append((int) (Math.toDegrees(t1.getAngle()))).append("\nPower: ").append(Math.round(100*t1.getScale()));
        }
        else{
            sb.append("T2: turn\n").append("Angle: ").append((int) (Math.toDegrees(t2.getAngle()))).append("\nPower: ").append(Math.round(100*t2.getScale()));
        }
        //Add T1 and T2 health
        sb.append("\nT1 HP: ").append(t1.getHealth()).append("\nT2 HP: ").append(t2.getHealth());
        textArea.setText(sb.toString());
    }

    private Body createGround(){
        Box_factory box_factory = new Box_factory("ground",world, 0,0, Gdx.graphics.getWidth()*2*WORLD_TO_BOX, 2*WORLD_TO_BOX,0,0.8f,0.0f,true,false);
        return box_factory.getBody();
    }

    public static void clearProjectiles(){
        projectiles = null;
    }

    public static Tank getTank1(){
        return t1;
    }

    public static Tank getTank2(){
        return t2;
    }
}

