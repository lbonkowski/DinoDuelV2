package com.dinoduel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Scenes.Hud;

public class PlayScreen implements Screen {

    private DinoDuel game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(DinoDuel game) {
        this.game = game;
        //Camera that follows the players
        gameCam = new OrthographicCamera();
        //Fits the proper aspect ratio
        gamePort = new FitViewport(DinoDuel.V_WIDTH, DinoDuel.V_HEIGHT, gameCam);
        //Creates the hud
        hud = new Hud(game.batch);
        //Renders the map
        maploader = new TmxMapLoader();
        map = maploader.load("DinoDuel Basic Tilesets/testLevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }
//dt = delta time
    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }//end update

    private void handleInput(float dt) {
        if(Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;
        }
    }//end handleInput

    @Override
    public void render(float deltaTime) {
        update(deltaTime);

       //clears the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //sets the batch to draw what the camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
