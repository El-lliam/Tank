package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.EnemyMove;
import exception.PlayerDieException;
import objects.Object;
import objects.Tree;
import tanks.EnemyTank;

public class EnemytankToTree extends Sensor{

	private EnemyMove enemyMove;
	
	public EnemytankToTree(Sensor n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof EnemyTank && objectA instanceof Tree) {
			enemyMove = new EnemyMove(objectD, objectA, x, y);
			return true;
		}
		return false;
	}

	@Override
	public Action extraire() {
		return enemyMove;
	}

}
