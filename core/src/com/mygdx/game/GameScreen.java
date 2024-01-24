package com.mygdx.game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import animation.Animation;
import bullets.Bullet;
import constante.TextureFactory;
import movingObjects.Enemy;
import movingObjects.MovingObject;
import movingObjects.Player;
import objects.Object;

public class GameScreen extends AbstractScreen {
	private TextureRegion _boardBackground;
	private Texture border;
	private gameTable table;
	private ArrayList<Object> objects;
	private ArrayList<MovingObject> movingObjects;
	private float time = 0;  // counter
	
	public GameScreen(MyGdxGame game) {
        super(game);
        assignAssets();
        try {
        	
			table = new gameTable();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        player = table.getPlayer();
        objects = table.getObjectTable();
        movingObjects = table.getMovingObjectTable();
        _camera.position.set(600, 480, 0);
        border = new Texture(createProceduralPixmap(_boardBackground.getRegionWidth()+2, _boardBackground.getRegionHeight()+2));
    }

	/** update game world **/
	@Override
	public void update(float deltaTime) {
		time += Gdx.graphics.getDeltaTime();
		
		
		_batch.draw(_boardBackground, 0, 0);
		_batch.draw(border, -1, -1);
		
		
		// draw
		drawTable();
		
		// animation
		showAnimation();
		
		// clean the table
		ajustTable();
				
		// moving actions
		moveAction();
				
		// clean moving objects which are out of bounds
		outOfBoundsAction();
				
		// Bullet and objects collision detection
		bangAction();
		
		// clear
		clear();
	}
	
	/** render current scene **/
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_camera.update();
		_batch.setProjectionMatrix(_camera.combined);
		
		int positionX = 1025;
		int positionY = 550;
		
		// draw life
		for(int i = 0; i < player.getLife(); i++) {
			_batch.draw(TextureFactory.getHeart(), positionX, positionY);
			positionX += TextureFactory.getHeart().getWidth();
		}
		
		positionX = 1025;
		positionY -= 100;
		// draw enemies quantities
		for(MovingObject o:table.getMovingObjectTable()) {
			if(o instanceof Enemy) {
				_batch.draw(TextureFactory.getTexture(o.getClass(), 0), positionX, positionY);
				positionX += TextureFactory.getTexture(o.getClass(), 0).getWidth() + 10;
			}
		}
		

		if(movingObjects.size() <= 1) {
			_parent.changeScreen("victoryScreen");
		}
		if(player.getLife() <= 0) {
			_parent.changeScreen("loseScreen");
		}
			
	}
	
	
	/** window resize **/
	@Override
	public void resize(int width, int height) {
	}

	/** in advance load assets **/
	@Override
	public void show() {
		_assetManager.load("Fond.png", Texture.class);
	}

	/** unload assets **/
	@Override
	public void hide() {
		_assetManager.unload("Fond.png");
		border.dispose();
	}

	/** game pause **/
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	/** initialize or reset current screen state **/
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	/** nonuse : repalce by hide method**/
	@Override
	public void dispose() {}

	/** get and initialize assets **/
	@Override
	public void assignAssets() {
        _boardBackground = new TextureRegion(TextureFactory.getFond());
        _boardBackground.flip(false, true);
	}


	public gameTable getTable() {
		return table;
	}

