package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    private TankiChads game;

    public GameScreen(TankiChads game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.background, 0, -1);
        game.batch.end();
    }
}

