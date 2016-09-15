package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import java.util.Random;

/**
 * Created by kevinsheridan on 6/28/15.
 */
public class menuAsteroidBottomActor extends Actor
{
    private float rotVal = 900.0f;
    private float durationVal = 25f;

    Sprite asteroid;
    Random rand = new Random(System.currentTimeMillis());

    int startX, startY, rangeX, rangeY, startDelay;
    long startTime;
    boolean firstRun = true;

    public menuAsteroidBottomActor(int _startX, int _startY, int _rangeX, int _rangeY, int _delayMillis, String path)
    {
        asteroid = new Sprite(new Texture(path));
        asteroid.setColor(0.5f, 0.5f, 0.5f, 1f);

        startX = _startX;
        startY = _startY;
        rangeX = _rangeX;
        rangeY = _rangeY;
        startDelay = _delayMillis;

        startTime = System.currentTimeMillis();

        setBounds(0, 0, asteroid.getWidth(), asteroid.getHeight());

        //set the asteroid's starting position and starting scale with random(constrained) values
        menuAsteroidBottomActor.this.setPosition(startX + rand.nextInt(rangeX), startY);
        menuAsteroidBottomActor.this.setScale(mapfloat(rand.nextFloat(), 0.0f, 1.0f, 0.25f, 0.5f));
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        asteroid.setScale(getScaleX(), getScaleY());
        asteroid.setPosition(getX() - (getWidth() / 2), getY() - (getHeight() / 2));
        asteroid.setRotation(getRotation());
        asteroid.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta)
    {
        this.actionLogic();
        super.act(delta);
    }

    private void actionLogic()
    {
        MoveByAction mba;
        RotateByAction ra;
        ParallelAction pa;

        if(firstRun)
        {
            //check if enough time has passed
            if(System.currentTimeMillis() - startTime >= startDelay)
            {
                //setup the action
                mba = new MoveByAction();
                mba.setAmount(0, -rangeY);
                mba.setDuration(durationVal);

                ra = new RotateByAction();
                ra.setAmount(mapfloat(rand.nextFloat(), 0.0f, 1.0f, -rotVal, rotVal));
                ra.setDuration(durationVal);

                pa = new ParallelAction(mba, ra);

                //run the action
                menuAsteroidBottomActor.this.addAction(pa);
                //clean up the first run variables for looping
                firstRun = false;
            }
        }
        //if this is not the first run of act()
        else
        {
            //check if any actions are running
            if(menuAsteroidBottomActor.this.getActions().size == 0)
            {
                //reset the asteroid and give it random position again
                menuAsteroidBottomActor.this.setPosition(startX + rand.nextInt(rangeX), startY);
                menuAsteroidBottomActor.this.setScale(mapfloat(rand.nextFloat(), 0.0f, 1.0f, 0.25f, 0.5f));
                //setup the action
                mba = new MoveByAction();
                mba.setAmount(0, -rangeY);
                mba.setDuration(durationVal);

                ra = new RotateByAction();
                ra.setAmount(mapfloat(rand.nextFloat(), 0.0f, 1.0f, -rotVal, rotVal));
                ra.setDuration(durationVal);

                pa = new ParallelAction(mba, ra);

                //run the action
                menuAsteroidBottomActor.this.addAction(pa);
            }
        }
    }

    private float mapfloat(float x, float in_min, float in_max, float out_min, float out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
