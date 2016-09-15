package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 * Created by kevinsheridan on 6/13/15.
 */

public class menuTitleActor extends Actor
{

    int startX, startY, endX, endY, delay;
    long startTime = 0;
    boolean flyInActionSet = false;

    Sprite title = new Sprite(new Texture("SSM-menu-title.png"));

    public menuTitleActor(int _startX, int _startY, int _endX, int _endY, int _delay)
    {
        startX = _startX;
        startY = _startY;
        endX = _endX;
        endY = _endY;
        delay = _delay;

        startTime = System.currentTimeMillis();

        setPosition(startX, startY);
        setScale(1.5f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        title.setScale(getScaleX(), getScaleY());
        title.setPosition(getX() - (getWidth() / 2), getY() - (getHeight() / 2));
        title.setRotation(getRotation());
        title.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        MoveToAction mta;

        //if the action has been set
        if(flyInActionSet)
        {

        }
        else
        {
            //waits for the right amount of time to pass
            if(System.currentTimeMillis() - startTime >= delay)
            {
                mta = new MoveToAction();
                mta.setPosition(endX, endY);
                mta.setDuration(1f);
                mta.setInterpolation(Interpolation.exp10Out);

                menuTitleActor.this.addAction(mta);

                //set the action bool
                flyInActionSet = true;
            }
        }
        super.act(delta);
    }
}
