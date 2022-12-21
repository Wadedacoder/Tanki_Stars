package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Projectile{
    private int damage;
    private float AOE_range;
    private Box_factory box;
    private Sprite sprite;
    private boolean hascollided;
    private float scale;
    private float angle;
    public Projectile(int damage, float AOE_range, World world, float x, float y, float angle, float scale) {
        this.damage = damage;
        this.AOE_range = AOE_range;
        this.sprite = new Sprite(Assets.nuke);
        this.sprite.setPosition(x, y);
        this.scale = scale;
        this.angle = angle;
        box = new Box_factory("projectile", world, x,y, sprite.getWidth()*GameScreen.WORLD_TO_BOX, sprite.getHeight()*GameScreen.WORLD_TO_BOX,1.0f, 0.0f, 1.0f, false, false);
        fired();
    }

    public void hit() {
        System.out.println("hit");
    }
    public void miss() {
        hascollided = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Body getBody() {
        return box.getBody();
    }

    public void fired(){
        box.getBody().setLinearVelocity((float) (Math.cos(angle) * 8*scale), (float) (Math.sin(angle) * 8)*scale);
        System.out.println(Math.sin(angle) * 8*scale);
    }

}


//class CannonBall extends Projectile {
//
//}
//
//class MortarShell extends Projectile {
//
//}
//
//class Nuke extends Projectile {
//
//}
//
//class Laser extends Projectile {
//    @Override
//    public void gravity() {
//        // deez nuts
//    }
//}
//
//class Shock extends Projectile {
//    @Override
//    public void gravity() {
//        // don't do it!
//    }
//}