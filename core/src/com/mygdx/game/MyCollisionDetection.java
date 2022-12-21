package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.sun.org.apache.xpath.internal.functions.FuncQname;

public class MyCollisionDetection implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        // TODO Auto-generated method stub
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fb.getUserData() != null) {
            System.out.println("Collision detected");
            System.out.println(getID(fa));
            System.out.println(getID(fb));
            if (getID(fa).equals("projectile") && getID(fb).equals("tank1")) {
                System.out.println("Projectile hit tank");
                Body tank = fb.getBody();
                Tank tank1 = GameScreen.getTank1();
                tank1.decreaseHealth(30);
                Body projectile = fa.getBody();
                tank.applyLinearImpulse(projectile.getLinearVelocity().x * 0.1f,projectile.getLinearVelocity().y * -0.1f , tank.getPosition().x, tank.getPosition().y, true);
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }
            if (getID(fa).equals("tank1") && getID(fb).equals("projectile")) {
                System.out.println("Projectile hit tank");
                Body tank = fa.getBody();
                Tank tank1 = GameScreen.getTank1();
                tank1.decreaseHealth(30);
                Body projectile = fb.getBody();
                tank.applyLinearImpulse(projectile.getLinearVelocity().x * 0.1f,projectile.getLinearVelocity().y * -0.1f , tank.getPosition().x, tank.getPosition().y, true);
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }
            if (getID(fa).equals("projectile") && getID(fb).equals("tank2")) {
                System.out.println("Projectile hit tank");
                Body tank = fb.getBody();
                Body projectile = fa.getBody();
                Tank tank2 = GameScreen.getTank2();
                tank2.decreaseHealth(30);
                tank.applyLinearImpulse(projectile.getLinearVelocity().x * 0.1f,projectile.getLinearVelocity().y * -0.1f , tank.getPosition().x, tank.getPosition().y, true);
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }
            if (getID(fa).equals("tank2") && getID(fb).equals("projectile")) {
                System.out.println("Projectile hit tank");
                Body tank = fa.getBody();
                Body projectile = fb.getBody();
                Tank tank2 = GameScreen.getTank2();
                tank2.decreaseHealth(30);
                tank.applyLinearImpulse(projectile.getLinearVelocity().x * 0.1f,projectile.getLinearVelocity().y * -0.1f , tank.getPosition().x, tank.getPosition().y, true);
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fb.getUserData() != null) {
//            System.out.println("Collision detected");
            if (getID(fa).equals(("tank1")) && getID(fb).equals(("tank2"))) {
                contact.setEnabled(false);
            }
            if (getID(fa).equals(("tank2")) && getID(fb).equals(("tank1"))) {
                contact.setEnabled(false);
            }
            if (getID(fa).equals("projectile") && getID(fb).equals("ground")) {
                System.out.println("Projectile hit ground");
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }
            if (getID(fa).equals("ground") && getID(fb).equals("projectile")) {
                System.out.println("ground hit projectile");
//                GameScreen.clearProjectiles();
                contact.setEnabled(false);
                GameScreen.clearProjectiles();
            }

        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub

    }

    private String getID(Fixture fixture) {
        return ((Box_factory) fixture.getUserData()).getId();
    }
}
