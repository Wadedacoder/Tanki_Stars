package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

public class InProgressScreen extends ScreenAdapter {
    private TankiChads game;

    public InProgressScreen(TankiChads game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.inprogress_background, 0, -1);
        game.batch.end();
    }
}

