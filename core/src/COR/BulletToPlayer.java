package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.BulletShootPlayer;
import bullets.Bullet;
import exception.PlayerDieException;
import movingObjects.Enemy;
import movingObjects.Player;
import objects.Object;

public class BulletToPlayer extends Sensor{
	private BulletShootPlayer bulletShootPlayer;
	
	public BulletToPlayer(Sensor n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof Bullet && objectA instanceof Player) {
			Bullet bullet = (Bullet) objectD;
			if(bullet.getObject() instanceof Enemy) {
				bulletShootPlayer = new BulletShootPlayer(objectD, objectA, x, y);
				return true;
			}
		}
		return false;
	}

	@Override
	public Action extraire() {
		return bulletShootPlayer;
	}

}
