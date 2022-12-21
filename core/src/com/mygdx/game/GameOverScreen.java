package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameOverScreen extends ScreenAdapter
{
    private TankiChads game;
    private int winner;

    private Stage stage;
    private Label gameOverLabel;
    private TextButton Button;

    public GameOverScreen(TankiChads game, int score)
    {
        this.game = game;
        this.winner = score;
        stage = new Stage();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.font;
        gameOverLabel = new Label("Game Over", style);
        gameOverLabel.setFontScale(2);
        gameOverLabel.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 100, Align.center);
        stage.addActor(gameOverLabel);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Assets.font;
        Button = new TextButton("Back to menu", textButtonStyle);
        Button.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 100, Align.center);
        stage.addActor(Button);
        Button.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu((TankiChads) Gdx.app.getApplicationListener()));
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        gameOverLabel.setText("Player " + winner + " wins!");

        game.batch.begin();
        game.batch.draw(Assets.Blank_background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        Assets.font.draw(game.batch, "Player " + winner + " wins!", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() - 200);
        game.batch.end();
        stage.draw();

    }
}
