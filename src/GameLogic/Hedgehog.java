package GameLogic;

import java.util.Random;

public class Hedgehog extends Enemy{

	Random rnd = new Random();
	Hedgehog(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public boolean contrattack() {
		int chance = rnd.nextInt(100)+1;
		if(chance<=40) return true;
		else return false;
	}
}
