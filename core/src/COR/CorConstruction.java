package COR;

public class CorConstruction {
	private Sensor sensor;
	
	/**
	 * construct the chain of responsability
	 */
	public Sensor constructCOR() {
		sensor = new PlayerToTree(null);
		BulletToBrick bulletToBrick = new BulletToBrick(sensor);
		BulletToStrongbrick bulletToStrongbrick = new BulletToStrongbrick(bulletToBrick);
		EnemytankToTree enemytankToTree = new EnemytankToTree(bulletToStrongbrick);
		BulletToEnemy bulletToEnemy = new BulletToEnemy(enemytankToTree);
		BulletToPlayer bulletToPlayer = new BulletToPlayer(bulletToEnemy);
		EnemytankToBrick enemytankToBrick = new EnemytankToBrick(bulletToPlayer);
		EnemytankToStrongbrick enemytankToStrongbrick = new EnemytankToStrongbrick(enemytankToBrick);
		sensor = enemytankToStrongbrick;
		
		return sensor;
	}

}
