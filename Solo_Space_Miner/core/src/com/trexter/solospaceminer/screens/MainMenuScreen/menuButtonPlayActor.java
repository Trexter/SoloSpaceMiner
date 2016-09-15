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
 * Created by kevinsheridan on 6/28/15.
 */
public class menuButtonPlayActor extends Actor
{
    Sprite button;
    int actionDelay, deltaX;
    long startTime;
    boolean actionAdded = false;


    public menuButtonPlayActor(int startX, int startY, int scaleX, int scaleY, int _deltaX, String texturePath, int _actionDelay)
    {
        menuButtonPlayActor.this.setTouchable(Touchable.enabled);

        button = new Sprite(new Texture(texturePath));
        menuButtonPlayActor.this.setBounds(0, 0, button.getWidth(), button.getHeight());
        setPosition(startX, startY);
        setScale(scaleX, scaleY);

        actionDelay = _actionDelay;
        startTime = System.currentTimeMillis();
        deltaX = _deltaX;

        addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                ScaleToAction sta = new ScaleToAction();
                sta.setScale(1f);
                sta.setDuration(0.1f);

                menuButtonPlayActor.this.addAction(sta);

                //trigger camera movement to the play buttons next area
                MoveToAction mta = new MoveToAction();
                mta.setPosition(1200, 0);
                mta.setDuration(2f);
                mta.setInterpolation(Interpolation.exp10Out);
                getStage().getActors().get(28).addAction(mta);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScaleToAction sta = new ScaleToAction();
                sta.setScale(0.98f);
                sta.setDuration(0.1f);

                menuButtonPlayActor.this.addAction(sta);
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
        //draw the button
        button.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        buttonLogic();
        super.act(delta);
    }

    protected void buttonLogic()
    {

        if(!actionAdded)
        {
            if(System.currentTimeMillis() - startTime >= actionDelay)
            {
                MoveByAction mba = new MoveByAction();
                mba.setAmountX(deltaX);
                mba.setDuration(1f);
                mba.setInterpolation(Interpolation.exp10Out);

                menuButtonPlayActor.this.addAction(mba);

                actionAdded = true;
            }
        }

    }


}
