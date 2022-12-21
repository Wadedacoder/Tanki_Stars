package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Box_factory {
    private String id;
    private Body body;

    public Box_factory(String id, World world, Sprite sprite, float density, float friction, float restitution, boolean isStatic, boolean isSensor) {
        this(id, world, sprite.getX()*GameScreen.WORLD_TO_BOX, sprite.getY()*GameScreen.WORLD_TO_BOX, sprite.getWidth()*GameScreen.WORLD_TO_BOX, sprite.getHeight()*GameScreen.WORLD_TO_BOX, density, friction, restitution, isStatic, isSensor);
    }

    public Box_factory(String id, World world, float x, float y, float width, float height, float density, float friction, float restitution, boolean isStatic, boolean isSensor) {
        this.id = id;
        CreateBox(world, x, y, width, height, density, friction, restitution, isStatic, isSensor);
    }

    private void CreateBox(World world, float x, float y , float width, float height, float density, float friction, float restitution, boolean isStatic, boolean isSensor) {
        BodyDef bodyDef = new BodyDef();
        if (isStatic) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
            System.out.println("Static body created");
        } else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        this.body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        this.body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
//        return body;
    }


    public Body getBody() {
        return body;
    }

    public void hit() {
        System.out.println(id + "hit");
    }

    public String getId() {
        return id;
    }
}
