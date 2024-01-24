package bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.gameTable;

import constante.TextureFactory;
import movingObjects.MovingObject;
import movingObjects.Tank;
import objects.Object;
import plane.Plane;

public class Bullet extends MovingObject{

	boolean leftMove;
	boolean rightMove;
	boolean upMove;
	boolean downMove;
	private int damage;
	private MovingObject object;
	private float vectX;
	private float vectY;
	private float rateX;
	private float rateY;
	
	public Bullet(float x, float y, gameTable t, MovingObject object) {
		super(x, y, t);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
		
		this.object = object;
		if(object instanceof Tank) {
			Tank tank = (Tank)object;
			leftMove = tank.isLeftMove();
			rightMove = tank.isRightMove();
			upMove = tank.isUpMove();
			downMove = tank.isDownMove();
			switch (tank.getDirection()) {
			case 0:
				upMove = true;
				break;
			case 3:
				leftMove = true;
				break;
			case 2:
				downMove = true;
				break;
			case 1:
				rightMove = true;
				break;
			default:
				upMove = true;
				break;
			}
		}
		
		
		
		String[] line;
    	//mettre le txt
    	FileHandle file = Gdx.files.internal("bullet.txt");
    	String text = file.readString();
    	line = text.split("\r\n");
		String[] content;
		for(String s:line) {
			content = s.split("=");
			if(content[0].equals("damage"))
				damage = Integer.parseInt(content[1]);
			if(content[0].equals("speed"))
				setSpeed(Integer.parseInt(content[1]));
		}
	}

	@Override
	public boolean outOfBounds(float x, float y) {
		return x < 0 || x > 975-width || y < 0 || y > 975;
	}

	@Override
	public void step() {
		if(object instanceof Tank) {
			if(downMove) {
				y -= getSpeed();
			}
			if(upMove) {
				y += getSpeed();
			}
			if(leftMove) {
				x -= getSpeed();
			}
			if(rightMove) {
				x += getSpeed();
			}
		}
		else if(object instanceof Plane) {
			x = x + rateX * Gdx.graphics.getDeltaTime();
			y = y + rateY * Gdx.graphics.getDeltaTime();
			System.out.println(x);
		}
		
	}
	
	/** Åö×²Ëã·¨ */
    public boolean hit(Object other){
    	return this.getRectangle().intersects(other.getRectangle());
    }

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public MovingObject getObject() {
		return object;
	}

	public void setObject(MovingObject object) {
		this.object = object;
	}

	public float getVectX() {
		return vectX;
	}

	public void setVectX(float vectX) {
		this.vectX = vectX;
		rateX = 0.5f * (vectX - x);
	}

	public float getVectY() {
		return vectY;
	}

	public void setVectY(float vectY) {
		this.vectY = vectY;
		rateY = 0.5f * (vectY - y);
	}

    
}
