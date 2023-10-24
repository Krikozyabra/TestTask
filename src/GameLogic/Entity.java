package GameLogic;

public abstract class Entity {
	abstract int attack(int enemyProtection);
	abstract void setType(String typeName) throws Exception;
	abstract void died(Player p);
}
