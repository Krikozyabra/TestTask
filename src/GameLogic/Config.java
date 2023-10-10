package GameLogic;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import GameLogic.SpellsInterfaces.BarbarianInterface;
import GameLogic.SpellsInterfaces.PaladinInterface;
import GameLogic.SpellsInterfaces.PriestInterface;

public class Config {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	String[] playerTypes = {"Паладин", "Варвар", "Жрец", "Вор", "Мечник"};
	HashMap<String, String> playerDescriptions = new HashMap<>();
	HashMap<String, HashMap<String, Integer>> playerStats = new HashMap<>();
	
	PriestInterface priestSpells;
	BarbarianInterface barbarianSpells;
	PaladinInterface paladinSpells;
	
	String[] enemyTypes = {"Волк","Змея","Заяц убийца","Острый еж"};
//	String[] enemyTypes = {"Острый еж"};
	HashMap<String, HashMap<String, Integer>> enemyStats = new HashMap<>();
	
	String[] damageTypes = {"Упало дерево", "Одуванчик", "Колодец"};
//	String[] damageTypes = {"Колодец"};
	HashMap<String, HashMap<String, String>> damageDescriptions = new HashMap<>();
	HashMap<String, EventInterface> damageEvents = new HashMap<>();
	
	String[] freeLocationsTypes = {"Лагерь"};
	HashMap<String, String> freeLocationDescriptions = new HashMap<>();
	HashMap<String, EventInterface> freeLocationsEvents = new HashMap<>();
	
	String[] healTypes = {"Костер", "Подушка"};
	HashMap<String, HashMap<String, String>> healDescriptions = new HashMap<>();
	HashMap<String, EventInterface> healEvents = new HashMap<>();
	
