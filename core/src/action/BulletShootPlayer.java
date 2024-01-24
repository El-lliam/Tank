package action;

import animation.Explore;
import exception.PlayerDieException;
import movingObjects.Player;
import objects.Object;

public class BulletShootPlayer extends BulletAction{

	public BulletShootPlayer(Object objectD, Object objectA, float x, float y) {
		super(objectD, objectA, x, y);
	}


	@Override
	public void extraActions() throws PlayerDieException{
		Player player = (Player) objectA;
		player.loseLife(bullet.getDamage());
		player.setX(player.getXorign());
		player.setY(player.getYorign());
		if(player.getLife() <= 0) {
			Explore explore = new Explore(player.getX(), player.getY(), player.getTable());
			player.getTable().addAnimation(explore);
			throw new PlayerDieException("YOU LOSE");
		}
	}
}
