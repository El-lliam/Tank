package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import movingObjects.Player;

import com.badlogic.gdx.InputProcessor;

public abstract class AbstractScreen implements Screen , InputProcessor {
	private enum State {Loading, Done};
	private State _state;
	
	protected MyGdxGame _parent;
	protected OrthographicCamera _camera;
	protected SpriteBatch _batch;
	protected AssetManager _assetManager;
	protected Player player;
	
	
	public AbstractScreen(MyGdxGame game) {
        this._parent = game;
        this._camera = game.getCamera();
        this._batch = game.getBatch();
        _assetManager = game.getAssetManager();
    }
	
	@Override
	public void render(float delta) {
		if(_state == State.Loading) {
			if(_assetManager.update()) {
				assignAssets();
				_state = State.Done;
			}
		} else {
			render();
			update(delta);
		}
	}
	
	
    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public abstract void render();
	
    @Override
    public abstract void resize(int width, int height);
    @Override
    public abstract void show();
    @Override
    public abstract void hide();
    @Override
    public abstract void pause();
    @Override
    public abstract void resume();
    @Override
    public abstract void dispose();
    public abstract void update(float delta);   // update game world
    
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    public abstract void assignAssets();        // get assets
}