	Config(){
		//Setting descriptions for classes
		playerDescriptions.put("Паладин", "Фенсил следовал добру сколько себя помнит. Ещё будучи ребёнком он пытался защитить других, даже если вместо того чтобы спасти кого-то у него получалось лишь получить синяки вместе с тем, кого он пытался спасти. Его никогда не волновало количество противников, он всегда рвался в гущу боя. Смелость, одержимость своими идеями и обострённое чувство справедливости - триада, сформировавшая стальной характер Фенсила.\n"
				+ "Будучи подростком он начал помогать священникам исцелять раненных, где понял, что его идеи это ничто иное, как отражение божеской мысли. Он решил стать поборником добра и ежедневные тренировки вкупе с молитвами стали его жизнью.");
		playerDescriptions.put("Варвар", "Кнуд мало что помнит о своём детстве и юношестве. Кнуд вообще мало пользуется своим мозгом - следствие зверского воспитания, употребления галлюциногенных грибов и полного разрушения личности ещё с ранних лет. Из воспоминаний у него есть лишь лица близких и лица врагов, а также его первое убийство. Его он совершил, когда ему исполнилось 5 лет, это был обряд инициации, который должны были проходить все мужчины в его родной деревне. Тогда он убил троих людей, которые пытались от него сбежать. Это были обычные крестьяне, которых привезли взрослые после набега на какой-то город. В общем, Кнуд никогда не отличался умом или эмоциональностью. У него есть лишь страсть к убийствам, злоба и желание набить своё брюхо полуокровавленной жаренной плотью ");
		playerDescriptions.put("Жрец", "Консул Штальт родился в борделе, своего отца он не знает, да и вряд ли его знает даже мать Штальта. Он жил в борделе до 4 лет, естественно не за просто так. Ему приходилось работать на хозяина борделя в качестве слуги. Впрочем, ему повезло, что хозяин борделя был хотя бы наполовину человек с совестью, ведь когда некоторые извращенцы предлагали ему деньги за сеанс со Штальтом, тот отказывал и запрещал вход этим негодяяем в своё заведение. Это был своеобразный отбор на адекватность, в конце концов его работницы это его собственность и будет нехорошо, если кто-то взявший в аренду их испортит, а если человек требует кого-то вроде Штальта, то это явно опасный и неуравновешенный индивид. В общем, вскоре мать Штальта покончила с собой, хотя и матерью она ему не особо то и была, ведь видел он её не чаще, чем каких-то других работниц борделя. Однажды в бордель зашли пьяные стражники и что-то не поделили с хозяином, началась резня. Штальта спас священнослужитель, который поклонялся богам Благоденствия - богам, которые покровительствовали удовольствиям и разгульной жизни. Что неудивительно, ведь другой священнослужитель вряд ли бы оказался в борделе. У Штальта не было выбора, кроме как жить в храме, что, впрочем, ему очень понравилось. Когда он наконец получил сан Консула, он отправился в путешествие, дабы проповедовать своё учение");
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
		playerStats.put("Острый еж", new HashMap<String, Integer>() {{
			put("attack", 20);
			put("protection", 18);
			put("minDMG", 0);
			put("maxDMG", 0);
			put("hp", 15);
			}});
		
		priestSpells = new PriestInterface(){
			@Override
			public boolean passive(Event e, Player player, int turn) {
				Scanner scan = new Scanner(System.in);
				Random rnd = new Random();
				String type = "";
				switch(e.getEventType()) {
					case Event.ENEMY:{
						type = "враг - "+e.getEnemy().type;
						break;
					}
					case Event.RANDOM_DAMAGE:{
						if(e.getType() == "Колодец") {
							type = "бафф";
						}else {
							type = "получение урона";
						}
						break;
					}
					case Event.FREE_LOCATION:{
						type = "свободная локация для прогулок";
						break;
					}
					case Event.RANDOM_HEAL:{
						type = "восстановление хп";
						break;
					}
				}
				System.out.println("Бог даровал жрецу возможность видеть, что на следующей клетке"
						+ "\nОн говорит, что на следующей клетке будет "+Config.ANSI_YELLOW+type+Config.ANSI_RESET
						+ "\nВы сходите(1) или попытаетесь избежать(2)?");
				while(true) {
				byte answer = scan.nextByte();
				if(answer == 1) {
					player.addPosition(turn);
					return false;
				}else if(answer == 2) {
					int chance = rnd.nextInt(100)+1;
					if(chance <= player.runAwayChance) {
						player.addPosition(turn+1);
						System.out.format("Вы смогли обойти надвигающуюся клетку и переместились на %d и оказались на клетке №%d\n",turn,player.getPosition());
						return true;
					}else {
						System.out.println("Боги к вам не благосклонны и не дали миновать предопределленых событий");
						player.addPosition(turn);
						return false;
					}
				}else {
					System.out.println("Такой команды не предусмотренно.");
				}
				}
			}
		};
		barbarianSpells = new BarbarianInterface() {

			@Override
			public void passive(Player player, int turn) {
				Random rnd = new Random();
				int turn2 = rnd.nextInt(6)+1;
				turn2 = turn > turn2 ? turn : turn2;
				System.out.format("Варвара часто преследуют умные мысли и иногда они его догонят."
						+ "\nЭтот случай стал тем самым исключением и он перекинул кубик,"
						+ "\nчтобы сходить на самое большое количество шагов - %d\n", turn2);
				player.addPosition(turn2);
				System.out.format("Вы переместились на %d и оказались на клетке %d\n",turn2,player.getPosition());
			}
			
		};
		paladinSpells = new PaladinInterface() {

			@Override
			public boolean passive(Player player) {
				Random rnd = new Random();
				if((rnd.nextInt(100)+1) <= 70) {
					System.out.println("Боги дают тебе возрождение");
					player.hp = 1;
					return true;
				}else {
					System.out.println("Боги решили, что тебе и в земле нормально");
					return false;
				}
			}
			
		};
		
		//Setting types of random damage
		damageDescriptions.put("Упало дерево", new HashMap<String, String>() {{
			put("damage", "2");
			put("description", "Вы не заметили старый дуб, проеденный короедами, \nи он упал вам на темечко. Вы получил - 2 урона");
		}});
		damageDescriptions.put("Одуванчик", new HashMap<String, String>(){{
			put("damage","4");
			put("description", "Кто-то явно воткнул этот железный одуванчик сюда специально, чтобы вы напоролись."
					+ "\nВы получаете - 4 урона");
		}});
		damageDescriptions.put("Колодец", new HashMap<String, String>(){{
			put("damage","3");
			put("description", "Вы обнаружили ветхий, заброшенный колодец и решили,"
					+ "\nчто будет круто справить туда свою нужду, но вы не учел того факта,\nчто это освященный колодец.");
		}});
		
		damageEvents.put("Упало дерево", new EventInterface() {
			@Override
			public String execute(Player p) {
				if(p.type == "Варвар") {
					System.out.println("На вас упал старый дуб");
					return "Варвар почесал голову... и пошел дальше";
				}else {
					p.hp -= Integer.parseInt(damageDescriptions.get("Упало дерево").get("damage"));
					return damageDescriptions.get("Упало дерево").get("description");
				}
			}});
		damageEvents.put("Одуванчик", new EventInterface() {
			@Override
			public String execute(Player p) {
				p.hp -= Integer.parseInt(damageDescriptions.get("Одуванчик").get("damage"));
				return damageDescriptions.get("Одуванчик").get("description");
			}
		});
		damageEvents.put("Колодец", new EventInterface() {
			@Override
			public String execute(Player p) {
				if(p.type == "Жрец") {
					p.minDMG = p.maxDMG-1;
					return damageDescriptions.get("Колодец").get("description")+"\nБоги Благоденствия приняли твою жертву и даровали возможность атаковать почти на максимум. Твой урон стал "+p.minDMG+"-"+p.maxDMG;
				}else {
					p.hp -= Integer.parseInt(damageDescriptions.get("Колодец").get("damage"));
					return damageDescriptions.get("Колодец").get("description")+"Вы получили божественную кару на 3 урона";
				}
			}
		});
		
		freeLocationDescriptions.put("Лагерь", "Ты попал в тихий разбойнический лагерь. В нем еще тлеют угольки, видимо что-то их испугало...");
		
		freeLocationsEvents.put("Лагерь", new EventInterface() {
			@Override
			public String execute(Player p) {
				return freeLocationDescriptions.get("Лагерь");
			}
			
		});
		
		//Setting types of random heal
		healDescriptions.put("Костер", new HashMap<String, String>(){{
			put("heal","4");
			put("description", "Вы нашли место, где друиды проводили инквизицию деревьев-еретиков, \nи решили восстановить свои силы. Восстановление хп = 4");
		}});
		healDescriptions.put("Подушка", new HashMap<String, String>(){{
			put("heal","5");
			put("description", "С неба упала подушка и вы решили, что Боги хотят, чтобы вы отдохнули! Восстановление хп = 5");
		}});
		
		healEvents.put("Костер", new EventInterface() {
			@Override
			public String execute(Player p) {
				p.hp += Integer.parseInt(healDescriptions.get("Костер").get("heal"));
				return healDescriptions.get("Костер").get("description");
			}
		});
		healEvents.put("Подушка", new EventInterface() {
			@Override
			public String execute(Player p) {
				p.hp += Integer.parseInt(healDescriptions.get("Подушка").get("heal"));
				return healDescriptions.get("Подушка").get("description");
			}
		});
	}
}
