package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class Settings extends ScreenAdapter {
    private TankiChads game;

    public Settings(TankiChads game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.setting_background, 0, -1);
        game.batch.end();
    }
}

