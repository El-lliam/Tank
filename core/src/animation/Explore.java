package animation;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.gameTable;


public class Explore extends Animation{
	
	public Explore(float x, float y, gameTable t) {
		super(x, y, t);
		int i = 0;
		for(Texture texture: array) {
			animation[i] = texture;
			i++;
		}
		lastingTime = 1;
	}
	
	
}
