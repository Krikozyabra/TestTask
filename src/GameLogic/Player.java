package GameLogic;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Random;

public class Player extends Entity{
	Config cfg = new Config();
	
	int minDMG, maxDMG;
	int protection;
	int hp;
	int maxHP;
	int attack;
	int healAmount = 4;
	int amountGold = 0;
	int runAwayChance;
	String type;
	
	private int positionOnDesk = 0;
	private boolean isFighting = false;

	@Override
	void died() {
		// TODO Auto-generated method stub
		System.out.println("К сожалению вы умерли :(");
	}

	@Override
	int attack(int enemyProtection) {
		byte modificator = (byte)(enemyProtection - this.attack+1);
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
	
	boolean isAlive() {
		if(this.hp > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	void setType(String typeName) throws Exception {
		HashMap<String, Integer> stats = cfg.playerStats.get(typeName);
		this.type = typeName;
		if(stats.get("attack")>30 || stats.get("protection")>30) {
			throw new Exception();
		}
		this.minDMG = stats.get("minDMG");
		this.maxDMG = stats.get("maxDMG");
		this.attack = stats.get("attack");
		this.hp = stats.get("hp");
		this.maxHP = stats.get("maxHP");
		this.protection = stats.get("protection");
		this.runAwayChance = stats.get("runAwayChance");
	}
	
	String heal() {
		if(healAmount>0) {
			this.hp += (int)(this.maxHP*0.3);
			if(this.hp > this.maxHP) this.hp=this.maxHP;
			this.healAmount--;
			return "Вы благополучно восстановились на 30% от макс. здоровья.\nКоличество восстановленного здороья = "
					+ ""+(int)(this.maxHP*0.3);
		}else {
			return "У вас закончились лечащие хлебцы";
		}
	}

	public boolean inFight() {
		// TODO Auto-generated method stub
		return isFighting;
	}
	
	public void setFightingStatus(boolean status) {
		this.isFighting = status;
	}
	
	public int getPosition() {
		return positionOnDesk;
	}
	
	public void addPosition(int x) {
		this.positionOnDesk+=x;
	}

	public Formatter showStats() {
		return new Formatter().format("Здоровье = %d"
				+ "\nУрон = %d-%d"
				+ "\nЗащита = %d"
				+ "\nАтака = %d"
				+ "\nЗолото = %d"
				,this.hp,this.minDMG,this.maxDMG,this.protection,this.attack, this.amountGold);
	}

	public void win() {
		// TODO Auto-generated method stub
		System.out.println("Вы дошли до 100 клетки! Поздравляю!");
	}
}
