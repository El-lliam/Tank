package action;

import com.mygdx.game.gameTable;

import objects.Object;

public class BulletShootBrick extends BulletAction{
	private gameTable table;
	
	public BulletShootBrick(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
		table = objectD.getTable();
	}

	@Override
	public void extraActions(){
	   	// delete brick
	   	for(Object o:table.getObjectTable()) {
	   		if(o != null && o.equals(objectA)) {
	   			int index = table.getObjectTable().indexOf(o);
	   			table.getObjectTable().set(index, null);
	   			break;
	   		}
	   			
	   	}
	}

}
