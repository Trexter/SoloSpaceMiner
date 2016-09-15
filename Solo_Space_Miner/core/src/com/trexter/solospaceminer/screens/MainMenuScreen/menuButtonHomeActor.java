package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by kevinsheridan on 7/4/15.
 */
public class menuButtonHomeActor extends Actor
{
    Sprite button;
    float scale;

    public menuButtonHomeActor(float _x, float _y, float _scale, String texturePath)
    {
        setTouchable(Touchable.enabled);

        button = new Sprite(new Texture(texturePath));
        setBounds(0, 0, button.getWidth(), button.getHeight());
        setPosition(_x, _y);
        scale = _scale;
        setScale(scale);

        addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                ScaleToAction sta = new ScaleToAction();
                sta.setScale(scale);
                sta.setDuration(0.1f);

                addAction(sta);

                //trigger camera movement to the play buttons next area
                MoveToAction mta = new MoveToAction();
                mta.setPosition(0, 0);
                mta.setDuration(2f);
                mta.setInterpolation(Interpolation.exp10Out);
                getStage().getActors().get(28).addAction(mta);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScaleToAction sta = new ScaleToAction();
                sta.setScale(scale - (0.02f * scale));
                sta.setDuration(0.1f);

                addAction(sta);
                Gdx.input.vibrate(10);
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        button.setPosition(getX(), getY());
        button.setRotation(getRotation());
        button.setScale(getScaleX(), getScaleY());
        //sets bounds to compensate for pos change and scale
        setBounds(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        //draw the button
        button.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }

}
