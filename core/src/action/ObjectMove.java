package action;

import movingObjects.MovingObject;
import objects.Object;

public class ObjectMove extends Action{
	private MovingObject object;
	
	public ObjectMove(Object objectD, Object objectA, float x, float y) {
		super(objectD,objectA,x,y);
		this.object = (MovingObject)objectD;
	}

	@Override
	public void treat() throws Exception {
		if(!object.outOfBounds(x, y)) {
			object.setX(x);
			object.setY(y);
		}
		extraActions();
	}

	public void extraActions() {}
}
