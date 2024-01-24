package objects;

import java.awt.Rectangle;

import com.mygdx.game.gameTable;

public abstract class Object {
	protected int textureIndex;
	protected gameTable table;
	protected float x;    //Horizontal coordinate
    protected float y;    //Vertical coordinate
    protected int width;    
    protected int height;   
    
    public Object(float x, float y, gameTable t) {
    	super();
		this.x = x;
		this.y = y;
		table = t;
		textureIndex = 0;
	}
    
    public int getTextureIndex() {
		return textureIndex;
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public gameTable getTable() {
		return table;
	}
	public void setTable(gameTable table) {
		this.table = table;
	}

	public Rectangle getRectangle() {
    	return new Rectangle((int)x, (int)y, width, height);
    }
	
	
}
