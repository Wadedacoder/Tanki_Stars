package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class LoadGameScreen extends ScreenAdapter {
    private TankiChads game;

    public LoadGameScreen(TankiChads game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.load_background, 0, -1);
        game.batch.end();
    }
}

