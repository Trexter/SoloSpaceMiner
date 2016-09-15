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
import com.trexter.solospaceminer.screens.MainMenuScreen.menuShipSelector;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by kevinsheridan on 8/2/15.
 */
public class menuPropulsionSelector extends Group
{
    public float centerX, centerY;
    float scrollX = 0;
    float scrollY = 0;

    menuShipSelector shipSelector;

    //the parts
    ArrayList<menuAttachment> Parts = new ArrayList<menuAttachment>(10);

    public menuPropulsionSelector(float _centerX, float _centerY, menuShipSelector mss)
    {
        shipSelector = mss;
        centerX = _centerX;
        centerY = _centerY;

        //set up all primary parts
        Parts.add(new menuAttachment(centerX, centerY, 0, 2f, "Type_7_Medium_Engine.png", "Type 7 Med. Engine", "test of the description"));
        Parts.add(new menuAttachment(centerX, centerY, 250, 2f, "Svesdan_Light_Engine.png", "Svesdan Light Engine", "test of the description"));

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
                menuPropulsionSelector.this.scrollLockLogic(velocityX);
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                scrollX += deltaX;
                menuPropulsionSelector.this.scrollLogic();
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
            this.addActor(Parts.get(1));
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

        //change preferences to ship attachments
        Preferences prefs = Gdx.app.getPreferences(LibGdxGame.prefName);
        String prefName = prefs.getString("SELECTED_SHIP") + "_PROPULSION";
        //find the file name of the selected part;
        String selectedPart = "";
        for(int i = 0; i < Parts.size(); i++)
        {
            if(this.getChildren().get(closestActorIndex).getName().equals(Parts.get(i).partName))
            {
                selectedPart = Parts.get(i).file;
                break;
            }
        }
        prefs.putString(prefName, selectedPart);
        prefs.flush();

        shipSelector.updateShips();
    }
}
