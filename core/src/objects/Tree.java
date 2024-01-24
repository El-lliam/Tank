package objects;

import com.mygdx.game.gameTable;

import constante.TextureFactory;

public class Tree extends stillObject{
	public Tree(float x, float y, gameTable t) {
		super(x, y, t);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
	}
}
