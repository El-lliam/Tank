package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import COR.CorConstruction;
import COR.Sensor;
import action.Action;
import animation.Animation;
import bullets.Bullet;
import movingObjects.MovingObject;
import movingObjects.Player;
import objects.Object;
import plane.Plane;
import tanks.EnemyTank;

public class gameTable {
	public static final float blockHight = 37.5f;
	public static final float blockWidth = 37.5f;
	
	private Player player;
	ArrayList<Object> objectTable; // contains all of the objects except bullet (trees, bricks, players, tanks...)
	ArrayList<MovingObject> movingObjectTable;
	ArrayList<Animation> animationList;
	private Sensor sensor;  // chain of responsibility
	private Bullet[] bullets;
	private Bullet[] enemyBullets;
	private int numbrePlayerBulletsMax;
	private int numbreEnemyBulletsMax;
	private int enemyReserve;
	private ArrayList<EnemyTank> reserveTanks;
	private ArrayList<Bullet> planeBullets;
	
	/**
	 *  table size 975*975,
	 *  width->26 blocks,
	 *  height->26 blocks,
	 *  using table.txt for creating the table,
	 *  using classObjects.txt for getting the names of all the objects,
	 *  using tableSetting.txt for setting the rules of the game.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public gameTable() throws ClassNotFoundException, NoSuchMethodException, SecurityException, 
						InstantiationException, IllegalAccessException, IllegalArgumentException, 
						InvocationTargetException {
		movingObjectTable = new ArrayList<MovingObject>();
		objectTable = new ArrayList<Object>();
		animationList = new ArrayList<Animation>();
		reserveTanks = new ArrayList<EnemyTank>();
		planeBullets = new ArrayList<Bullet>();
		
		player = new Player(8*blockWidth, 0, this);
		objectTable.add(player);
		
	    String[] line;
		String[] content;
		ArrayList<String> classNames = new ArrayList<String>();
    	//load class names
    	FileHandle file = Gdx.files.internal("classObjects.txt");
    	String text = file.readString();
    	line = text.split("\r\n");
    	for(String s:line) {
    		if(!s.equals("")) {
    			content = s.split("=");
    			classNames.add(Integer.parseInt(content[0]), content[1]);
    		}
    	}
    	
    	// load table
		file = Gdx.files.internal("table.txt");
    	text = file.readString();
    	line = text.split("\r\n");
		for(int i = 25; i >= 0; i--) {
			content = line[i].split(" ");
			
			for(int j = 0; j < 26; j++) {
				int type = Integer.parseInt(content[j]);
				if(type == 0)
					continue;
				Class<?> class1 = Class.forName(classNames.get(type));
				Constructor<?> constructor = class1.getConstructor(float.class, float.class, gameTable.class);
				Object object = (Object) constructor.newInstance(j*blockWidth, (25-i)*blockHight, this);
				
				
				if(object instanceof MovingObject) {  // moving objects should be under other objects
					objectTable.add(1,object);
					movingObjectTable.add((MovingObject)object);
				}
				else 
					objectTable.add(object);
					
			}
		}
		
    	//load settings
    	file = Gdx.files.internal("tableSetting.txt");
    	text = file.readString();
    	line = text.split("\r\n");
		for(String s:line) {
			content = s.split("=");
			if(content[0].equals("numbrePlayerBulletsMax"))
				numbrePlayerBulletsMax = Integer.parseInt(content[1]);
			if(content[0].equals("numbreEnemyBulletsMax"))
				numbreEnemyBulletsMax = Integer.parseInt(content[1]);
			if(content[0].equals("enemyReserve"))
				enemyReserve = Integer.parseInt(content[1]);
			if(content[0].equals("positions")) {
				String[] positions = content[1].split(";");
				for(int i = 0; i < enemyReserve; i++) {
					String[] position = positions[i].split(",");
					EnemyTank tank = new EnemyTank(Integer.parseInt(position[0])*blockWidth, Integer.parseInt(position[1])*blockHight, this);
					reserveTanks.add(tank);
				}
			}
				
		}
		
		Plane plane = new Plane(0, 0, this);
		objectTable.add(plane);
		movingObjectTable.add(plane);
		
		bullets = new Bullet[numbrePlayerBulletsMax];
		enemyBullets = new Bullet[numbreEnemyBulletsMax];
		CorConstruction cor = new CorConstruction();
		sensor = cor.constructCOR();
	}

	

	public ArrayList<MovingObject> getMovingObjectTable() {
		return movingObjectTable;
	}



	public void setMovingObjectTable(ArrayList<MovingObject> movingObjectTable) {
		this.movingObjectTable = movingObjectTable;
	}



	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	public ArrayList<Object> getObjectTable() {
		return objectTable;
	}

	public void setObjectTable(ArrayList<Object> objectTable) {
		this.objectTable = objectTable;
	}

	
	/**
	 * Detect objet's action
	 * @param objetD
	 * @param objectA
	 * @param x
	 * @param y
	 * @throws Exception
	 */
	public void objectDetect(Object objetD, Object objectA, float x, float y) throws Exception {
		assert sensor != null : "Error : the chain of responsibility is vide...";
		if(objetD != null) {
			Action action = sensor.detect(objetD, objectA, x, y, this);
			if (action != null)
				action.treat();
		}
	}
	
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Bullet[] getBullets() {
		return bullets;
	}

	public void setBullets(Bullet[] bullets) {
		this.bullets = bullets;
	}

	public Bullet[] getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(Bullet[] enemyBullets) {
		this.enemyBullets = enemyBullets;
	}

	

	public ArrayList<EnemyTank> getReserveTanks() {
		return reserveTanks;
	}



	public void setReserveTanks(ArrayList<EnemyTank> reserveTanks) {
		this.reserveTanks = reserveTanks;
	}



	public ArrayList<Animation> getAnimationList() {
		return animationList;
	}
	
	public void addAnimation(Animation animation) {
		this.animationList.add(animation);
	}



	public ArrayList<Bullet> getPlaneBullets() {
		return planeBullets;
	}



	public void setPlaneBullets(ArrayList<Bullet> planeBullets) {
		this.planeBullets = planeBullets;
	}

	
	
	
}
