package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.trexter.solospaceminer.LibGdxGame;

/**
 * Created by kevinsheridan on 7/26/15.
 */
public class menuPaladin extends Actor
{
    Sprite body;
    Sprite primary;
    int primaryRelX = 0;
    int primaryRelY = 0;
    Sprite secondary;
    int secondaryRelX = 0;
    int secondaryRelY = 0;
    int secondaryRelX2 = 0;
    int secondaryRelY2 = 0;
    Sprite propulsion;
    int propulsionRelX = 0;
    int propulsionRelY = 0;
    int propulsionRelX2 = 0;
    int propulsionRelY2 = 0;

    public float y_offset;

    public menuPaladin(float _x, float _y, float _yoffset)
    {
        y_offset = _yoffset;

        this.setName("PALADIN");

        body = new Sprite(new Texture(menuShipLayout.Paladin.file));
        primary = new Sprite(new Texture(menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.file));
        secondary = new Sprite(new Texture(menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.file));
        propulsion = new Sprite(new Texture(menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.file));

        this.setPosition(_x, _y + y_offset);
        this.setBounds(getX(), getY(), body.getWidth(), body.getHeight());

        this.updateSprites();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        primary.setPosition(getX() + (body.getWidth() / 2) + primaryRelX, getY() + (body.getHeight() / 2) + primaryRelY);
        primary.setScale(getScaleX(), getScaleY());
        primary.setColor(getColor());
        primary.draw(batch, parentAlpha);

        secondary.setPosition(getX() + (body.getWidth() / 2) + secondaryRelX, getY() + (body.getHeight() / 2) + secondaryRelY);
        secondary.setScale(getScaleX(), getScaleY());
        secondary.setColor(getColor());
        secondary.draw(batch, parentAlpha);

        secondary.setPosition(getX() + (body.getWidth() / 2) + secondaryRelX2, getY() + (body.getHeight() / 2) + secondaryRelY2);
        secondary.setScale(getScaleX(), getScaleY());
        secondary.setColor(getColor());
        secondary.draw(batch, parentAlpha);

        propulsion.setPosition(getX() + (body.getWidth() / 2) + propulsionRelX, getY() + (body.getHeight() / 2) + propulsionRelY);
        propulsion.setScale(getScaleX(), getScaleY());
        propulsion.setColor(getColor());
        propulsion.draw(batch, parentAlpha);

        propulsion.setPosition(getX() + (body.getWidth() / 2) + propulsionRelX2, getY() + (body.getHeight() / 2) + propulsionRelY2);
        propulsion.setScale(getScaleX(), getScaleY());
        propulsion.setColor(getColor());
        propulsion.draw(batch, parentAlpha);

        body.setPosition(getX() + (body.getWidth() / 2), getY() + (body.getHeight() / 2));
        body.setScale(getScaleX(), getScaleY());
        body.setColor(getColor());
        body.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void updateSprites()
    {
        Preferences prefs = Gdx.app.getPreferences(LibGdxGame.prefName);

        //PRIMARY
        if(prefs.getString("PALADIN_PRIMARY").equals(menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.file))
        {
            primaryRelX = menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.relX;
            primaryRelY = menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.relY;

            primary.getTexture().dispose();
            primary.setTexture(new Texture(menuShipLayout.Paladin.Primary.Arvak_Plasma_Gun.file));
        }

        //SECONDARY
        if(prefs.getString("PALADIN_SECONDARY").equals(menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.file))
        {
            secondaryRelX = menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.relX;
            secondaryRelY = menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.relY;
            secondaryRelX2 = menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.relX2;
            secondaryRelY2 = menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.relY2;

            secondary.getTexture().dispose();
            secondary.setTexture(new Texture(menuShipLayout.Paladin.Secondary.Arvak_Light_Missle.file));
        }

        //PROPULSION

        if(prefs.getString("PALADIN_PROPULSION").equals(menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.file))
        {
            propulsionRelX = menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.relX;
            propulsionRelY = menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.relY;
            propulsionRelX2 = menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.relX2;
            propulsionRelY2 = menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.relY2;

            propulsion.getTexture().dispose();
            propulsion.setTexture(new Texture(menuShipLayout.Paladin.Propulsion.Type_7_Medium_Engine.file));
        }
        if(prefs.getString("PALADIN_PROPULSION").equals(menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.file))
        {
            propulsionRelX = menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.relX;
            propulsionRelY = menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.relY;
            propulsionRelX2 = menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.relX2;
            propulsionRelY2 = menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.relY2;

            propulsion.getTexture().dispose();
            propulsion.setTexture(new Texture(menuShipLayout.Paladin.Propulsion.Svesdan_Light_Engine.file));
        }

    }
}
