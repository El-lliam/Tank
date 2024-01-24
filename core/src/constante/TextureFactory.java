package constante;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import animation.Animation;
import animation.Explore;
import animation.place;
import bullets.Bullet;
import movingObjects.Player;
import objects.Brick;
import objects.Flag;
import objects.StrongBrick;
import objects.Tree;
import plane.Plane;
import tanks.EnemyTank;
import tanks.TankBoss;

public class TextureFactory {
	public static final float  ASPECT_RATIO    = 1.777777f;
	
	// Orthographic camera's viewport size
    public static final int    VIEWPORT_WIDTH  = 1450;
    public static final int    VIEWPORT_HEIGHT = 1000;
    
    private static TextureFactory instance = null;
    
    public static HashMap<Class<?>, ArrayList<Texture>> map;
    
    private static Texture fond;
    private static Texture victory;
    private static Texture lose;
    
    private static Texture heart;
    
    public static TextureFactory getInstance()
    {
        if (instance == null)
        {
            instance = new TextureFactory();
            
            heart = new Texture("heart.png");
            
            victory = new Texture("victory.png");
            
            lose = new Texture("lose.png");
            
            map = new HashMap<Class<?>, ArrayList<Texture>>();
            
            
            ArrayList<Texture> flagList = new ArrayList<Texture>();
            ArrayList<Texture> playerList = new ArrayList<Texture>();
            ArrayList<Texture> brickList = new ArrayList<Texture>();
            ArrayList<Texture> strongBrickList = new ArrayList<Texture>();
            ArrayList<Texture> bulletList = new ArrayList<Texture>();
            ArrayList<Texture> enemyTankList = new ArrayList<Texture>();
            ArrayList<Texture> tankBossList = new ArrayList<Texture>();
            ArrayList<Texture> treeList = new ArrayList<Texture>();
            ArrayList<Texture> planeList = new ArrayList<Texture>();
            ArrayList<Texture> exploreList = new ArrayList<Texture>();
            ArrayList<Texture> placeList = new ArrayList<Texture>();
            
            Texture drapeau;
            Texture murBrique;
            Texture murRenforce;
            Texture arbre;
            fond = new Texture("Fond.png");
            arbre = new Texture("Arbre.png");
            murBrique = new Texture("MurBrique.png");
            murRenforce = new Texture("MurRenforce.png");
            drapeau = new Texture("Drapeau.png");
            
            flagList.add(drapeau);
            brickList.add(murBrique);
            strongBrickList.add(murRenforce);
            treeList.add(arbre);
            
            Texture joueurU = new Texture("CharJoueur.png"); // up
            Texture joueurR = new Texture("CharJoueurR.png"); // right
            Texture joueurD = new Texture("CharJoueurD.png"); // down
            Texture joueurL = new Texture("CharJoueurL.png"); // left
            playerList.add(joueurU);
            playerList.add(joueurR);
            playerList.add(joueurD);
            playerList.add(joueurL);
            
            Texture chasseurCharU = new Texture("ChasseurChar.png");
            Texture chasseurCharR = new Texture("ChasseurCharR.png");
            Texture chasseurCharD = new Texture("ChasseurCharD.png");
            Texture chasseurCharL = new Texture("ChasseurCharL.png");
            enemyTankList.add(chasseurCharU);
            enemyTankList.add(chasseurCharR);
            enemyTankList.add(chasseurCharD);
            enemyTankList.add(chasseurCharL);
            
            Texture chasseurDrapeauU = new Texture("ChasseurDrapeau.png");
            Texture chasseurDrapeauR = new Texture("ChasseurDrapeauR.png");
            Texture chasseurDrapeauD = new Texture("ChasseurDrapeauD.png");
            Texture chasseurDrapeauL = new Texture("ChasseurDrapeauL.png");
            tankBossList.add(chasseurDrapeauU);
            tankBossList.add(chasseurDrapeauR);
            tankBossList.add(chasseurDrapeauD);
            tankBossList.add(chasseurDrapeauL);
            
            Texture projectile = new Texture("Projectile.png");
            bulletList.add(projectile);
            
            
            Texture planeTexture = new Texture("Plane.png");
            planeList.add(planeTexture);
            
            
            Texture exploreTexture1 = new Texture("13-1.png");
            Texture exploreTexture2 = new Texture("13-2.png");
            Texture exploreTexture3 = new Texture("13-3.png");
            Texture exploreTexture4 = new Texture("13-4.png");
            Texture exploreTexture5 = new Texture("13-5.png");
            Texture exploreTexture6 = new Texture("13-6.png");
            Texture exploreTexture7 = new Texture("13-7.png");
            Texture exploreTexture8 = new Texture("13-8.png");
            Texture exploreTexture9 = new Texture("13-9.png");
            Texture exploreTexture10 = new Texture("13-10.png");
            Texture exploreTexture11 = new Texture("13-11.png");
            Texture exploreTexture12 = new Texture("13-12.png");
            Texture exploreTexture13 = new Texture("13-13.png");
            Texture exploreTexture14 = new Texture("13-14.png");
            Texture exploreTexture15 = new Texture("13-15.png");
            Texture exploreTexture16 = new Texture("13-16.png");
            Texture exploreTexture17 = new Texture("13-17.png");
            Texture exploreTexture18 = new Texture("13-18.png");
            Texture exploreTexture19 = new Texture("13-19.png");
            Texture exploreTexture20 = new Texture("13-20.png");
            Texture exploreTexture21 = new Texture("13-21.png");
            Texture exploreTexture22 = new Texture("13-22.png");
            exploreList.add(exploreTexture1);
            exploreList.add(exploreTexture2);
            exploreList.add(exploreTexture3);
            exploreList.add(exploreTexture4);
            exploreList.add(exploreTexture5);
            exploreList.add(exploreTexture6);
            exploreList.add(exploreTexture7);
            exploreList.add(exploreTexture8);
            exploreList.add(exploreTexture9);
            exploreList.add(exploreTexture10);
            exploreList.add(exploreTexture11);
            exploreList.add(exploreTexture12);
            exploreList.add(exploreTexture13);
            exploreList.add(exploreTexture14);
            exploreList.add(exploreTexture15);
            exploreList.add(exploreTexture16);
            exploreList.add(exploreTexture17);
            exploreList.add(exploreTexture18);
            exploreList.add(exploreTexture19);
            exploreList.add(exploreTexture20);
            exploreList.add(exploreTexture21);
            exploreList.add(exploreTexture22);
            
            Texture placeTexture = new Texture("heart.png");
            placeList.add(placeTexture);
            
            map.put(Flag.class, flagList);
            map.put(Player.class, playerList);
            map.put(Brick.class, brickList);
            map.put(StrongBrick.class, strongBrickList);
            map.put(Bullet.class, bulletList);
            map.put(EnemyTank.class, enemyTankList);
            map.put(TankBoss.class, tankBossList);
            map.put(Tree.class, treeList);
            map.put(Plane.class, planeList);
            map.put(Explore.class, exploreList);
            map.put(place.class, placeList);
        }
        return instance;
    }

	public static Texture getFond() {
		return fond;
	}
	

	public static Texture getVictory() {
		return victory;
	}

	public static Texture getLose() {
		return lose;
	}


	public static Texture getTexture(Class<?> o, int index) {
		return map.get(o).get(index);
	}

	public static Texture getHeart() {
		return heart;
	}

	public static ArrayList<Texture> getAnimation(Class<?> o){
		return map.get(o);
	}
    
    
}
