package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.PlayerShootEnemy;
import bullets.Bullet;
import exception.PlayerDieException;
import movingObjects.Enemy;
import movingObjects.Player;
import objects.Object;

public class BulletToEnemy extends Sensor{

	private PlayerShootEnemy playerShootEnemy;
	
	public BulletToEnemy(Sensor n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof Bullet && objectA instanceof Enemy) {
			Bullet bullet = (Bullet) objectD;
			if(bullet.getObject() instanceof Player) {
				playerShootEnemy = new PlayerShootEnemy(objectD, objectA, x, y);
				return true;
			}
				
		}
		return false;
	}

	@Override
	public Action extraire() {
		return playerShootEnemy;
	}

}
