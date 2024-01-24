package tanks;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.gameTable;

import action.EnemyMove;
import bullets.Bullet;
import constante.TextureFactory;
import movingObjects.Enemy;
import movingObjects.Player;
import movingObjects.Tank;
import objects.Object;

public class EnemyTank extends Tank implements Enemy{

	private int frequency;
	
	private int iter;
	
	private Player player;
	
	private boolean huntX = true; // x first
	
	public EnemyTank(float x, float y, gameTable t) {
		super(x, y, t);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
		
		String[] line;
    	//mettre le txt
    	FileHandle file = Gdx.files.internal("enemyTank.txt");
    	String text = file.readString();
    	line = text.split("\r\n");
		String[] content;
		for(String s:line) {
			content = s.split("=");
			if(content[0].equals("life")) {
				life = Integer.parseInt(content[1]);
				MaxLife = life;
			}
				
			if(content[0].equals("speed"))
				setSpeed(Integer.parseInt(content[1]));
			if(content[0].equals("bulletFreq"))
				frequency = Integer.parseInt(content[1]);
			if(content[0].equals("direction"))
				setDirection(Integer.parseInt(content[1]));
		}
		Move();
		bullets = new Bullet[1];
		iter = frequency;
		player = table.getPlayer();
	}

	public void randomMove() {
		Random random = new Random();
		setDirection(random.nextInt(3));
	}
	
	public void Move() {
		switch(direction) {
		case 0:
			setUpMove(true);
			break;
		case 1:
			setRightMove(true);
			break;
		case 2:
			setDownMove(true);
			break;
		case 3:
			setLeftMove(true);
			break;
			default:
				break;
		}
	}
	
	@Override
	public void step() {
		
		// move
		try {
			Move();
			boolean ishited = false; // if player has beening moving to an object
			float iterX = x;
			float iterY = y;
			if(upMove) {
				iterY = y + getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {  // use chain of responsibility to detect if play can move
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, x, iterY);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					EnemyMove move = new EnemyMove(this, null, x, iterY);
					move.treat();
				}
			}
			if(downMove) {
				iterY = y - getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, x, iterY);
						ishited = true;
						iterY = y;
						break;
					}
				}
				if(!ishited) {
					EnemyMove move = new EnemyMove(this, null, x, iterY);
					move.treat();
				}
			}
			if (leftMove)
			{
				iterX = x - getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, iterX, y);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					EnemyMove move = new EnemyMove(this, null, iterX, y);
					move.treat();
				}

			}
			if (rightMove)
			{
				iterX = x + getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, iterX, y);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					EnemyMove move = new EnemyMove(this, null, iterX, y);
					move.treat();
				}
			}
        }catch (Exception e) {
			e.printStackTrace();
		}
		
		// shoot
		iter -= Gdx.graphics.getDeltaTime();
		if(iter <= 0) {
			Bullet[] bs = this.shoot();
			for(int i = 0; i < table.getEnemyBullets().length; i++) {
    			if(table.getEnemyBullets()[i] == null) {
    				table.getEnemyBullets()[i] = bs[0];
    				break;
    			}
    		}
			iter = frequency;
		}
	}
	
	public void hunt() {
		if(huntX) {
			if(x < player.getX() - 15)
				setDirection(1);
			else if(x > player.getX() + 15)
				setDirection(3);
			else if(y > player.getY() + 15)
				setDirection(2);
			else if(y < player.getY() - 15)
				setDirection(0);
		}
		else {
			if(y > player.getY() + 15)
				setDirection(2);
			else if(y < player.getY() - 15)
				setDirection(0);
			else if(x < player.getX() - 15)
				setDirection(1);
			else if(x > player.getX() + 15)
				setDirection(3);
		}
		
	}

	public boolean isHuntX() {
		return huntX;
	}

	public void setHuntX(boolean huntX) {
		this.huntX = huntX;
	}

	
}
