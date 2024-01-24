package tanks;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.gameTable;

import action.EnemyMove;
import bullets.Bullet;
import constante.TextureFactory;
import objects.Object;

public class TankBoss extends EnemyTank{


	private float countDown = 0.6f;
	
	private int frequency;
	
	private int iter;
	
	public TankBoss(float x, float y, gameTable t) {
		super(x, y, t);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
		
		String[] line;
    	//mettre le txt
    	FileHandle file = Gdx.files.internal("tankBoss.txt");
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
	}

	
	@Override
	public void step() {
		countDown -= Gdx.graphics.getDeltaTime();
		// randomly change moving direction per 3 seconds
		if(countDown <= 0) {
	        countDown += 0.6f; // add three seconds
	        hunt();
		}
		
		
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
	

}
