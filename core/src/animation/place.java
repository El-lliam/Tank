package animation;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.gameTable;

public class place extends Animation{

	public place(float x, float y, gameTable t) {
		super(x, y, t);
		int i = 0;
		for(Texture texture: array) {
			animation[i] = texture;
			i++;
		}
		lastingTime = 1;
	}
	
	@Override
	public void anime() {
		textureIndex = 0;
	}

}