	public void setTable(gameTable table) {
		this.table = table;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
    public boolean keyDown(int keycode) {
		try {
			switch (keycode) {
				case Keys.UP: {
					player.setDirection(0);
					player.setUpMove(true);
					break;
				}
				case Keys.DOWN: {
					player.setDirection(2);
					player.setDownMove(true);
					break;
				}
				case Keys.RIGHT: {
					player.setDirection(1);
					player.setRightMove(true);
					break;
				}
				case Keys.LEFT: {
					player.setDirection(3);
					player.setLeftMove(true);
					break;
				}
				case Keys.SPACE:{
					Bullet[] bs = player.shoot(); // player shoots
	        		for(int i = 0; i < table.getBullets().length; i++) {
	        			if(table.getBullets()[i] == null) {
	        				table.getBullets()[i] = bs[0];
	        				break;
	        			}
	        				
	        		}
					break;
				}
				default:
					return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
    }
	
	@Override
    public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.UP: {
			player.setUpMove(false);
			break;
		}
		case Keys.DOWN: {
			player.setDownMove(false);
			break;
		}
		case Keys.RIGHT: {
			player.setRightMove(false);
			break;
		}
		case Keys.LEFT: {
			player.setLeftMove(false);
			break;
		}
		}
		return true;
    }
	
	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		try {
			if(screenX >= 266 && screenX <= 533 && screenY >= 0 && screenY <= 200) {
				player.setDirection(0);
				player.setUpMove(true);
	
			}
			else if(screenX >= 266 && screenX <= 533 && screenY >= 400 && screenY <= 600){
				player.setDirection(2);
				player.setDownMove(true);
			}
			else if(screenX >= 533 && screenX <= 800 && screenY >= 200 && screenY <= 400){
				player.setDirection(1);
				player.setRightMove(true);
			}
			else if(screenX >= 0 && screenX <= 266 && screenY >= 200 && screenY <= 400){
				player.setDirection(3);
				player.setLeftMove(true);
			}
			else if(screenX >= 266 && screenX <= 533 && screenY >= 200 && screenY <= 400){
				Bullet[] bs = player.shoot(); // player shoots
        		for(int i = 0; i < table.getBullets().length; i++) {
	       			if(table.getBullets()[i] == null) {
	       				table.getBullets()[i] = bs[0];
	       			}
	       				
	      		}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	if(screenX >= 266 && screenX <= 533 && screenY >= 0 && screenY <= 200) {
			player.setUpMove(false);
		}
		else if(screenX >= 266 && screenX <= 533 && screenY >= 400 && screenY <= 600){
			player.setDownMove(false);
		}
		else if(screenX >= 533 && screenX <= 800 && screenY >= 200 && screenY <= 400){
			player.setRightMove(false);
		}
		else if(screenX >= 0 && screenX <= 266 && screenY >= 200 && screenY <= 400){
			player.setLeftMove(false);
		}
        return true;
    }
	
	public void drawTable() {
		// draw table
		for(Object o: objects) {
			if(o != null) {
				_batch.draw(TextureFactory.getTexture(o.getClass(), o.getTextureIndex()), o.getX(), o.getY());
			}
		}
		// bullets
		for(Bullet b:table.getBullets()) {
			if(b != null) {
				_batch.draw(TextureFactory.getTexture(b.getClass(), b.getTextureIndex()), b.getX(), b.getY());
			}
		}
		// enemy bullets
		for(Bullet b:table.getEnemyBullets()) {
			if(b != null) {
				_batch.draw(TextureFactory.getTexture(b.getClass(), b.getTextureIndex()), b.getX(), b.getY());
			}
		}
		for(Bullet b:table.getPlaneBullets()) {
			if(b != null) {
				_batch.draw(TextureFactory.getTexture(b.getClass(), b.getTextureIndex()), b.getX(), b.getY());
			}
		}
		// animation
		for(Animation animation: table.getAnimationList()) {
			if(animation != null) {
				_batch.draw(TextureFactory.getTexture(animation.getClass(), animation.getTextureIndex()), animation.getX(), animation.getY());
			}
		}
	}
	
	public void outOfBoundsAction() {
		
		// clean bullets
		int index = 0; // index
        Bullet[] bulletLives = new Bullet[table.getBullets().length];
		for(Bullet b:table.getBullets()) {
			if(b != null) {
				if (!b.outOfBounds(b.getX(), b.getY())) {
	                bulletLives[index++] = b;
	            }
			}
		}
		table.setBullets(bulletLives); // keep the bullets which are not out of bounds
		
		// clean enemy bullets
		index = 0; // index
        bulletLives = new Bullet[table.getEnemyBullets().length];
		for(Bullet b:table.getEnemyBullets()) {
			if(b != null) {
				if (!b.outOfBounds(b.getX(), b.getY())) {
	                bulletLives[index++] = b;
	            }
			}
		}
		table.setEnemyBullets(bulletLives); // keep the bullets which are not out of bounds
		
		index = -1;
		int i = 0;
		for(Bullet b:table.getPlaneBullets()) {
			if(b != null) {
				if(b.getVectX() >= b.getX()-20 && b.getVectX() <= b.getX()+20 && b.getVectY() >= b.getY()-20 && b.getVectY() <= b.getY()+20)
					index = i;
				i++;
			}
		}
		if(table.getPlaneBullets().size() != 0) {
			if(index != -1) {
			
				table.getPlaneBullets().remove(index);
			}
		}
			
	}
	
	public void moveAction() {
		// player
		player.step();
		
		// bullets
		for(Bullet b:table.getBullets()) {
			if(b != null) {
				b.step();
			}
		}
		// enemy bullets
		for(Bullet b:table.getEnemyBullets()) {
			if(b != null) {
				b.step();
			}
		}
		for(Bullet b:table.getPlaneBullets()) {
			if(b != null) {
				b.step();
			}
		}
		
		// movingObjects
		for(MovingObject o:movingObjects) {
			if(o != null) {
				o.step();
			}
		}
	}
	
	 /** Bullet and object collision detection */
    public void bangAction() {
        for (Bullet b: table.getBullets()) { // Iterate through all bullets
        	if(b != null)
        		bang(b); // Collision check between bullets and objects
        }
        
        // enemy bullets
        for(Bullet b:table.getEnemyBullets()) {
        	if(b != null)
        		bang(b);
        }
        for(Bullet b:table.getPlaneBullets()) {
        	if(b != null)
        		bang(b);
        }
    }
    
	/** Bullet and object collision detection */
    public void bang(Bullet bullet) {
    	/**
    	 * Objects collide with bullets
    	 */
        for (Object o: table.getObjectTable()) {
        	if(o != null) {
        		if (bullet.hit(o)) { // Determine if a hit has been made
        			try {
						table.objectDetect(bullet, o, o.getX(), o.getY());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
        }
    }
    
    /**
     * show animation
     */
    public void showAnimation() {
    	for(Animation animation: table.getAnimationList()) {
    		if(animation != null) {
    			animation.anime();
    		}
    	}
    }
    
    /**
     * clean the null pointer in arraylist of objects
     */
    public void ajustTable() {
    	Iterator<Object> iterator = objects.iterator();
	   	while (iterator.hasNext()) {
	   	    Object o = iterator.next();
	   	    if(o == null) {
		   	    iterator.remove();
	   			break;
	   		}
	   	}
	   	
	   	Iterator<MovingObject> iterator2 = movingObjects.iterator();
	   	while (iterator2.hasNext()) {
	   		MovingObject o = iterator2.next();
	   	    if(o == null) {
		   	    iterator2.remove();
	   			break;
	   		}
	   	}
    }
    
    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        // Draw a cyan-colored border around square
        pixmap.setColor(0.94f, 0.909f, 0.29f, 1);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;
    }
    
    public void clear() {
    	// clean the animations
    	ArrayList<Integer> index = new ArrayList<Integer>();
    	int i = 0;
    	for(Animation animation: table.getAnimationList()) {
    		if(animation != null) {
    			if(animation.getLastingTime() == 0) {
    				index.add(i);
    				i++;
    			}
    		}
    	}
    	for(int j:index) {
    		table.getAnimationList().remove(j);
    	}
    	
    }
}
