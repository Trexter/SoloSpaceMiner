package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.trexter.solospaceminer.LibGdxGame;

import static java.lang.Math.abs;

/**
 * Created by kevinsheridan on 7/4/15.
 * This is highly experimental (for me at least)
 */
public class menuShipSelector extends Group
{
    float centerX, centerY;
    float scrollX, scrollY;

    menuPaladin paladin;
    menuDawnstar dawnstar;
    menuActaeon actaeon;

    public menuShipSelector(float _x, float _y)
    {
        centerX = _x;
        centerY = _y;
        scrollX = 0;
        scrollY = 0;
        //create ships with their y-offsets
        paladin = new menuPaladin(centerX, centerY, 0);
        dawnstar = new menuDawnstar(centerX, centerY, 1700);
        actaeon = new menuActaeon(centerX, centerY, 3400);

        this.addActor(paladin);
        this.addActor(dawnstar);
        this.addActor(actaeon);

        //add gesture detector
        this.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("clearing actions");
                for(int i = 0; i < menuShipSelector.this.getChildren().size; i++)
                {
                    menuShipSelector.this.getChildren().get(i).clearActions();
                    menuShipSelector.this.getChildren().get(i).setPosition(menuShipSelector.this.getChildren().get(i).getX(), menuShipSelector.this.getChildren().get(i).getY());
                }

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public void fling(InputEvent event, float velocityX, float velocityY, int button) {
                menuShipSelector.this.scrollLockLogic(velocityY);
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                scrollY += deltaY;
                menuShipSelector.this.scrollLogic();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        this.scaleLogic();
        super.act(delta);
    }

    public void updateShips()
    {
        paladin.updateSprites();
        dawnstar.updateSprites();
        actaeon.updateSprites();
    }

    private void scrollLockLogic(float yVel)
    {
        float velConst = 0.4f;

        int closestActorIndex = 0;
        //cycle through all children and finds the one closest to the center point
        for(int i = 0; i < this.getChildren().size; i++)
        {
            //this will compensate for velocity
            if(abs(this.getChildren().get(i).getY() + (yVel * velConst) - centerY) < abs(this.getChildren().get(closestActorIndex).getY() + (yVel * velConst) - centerY))
            {
                closestActorIndex = i;
            }
        }

        float deltaY = this.getChildren().get(closestActorIndex).getY() - centerY;
        float flyDur = abs(deltaY) / 1000f;

        //System.out.println(yVel);
        //adds the move by action to the actors
        for(int i = 0; i < this.getChildren().size; i++)
        {
            MoveByAction mba = new MoveByAction();
            mba.setAmount(0, -deltaY);
            mba.setDuration(flyDur);
            mba.setInterpolation(Interpolation.exp10Out);
            this.getChildren().get(i).addAction(mba);
        }
        //sets the scrolly
        scrollY -= deltaY;

        //sets the selected ship
        Preferences prefs = Gdx.app.getPreferences(LibGdxGame.prefName);
        prefs.putString("SELECTED_SHIP", this.getChildren().get(closestActorIndex).getName());
        prefs.flush();
    }

    private void scrollLogic()
    {
        paladin.setPosition(paladin.getX(), centerY + paladin.y_offset + scrollY);
        dawnstar.setPosition(dawnstar.getX(), centerY + dawnstar.y_offset + scrollY);
        actaeon.setPosition(actaeon.getX(), centerY + actaeon.y_offset + scrollY);
    }

    private void scaleLogic()
    {
        float scaleFactor = 2f;
        //sets and scales y_offsets according to group scale
        paladin.setScale(scaleFactor, scaleFactor);
        dawnstar.setScale(scaleFactor, scaleFactor);
        actaeon.setScale(scaleFactor, scaleFactor);
    }

    public void addColorActionToChildren(ColorAction _ca)
    {
        for(int i = 0; i < this.getChildren().size; i++)
        {
            ColorAction ca = _ca;
            this.getChildren().get(i).addAction(ca);
        }
    }
}
