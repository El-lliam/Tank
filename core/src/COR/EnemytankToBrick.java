package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.EnemytankTurns;
import exception.PlayerDieException;
import objects.Brick;
import objects.Object;
import tanks.EnemyTank;

public class EnemytankToBrick extends Sensor{

	EnemytankTurns enemytankTurns;
	public EnemytankToBrick(Sensor n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table)
			throws PlayerDieException {
		if(objectD instanceof EnemyTank && objectA instanceof Brick) {
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
