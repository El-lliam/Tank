package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;

import constante.TextureFactory;
import movingObjects.Player;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch _batch;
	private Rectangle _glviewport;
	private OrthographicCamera _camera;
	private String curScreen;
	private Map<String, AbstractScreen> _screens; // screen list
	private AssetManager _assetManager;
	private Player player;
	
	int screenHeight;
	int screenWidth;
	
	@Override
	public void create () {
		TextureFactory.getInstance();
		
		_batch = new SpriteBatch();
		_glviewport = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		_camera = new OrthographicCamera(1450, 1000);
	    _camera.setToOrtho(false, 1450, 1000);
	    
	    _camera.update();
	    
	    // create assets manager
	    _assetManager = new AssetManager();
	    
	    _assetManager.load("Fond.png", Texture.class);
	    // begin load
	    _assetManager.finishLoading();
	    
	    _screens = new HashMap<String, AbstractScreen>();
	    
	    GameScreen gScreen = new GameScreen(this);
	    VictoryScreen vScreen = new VictoryScreen(this);
	    LoseScreen lScreen = new LoseScreen(this);
	    _screens.put("gameScreen", gScreen);
	    _screens.put("victoryScreen", vScreen);
	    _screens.put("loseScreen", lScreen);
	    changeScreen("gameScreen");
	    curScreen = "gameScreen";
	    player = getCurScreen().getPlayer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport((int) _glviewport.x, (int) _glviewport.y, (int) _glviewport.width, (int) _glviewport.height);
		_camera.update();
		_batch.setProjectionMatrix(_camera.combined);
		
		
		_batch.begin();
		
		// render current screen
	    float delta = Math.min(Gdx.graphics.getDeltaTime(), 1.0f / 60.0f);
	    getCurScreen().render(delta);
	    
		_batch.end();
	}
	
	@Override
	public void dispose () {
		_batch.dispose();
		_assetManager.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		// calculate new viewport
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        if (aspectRatio > TextureFactory.ASPECT_RATIO) {
            scale = (float) height / (float) TextureFactory.VIEWPORT_HEIGHT;
            crop.x = (width - TextureFactory.VIEWPORT_WIDTH * scale) / 2.0f;
        } else if (aspectRatio < TextureFactory.ASPECT_RATIO) {
            scale = (float) width / (float) TextureFactory.VIEWPORT_WIDTH;
            crop.y = (height - TextureFactory.VIEWPORT_HEIGHT * scale) / 2.0f;
        } else {
            scale = (float) width / (float) TextureFactory.VIEWPORT_WIDTH;
        }
        float w = (float) TextureFactory.VIEWPORT_WIDTH * scale;
        float h = (float) TextureFactory.VIEWPORT_HEIGHT * scale;
        _glviewport.set(crop.x, crop.y, w, h);
        super.resize(width, height);
	}
	
	 // general screen change method
	public boolean changeScreen(String key) {
	        AbstractScreen nextScreen = _screens.get(key);
	        Screen curScreen = getCurScreen();
	        if(nextScreen == null || nextScreen == curScreen) 
	            return false;
	        // pause current screen
	        if(curScreen != null)
	            curScreen.pause();
	        // disable input
	        Gdx.input.setInputProcessor(null);
	        // change screen
	        setCurScreen(key);
	        // enable input
	        Gdx.input.setInputProcessor(nextScreen);
	        // reset some state of current screen
	        nextScreen.resume();
	        return true;
	    }

	public AbstractScreen getCurScreen() {
		return _screens.get(curScreen);
	}

	public void setCurScreen(String curScreen) {
		this.curScreen = curScreen;
	}

	public OrthographicCamera getCamera() {
		return _camera;
	}

	public void setCamera(OrthographicCamera _camera) {
		this._camera = _camera;
	}

	public SpriteBatch getBatch() {
		return _batch;
	}

	public void setBatch(SpriteBatch batch) {
		this._batch = batch;
	}

	public AssetManager getAssetManager() {
		   return _assetManager;
	}
	
}
		
