package COR;

import com.mygdx.game.gameTable;

import action.Action;
import action.ObjectMove;
import exception.PlayerDieException;
import movingObjects.Player;
import objects.Object;
import objects.Tree;

public class PlayerToTree extends Sensor{
	public PlayerToTree(Sensor n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	private ObjectMove playerMove;
	@Override
	public boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table) throws PlayerDieException {	
		if(objectD instanceof Player && objectA instanceof Tree) {
			playerMove = new ObjectMove(objectD, objectA, x, y);
			return true;
		}
		return false;
	}

	@Override
	public Action extraire() {
		return playerMove;
	}

	

}
