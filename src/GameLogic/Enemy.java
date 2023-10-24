package GameLogic;

import java.util.HashMap;
import java.util.Random;

public class Enemy extends Entity{
	
	int minDMG, maxDMG;
	int minGold, maxGold;
	int protection;
	int hp;
	int attack;
	Config cfg = new Config();
	
	String type;
	
	Enemy(String type){
		try {
			setType(type);
		} catch (Exception e) {
			System.out.println("Данный тип противнника - "+type+". Имеет превышенные параметры защиты и атаки.");
		}
	}
	
	@Override
	void died(Player p) {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		if(this.type == "Острый еж") {
			int drop = rnd.nextInt(100)+1;
			if(drop <= 20) {
				drop = rnd.nextInt(1)+1;
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.println("Вам выпало: "+drop+" иголок(а)");
				p.putInInventory("Иголки", drop);
			}else {
				System.out.println("Грустный тромбон... Вам ничего не выпало");
			}
		}
	}

	@Override
	int attack(int enemyProtection) {
		byte modificator = (byte)(enemyProtection - this.attack);
		if(modificator<=0) modificator = 1;
		Random rnd = new Random();
		boolean isAttacking = false;
		for(byte i = 0; i<modificator;i++) {
			byte chance = (byte)(rnd.nextInt(6)+1);
			if(chance>4){
				isAttacking = true;
				break;
			}
		}
		if(isAttacking) return rnd.nextInt(this.maxDMG-this.minDMG)+this.minDMG;
		else return 0;
	}

	@Override
	void setType(String typeName) throws Exception {
		HashMap<String, Integer> stats = cfg.enemyStats.get(typeName);
		this.type = typeName;
		if(stats.get("attack")>30 || stats.get("protection")>30) {
			throw new Exception();
		}
		this.minDMG = stats.get("minDMG");
		this.maxDMG = stats.get("maxDMG");
		this.attack = stats.get("attack");
		this.hp = stats.get("hp");
		this.protection = stats.get("protection");
		this.minGold = stats.get("minGold");
		this.maxGold = stats.get("maxGold");
	}

	public String getStats() {
		return "\n Урон = "+this.minDMG+"-"+this.maxDMG
				+ "\n Здоровье = "+this.hp
				+ "\n Атака = "+this.attack
				+ "\n Защита = "+this.protection
				+ "\n Золото = "+this.minGold+"-"+this.maxGold;
	}
}
