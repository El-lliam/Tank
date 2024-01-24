package COR;

import com.mygdx.game.gameTable;

import action.Action;
import exception.PlayerDieException;
import objects.Object;

public abstract class Sensor {
	private Sensor next;
	
	public Sensor(Sensor n) {
		next = n;
	}
	
	/**
	 * deterct if the objectD could go to (x,y)
	 * @param objectD : object needing action
	 * @param objectA : object is hitted (crashed)
	 * @param x : Horizontal coordinate the object is going
	 * @param y : Vertical coordinate the object is goning
	 * @param table
	 * @return
	 * @throws PlayerDieException
	 */
	public abstract boolean isDetected(Object objectD, Object objectA, float x, float y, gameTable table) throws PlayerDieException;
	
	public abstract Action extraire();

	public Sensor getSuivant() {
		return next;
	}

	public void setSuivant(Sensor next) {
		this.next = next;
	}
	
	public Action detect(Object objectD, Object objectA, float x, float y, gameTable table) throws PlayerDieException {
		
		if(isDetected(objectD, objectA, x, y, table))
			return extraire();
		else if(next != null)
			return next.detect(objectD, objectA, x, y, table);
		else
			return null;
	}
}
