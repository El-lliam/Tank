package movingObjects;

import com.mygdx.game.gameTable;

import bullets.Bullet;
import objects.Object;

public abstract class MovingObject extends Object{
	private float speed;
	
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public MovingObject(float x, float y, gameTable t) {
		super(x, y, t);
	}

	/**
     * Check for out of bounds
     * @param x the position
     * @return true if out of bounds
     */
    public boolean outOfBounds(float x, float y) {
    	return x < -width || x > 975 || y < -height || y > 975;
    }

    /**
     * Flying objects move one step
     */
    public abstract void step();
    
    /**
     * Check if the current flying object is hit by a bullet (x,y) (shoot)
     * @param Bullet class bullet 
     * @return true Indicates a hit
     */
    public boolean shootBy(Bullet bullet){
        return this.getRectangle().intersects(bullet.getRectangle());
    }
}
