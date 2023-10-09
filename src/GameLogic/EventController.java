package GameLogic;

import java.util.HashMap;
import java.util.Random;

public class EventController {
	Config cfg = new Config();
	
	public Event randomEvent() {
		Random rnd = new Random();
		int chance = rnd.nextInt(100)+1;
		if(chance <= 25) {
			byte enemyId = (byte) (rnd.nextInt(cfg.enemyTypes.length)); //Choosing enemy's type
			String enemyType = cfg.enemyTypes[enemyId];
			if(enemyType == "Острый еж") {
				return new Event(Event.ENEMY, new Enemy(enemyType), new Hedgehog(enemyType)); //Event - enemy
			}else {
				return new Event(Event.ENEMY, new Enemy(enemyType)); //Event - enemy
			}
		}else if(chance <= 50) {
			byte damageId = (byte) (rnd.nextInt(cfg.damageTypes.length)); //Choosing random damage's type
			String damageType = cfg.damageTypes[damageId];
			HashMap<String, String> damageDescription = cfg.damageDescriptions.get(damageType);
			int damageAmount = Integer.parseInt(damageDescription.get("damage"));
			return new Event(Event.RANDOM_DAMAGE, damageAmount, damageDescription.get("description")); //Event - random damage
		}else if(chance <= 75) {
			byte freeLocationId = (byte)(rnd.nextInt(cfg.freeLocations.length));
			return new Event(Event.FREE_LOCATION, cfg.freeLocations[freeLocationId]);
		}else if(chance <= 100) {
			byte healId = (byte) (rnd.nextInt(cfg.healTypes.length)); //Choosing random damage's type
			String healType = cfg.healTypes[healId];
			HashMap<String, String> healDescription = cfg.healDescriptions.get(healType);
			int healAmount = Integer.parseInt(healDescription.get("heal"));
			return new Event(Event.RANDOM_HEAL,healAmount, healDescription.get("description")); //Event - random damage
		}
		return null;
	}
}
