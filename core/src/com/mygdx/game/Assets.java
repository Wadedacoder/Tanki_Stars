package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.ArrayList;

public class Assets {
    public static Texture background;
    public static Texture Blank_background;
    public static Texture load_background;
    public static Texture inprogress_background;
    public static Texture setting_background;
    public static Texture Logo;
    public static ArrayList<Texture> tanks;
    public static ArrayList<Texture> game_tanks;
    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    public static BitmapFont font;
    public static Texture load_Game;
    public static Texture new_Game;
    public static Texture exit_Game;
    public static Texture settings_Game;
    public static Texture screen_Game;
    public static Texture back;
    public static Texture select_Game_Mode_SpriteSheet;
    public static Texture select_tank;
    public static ArrayList<TextureRegion> select_Game_Modes = new ArrayList<TextureRegion>();

    public static Texture nuke;


    public static Skin uiSkin;

    public static void load() {
        background = new Texture(Gdx.files.internal("background.png"));
        Blank_background = new Texture(Gdx.files.internal("Tank_BackGround.png"));
        load_background = new Texture(Gdx.files.internal("load_background.png"));
        inprogress_background = new Texture(Gdx.files.internal("inprogress.png"));
        setting_background = new Texture(Gdx.files.internal("Setting.png"));
        screen_Game = new Texture(Gdx.files.internal("gameScreen.png"));
        Logo = new Texture(Gdx.files.internal("logo.png"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wpa-gothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.color = com.badlogic.gdx.graphics.Color.BLACK;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        load_Game = new Texture(Gdx.files.internal("load_game.png"));
        new_Game = new Texture(Gdx.files.internal("new_game.png"));
        exit_Game = new Texture(Gdx.files.internal("exit.png"));
        settings_Game = new Texture(Gdx.files.internal("settings.png"));
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        select_Game_Mode_SpriteSheet = new Texture(Gdx.files.internal("GameModesScreens.png"));
        load_Game.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        new_Game.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        exit_Game.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settings_Game.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        select_Game_Mode_SpriteSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        select_Game_Modes.add(new TextureRegion(select_Game_Mode_SpriteSheet, 40, 40, 689, 720));
        select_Game_Modes.add(new TextureRegion(select_Game_Mode_SpriteSheet, 932, 16, 689, 720));
        select_Game_Modes.add(new TextureRegion(select_Game_Mode_SpriteSheet, 1840, 32, 689, 785));
        back = new Texture(Gdx.files.internal("back.png"));
        back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tanks = new ArrayList<Texture>();
        tanks.add(new Texture(Gdx.files.internal("green.png")));
        tanks.add(new Texture(Gdx.files.internal("orange.png")));
        tanks.add(new Texture(Gdx.files.internal("blue.png")));
        tanks.add(new Texture(Gdx.files.internal("chad_tank.png")));
        game_tanks = new ArrayList<Texture>();
        game_tanks.add(new Texture(Gdx.files.internal("game_green.png")));
        game_tanks.add(new Texture(Gdx.files.internal("game_orange.png")));
        game_tanks.add(new Texture(Gdx.files.internal("game_blue.png")));
        for(Texture tank : tanks){
            tank.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        select_tank = new Texture(Gdx.files.internal("Select_Tank.png"));
        nuke = new Texture(Gdx.files.internal("nuke.png"));
    }

    public static TextField.TextFieldStyle getTextStyle(float r, float g, float b, float a) {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = Assets.font;
        style.fontColor = new Color(r, g, b, a);
//        style.background = Assets.uiSkin.getDrawable("textfield");
        return style;
    }


}
