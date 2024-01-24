package action;

import bullets.Bullet;
import objects.Object;
import tanks.EnemyTank;

public abstract class BulletAction extends Action{
	protected Bullet[] bullets;
	protected Bullet bullet;

	public BulletAction(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
		bullet = (Bullet)objectD;
		if(bullet.getObject() instanceof EnemyTank)
			bullets = objectD.getTable().getEnemyBullets();
		else
			bullets = objectD.getTable().getBullets();
	}

	@Override
	public void treat() throws Exception {
		int pos = -1;
   	 	// delete bullet
		for(int i = 0; i < bullets.length; i++) {
			if(bullets[i] != null && bullets[i].equals(bullet)) {
				pos = i;
				break;
			}
	   	}
	   	if(pos != -1) {
	   		bullets[pos] = null;
	   	}
	   	extraActions();
	}
	
	public void extraActions() throws Exception {}
}
