package action;

import objects.Object;

public abstract class Action {
	protected Object objectD;
	protected Object objectA;
	protected float x, y;
	
	public Action(Object objectD, Object objectA, float x, float y) {
		this.objectD = objectD;
		this.objectA = objectA;
		this.x = x;
		this.y = y;
	}
	
	public abstract void treat() throws Exception;
}
