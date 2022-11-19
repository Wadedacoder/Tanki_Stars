package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Assets {
    public static Texture Background;

    public static void load() {
        Background = new Texture(Gdx.files.internal("chad_DMC.png"));
    }

}
