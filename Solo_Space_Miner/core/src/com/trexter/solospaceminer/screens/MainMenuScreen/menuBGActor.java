package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;


/**
 * Created by kevinsheridan on 6/5/15.
 * Trexter - this is the background actor
 */

public class menuBGActor extends Actor {

    Sprite bgSprite = new Sprite(new Texture("menu_star_bg.png"));

    public menuBGActor()
    {
        setBounds(0, 0, bgSprite.getWidth(), bgSprite.getHeight());
        menuBGActor.this.setPosition(0, -100);
        menuBGActor.this.setScale(2.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        bgSprite.setScale(getScaleX(), getScaleY());
        bgSprite.setPosition(getX() - (getWidth() / 2), getY() - (getHeight() / 2));
        bgSprite.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }

    public void calculateParallax(Vector3 deltaVec)
    {
        float parrallaxBias = 0.95f;

        MoveByAction mba = new MoveByAction();
        mba.setAmount(deltaVec.x * parrallaxBias, deltaVec.y * parrallaxBias);
        mba.setDuration(0.001f);

        addAction(mba);
    }
}
