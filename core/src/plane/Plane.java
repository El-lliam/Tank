package plane;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.gameTable;

import animation.place;
import bullets.Bullet;
import constante.TextureFactory;
import movingObjects.Enemy;
import movingObjects.MovingObject;

public class Plane extends MovingObject implements Enemy{
	private float desX;
	private float desY;
	
	private float rateX;
	private float rateY;
	
	private int bulletRate;
	private int counter;
	
	private Bullet[] bullets;

	public Plane(float x, float y, gameTable t) {
		super(x, y, t);
		
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
		
		
		this.x = x - width; 
		this.y = y - height;
		 
		
		String[] line;
    	//mettre le txt
    	FileHandle file = Gdx.files.internal("plane.txt");
    	String text = file.readString();
    	line = text.split("\r\n");
		String[] content;
		for(String s:line) {
			content = s.split("=");	
			if(content[0].equals("k"))
				setSpeed(Float.parseFloat(content[1]));
			if(content[0].equals("desX"))
				desX = Float.parseFloat(content[1]);
			if(content[0].equals("desY"))
				desY = Float.parseFloat(content[1]);
			if(content[0].equals("bulletRate"))
				bulletRate = Integer.parseInt(content[1]);
		}
		counter = bulletRate;
		rateX = getSpeed() * (desX - x);
		rateY = getSpeed() * (desY - y);
		bullets = new Bullet[1];
	}

	@Override
	public void step() {
		x = x + rateX * Gdx.graphics.getDeltaTime();
		y = y + rateY * Gdx.graphics.getDeltaTime();
		counter -= Gdx.graphics.getDeltaTime();
		if(counter <= 0) {
			counter = bulletRate;
			Bullet[] bullets = shoot();
			table.getPlaneBullets().add(bullets[0]);
		}
	}

	/** shoot bullets 
	 * 
	 * @return Bullet[]
	 */
    public Bullet[] shoot(){
    	float xstep = 0;
    	float ystep = 0;
    	Random random = new Random();
		if(random.nextInt(2) == 0) {
			int x = random.nextInt(100);
			xstep = this.y + x;
			ystep = this.x + x;
		}
		else {
			int x = random.nextInt(100);
			xstep = this.y + x;
			ystep = this.x + x;
		}
        bullets[0] = new Bullet(x + getWidth()/2, y + getHeight()/2, table, this);
        bullets[0].setVectX(xstep);
        bullets[0].setVectY(ystep);
        //place place1 = new place(xstep, ystep, table);
        //table.addAnimation(place1);
        return bullets;
    }
}
