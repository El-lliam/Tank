package movingObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.gameTable;

import action.ObjectMove;
import bullets.Bullet;
import constante.TextureFactory;
import objects.Object;

public class Player extends Tank{

	private float xorign;
	private float yorign;
	
	public Player(float x, float y, gameTable t) {
		super(x, y, t);
		width = TextureFactory.getTexture(this.getClass(), direction).getWidth();
		height = TextureFactory.getTexture(this.getClass(), direction).getHeight();
		
		String[] line;
    	//mettre le txt
    	FileHandle file = Gdx.files.internal("player.txt");
    	String text = file.readString();
    	line = text.split("\r\n");
		String[] content;
		for(String s:line) {
			content = s.split("=");
			if(content[0].equals("life")) {
				life = Integer.parseInt(content[1]);
				MaxLife = life;
			}
				
			if(content[0].equals("speed"))
				setSpeed(Integer.parseInt(content[1]));
		}
		bullets = new Bullet[1];
		xorign = x;
		yorign = y;
	}
    
	@Override
	public void step()
	{
		try {
			boolean ishited = false; // if player has beening moving to an object
			float iterX = x;
			float iterY = y;
			if(upMove) {
				iterY = y + getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {  // use chain of responsibility to detect if play can move
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, x, iterY);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					ObjectMove move = new ObjectMove(this, null, x, iterY);
					move.treat();
				}
			}
			if(downMove) {
				iterY = y - getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, x, iterY);
						ishited = true;
						iterY = y;
						break;
					}
				}
				if(!ishited) {
					ObjectMove move = new ObjectMove(this, null, x, iterY);
					move.treat();
				}
			}
			if (leftMove)
			{
				iterX = x - getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, iterX, y);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					ObjectMove move = new ObjectMove(this, null, iterX, y);
					move.treat();
				}

			}
			if (rightMove)
			{
				iterX = x + getSpeed() * Gdx.graphics.getDeltaTime();
				for(Object o: table.getObjectTable()) {
					if(o != null && !o.equals(this) && this.crash(iterX, iterY, o)) {
						table.objectDetect(this, o, iterX, y);
						ishited = true;
						break;
					}
				}
				if(!ishited) {
					ObjectMove move = new ObjectMove(this, null, iterX, y);
					move.treat();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getXorign() {
		return xorign;
	}

	public void setXorign(float xorign) {
		this.xorign = xorign;
	}

	public float getYorign() {
		return yorign;
	}

	public void setYorign(float yorign) {
		this.yorign = yorign;
	}
	
	
}
