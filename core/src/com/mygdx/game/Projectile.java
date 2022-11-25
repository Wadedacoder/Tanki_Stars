package com.mygdx.game;

public class Projectile implements ProjectileMotion{
    int damage;


    @Override
    public void gravity() {
        System.out.println("Your mom");
    }
}

class CannonBall extends Projectile {

}

class MortarShell extends Projectile {

}

class Nuke extends Projectile {

}

class Laser extends Projectile {
    @Override
    public void gravity() {
        // deez nuts
    }
}

class Shock extends Projectile {
    @Override
    public void gravity() {
        // don't do it!
    }
}