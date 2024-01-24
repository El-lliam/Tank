package movingObjects;

import java.awt.Rectangle;

import com.mygdx.game.gameTable;

import bullets.Bullet;
import objects.Object;

public abstract class Tank extends MovingObject{
	

	protected double life;
    protected double MaxLife; 
    protected Bullet[] bullets;
    protected boolean leftMove;
	protected boolean rightMove;
	protected boolean upMove;
	protected boolean downMove;
	protected int direction; // 0 up, 1 right, 2 down, 3 left 

    public Tank(float x, float y, gameTable t) {
		super(x, y, t);
		direction = 0;
	}
    
    public void setLeftMove(boolean t)
	{
		if(rightMove && t) rightMove = false;
		if(upMove && t) upMove = false;
		if(downMove && t) downMove = false;
		leftMove = t;
	}
	public void setRightMove(boolean t)
	{
		if(leftMove && t) leftMove = false;
		if(upMove && t) upMove = false;
		if(downMove && t) downMove = false;
		rightMove = t;
	}
	public void setUpMove(boolean t)
	{
		if(leftMove && t) leftMove = false;
		if(rightMove && t) rightMove = false;
		if(downMove && t) downMove = false;
		upMove = t;
	}
	public void setDownMove(boolean t)
	{
		if(leftMove && t) leftMove = false;
		if(rightMove && t) rightMove = false;
		if(upMove && t) upMove = false;
		downMove = t;
	}
	
	public double getLife() {
		return life;
	}
	public void setLife(double life) {
		this.life = life;
	}
	public double getMaxLife() {
		return MaxLife;
	}
	public void setMaxLife(double maxLife) {
		MaxLife = maxLife;
	}
    
	

    public Bullet[] getBullets() {
		return bullets;
	}

	public void setBullets(Bullet[] bullets) {
		this.bullets = bullets;
	}

	public void loseLife(double damage) {
    	life = life - damage;
    }

	public boolean isLeftMove() {
		return leftMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public boolean isUpMove() {
		return upMove;
	}

	public boolean isDownMove() {
		return downMove;
	}
    
	/** shoot bullets 
	 * 
	 * @return Bullet[]
	 */
    public Bullet[] shoot(){
    	int xstep = 0;
    	int ystep = 0;
    	switch (direction) {
		case 0:
			xstep = getWidth()/2 - 7;
			ystep = getHeight() + 7;
			break;
		case 3:
			ystep = getHeight()/2 - 7;
			break;
		case 2:
			xstep = getWidth()/2 - 7;
			break;
		case 1:
			xstep = getWidth() + 7;
			ystep = getHeight()/2 - 7;
			break;
		default:
			break;
		}
        bullets[0] = new Bullet(x+xstep, y+ystep, table, this);
        return bullets;
    }
    
    /** Åö×²Ëã·¨ 
     * @param x x
     * @param y y
     * @param other another object
     * @return rectangle
     * */
    public boolean crash(float x, float y, Object other){
    	Rectangle rectangle = new Rectangle((int)x, (int)y, width, height);
    	return rectangle.intersects(other.getRectangle());
    }
    
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
		textureIndex = direction;
	}
    
	@Override
	public boolean outOfBounds(float x, float y) {
    	return x < 0 || x > 975-width || y < 0 || y > 975-height;
    }

}
