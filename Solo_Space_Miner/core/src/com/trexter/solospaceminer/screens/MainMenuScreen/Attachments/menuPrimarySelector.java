package com.trexter.solospaceminer.screens.MainMenuScreen.Attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.trexter.solospaceminer.LibGdxGame;
import com.trexter.solospaceminer.screens.MainMenuScreen.menuShipLayout;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by kevinsheridan on 8/1/15.
 * This might be the best designed class I've made yet! It is very expandable!
 */
public class menuPrimarySelector extends Group
{
    public float centerX, centerY;
    float scrollX = 0;
    float scrollY = 0;

    //the parts
    ArrayList<menuAttachment> Parts = new ArrayList<menuAttachment>(10);

    public menuPrimarySelector(float _centerX, float _centerY)
    {
        centerX = _centerX;
        centerY = _centerY;

        //set up all primary parts
        Parts.add(new menuAttachment(centerX, centerY, 0, 2f, "Arvak_Plasma_Gun.png", "Arvak Plasma Gun", "test of the description"));

        //actor adding function
        addCorrespondingShipsActors();
        //touch detection
        this.addListener(new ActorGestureListener()
        {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public void fling(InputEvent event, float velocityX, float velocityY, int button) {
                menuPrimarySelector.this.scrollLockLogic(velocityX);
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                scrollX += deltaX;
                menuPrimarySelector.this.scrollLogic();
            }
        });
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
    }

    public void addCorrespondingShipsActors()
    {
        Preferences prefs = Gdx.app.getPreferences(LibGdxGame.prefName);
        if(prefs.getString("SELECTED_SHIP").equals("PALADIN"))
        {
            //paladin's attachment list
            this.addActor(Parts.get(0));

        }
    }

    private void scrollLogic(){
        //this sorts through every combination of actor and part and sets its position correctly
        for(int i = 0; i < this.getChildren().size; i++)
        {
            String actorName = this.getChildren().get(i).getName();
            for(int j = 0; j < Parts.size(); j++)
            {
                if(actorName.equals(Parts.get(j).partName))
                {
                    this.getChildren().get(i).setPosition(centerX + Parts.get(j).xOffset + scrollX, Parts.get(j).getY());
                    break;
                }
            }
        }
    }

    private void scrollLockLogic(float _deltaX)
    {
        //finds the actor closest to the center
        int closestActorIndex = 0;
        for(int i = 0; i < this.getChildren().size; i++)
        {
            if(abs(this.getChildren().get(i).getX() - centerX) < abs(this.getChildren().get(closestActorIndex).getX() - centerX))
            {
                closestActorIndex = i;
            }
        }

        float deltaX = this.getChildren().get(closestActorIndex).getX() - centerX;
        float flyDur = abs(deltaX) / 1000f;

        for(int i = 0; i < this.getChildren().size; i++)
        {
            MoveByAction mba = new MoveByAction();
            mba.setAmount(-deltaX, 0f);
            mba.setInterpolation(Interpolation.exp10Out);
            mba.setDuration(flyDur);

            this.getChildren().get(i).addAction(mba);
        }

        scrollX -= deltaX;
    }
}
