package GameLogic;

public class Event {
	public final static int ENEMY = 1;
	public final static int RANDOM_DAMAGE = 2;
//	public static int SHOP = 3;
	public final static int FREE_LOCATION = 4;
	public final static int RANDOM_HEAL = 5;
	
	private EventInterface eventInterface;
	
	private int type = 0;
	private Enemy enemy = null;
	private Hedgehog ezh = null;
	private int damageHeal = 0;
	private String eventDescription = null;
	private String locationDescription = null;
	
	Event(int type, EventInterface ei){
		setType(type);
		setEventInterface(ei);
	}
	Event(int type, Enemy e, Hedgehog ezh){
		setType(type);
		setEnemy(e);
		if(e.type == "Острый еж") setEzh(ezh);
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

	public EventInterface getEventInterface() {
		return eventInterface;
	}

	public void setEventInterface(EventInterface eventInterface) {
		this.eventInterface = eventInterface;
	}
}
