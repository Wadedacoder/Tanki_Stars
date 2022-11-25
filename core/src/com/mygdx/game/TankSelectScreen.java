package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class TankSelectScreen extends ScreenAdapter {
    private TankiChads game;
    private int gameMode;

    private ArrayList<ImageButton> tanks;
    private Stage stage;


    TankSelectScreen(TankiChads game, int gameMode) {
        this.game = game;
        this.gameMode = gameMode;
        stage = new Stage();
        this.tanks = new ArrayList<ImageButton>();
        System.out.println(gameMode);
        for(int i = 0; i < 4; i++) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.up = new TextureRegionDrawable(Assets.tanks.get(i));
            tanks.add(new ImageButton(style));
            tanks.get((i)).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen((TankiChads) Gdx.app.getApplicationListener()));
                }
            });
        }
        tanks.get(0).setPosition(683.51f,223.5f);
        tanks.get(0).setSize(326,194);
        tanks.get(1).setPosition(855,24);
        tanks.get(1).setSize(372,199);
        tanks.get(2).setPosition(367, 61);
        tanks.get(2).setSize(398,205);
        tanks.get(3).setPosition(53, 153);
        tanks.get(3).setSize(288,207);

        for(int i = 0; i < 4; i++) {
            stage.addActor(tanks.get(i));
        }

        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        stage.act();
        game.batch.begin();
        game.batch.draw(Assets.Blank_background, 0, -1);
        game.batch.draw(Assets.select_tank, Gdx.graphics.getWidth() / 2 - Assets.select_tank.getWidth() / 2, Gdx.graphics.getHeight()  - Assets.select_tank.getHeight(), Assets.select_tank.getWidth(), Assets.select_tank.getHeight());
        game.batch.end();
        stage.draw();
    }

}
