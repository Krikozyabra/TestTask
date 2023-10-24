package GameLogic;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Player{
	Config cfg = new Config();
	
	int minDMG, maxDMG;
	int protection;
	int hp;
	int maxHP;
	int attack;
	int healAmount = 4;
	int amountGold = 0;
	int runAwayChance;
	int respawnChance = 70;
	int predictionChance = 100;
	int turn=0;
	String type;
	HashMap<String, Integer> inventory = new HashMap<>();
	
	private int positionOnDesk = 0;
	private boolean isFighting = false;

	public boolean buff =false;
	
	Player(){
		putInInventory("Иголки",3);
	}
	
	void died() {
		// TODO Auto-generated method stub
		GameLogic gl = new GameLogic();
		System.out.println("К сожалению вы умерли :(");
		System.out.format("Вы смогли добраться лишь до %d клетки\n", this.positionOnDesk);
		gl.gameStart();
	}

	
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
		return new Formatter().format("Здоровье = %d/%d"
				+ "\nУрон = %d-%d"
				+ "\nЗащита = %d"
				+ "\nАтака = %d"
				+ "\nЗолото = %d"
				,this.hp,this.maxHP,this.minDMG,this.maxDMG,this.protection,this.attack, this.amountGold);
	}
	public void showInventory() {
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("Ваш инвентарь:");
		if(this.inventory.isEmpty()) {
			System.out.println("ПУСТОЙ :)");
			return;
		}
		for(String x: this.inventory.keySet()) {
			System.out.println(x+": "+this.inventory.get(x).toString());
		}
	}
	public void putInInventory(String item, int amount) {
		this.inventory.put(item, amount);
		System.out.println("Вам добавлен "+item+" в размере "+amount+"штук(и)");
	}
	public void removeFromInventory(String item, int amount) {
		this.inventory.replace(item, this.inventory.get(item), this.inventory.get(item)-amount);
	}
	public void win() {
		// TODO Auto-generated method stub
		System.out.println("Вы дошли до 100 клетки! Поздравляю!");
	}

}
