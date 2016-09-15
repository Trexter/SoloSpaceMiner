package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuPrimarySelector;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuPropulsionSelector;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuSecondarySelector;


/**
 * Created by kevinsheridan on 7/31/15.
 */
public class menuButtonSelectActor extends Actor
{
    Sprite button;
    String mode = "modify";
    menuShipSelector shipSelector;
    menuPrimarySelector primarySelector;
    menuSecondarySelector secondarySelector;
    menuPropulsionSelector propulsionSelector;


    public menuButtonSelectActor(int startX, int startY, int scaleX, int scaleY, String texturePath, menuShipSelector mss, menuPrimarySelector mps, menuSecondarySelector msecs, menuPropulsionSelector mprops)
    {
        menuButtonSelectActor.this.setTouchable(Touchable.enabled);

        button = new Sprite(new Texture(texturePath));
        menuButtonSelectActor.this.setBounds(0, 0, button.getWidth(), button.getHeight());
        setPosition(startX, startY);
        setScale(scaleX, scaleY);

        shipSelector = mss;
        primarySelector = mps;
        primarySelector.setPosition(primarySelector.centerX, primarySelector.centerY);
        secondarySelector = msecs;
        secondarySelector.setPosition(secondarySelector.centerX, secondarySelector.centerY);
        propulsionSelector = mprops;
        propulsionSelector.setPosition(propulsionSelector.centerX, propulsionSelector.centerY);

        addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                //scale back to normal
                ScaleToAction sta = new ScaleToAction();
                sta.setScale(1f);
                sta.setDuration(0.1f);

                menuButtonSelectActor.this.addAction(sta);

                //performs necessary steps
                menuButtonSelectActor.this.buttonLogic();

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScaleToAction sta = new ScaleToAction();
                sta.setScale(0.98f);
                sta.setDuration(0.1f);

                menuButtonSelectActor.this.addAction(sta);
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
        super.act(delta);
    }

    protected void buttonLogic()
    {
        if(mode.equals("modify"))
        {
            button.getTexture().dispose();
            button.setTexture(new Texture("SSM-SelectShip-Btn.png"));
            mode = "select";

            //set ship to untouchable
            shipSelector.setTouchable(Touchable.disabled);
            //change ship color
            for(int i = 0; i < shipSelector.getChildren().size; i++)
            {
                ColorAction ca = new ColorAction();
                ca.setEndColor(Color.DARK_GRAY);
                ca.setDuration(0.25f);
                shipSelector.getChildren().get(i).addAction(ca);
            }

            //move the menus down
            MoveToAction mta0 = new MoveToAction();
            mta0.setPosition(primarySelector.centerX, -800f);
            mta0.setInterpolation(Interpolation.exp10Out);
            mta0.setDuration(1f);
            primarySelector.addAction(mta0);

            MoveToAction mta1 = new MoveToAction();
            mta1.setPosition(secondarySelector.centerX, -1200f);
            mta1.setInterpolation(Interpolation.exp10Out);
            mta1.setDuration(0.75f);
            secondarySelector.addAction(mta1);

            MoveToAction mta2 = new MoveToAction();
            mta2.setPosition(propulsionSelector.centerX, -1600f);
            mta2.setInterpolation(Interpolation.exp10Out);
            mta2.setDuration(0.5f);
            propulsionSelector.addAction(mta2);

        }
        else if(mode.equals("select"))
        {
            button.getTexture().dispose();
            button.setTexture(new Texture("SSM-Modify-Btn.png"));
            mode = "modify";

            //set ship to touchable
            shipSelector.setTouchable(Touchable.enabled);
            //change ship color
            for(int i = 0; i < shipSelector.getChildren().size; i++)
            {
                ColorAction ca = new ColorAction();
                ca.setEndColor(Color.WHITE);
                ca.setDuration(0.25f);
                shipSelector.getChildren().get(i).addAction(ca);
            }

            //move the menus up
            MoveToAction mta0 = new MoveToAction();
            mta0.setPosition(primarySelector.centerX, primarySelector.centerY);
            mta0.setInterpolation(Interpolation.exp10In);
            mta0.setDuration(0.5f);
            primarySelector.addAction(mta0);

            MoveToAction mta1 = new MoveToAction();
            mta1.setPosition(secondarySelector.centerX, secondarySelector.centerY);
            mta1.setInterpolation(Interpolation.exp10In);
            mta1.setDuration(0.5f);
            secondarySelector.addAction(mta1);

            MoveToAction mta2 = new MoveToAction();
            mta2.setPosition(propulsionSelector.centerX, propulsionSelector.centerY);
            mta2.setInterpolation(Interpolation.exp10In);
            mta2.setDuration(0.5f);
            propulsionSelector.addAction(mta2);
        }
    }


}
