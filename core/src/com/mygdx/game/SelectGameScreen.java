package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class SelectGameScreen extends ScreenAdapter {
    private TankiChads game;

    private Stage stage;
    private ArrayList<ImageButton> gameMode;
    private ImageButton back;

    public SelectGameScreen(TankiChads game) {
        this.game = game;
        stage = new Stage();
        // Making a list of game modes to choose from
        ArrayList<ImageButton.ImageButtonStyle> styles = new ArrayList<>();
        gameMode = new ArrayList<ImageButton>();
        for(int i = 0; i < 3; i++){
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.up = new TextureRegionDrawable(Assets.select_Game_Modes.get(i));
            styles.add(style);
            gameMode.add(new ImageButton(styles.get(i)));
        }

        // Adding listeners to the buttons
        // 0 - Vs AI
        // 1 - 1v1
        // 2 - Gigachad mode
        gameMode.get(0).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TankSelectScreen((TankiChads) Gdx.app.getApplicationListener(),0));
            }
        });

        gameMode.get(1).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TankSelectScreen((TankiChads) Gdx.app.getApplicationListener(),1));
            }
        });

        gameMode.get(2).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new InProgressScreen((TankiChads) Gdx.app.getApplicationListener()));
            }
        });
        back = new ImageButton(new TextureRegionDrawable(Assets.back));
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu((TankiChads) Gdx.app.getApplicationListener()));
            }
        });
        back.setSize(100,100);
        back.setPosition(-10,Gdx.graphics.getHeight()-100, Align.bottomLeft);


        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(-10, Gdx.graphics.getHeight()/2+250);
        table.padTop(100);
        table.add(gameMode.get(0)).width(347).height(361).padLeft(50).padRight(50);
        table.add(gameMode.get(1)).width(347).height(352).padLeft(50).padRight(50);
        table.add(gameMode.get(2)).width(347).height(417).padLeft(50).padRight(50);
        stage.addActor(table);
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        stage.act();
        game.batch.begin();
        game.batch.draw(Assets.background, 0, -1);
        game.batch.end();
        stage.draw();
    }
}
