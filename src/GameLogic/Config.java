package GameLogic;

import java.util.HashMap;

public class Config {
	String[] playerTypes = {"Паладин", "Варвар", "Жрец", "Вор", "Мечник"};
	HashMap<String, String> playerDescriptions = new HashMap<>();
	HashMap<String, HashMap<String, Integer>> playerStats = new HashMap<>();
	
	String[] enemyTypes = {"Волк","Змея","Заяц убийца"};
	HashMap<String, HashMap<String, Integer>> enemyStats = new HashMap<>();
	
	String[] damageTypes = {"Упало дерево", "Ударился мизинчиком об одуванчик"};
	HashMap<String, HashMap<String, String>> damageDescriptions = new HashMap<>();
	
	String[] freeLocations = {"Ты попал в тихий разбойнический лагерь. В нем еще тлеют угольки, видимо что-то их испугало..."};
	
	Config(){
		//Setting descriptions for classes
		playerDescriptions.put("Паладин", "Великий воин с большим щитом");
		playerDescriptions.put("Варвар", null);
		playerDescriptions.put("Жрец", null);
		playerDescriptions.put("Вор", "Рахиль не знал своих родителей, поскольку воспитывался в сиротском приюте Подгорода "
				+ "\n- гигантских подземных сооружений под великим городом Штиф. С раннего детства он видел и болезни и смерть, как малых детей "
				+ "\n- таких же сирот из приюта как он, так и просто бедняков, что гибли прямо на улицах и там же разлагались, распространяя заразу вокруг себя. "
				+ "\nРахилю пришлось рано повзрослеть, в Подгороде можно было выжить будучи либо сильным, либо хитрым. "
				+ "\nЕго острый ум давал ему врождённую предрасположенность ко второму виду деятельности. "
				+ "\nТак, ещё совсем малой ребёнок пользовался своей детской ловкостью, лихо обворовывая и без того не богатых торговцев, что иногда рисковали посещать Подгород. "
				+ "\nЗа долгие годы он отточил свои навыки притворства и скрытности. Его зоркий глаз способен найти защиту в любой обороне "
				+ "\n- иначе ты никогда не вскроешь замок и не своруешь мешок с золотом из-под пазухи торговца. Единственное оружие, что он признаёт "
				+ "\n- кинжал, ведь его легко спрятать, а убить неподозревающую жертву куда проще. Рахиль никогда не работал в команде, "
				+ "\nс тех пор как сиротский приют закрыли, а все его друзья детства погибли на его глазах. Его сложно удивить или поразить, "
				+ "\nвесь мир для него лишь арена, на которой каждый сам за себя\n");
		playerDescriptions.put("Мечник", "Лордер - буйный пьяница, известный на всё королевство. "
				+ "\nНет ни одной таверны, в которую бы он не зашёл, а в половину из них ему запрещён вход "
				+ "\n(что его не сильно, впрочем, удерживает) за его буйный нрав и ярые драки. "
				+ "\nОн не обладал никогда богатырским телосложением, со стороны он весьма худощав,"
				+ "'nоднако его ежедневные тренировки по схемам древних воинов привели к тому, что его не слишком-то объёмные, "
				+ "\nно крайне мощные мускулы тверды как камень, даже будучи расслабленными. "
				+ "\nБлагодаря этому он спокойно орудует двуручным клинком, разрубая латные доспехи, словно бочку с медовухой.\n"
				+ "Его философия проста - выпей и дерись. Его мечта умереть либо пьяным, либо в бою, а ещё лучше одновременно.\n");
		
		//Setting stats for classes
		playerStats.put("Паладин", new HashMap<String, Integer>() {{
			put("attack", 15);
			put("protection", 20);
			put("minDMG", 1);
			put("maxDMG", 4);
			put("hp", 20);
			put("maxHP", 20);
			put("runAwayChance", 20);
			}});
		playerStats.put("Варвар", new HashMap<String, Integer>() {{
			put("attack", 15);
			put("protection", 16);
			put("minDMG", 1);
			put("maxDMG", 5);
			put("hp", 17);
			put("maxHP", 17);
			put("runAwayChance", 20);
			}});
		playerStats.put("Жрец", new HashMap<String, Integer>() {{
			put("attack", 13);
			put("protection", 13);
			put("minDMG", 2);
			put("maxDMG", 4);
			put("hp", 10);
			put("maxHP", 10);
			put("runAwayChance", 20);
			}});
		playerStats.put("Вор", new HashMap<String, Integer>() {{
			put("attack", 28);
			put("protection", 10);
			put("minDMG", 1);
			put("maxDMG", 4);
			put("hp", 15);
			put("maxHP", 15);
			put("runAwayChance", 30);
			}});
		playerStats.put("Мечник", new HashMap<String, Integer>() {{
			put("attack", 20);
			put("protection", 15);
			put("minDMG", 2);
			put("maxDMG", 6);
			put("hp", 15);
			put("maxHP", 15);
			put("runAwayChance", 20);
			}});
		
		//Setting stats for enemies
		playerStats.put("Волк", new HashMap<String, Integer>() {{
			put("attack", 20);
			put("protection", 14);
			put("minDMG", 2);
			put("maxDMG", 6);
			put("hp", 15);
			}});
		playerStats.put("Заяц убийца", new HashMap<String, Integer>() {{
			put("attack", 16);
			put("protection", 15);
			put("minDMG", 2);
			put("maxDMG", 4);
			put("hp", 10);
			}});
		playerStats.put("Змея", new HashMap<String, Integer>() {{
			put("attack", 20);
			put("protection", 18);
			put("minDMG", 1);
			put("maxDMG", 3);
			put("hp", 12);
			}});
		
		//Setting types of random damage
		damageDescriptions.put("Упало дерево", new HashMap<String, String>() {{
			put("damage", "2");
			put("description", "Ты не заметил старый дуб, проеденный короедами, и он упал тебе на темечко. Ты получил - 2 урона");
		}});
		damageDescriptions.put("Ударился мизинчиком об одуванчик", new HashMap<String, String>(){{
			put("damage","4");
			put("description", "Кто-то явно воткнул этот железный одуванчик сюда специально, чтобы вы напоролись."
					+ "\nВы получаете - 4 урона");
		}});
	}
}
