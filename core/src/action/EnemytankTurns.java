package action;

import java.util.ArrayList;
import java.util.Random;

import objects.Object;
import objects.Tree;
import tanks.EnemyTank;

public class EnemytankTurns extends Action{
	private EnemyTank tank;

	public EnemytankTurns(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
		tank = (EnemyTank) objectD;
	}

	@Override
	public void treat() throws Exception {
		if(tank.isHuntX())
			tank.setHuntX(false);
		else
			tank.setHuntX(true);
		
		ArrayList<Integer> cantTurnList = new ArrayList<Integer>();
		ArrayList<Integer> directionList = new ArrayList<Integer>();
		directionList.add(0);
		directionList.add(1);
		directionList.add(2);
		directionList.add(3);
		
		for(Object o: tank.getTable().getObjectTable()) {  // detect which direction has objects
			if(o != null && !o.equals(tank) && !(o instanceof Tree)) {
				// up
				if(tank.crash(tank.getX(), tank.getY()+5, o))
					cantTurnList.add(0);
				// down
				if(tank.crash(tank.getX(), tank.getY()-5, o))
					cantTurnList.add(2);
				// right
				if(tank.crash(tank.getX()+5, tank.getY(), o))
					cantTurnList.add(1);
				// left
				if(tank.crash(tank.getX()-5, tank.getY(), o))
					cantTurnList.add(3);
			}
		}
		
		for(Integer i:cantTurnList) { // delete the direction with objects
			if(directionList.contains(i))
				directionList.remove(i);
		}
		
		Random random = new Random();
		if(directionList.size() != 0)
			tank.setDirection(directionList.get(random.nextInt(directionList.size())));

	}

}
