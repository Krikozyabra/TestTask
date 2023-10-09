package GameLogic;

public class Event {
	public final static int ENEMY = 1;
	public final static int RANDOM_DAMAGE = 2;
//	public static int SHOP = 3;
	public final static int FREE_LOCATION = 4;
	public final static int RANDOM_HEAL = 5;
	
	private int type = 0;
	private Enemy enemy = null;
	private Hedgehog ezh = null;
	private int damageHeal = 0;
	private String eventDescription = null;
	private String locationDescription = null;
	
	Event(int type, int damageHeal, String eventDescription){
		setType(type);
		setDamageHeal(damageHeal);
		setEventDescription(eventDescription);
	}
	
	Event(int eventType, String locationDescription){
		setType(eventType);
		setLocationDescription(locationDescription);
	}
	Event(int type, Enemy enemy){
		setType(type);
		setEnemy(enemy);
	}
	Event(int type, Enemy enemy, Hedgehog ezh){
		setType(type);
		setEnemy(enemy);
		setEzh(ezh);
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

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public int getDamageHeal() {
		return damageHeal;
	}

	public void setDamageHeal(int damageHeal) {
		this.damageHeal = damageHeal;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Hedgehog getEzh() {
		return ezh;
	}

	public void setEzh(Hedgehog ezh) {
		this.ezh = ezh;
	}
}
