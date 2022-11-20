package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class MainMenu extends ScreenAdapter {
    TankiChads game;
    Stage stage;
    Skin skin;
    TextButton play_button;
    TextButton Load_button;
    Table table;


    public MainMenu(TankiChads game) {
        this.game = game;
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
//        textButtonStyle = skin.getFont("default-font");
        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()/2);
        play_button = new TextButton("Play!", skin, "default");
        play_button.setWidth(200f);
        play_button.setHeight(20f);
        play_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectGameScreen((TankiChads) Gdx.app.getApplicationListener()));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                play_button.setStyle(skin.get("color", TextButton.TextButtonStyle.class));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                play_button.setStyle(skin.get("default", TextButton.TextButtonStyle.class));
            }
        });

        Load_button = new TextButton("Load!", skin, "default");
        Load_button.setWidth(200f);
        Load_button.setHeight(20f);
//        Load_button.setPosition(Gdx.graphics.getWidth() / 2 - Load_button.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Load_button.getHeight() / 2);
        Load_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectGameScreen((TankiChads) Gdx.app.getApplicationListener()));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                Load_button.setStyle(skin.get("color", TextButton.TextButtonStyle.class));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                Load_button.setStyle(skin.get("default", TextButton.TextButtonStyle.class));
            }
        });
//        table.setFillParent(true);
        table.add(play_button).width(200f).height(20f).padRight(10f);
        table.add(Load_button).width(200f).height(20f).padLeft(10f);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        game.batch.begin();
        game.batch.draw(Assets.Background, 0, -1);
        game.batch.end();
        stage.draw();
    }
}
