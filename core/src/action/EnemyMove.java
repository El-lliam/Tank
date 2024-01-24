package action;

import objects.Object;
import tanks.EnemyTank;

public class EnemyMove extends ObjectMove{

	public EnemyMove(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
	}

	@Override
	public void extraActions() {
		EnemyTank tank = (EnemyTank) objectD;
		int direction = tank.getDirection() + 1;
		if(direction > 3)
			direction = 0;
		
		if(tank.getY() >= 973.5f-tank.getHeight())
			tank.setDirection(direction);
		
		if(tank.getY() <= 1.5f)
			tank.setDirection(direction);
		
		if(tank.getX() >= 973.5f-tank.getWidth())
			tank.setDirection(direction);
		
		if(tank.getX() <= 1.5f)
			tank.setDirection(direction);
		
		//if(tank.detectCross())
			//tank.setDirection(direction);
	}
}
