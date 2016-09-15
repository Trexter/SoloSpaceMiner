package com.trexter.solospaceminer.screens.MainMenuScreen.Attachments;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import javax.swing.GroupLayout;

/**
 * Created by kevinsheridan on 8/1/15.
 */
public class menuAttachment extends Actor
{
    public String file;
    public String partName;
    public String partDescription;

    float xOffset;
    float centerX;
    float centerY;

    Sprite part;

    public menuAttachment(float _x, float _y, float _xOffset, float _scale, String _file, String _partName, String _partDescription)
    {
        file = _file;
        partName = _partName;
        setName(partName);
        partDescription = _partDescription;

        xOffset = _xOffset;
        centerX = _x;
        centerY = _y;

        part = new Sprite(new Texture(file));

        setPosition(centerX + xOffset, centerY);
        part.setPosition(centerX + xOffset, centerY);
        setWidth(part.getWidth());
        setHeight(part.getHeight());
        setScale(_scale);
        //this will center it
        setOrigin(Align.center);
        setBounds(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        part.setPosition(getX(), getY());
        part.setScale(getScaleX(), getScaleY());
        part.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
