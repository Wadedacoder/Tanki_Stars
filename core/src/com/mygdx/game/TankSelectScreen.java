package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import java.util.ArrayList;

public class TankSelectScreen extends ScreenAdapter {
    private TankiChads game;
    private int gameMode;

    private ArrayList<ImageButton> tanks;


    TankSelectScreen(TankiChads game, int gameMode) {
        this.game = game;
        this.gameMode = gameMode;
        System.out.println(gameMode);
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.Blank_background, 0, -1);
        game.batch.end();
    }

}
