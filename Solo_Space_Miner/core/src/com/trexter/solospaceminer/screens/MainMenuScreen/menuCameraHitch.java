package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

/**
 * Created by kevinsheridan on 6/27/15.
 */
public class menuCameraHitch extends Actor
{
    float lastX = getX();
    float lastY = getY();

    public menuCameraHitch()
    {
        setVisible(false);
        setTouchable(Touchable.disabled);

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {

    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }

    public Vector3 getDeltaP()
    {
        Vector3 vec = new Vector3(getX() - lastX, getY() - lastY, 0);
        lastX = getX();
        lastY = getY();
        return vec;
    }
}
