package GameLogic;

public abstract class Entity {
	abstract void died();
	abstract int attack(int enemyProtection);
	abstract void setType(String typeName) throws Exception;
}
