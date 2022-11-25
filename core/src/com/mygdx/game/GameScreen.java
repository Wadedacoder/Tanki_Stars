package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    private TankiChads game;
    private Tank t1;
    private Tank t2;

    public GameScreen(TankiChads game) {
        this.game = game;
        t1 = new Tank();
        t2  = new Tank();
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.background, 0, -1);
        game.batch.end();
    }
}

