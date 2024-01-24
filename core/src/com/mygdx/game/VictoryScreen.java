package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import constante.TextureFactory;

public class VictoryScreen extends AbstractScreen{

	private TextureRegion _backgroud;
	
	public VictoryScreen(MyGdxGame game) {
		super(game);
		assignAssets();
		_camera.position.set(600, 480, 0);
		
	}

	@Override
	public void render() {
		_batch.draw(_backgroud, 0, 0);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		_assetManager.load("victory.png", Texture.class);
	}

	@Override
	public void hide() {
		_assetManager.unload("victory.png");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignAssets() {
	     _backgroud = new TextureRegion(TextureFactory.getVictory());
	     _backgroud.flip(false, true);
	}

}
