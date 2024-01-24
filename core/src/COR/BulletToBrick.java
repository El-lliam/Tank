package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.BulletShootBrick;
import bullets.Bullet;
import exception.PlayerDieException;
import objects.Brick;
import objects.Object;

public class BulletToBrick extends Sensor{
	private BulletShootBrick bulletShootBrick;
	public BulletToBrick(Sensor n) {
		super(n);
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof Bullet && objectA instanceof Brick) {
			bulletShootBrick = new BulletShootBrick(objectD, objectA, x, y);
			return true;
		}
		return false;
	}

	@Override
	public Action extraire() {
		return bulletShootBrick;
	}

}
