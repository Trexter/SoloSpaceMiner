package com.trexter.solospaceminer;

/**
 * this was created by kevin sheridan as a part of Solo Space Miner on June 1 2015
 * Trexter
 */


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.trexter.solospaceminer.screens.SplashScreen.Splash;


public class LibGdxGame extends Game {
    public final static int worldWIDTH = 1120;
    public final static int worldHEIGHT = 1920;

    public final static String prefName = "SSM-prefs";
	
	@Override
	public void create () //runs once when the program is created
    {
        //set up game's preferences to default if they are not intialized
        Preferences prefs = Gdx.app.getPreferences(prefName);
        if(prefs.getBoolean("RANONCE") == true)
        {
            //the game has ran once
        }
        else
        {
            //initialize the preferences
            prefs.putBoolean("RANONCE", false);

            prefs.putString("SELECTED_SHIP", "PALADIN");

            prefs.putString("PALADIN_PRIMARY", "Arvak_Plasma_Gun.png");
            prefs.putString("PALADIN_SECONDARY", "Arvak_Light_Missile.png");
            prefs.putString("PALADIN_PROPULSION", "Svesdan_Light_Engine.png");

            prefs.putString("DAWNSTAR_PRIMARY", "Arvak_Plasma_Gun.png");
            prefs.putString("DAWNSTAR_SECONDARY", "Arvak_Light_Missile.png");
            prefs.putString("DAWNSTAR_PROPULSION", "Svesdan_Light_Engine.png");

            prefs.putString("ACTAEON_PRIMARY", "Arvak_Plasma_Gun.png");
            prefs.putString("ACTAEON_SECONDARY", "Arvak_Light_Missile.png");
            prefs.putString("ACTAEON_PROPULSION", "Svesdan_Light_Engine.png");

            prefs.flush();
        }

        setScreen(new Splash());
	}
}
