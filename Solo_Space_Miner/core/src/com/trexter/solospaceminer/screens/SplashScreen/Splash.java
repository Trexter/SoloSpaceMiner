package com.trexter.solospaceminer.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by kevinsheridan on 6/1/15.
 * Trexter
 * This is the splash screen class which runs when the app is started up
 */

public class Splash implements Screen {

    //intitialize the objects
    //load in the splash image it can be anything or change
    private Texture texture = new Texture("game_test_splash.png");
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {
        //scale and add the image to the stage
        splashImage.setWidth(Gdx.graphics.getWidth());
        splashImage.setHeight(Gdx.graphics.getHeight());
        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.delay(2), Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                // change the screen to the main menu from here
                ((Game) Gdx.app.getApplicationListener()).setScreen(new com.trexter.solospaceminer.screens.MainMenuScreen.MainMenu());
            }
        })));
    }

    @Override
    public void hide()
    {
        //once the screen is changed call dispose method
        dispose();
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
        //get rid of our unused objects
        texture.dispose();
        stage.dispose();
    }

}
