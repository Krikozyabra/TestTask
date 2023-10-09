package GameLogic;

public class Event {
	public final static int ENEMY = 1;
	public final static int RANDOM_DAMAGE = 2;
//	public static int SHOP = 3;
	public final static int FREE_LOCATION = 4;
	
	private int type = 0;
	private Enemy enemy = null;
	private int damage = 0;
	private String damageDescription = null;
	private String locationDescription = null;
	
	Event(int type, int damage, String damageDescription){
		setType(type);
		setDamage(damage);
		setDamageDescription(damageDescription);
	}
	
	Event(int eventType, String locationDescription){
		setType(eventType);
		setLocationDescription(locationDescription);
	}
	
	Event(int type, Enemy enemy){
		setType(type);
		setEnemy(enemy);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
}
