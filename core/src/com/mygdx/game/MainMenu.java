package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class MainMenu extends ScreenAdapter {
    private TankiChads game;
    private Stage stage;
    private Skin skin;
    private ImageButton play_button;
    private ImageButton load_button;

    private ImageButton exit_button;
    private ImageButton settings_button;

    final float[] factor_1 = {1f};


    public MainMenu(TankiChads game) {
        this.game = game;
        stage = new Stage();


        //play button
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(Assets.new_Game);
        play_button = new ImageButton(style);
        play_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Play button clicked");
//                ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectGameScreen((TankiChads) Gdx.app.getApplicationListener()));
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectGameScreen((TankiChads) Gdx.app.getApplicationListener()));
            }
        });

        // Load button
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.up = new TextureRegionDrawable(Assets.load_Game);
        load_button = new ImageButton(style1);
        load_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LoadGameScreen((TankiChads) Gdx.app.getApplicationListener()));
            }

        });

        //Creating a table for buttons
        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()/2+100);
        table.add(play_button).width(328).height(216).padRight(7f);
        table.add(load_button).width(328).height(216).padLeft(7f);

        //adding exit button
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.up = new TextureRegionDrawable(Assets.exit_Game);
        exit_button = new ImageButton(style2);
        exit_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        exit_button.setPosition(Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() - 30, Align.bottomLeft);
        exit_button.setSize(20, 20);

        //adding settings button
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.up = new TextureRegionDrawable(Assets.settings_Game);
        settings_button = new ImageButton(style3);
        settings_button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Settings((TankiChads) Gdx.app.getApplicationListener()));
            }
        });
        settings_button.setPosition(10, Gdx.graphics.getHeight() - 30);
        settings_button.setSize(20, 20);

        //Adding the table of buttons to the stage
        stage.addActor(table);
        stage.addActor(exit_button);
        stage.addActor(settings_button);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        game.batch.begin();
        game.batch.draw(Assets.background, 0, -1);
        game.batch.end();
        stage.draw();
    }
}
