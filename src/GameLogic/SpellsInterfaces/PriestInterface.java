package GameLogic.SpellsInterfaces;

import GameLogic.Event;
import GameLogic.Player;

public interface PriestInterface {
	public boolean passive(Event e, Player p, int turn);
}
