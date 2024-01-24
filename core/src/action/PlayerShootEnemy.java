package action;

import animation.Explore;
import movingObjects.Tank;
import objects.Object;
import tanks.EnemyTank;

public class PlayerShootEnemy extends BulletAction{

	public PlayerShootEnemy(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
	}

	@Override
	public void extraActions() {
		Tank tank = (Tank) objectA;
		tank.loseLife(bullet.getDamage());
		if(tank.getLife() <= 0) {
			// animation of exploring
			Explore explore = new Explore(tank.getX(), tank.getY(), tank.getTable());
			tank.getTable().addAnimation(explore);
			
			EnemyTank newTank = null;
			if(tank.getTable().getReserveTanks().size() > 0) {
				newTank = tank.getTable().getReserveTanks().get(0);
				tank.getTable().getReserveTanks().remove(0);
			}
			
			int index;
			// delete tank
			for(Object o:tank.getTable().getMovingObjectTable()) {
		   		if(o != null && o.equals(tank)) {
		   			index = tank.getTable().getMovingObjectTable().indexOf(o);
		   		 // we can't remove the object directly in the loop, so we put them null
		   			tank.getTable().getMovingObjectTable().set(index, newTank);
		   			break;
		   		}
		   	}
			for(Object o:tank.getTable().getObjectTable()) {
		   		if(o != null && o.equals(tank)) {
		   			index = tank.getTable().getObjectTable().indexOf(o);
		   		 // we can't remove the object directly in the loop, so we put them null
		   			tank.getTable().getObjectTable().set(index, newTank);
		   			break;
		   		}
		   	}
			
		}
	}

}
