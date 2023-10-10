package GameLogic;

import java.util.Random;

public class EventController {
	Config cfg = new Config();
	
	public Event randomEvent() {
		Random rnd = new Random();
		int chance = rnd.nextInt(100)+1;
		if(chance <= 25) {
			byte enemyId = (byte) (rnd.nextInt(cfg.enemyTypes.length)); //Choosing enemy's type
			String enemyType = cfg.enemyTypes[enemyId];
			return new Event(Event.ENEMY, new Enemy(enemyType), new Hedgehog(enemyType)); //Event - enemy
		}else if(chance <= 50) {
			byte damageId = (byte) (rnd.nextInt(cfg.damageTypes.length)); //Choosing random damage's type
			String damageType = cfg.damageTypes[damageId];
			return new Event(Event.RANDOM_DAMAGE, cfg.damageEvents.get(damageType), cfg.damageDescriptions.get(damageType).get("description"), damageType); //Event - random damage
		}else if(chance <= 75) {
			byte freeLocationId = (byte)(rnd.nextInt(cfg.freeLocationsTypes.length));
			String freeLocationType = cfg.freeLocationsTypes[freeLocationId];
			return new Event(Event.FREE_LOCATION, cfg.freeLocationsEvents.get(freeLocationType), cfg.freeLocationDescriptions.get(freeLocationType), freeLocationType);
		}else if(chance <= 100) {
			byte healId = (byte) (rnd.nextInt(cfg.healTypes.length));
			String healType = cfg.healTypes[healId];
			return new Event(Event.RANDOM_HEAL, cfg.healEvents.get(healType), cfg.healDescriptions.get(healType).get("descriptions"), healType);
		}
		return null;
	}
}
