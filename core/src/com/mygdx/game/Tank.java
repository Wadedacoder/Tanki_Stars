package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Tank {
    private final int[] healthpacks = {200, 30, 100};
    private int health;
    private Projectile projectiles;
    private Weapon weapon;
    private int ID;
    private double fuel;
    private Box_factory box;
    private Sprite sprite;
    private float scale = 0.5f;
    private float angle = 0;

    public Tank(int ID,  double fuel, World world, int perID) {
        this.ID = ID;
//        this.velocity = 0;
        this.fuel = 100;
        this.health = healthpacks[ID];
//        this.projectiles = Nuke;
        Texture tank = Assets.game_tanks.get(ID);
        this.sprite = new Sprite(tank);
        String tmpID = "tank" + perID;
        box = new Box_factory(tmpID, world, this.sprite, 0.0f, 0.0f, 0.0f, false, false);

//        body =
        }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void decreaseFuel(int fuel) {
        this.fuel -= fuel;
    }

    public double getFuel() {
        return fuel;
    }

    public Body getBody() {
        return box.getBody();
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            box.getBody().applyForceToCenter(-1, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            box.getBody().applyForceToCenter(1, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            angle = clamp(angle + 0.1f, 0, (float) Math.PI);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            angle = clamp(angle - 0.1f, 0, (float) Math.PI);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            scale = clamp(scale - 0.01f, 0.1f, 1f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            scale = clamp(scale + 0.01f, 0.1f, 1f);
        }
//        System.out.println("Body Position: " + box.getBody().getPosition());
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public void shoot() {
//        System.out.println("scale: " + scale);
//        System.out.println("angle: " + angle);
        scale = 0.5f;
        box.getBody().setLinearVelocity(0, 0);
        angle = 0;
    }

    public float getScale() {
        return scale;
    }

    public float getAngle() {
        return angle;
    }

}
