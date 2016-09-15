package com.trexter.solospaceminer.screens.MainMenuScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.trexter.solospaceminer.LibGdxGame;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuPrimarySelector;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuPropulsionSelector;
import com.trexter.solospaceminer.screens.MainMenuScreen.Attachments.menuSecondarySelector;

/**
 * Created by kevinsheridan on 6/1/15.
 * Trexter the main menu as you can probably tell by the title of the class
 * Hi BTW
 */
public class MainMenu implements Screen {
    //initialize the crap out of this class
    float lastAccelX = 0;
    float lastAccelY = 0;
    Vector3 lastAccelDeltaVec = new Vector3(0,0,0);
    Vector3 lastLastAccelDeltaVec = new Vector3(0,0,0);

    Stage stage;
    OrthographicCamera camera;

    //actors
    menuBGActor bg = new menuBGActor();

    //home
    menuAsteroidBottomActor ast1b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 0, "Asteroid 1.png");
    menuAsteroidBottomActor ast2b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 1800, "Asteroid 2.png");
    menuAsteroidBottomActor ast3b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 3000, "Asteroid 3.png");
    menuAsteroidBottomActor ast4b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 6600, "Asteroid 1.png");
    menuAsteroidBottomActor ast5b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 8500, "Asteroid 2.png");
    menuAsteroidBottomActor ast6b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 9000, "Asteroid 3.png");
    menuAsteroidBottomActor ast7b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 11000, "Asteroid 1.png");
    menuAsteroidBottomActor ast8b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 14000, "Asteroid 2.png");
    menuAsteroidBottomActor ast9b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 15500, "Asteroid 3.png");
    menuAsteroidBottomActor ast10b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 17900, "Asteroid 1.png");
    menuAsteroidBottomActor ast11b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 20000, "Asteroid 2.png");
    menuAsteroidBottomActor ast12b = new menuAsteroidBottomActor(-900, 1100, 1100, 4400, 22000, "Asteroid 3.png");

    menuAsteroid1Actor ast1 = new menuAsteroid1Actor(-900, 1100, 1100, 4400, 0);
    menuAsteroid2Actor ast2 = new menuAsteroid2Actor(-900, 1100, 1100, 4400, 2000);
    menuAsteroid3Actor ast3 = new menuAsteroid3Actor(-900, 1100, 1100, 4400, 4000);
    menuAsteroid1Actor ast4 = new menuAsteroid1Actor(-900, 1100, 1100, 4400, 6000);
    menuAsteroid2Actor ast5 = new menuAsteroid2Actor(-900, 1100, 1100, 4400, 8000);
    menuAsteroid3Actor ast6 = new menuAsteroid3Actor(-900, 1100, 1100, 4400, 10000);
    menuAsteroid1Actor ast7 = new menuAsteroid1Actor(-900, 1100, 1100, 4400, 12000);
    menuAsteroid2Actor ast8 = new menuAsteroid2Actor(-900, 1100, 1100, 4400, 14000);
    menuAsteroid3Actor ast9 = new menuAsteroid3Actor(-900, 1100, 1100, 4400, 16000);
    menuAsteroid1Actor ast10 = new menuAsteroid1Actor(-900, 1100, 1100, 4400, 18000);
    menuAsteroid2Actor ast11 = new menuAsteroid2Actor(-900, 1100, 1100, 4400, 20000);
    menuAsteroid3Actor ast12 = new menuAsteroid3Actor(-900, 1100, 1100, 4400, 22000);

    menuTitleActor title = new menuTitleActor(600, 500, -600, 500, 100);

    menuButtonPlayActor playBtn = new menuButtonPlayActor(600, 0, 1, 1, -1200, "SSM-Play-Btn.png", 300);
    menuButtonSettingsActor settingsBtn = new menuButtonSettingsActor(600, -300, 1, 1, -1200, "SSM-Settings-Btn.png", 500);

    //ship upgrades
    menuShipSelector shipSelector = new menuShipSelector(350, -600);
    menuPrimarySelector primarySelector = new menuPrimarySelector(400, 1000);
    menuSecondarySelector secondarySelector = new menuSecondarySelector(400, 1000);
    menuPropulsionSelector propulsionSelector = new menuPropulsionSelector(400, 1000, shipSelector);
    menuButtonHomeActor homeButton = new menuButtonHomeActor(400f, -910f, 1.9f, "SSM-Home-Btn.png");
    menuButtonSelectActor selectButton = new menuButtonSelectActor(610, -950, 1, 1, "SSM-Modify-Btn.png", shipSelector, primarySelector, secondarySelector, propulsionSelector);

    //cam hitch
    menuCameraHitch cameraHitch = new menuCameraHitch();
    //end of actors



    @Override
    public void render(float delta) // runs once per frame
    {
        Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch

        //set the camera position to the hitches position
        //also runs the parallax functions for the specified actors
        this.setParallax();
        //accelerometer shake experimental**********
        //end of camera crap

        stage.getViewport().getCamera().update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show() // called once when the screen becomes THE! screen
    {
        //sets up the current stage
        camera = new OrthographicCamera();
        StretchViewport viewport = new StretchViewport(LibGdxGame.worldWIDTH, LibGdxGame.worldHEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        //set camera to starting position
        stage.getViewport().getCamera().translate(-900, -1000, 0);

        //add actors to the stage
        stage.addActor(bg);

        stage.addActor(ast1b);
        stage.addActor(ast2b);
        stage.addActor(ast3b);
        stage.addActor(ast4b);
        stage.addActor(ast5b);
        stage.addActor(ast6b);
        stage.addActor(ast7b);
        stage.addActor(ast8b);
        stage.addActor(ast9b);
        stage.addActor(ast10b);
        stage.addActor(ast11b);
        stage.addActor(ast12b);

        stage.addActor(ast1);
        stage.addActor(ast2);
        stage.addActor(ast3);
        stage.addActor(ast4);
        stage.addActor(ast5);
        stage.addActor(ast6);
        stage.addActor(ast7);
        stage.addActor(ast8);
        stage.addActor(ast9);
        stage.addActor(ast10);
        stage.addActor(ast11);
        stage.addActor(ast12);

        stage.addActor(title);

        stage.addActor(playBtn);
        stage.addActor(settingsBtn);
        //I have to add the cam hitch here for index reasons
        stage.addActor(cameraHitch);

        //ship upgrade actors
        stage.addActor(shipSelector);
        stage.addActor(primarySelector);
        stage.addActor(secondarySelector);
        stage.addActor(propulsionSelector);
        stage.addActor(homeButton);
        stage.addActor(selectButton);

        //fade in stage
        stage.addAction(Actions.alpha(0));
        stage.addAction(Actions.fadeIn(0.5f));
    }

    @Override
    public void hide()
    {
        this.dispose();
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }

    private void setParallax()
    {
        Vector3 deltaVec = cameraHitch.getDeltaP();
        stage.getCamera().translate(deltaVec);

        //run accel wiggle algorithm
        float accelX = -1f * Gdx.input.getAccelerometerX();
        float accelY = -1f * Gdx.input.getAccelerometerY();
        Vector3 accelDeltaVec = new Vector3(accelX - lastAccelX, accelY - lastAccelY, 0);
        accelDeltaVec.scl(5f);

        //average accel vectors
        Vector3 finalAccelDeltaVec = accelDeltaVec.add(lastAccelDeltaVec);
        finalAccelDeltaVec.add(lastLastAccelDeltaVec);
        finalAccelDeltaVec.scl(0.33333333f);
        lastLastAccelDeltaVec = lastAccelDeltaVec;
        lastAccelDeltaVec = accelDeltaVec;

        //translate cam
        stage.getCamera().translate(finalAccelDeltaVec);
        //add accel delta to camhitch delta
        deltaVec.add(finalAccelDeltaVec);
        //set the last accel vals
        lastAccelX = accelX;
        lastAccelY = accelY;
        //end of accel wiggle algorithm
        //run parallax functions
        bg.calculateParallax(deltaVec);
    }

}
