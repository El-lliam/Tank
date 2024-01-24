package animation;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.gameTable;

import constante.TextureFactory;
import objects.Object;

public abstract class Animation extends Object{

	protected Texture[] animation;
	protected ArrayList<Texture> array;
	protected float lastingTime;
	
	public Animation(float x, float y, gameTable t) {
		super(x, y, t);
		array = TextureFactory.getAnimation(this.getClass());
		animation = new Texture[array.size()];
	}

	public void anime() {
		textureIndex = (textureIndex+1)%(animation.length+1);
		if(textureIndex == array.size())
			lastingTime = 0;
	}


	public float getLastingTime() {
		return lastingTime;
	}

	public void setLastingTime(float lastingTime) {
		this.lastingTime = lastingTime;
	}
}
