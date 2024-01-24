package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.BulletShootStrongbrick;
import bullets.Bullet;
import exception.PlayerDieException;
import objects.Object;
import objects.StrongBrick;

public class BulletToStrongbrick extends Sensor{
	private BulletShootStrongbrick bulletShootBrick;
	public BulletToStrongbrick(Sensor n) {
		super(n);
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof Bullet && objectA instanceof StrongBrick) {
			bulletShootBrick = new BulletShootStrongbrick(objectD, objectA, x, y);
			return true;
		}
		return false;
	}

	@Override
	public Action extraire() {
		return bulletShootBrick;
	}

}
