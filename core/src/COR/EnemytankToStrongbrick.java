package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.EnemytankTurns;
import exception.PlayerDieException;
import objects.Object;
import objects.StrongBrick;
import tanks.EnemyTank;

public class EnemytankToStrongbrick extends Sensor{

	EnemytankTurns enemytankTurns;
	public EnemytankToStrongbrick(Sensor n) {
		super(n);
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof EnemyTank && objectA instanceof StrongBrick) {
			enemytankTurns = new EnemytankTurns(objectD, objectA, x, y);
			return true;
		}
		return false;
	}

	@Override
	public Action extraire() {
		return enemytankTurns;
	}

}
