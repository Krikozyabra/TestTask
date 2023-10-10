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
	public static final String ANSI_BOLD = "`e[1m";

	String[] playerTypes = { "Паладин", "Варвар", "Жрец", "Вор", "Мечник" };
	HashMap<String, String> playerDescriptions = new HashMap<>();
	HashMap<String, HashMap<String, Integer>> playerStats = new HashMap<>();

	PriestInterface priestSpells;
	BarbarianInterface barbarianSpells;
	PaladinInterface paladinSpells;

	String[] enemyTypes = { "Волк", "Змея", "Заяц убийца", "Острый еж" };
//	String[] enemyTypes = {"Острый еж"};
	HashMap<String, HashMap<String, Integer>> enemyStats = new HashMap<>();
	HashMap<String, String> enemyDescriptions = new HashMap<>();

	String[] damageTypes = { "Упало дерево", "Одуванчик", "Колодец" };
//	String[] damageTypes = {"Колодец"};
	HashMap<String, HashMap<String, String>> damageDescriptions = new HashMap<>();
	HashMap<String, EventInterface> damageEvents = new HashMap<>();

	String[] freeLocationsTypes = { "Лагерь" };
	HashMap<String, String> freeLocationDescriptions = new HashMap<>();
	HashMap<String, EventInterface> freeLocationsEvents = new HashMap<>();

	String[] healTypes = { "Костер", "Подушка" };
	HashMap<String, HashMap<String, String>> healDescriptions = new HashMap<>();
	HashMap<String, EventInterface> healEvents = new HashMap<>();

	Config() {
		// Setting descriptions for classes
		playerDescriptions.put("Паладин",
				"Фенсил следовал добру сколько себя помнит. Ещё будучи ребёнком он пытался защитить других, даже если вместо того чтобы спасти кого-то у него получалось лишь получить синяки вместе с тем, кого он пытался спасти. Его никогда не волновало количество противников, он всегда рвался в гущу боя. Смелость, одержимость своими идеями и обострённое чувство справедливости - триада, сформировавшая стальной характер Фенсила.\n"
						+ "Будучи подростком он начал помогать священникам исцелять раненных, где понял, что его идеи это ничто иное, как отражение божеской мысли. Он решил стать поборником добра и ежедневные тренировки вкупе с молитвами стали его жизнью.");
		playerDescriptions.put("Варвар",
				"Кнуд мало что помнит о своём детстве и юношестве. Кнуд вообще мало пользуется своим мозгом - следствие зверского воспитания, употребления галлюциногенных грибов и полного разрушения личности ещё с ранних лет. Из воспоминаний у него есть лишь лица близких и лица врагов, а также его первое убийство. Его он совершил, когда ему исполнилось 5 лет, это был обряд инициации, который должны были проходить все мужчины в его родной деревне. Тогда он убил троих людей, которые пытались от него сбежать. Это были обычные крестьяне, которых привезли взрослые после набега на какой-то город. В общем, Кнуд никогда не отличался умом или эмоциональностью. У него есть лишь страсть к убийствам, злоба и желание набить своё брюхо полуокровавленной жаренной плотью ");
		playerDescriptions.put("Жрец",
				"Консул Штальт родился в борделе, своего отца он не знает, да и вряд ли его знает даже мать Штальта. Он жил в борделе до 4 лет, естественно не за просто так. Ему приходилось работать на хозяина борделя в качестве слуги. Впрочем, ему повезло, что хозяин борделя был хотя бы наполовину человек с совестью, ведь когда некоторые извращенцы предлагали ему деньги за сеанс со Штальтом, тот отказывал и запрещал вход этим негодяяем в своё заведение. Это был своеобразный отбор на адекватность, в конце концов его работницы это его собственность и будет нехорошо, если кто-то взявший в аренду их испортит, а если человек требует кого-то вроде Штальта, то это явно опасный и неуравновешенный индивид. В общем, вскоре мать Штальта покончила с собой, хотя и матерью она ему не особо то и была, ведь видел он её не чаще, чем каких-то других работниц борделя. Однажды в бордель зашли пьяные стражники и что-то не поделили с хозяином, началась резня. Штальта спас священнослужитель, который поклонялся богам Благоденствия - богам, которые покровительствовали удовольствиям и разгульной жизни. Что неудивительно, ведь другой священнослужитель вряд ли бы оказался в борделе. У Штальта не было выбора, кроме как жить в храме, что, впрочем, ему очень понравилось. Когда он наконец получил сан Консула, он отправился в путешествие, дабы проповедовать своё учение");
		playerDescriptions.put("Вор",
				"Рахиль не знал своих родителей, поскольку воспитывался в сиротском приюте Подгорода "
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
				+ "\nоднако его ежедневные тренировки по схемам древних воинов привели к тому, что его не слишком-то объёмные, "
				+ "\nно крайне мощные мускулы тверды как камень, даже будучи расслабленными. "
				+ "\nБлагодаря этому он спокойно орудует двуручным клинком, разрубая латные доспехи, словно бочку с медовухой.\n"
				+ "Его философия проста - выпей и дерись. Его мечта умереть либо пьяным, либо в бою, а ещё лучше одновременно.\n");

		// Setting stats for classes
		playerStats.put("Паладин", new HashMap<String, Integer>() {
			{
				put("attack", 15);
				put("protection", 20);
				put("minDMG", 1);
				put("maxDMG", 4);
				put("hp", 20);
				put("maxHP", 20);
				put("runAwayChance", 20);
			}
		});
		playerStats.put("Варвар", new HashMap<String, Integer>() {
			{
				put("attack", 15);
				put("protection", 16);
				put("minDMG", 1);
				put("maxDMG", 5);
				put("hp", 17);
				put("maxHP", 17);
				put("runAwayChance", 20);
			}
		});
		playerStats.put("Жрец", new HashMap<String, Integer>() {
			{
				put("attack", 13);
				put("protection", 13);
				put("minDMG", 2);
				put("maxDMG", 4);
				put("hp", 10);
				put("maxHP", 10);
				put("runAwayChance", 20);
			}
		});
		playerStats.put("Вор", new HashMap<String, Integer>() {
			{
				put("attack", 28);
				put("protection", 10);
				put("minDMG", 1);
				put("maxDMG", 4);
				put("hp", 15);
				put("maxHP", 15);
				put("runAwayChance", 30);
			}
		});
		playerStats.put("Мечник", new HashMap<String, Integer>() {
			{
				put("attack", 20);
				put("protection", 15);
				put("minDMG", 2);
				put("maxDMG", 6);
				put("hp", 15);
				put("maxHP", 15);
				put("runAwayChance", 20);
			}
		});

		// Setting stats for enemies
		enemyStats.put("Волк", new HashMap<String, Integer>() {
			{
				put("attack", 20);
				put("protection", 14);
				put("minDMG", 2);
				put("maxDMG", 6);
				put("hp", 15);
				put("minGold", 1);
				put("maxGold", 4);
			}
		});
		enemyStats.put("Заяц убийца", new HashMap<String, Integer>() {
			{
				put("attack", 16);
				put("protection", 15);
				put("minDMG", 2);
				put("maxDMG", 4);
				put("hp", 10);
				put("minGold", 1);
				put("maxGold", 4);
			}
		});
		enemyStats.put("Змея", new HashMap<String, Integer>() {
			{
				put("attack", 20);
				put("protection", 18);
				put("minDMG", 1);
				put("maxDMG", 3);
				put("hp", 12);
				put("minGold", 1);
				put("maxGold", 4);
			}
		});
		enemyStats.put("Острый еж", new HashMap<String, Integer>() {
			{
				put("attack", 20);
				put("protection", 18);
				put("minDMG", 0);
				put("maxDMG", 0);
				put("hp", 15);
				put("minGold", 1);
				put("maxGold", 4);
			}
		});
		
		enemyDescriptions.put("Волк", "Самый обыкновенный волк. Да, это просто волк. Ну, волк. Вы представляете себе волка? "
				+ "\nДа, это лесной волк. Да, работа не волк. Работа это ворк, а волк это гулять. В общем, это волк. "
				+ "\nДа, у него мама волчица, а папа волк. Нет, в своей стае он не альфа и даже не бета. "
				+ "\nСкорее всего этот волк просто омежка, который вечно сидит в своей норе и никогда не держался за лапку с волчицей. "
				+ "\nВероятно какая-то волчица ему нравится, но он никогда в жизни ей не признается. "
				+ "\nУ него есть проблемы с социализацией. Да, волки стайные звери, но вы напоролись на одиночку. "
				+ "\nУже поняли почему?");
		enemyDescriptions.put("Заяц убийца", "Заяц - убийца\n"
				+ "Так его прозвали стражники, патрулирующие лесные тропы. Это обычный заяц, который испил воды из Озера Грёз. "
				+ "\nНесколько лет назад эти зайцы расплодились по всем окрестным лесам, сожрав всех обычных зайцев. "
				+ "\nОни в 15 раз больше, чем простые зайцы, а также обладают зубами-резцами, "
				+ "\nкоторые превосходят размерами лезвие гильотины. Глаза этих кроликов светятся красным, "
				+ "\nоднако это не делает их злыми, как вы могли подумать. Это довольно миролюбивое создание, но все на них нападают, "
				+ "\nпотому что эти кролики непроизвольно источают феромоны, вызывающие агрессию у других созданий. Они защищаются.\n"
				+ "Зло не они, а вы.");
		enemyDescriptions.put("Змея", "А? Змея? Честно говоря мы не уверены, что это змея. "
				+ "\nДа, мы видели, как она вылазила из яйца. "
				+ "\nВот только это яйцо было не в змеиной кладке, а заклеенное пластырем лежало в шкафу у одного из магов - верджиналов. "
				+ "\nТакими магами становятся люди, которые никогда не оставались наедине с противоположным полом вплоть до 30 лет. "
				+ "\nВ общем, эта #несовсемзмея выбралась из яйца, которое почему-то оберегалось от чужих глаз таким магом. "
				+ "\nОна не слишком-то опасна, ведь является совершенно никчёмной.\n"
				+ "Ха-ха, они с тем магом случайно не родственники?");
		enemyDescriptions.put("Острый еж", "Острый ёж является ежом, который покрыт иглами - лезвиями. Да, именно иглами - лезвиями. "
				+ "\nЕго иглы обоюдоострые, поэтому их крайне проблематично взять не порезавшись. "
				+ "\nЭти звери не умеют сражаться они только защищаются, сворачиваясь клубком. Но и они не так просты, как могут показаться. "
				+ "\nБоги обожают ежей, поэтому карают трусов, которые нападают на ежей и пытаются сбежать "
				+ "\n- они зацикливают пространство и побег становится невозможен. "
				+ "\nЕдинственный шанс выжить при встрече с ежом - убить его, нанеся удар между игл."
				+ "\nЭто тяжёлая задача, с которой справится не каждый боец.\n"
				+ "А вы справитесь?");

		priestSpells = new PriestInterface() {
			@Override
			public boolean passive(Event e, Player player, int turn) {
				Scanner scan = new Scanner(System.in);
				Random rnd = new Random();
				if(!(rnd.nextInt(100)+1 <= player.predictionChance)) {
					System.out.println("Боги навели суеты у вас в разуме и вы не смогли предугадать следующую клетку");
					return false;
				}
				String type = "";
				switch (e.getEventType()) {
				case Event.ENEMY: {
					type = "враг - " + e.getEnemy().type;
					break;
				}
				case Event.RANDOM_DAMAGE: {
					if (e.getType() == "Колодец") {
						type = "бафф";
					} else {
						type = "получение урона";
					}
					break;
				}
				case Event.FREE_LOCATION: {
					type = "свободная локация для прогулок";
					break;
				}
				case Event.RANDOM_HEAL: {
					type = "восстановление хп";
					break;
				}
				}
				System.out.println("Бог даровал жрецу возможность видеть, что на следующей клетке"
						+ "\nОн говорит, что на следующей клетке будет "+ANSI_YELLOW + type+ ANSI_RESET
						+ "\nВы сходите(1) или попытаетесь избежать(2)?");
				while (true) {
					byte answer = scan.nextByte();
					if (answer == 1) {
						player.addPosition(turn);
						return false;
					} else if (answer == 2) {
						int chance = rnd.nextInt(100) + 1;
						if (chance <= player.runAwayChance) {
							player.addPosition(turn + 1);
							System.out.format(
									"Вы смогли обойти надвигающуюся клетку и переместились на %d и оказались на клетке №%d\n",
									turn + 1, player.getPosition());
							return true;
						} else {
							System.out
									.println("Боги к вам не благосклонны и не дали миновать предопределленых событий");
							player.addPosition(turn);
							return false;
						}
					} else {
						System.out.println("Такой команды не предусмотренно.");
					}
				}
			}
		};
		barbarianSpells = new BarbarianInterface() {

			@Override
			public void passive(Player player, int turn) {
				Random rnd = new Random();
				int turn2 = rnd.nextInt(6) + 1;
				turn2 = turn > turn2 ? turn : turn2;
				System.out.format("Варвара часто преследуют умные мысли и иногда они его догонят."
						+ "\nЭтот случай стал тем самым исключением и он перекинул кубик,"
						+ "\nчтобы сходить на самое большое количество шагов - %d\n", turn2);
				player.addPosition(turn2);
				System.out.format("Вы переместились на %d и оказались на клетке %d\n", turn2, player.getPosition());
			}

		};
		paladinSpells = new PaladinInterface() {

			@Override
			public boolean passive(Player player) {
				Random rnd = new Random();
				if ((rnd.nextInt(100) + 1) <= player.respawnChance) {
					System.out.println("Боги дают тебе возрождение");
					player.hp = 1;
					return true;
				} else {
					System.out.println("Боги решили, что тебе и в земле нормально");
					return false;
				}
			}

		};

		// Setting types of random damage
		damageDescriptions.put("Упало дерево", new HashMap<String, String>() {
			{
				put("damage", "2");
				put("description",
						"Вы не заметили старый дуб, проеденный короедами, \nи он упал вам на темечко. Вы получил - 2 урона");
			}
		});
		damageDescriptions.put("Одуванчик", new HashMap<String, String>() {
			{
				put("damage", "4");
				put("description", "Кто-то явно воткнул этот железный одуванчик сюда специально, чтобы вы напоролись."
						+ "\nВы получаете - 4 урона");
			}
		});
		damageDescriptions.put("Колодец", new HashMap<String, String>() {
			{
				put("damage", "3");
				put("description", "Вы обнаружили ветхий, заброшенный колодец и решили,"
						+ "\nчто будет круто справить туда свою нужду, но вы не учел того факта,\nчто это освященный колодец.");
			}
		});

		damageEvents.put("Упало дерево", new EventInterface() {
			@Override
			public String execute(Player p) {
				if (p.type == "Варвар") {
					return "Когда варвар шёл по своим варварским делам, он проходил под старым и дряхлым дубом, что был изрядно поеден короедами, "
							+ "\nэтот дуб рухнул на него по воле ежиных богов, однако пустая голова варвара не почувствовала удара, "
							+ "\nпоэтому он почесал затылок и пошёл дальше";
				}else if(p.type == "Жрец"){
					p.hp -= Integer.parseInt(damageDescriptions.get("Упало дерево").get("damage"));
					return "\"Богоугодно!\" - подумал жрец, помочившись на старый и дряхлый дуб. Он успел отойти всего на пару шагов, прежде чем был наказан. "
							+ "\nНеведомо, то ли дуб не выдержал священной струи жреца, то ли боги решили поржать, "
							+ "\nно старый и трухлявый ствол рухнул прямо на жреца, который отчаянно пытался завязать шнурки на своих панталонах. "
							+ "\nБезрезультатно."
							+ "\nВам нанесло 2 урона";
				}else if(p.type == "Вор") {
					p.hp -= Integer.parseInt(damageDescriptions.get("Упало дерево").get("damage"));
					return "Вор двигался настолько скрытно, насколько ему только позволяли его навыки тихой ходьбы. "
							+ "\nОн перемещался по лесу, желая поймать какую-нибудь живность себе на обед. Услышав рык в кустах, он бросил туда кинжал, "
							+ "\nи оттуда мгновенно выскочил медведь, которому этот кинжал воткнулся между глаз. "
							+ "\nВор стал убегать и забрался на большой ствол старого и трухлявого дуба. Сложно сказать повезло ли ему - "
							+ "\nдуб под весом вора рухнул вниз, убив медведя, но при этом доставив неприятностей и вору"
							+ "\nВам нанесло 2 урона";
				}else if(p.type == "Паладин") {
					p.hp -= Integer.parseInt(damageDescriptions.get("Упало дерево").get("damage"));
					return "Лютоглавую сволоту безволосую да безносую, что и поглядеть-то стыдно на неё, шёл убивать воин света. "
							+ "\nМечом своим и щитом, да силою веры своей хотел он одолеть чудище страшное, да избавить мир от напастей выродков её противных. "
							+ "\nКогда добрался паладин великий до противности противной, да мерзоты мерзотной, взмахнул он клинком своим и рассёк чудище на две части, "
							+ "\nда только зацепил ударом могучим древо, что рядом стояло, оттого рухнул на героя дуб старый, что по башке его неприятно вломил."
							+ "\nВам нанесло 2 урона";
				}else if(p.type == "Мечник") {
					p.hp -= 6;
					return "Мечник тренировался в лесу, ведь там полным-полно всякой живности. Внезапно он увидел как некий мужчина очень быстро пытается "
							+ "\nскрыться от медведя. Не то, чтобы мечник был спасителем, но зарубить медведя удаётся не каждый день. Он нагнал их, "
							+ "\nкогда тот странный беглец уже  забрался на дерево. Мечник подскочил к медведю и уже замахнулся для удара, когда его придавила "
							+ "\nтуша мёртвого медведя, которого насмерть зашибло дубом. К сожалению такая масса была слишком тяжела, чтобы её сдвинуть, "
							+ "\nпоэтому мечнику пришлось лежать под трупом, пока он не сгнил настолько, что мечнику удалось выбраться."
							+ "\nВам нанесло 6 урона";
				}else {
					p.hp -= Integer.parseInt(damageDescriptions.get("Упало дерево").get("damage"));
					return damageDescriptions.get("Упало дерево").get("description");
				}
			}
		});
		damageEvents.put("Одуванчик", new EventInterface() {
			@Override
			public String execute(Player p) {
				if(p.type == "Варвар") {
					p.hp -= 6;
					return "Когда варвар обнаружил железный одуванчик, то он захотел его понюхать. От мощного вдоха "
							+ "\nметаллическая верхушка одуванчика оторвалась и залетела варвару в нос, "
							+ "\nкоторый в результате разорвался на две части. "
							+ "\nВы получили 6 урона";
				}else if(p.type == "Жрец") {
					p.hp -= Integer.parseInt(damageDescriptions.get("Одуванчик").get("damage"));
					return "Жрец был слишком занят подсчётом своих золотых монет и мыслями о покупке недвижимости на земле обетованной, "
							+ "\nпоэтому он не заметил торчавший из земли железный одуванчик, об который он ударился мизинцем ноги - "
							+ "\nтапочки не лучшая обувь для передвижения по пересечённой местности. Дико взвыв, Жрец помочился на ушиб, "
							+ "\nчтобы боль прошла, а рана продезинфецировалась. \"Богоугодно!\" - подумал жрец и поковылял дальше."
							+ "\nВам нанесло 4 урона";
				}else if(p.type == "Вор") {
					return "Благодаря своей зоркости, вор вовремя замечает притаившуюся опасность - железный одуванчик, который торчал из земли. "
							+ "\nСовершив ловкий пируэт он перепрыгивает одуванчик и движется дальше, но поскальзывается и приземляется затылком прямо на одуванчик. "
							+ "\nБоги милуют его и одуванчик в последний момент занюхивает варвар.";
				}else if(p.type == "Паладин") {
					p.hp -= Integer.parseInt(damageDescriptions.get("Одуванчик").get("damage"));
					return "Великий и могучий воин света, что дланью своей, которую ведут сами боги, карает злыдней всяких, да нечисть лютоглавую, "
							+ "\nотправился в путь-дорогу, да только непрост оказался путь - преградил ему дорогу одуванчик басурманский! "
							+ "\nДо того рассвирепел воин великий, что призвал он милость богов своих и, уверенный в бессмертии своём, напрыгнул грудью на "
							+ "\nжелезный одуванчик. Тот пробил насквозь его праведное тело, да так внутри и остался, "
							+ "\nпоскольку регенерация божеств пусть и болезненна до искр, но действенна"
							+ "\nВы получаете 4 урона";
				}else if(p.type == "Мечник") {
					p.hp -= Integer.parseInt(damageDescriptions.get("Одуванчик").get("damage"));
					return "Мечник только проснулся и отправился на поиски опохмела. К сожалению для него, проснувшегося в поле, ни одной банки с соленьями "
							+ "\nили бутылки медовухи в огромном радиусе не оказалось. Злобно зарычав, мечник ударил мечом по земле и попал по железному одуванчику, "
							+ "\nкоторый от удара отлетел мечнику прямо в голову"
							+ "\nВы получили 4 урона";
				}
				p.hp -= Integer.parseInt(damageDescriptions.get("Одуванчик").get("damage"));
				return damageDescriptions.get("Одуванчик").get("description");
			}
		});
		damageEvents.put("Колодец", new EventInterface() {
			@Override
			public String execute(Player p) {
			if(p.type == "Варвар") {
				p.hp -= Integer.parseInt(damageDescriptions.get("Колодец").get("damage"));
				return "Варвар шёл по полю, будучи голым ниже пояса. Высокая трава приятно щекотала то, что могла щекотать и нежно вытирала то, что могла вытирать. "
						+ "\nВ этот момент он заметил старый и разрушенный колодец. Решив, что тот освящён, он решил даровать богам самое ценное, что у него было - "
						+ "\nего тыловую невинность. Боги не оценили дар, хоть и приняли его, поэтому варвар не сыскал благославления и ушёл прочь, "
						+ "\nполучив новое венерическое заболевание."
						+ "\nВы получили 3 урона";
			}else if (p.type == "Жрец") {
					p.minDMG = p.maxDMG - 1;
					return "Думая о том, какое бы ещё богоугодное деяние совершить, жрец шёл вдоль заросшего высокой травой поля. "
							+ "\nНеожиданно он почувствовал жжение в груди и давление в мочевом пузыре, а после этого на его глаза попался старый колодец. "
							+ "\n\"Богоугодно!\" - вскрикнул жрец и испил воды из этого колодца, а после помочился в него. Это бы освящённый колодец "
							+ "\nи боги приняли жертву жреца, даровав ему силу."
							+ "\nВаш урон стал "+ p.minDMG + "-" + p.maxDMG;
			}else if(p.type == "Вор") {
				p.hp -= Integer.parseInt(damageDescriptions.get("Колодец").get("damage"));
				return "Убегая от очередной навязчивой поклонницы, которая так и норовит оставить вора у себя на пару ночей, он заприметил колодец, "
							+ "\nв котором и решил спрятаться. Навязчивая поклонница, а именно капитан местной стражи, не так приятна как хотелось бы, "
							+ "\nведь у себя на пару ночей означает не в постели и даже не дома, а в замке, внутри эциха с гвоздями. "
							+ "\nСтражники прошли мимо колодца, вор уже хотел праздновать свой триумф, как вдруг сверху упало ведро, уронившее вора на дно колодца. "
							+ "\nПока вор силился что-то осознать, сверху послышалась фраза \"Богоугодно!\" и полилась тёплая жидкость, "
							+ "\nкоторая крупными гроздьями капель падала на лицо вора"
							+ "\nВы получаете 3 урона";
			} else if(p.type == "Паладин") {
				p.respawnChance = 50;
				return "Когда силою своею перемещение осуществлял воин света великий, то дрожь по земле ходила неимоверная, что чудищам всем, да супостатам смерть возвещала! "
						+ "\nВдруг паладин услышал хор духов неправедных, воле и мыслям его противных. И отправился он навстречу опасностям и сволочам разным. "
						+ "\nДобрёл герой до колодца и ощутил силу в нём басурманскую, тогда разделся он догола и решил отдать себя на растерзание духам, "
						+ "\nчтобы очистить колодец, да позволить людям добрым воду из него пить сладкую. Да не помогла жертва великая, перемололи духи паладина в фарш, "
						+ "\nкоторый потом очень болезненно боги обратно в человека собирали."
						+ "\nВаша эгида паладина слабеет и шанс возродиться уменьшается до 50%";
			}else if(p.type == "Мечник") {
				p.hp -= Integer.parseInt(damageDescriptions.get("Колодец").get("damage"));
				return "Мечник искал выпивку и выходило это не слишком успешно, вдобавок ко всему он пытался восстановить события последних дней его беспробудного пьянства, "
						+ "\nибо он впервые недосчитался нескольких зубов, которые после нашёл вкованными в гарду своего меча. "
						+ "\nЗамученный горячей жаждой он выбился из сил и вдруг увидел колодец. Последние мысли в его воспалённом сознании подсказали, "
						+ "\nчто там может оказаться выпивка. Он подбежал к колодцу и обрадовался, увидел желтизну. Залпом он осушил заранее припасённую кружку. "
						+ "\nЭто была ошибка, фатальная ошибка."
						+ "\nВы получаете 3 урона";
			}
			else {
					p.hp -= Integer.parseInt(damageDescriptions.get("Колодец").get("damage"));
					return damageDescriptions.get("Колодец").get("description")
							+ "Вы получили божественную кару на 3 урона";
				}
			}
		});

		freeLocationDescriptions.put("Лагерь",
				"Ты попал в тихий разбойнический лагерь. В нем еще тлеют угольки, видимо что-то их испугало...");

		freeLocationsEvents.put("Лагерь", new EventInterface() {
			@Override
			public String execute(Player p) {
				return freeLocationDescriptions.get("Лагерь");
			}

		});

		// Setting types of random heal
		healDescriptions.put("Костер", new HashMap<String, String>() {
			{
				put("heal", "4");
				put("description",
						"Вы нашли место, где друиды проводили инквизицию деревьев-еретиков, \nи решили восстановить свои силы. Восстановление хп = 4");
			}
		});
		healDescriptions.put("Подушка", new HashMap<String, String>() {
			{
				put("heal", "5");
				put("description",
						"С неба упала подушка и вы решили, что Боги хотят, чтобы вы отдохнули! Восстановление хп = 5");
			}
		});

		healEvents.put("Костер", new EventInterface() {
			@Override
			public String execute(Player p) {
				if(p.type == "Варвар") {
					p.hp += Integer.parseInt(healDescriptions.get("Костер").get("heal"));
					return "Идя по лесу варвар увидел, что отряд друидов - инквизиторов напал на молодую рощу деревьев - еретиков. "
							+ "\nВарвара не волновали религиозные моменты, он просто решил, что убивать друидов веселее, чем рубить деревья, "
							+ "\nа потому встал на сторону защищающихся. В награду за помощь деревья отсыпали ему желудей"
							+ "\nВы восстановили 4 хп";
				}else if(p.type == "Жрец") {
					p.hp += Integer.parseInt(healDescriptions.get("Костер").get("heal"));
					return "Жрец шёл по лесу и истово молился своим богам, как вдруг он увидел, что его бывшие церковные сослуживцы нарядились в какую-то ветошь и листья, "
							+ "\nа потом начали рубить и сжигать деревья. Выяснилось, что это их протестное движение. Они решили мимикрировать под друидов и начать уничтожать то, "
							+ "\nчто друиды поклялись завещать. \"Богоугодно!\" - подумал жрец и присоединился к ним, после помочившись со всеми на  пепел, оставшийся после рощи. "
							+ "\nПсевдодруиды в благодарность накормили жреца целебными галлюциногенами."
							+ "\nВы восстановили 4 хп";
				}else if(p.type == "Вор") {
					p.hp += 1;
					return "Вор решил посетить свой старый схрон с награбленными ценностями и отправился в рощу. Его схрон был сделан в дупле одного из больших деревьев, "
							+ "\nчто росли в центре рощи. Однако когда вор пришёл на место, то обнаружил только пепелище и радующихся друидов, которые скрещивали свои рога. "
							+ "\nВор попытался с горя убить друидов, но один из них его обездвижил, а все остальные стали использовать вора в качестве обеденного стола, "
							+ "\nна его счастье, объедками его всё же подкармливали"
							+ "\nВы восстановили лишь 1 хп";
				}else if(p.type == "Паладин") {
					p.hp += Integer.parseInt(healDescriptions.get("Костер").get("heal"));
					return "Природу - красну девицу паладин обожает, он её всегда навещает, он локоны её лиственно-цветочные ласкает. Когда паладин пришёл в свою любимую рощу и обнаружил, "
							+ "\nчто она сожжена дотла, а на пепле древесном оскверняют мёртвую древесную плоть ироды рогатые, то воспылал праведным гневом паладин, "
							+ "\nрассёк каждого из этих иродов на сотни кусочков, а в придачу ещё и паренька одного освободил, которого твари эти использовали в качестве стола. "
							+ "\nДо того возгордился собой паладин, что раны на его теле затянулись."
							+ "\nВы восстановили 4 хп";
				}else if(p.type == "Мечник") {
					p.hp += 8;
					return "Воин продолжал разыскивать выпивку, чтобы продлить свою полноценную и насыщенную жизнь искателя приключений. Долго ли, коротко ли, но вышел он к роще, "
							+ "\nв которой друиды что-то праздновали. Присоединился к ним мечник и праздновал целый день. Однако, когда все запасы алкоголя были выпиты, мечник покинул это мероприятие, "
							+ "\nведь ему там было уже нечего делать."
							+ "\nВы восстановили целых 8 хп";
				}
				p.hp += Integer.parseInt(healDescriptions.get("Костер").get("heal"));
				return healDescriptions.get("Костер").get("description");
			}
		});
		healEvents.put("Подушка", new EventInterface() {
			@Override
			public String execute(Player p) {
				if(p.type == "Варвар") {
					p.hp += Integer.parseInt(healDescriptions.get("Подушка").get("heal"));
					return "Варвар никогда не отличался умом или сообразительностью, что неудивительно в целом. Во время его душераздирающего крика, "
							+ "\nкоторым он собирался вызвать дождь, чтобы помыться, в него прилетела с неба каменная божественная подушка. "
							+ "\nВарвар не почувствовал боли, а только сладкую истому и желание поспать."
							+ "\nВы восстановили 5 хп";
				}else if(p.type == "Жрец") {
					p.predictionChance = 60;
					return "Жрец гордо шёл вперёд, ведь наконец-то в своей жизни он смог самостоятельно помыть за собой посуду. Он настолько гордился собой, "
							+ "\nчто повторял каждый миг в своей голове - \"Богоугодно!\" Однако это было вовсе не так, богам это не было угодно, ведь жрец начал зарываться "
							+ "\nи считать себя превыше их. Наказание было суровым - они сбросили на него острую подушку, которая вырезала у Жреца один глаз. "
							+ "\nОн горько заплакал отовсюду откуда мог - в том числе и в панталонах."
							+ "\nТеперь вы можете предсказать следующую клетку с 60% шансом";
				}else if(p.type == "Вор") {
					p.hp += Integer.parseInt(healDescriptions.get("Подушка").get("heal"));
					return "Вор уже полз вдоль тракта от усталости. Ничего на свете он так не хотел как поспать. Иногда ему удавалось встать на ноги усилием могучей воли, "
							+ "\nно вскоре он падал обратно. К сожалению, по условностям игры он не мог отдохнуть без подушки, "
							+ "\nно великие ежиные боги решили сжалиться над ним и даровали ему желанный предмет. Едва голова вора коснулась приятного и мягкого предмета, "
							+ "\nкак он тут же уснул. Он отдохнул, но к сожалению, когда он проснулся, то обнаружил, что его ногу грызёт дикая собака"
							+ "\nВы восстановили 5 хп";
				}else if(p.type == "Паладин") {
					p.buff = true;
					return "Не знает ни страха, ни устали воин, что на светлые мысли настроен, да на благие дела побуждён. Шёл паладин три дня и три ночи, "
							+ "\nдве недели и три месяца, а всё никак в сон его не клонило. Решили боги ему доброе дело сделать и позволили поспать, "
							+ "\nда ещё и подушку железную с неба сбросили. До того обрадовался паладин разрешению, до того вера его окрепла, благодаря подушке железной, "
							+ "\nчто молится он стал вместо сна и раны на нём, как разрезы в водной глади, заживать стали."
							+ "\nВы получили регенерацию 1 хп за каждые 3 хода";
				}else if(p.type == "Мечник") {
					p.hp += Integer.parseInt(healDescriptions.get("Подушка").get("heal"));
					return "Мечник поймал белую горячку и поэтому разговаривал с большим ёжиком, пока шёл вдоль тракта. Его здоровый меч по пути "
							+ "\nотрубал головы всяким гоблинам, которые попадались по пути. Внезапно в мечника прилетела подушка с бутылками эля внутри. "
							+ "\nКогда мечник их опустошил, то восстановил своё здоровье, но увидел, что он убивал не гоблинов на тракте, "
							+ "\nа крестьян в какой-то деревне. С неба ему грозили пальчиком ежиные боги."
							+ "\nВы восстановили 5 хп";
				}
				p.hp += Integer.parseInt(healDescriptions.get("Подушка").get("heal"));
				return healDescriptions.get("Подушка").get("description");
			}
		});
	}
}
