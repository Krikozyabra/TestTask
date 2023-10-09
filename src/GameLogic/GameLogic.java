package GameLogic;

import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	Player player = new Player();
	Config cfg = new Config();
	EventController eventCont = new EventController();
	Scanner scan = new Scanner(System.in);
	Random rnd = new Random();
	
	public void gameStart() {
		int id = 0;
		while(true) {
			String className = cfg.playerTypes[id];
			System.out.println("Выберите класс:");
			System.out.println("Имя: "+className);
			System.out.println("Описание: "+cfg.playerDescriptions.get(className)+"\n"+cfg.playerStats.get(className).toString());
			System.out.println("1-Следующий класс, 2-Предыдущий класс, 3-Этот класс, 4~8-id Классов по списку");
			byte input = scan.nextByte();
			if(input==3) {
				try {
					player.setType(className);
				}catch(Exception e) {
					System.out.println("Некоторые стартовые параметры класса вышли из диапазона");
					quitGame();
				}
				
				System.out.println("Вы выбрали класс - "+className);
				System.out.println("Вы можете выиграть 2 способами:"
						+ "\n1) Дойти до клетки №100"
						+ "\n2) Сыграть в ящик");
				break;
			}else if(input>3 && input<9){
				className = cfg.playerTypes[input-4];
				System.out.println("Вы уверены, что хотите выбрать класс - "+className);
				System.out.println("1-Да, >2-Нет");
				input = scan.nextByte();
				if(input==1) {
					try {
						player.setType(className);
					}catch(Exception e) {
						System.out.println("Некоторые стартовые параметры класса вышли из диапазона");
						quitGame();
					}
					System.out.println("Вы выбрали класс - "+className);
					System.out.println("Вы можете выиграть 2 способами:"
							+ "\n1) Дойти до клетки №100"
							+ "\n2) Сыграть в ящик");
					break;
				}
			}else if(input>8){
				System.out.println("Такой команды не предусмотрено");
			}else {
				if(input==1) {
					id++;
					if(id>4) id=0;
				}else {
					id--;
					if(id<0) id = 4;
				}
			}
		}
		logic();
	}
	
	private void quitGame() {
		// TODO Auto-generated method stub
		System.out.println("Произошла непредвиденная ошибка :( Приносим свои извинения");
	}

	public void logic() {
		Event e = null;
		life_cycle:
		while(player.isAlive() || player.getPosition() < 100) {
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.format("Вы находитесь на %d клетке. Ваш следующий ход?\n"
						+ "1-Кинуть кубик или сходить\n"
						+ "2-Посмотреть статы\n"
						+ "3-Захилиться (%d осталось)\n",player.getPosition(), player.healAmount);
				byte answer = scan.nextByte();
				switch(answer) {
				case 1:{
					int turn = rnd.nextInt(6)+1;
					System.out.println("На кубике выпало - "+turn);
					if(player.type == "Жрец") {
						e = eventCont.randomEvent();
						String type = "";
						switch(e.getType()) {
							case Event.ENEMY:{
								type = "враг - "+e.getEnemy().type;
								break;
							}
							case Event.RANDOM_DAMAGE:{
								type = "получение урона в размере "+e.getDamageHeal();
								break;
							}
							case Event.FREE_LOCATION:{
								type = "свободная локация для прогулок";
								break;
							}
							case Event.RANDOM_HEAL:{
								type = "восстановление хп в размере "+e.getDamageHeal();
								break;
							}
						}
						System.out.println("Бог даровал жрецу возможность видеть, что на следующей клетке"
								+ "\nОн говорит, что на следующей клетке будет "+type
								+ "\nВы сходите(1) или восстановите чуть-чуть здоровья и сходите(2)?");
						answer = scan.nextByte();
						if(answer == 1) {
							if(!playEvent(e)) break life_cycle;
						}else if(answer == 2) {
							System.out.println(player.heal());
							if(!playEvent(e)) break life_cycle;
						}else {
							System.out.println("Такой команды не предусмотренно.");
						}
					}else if(player.type == "Варвар"){
						int turn2 = rnd.nextInt(6)+1;
						turn2 = turn > turn2 ? turn : turn2;
						System.out.format("Варвара часто преследуют умные мысли и иногда они его догонят."
								+ "\nЭтот случай стал тем самым исключением и он перекинул кубик,"
								+ "\nчтобы сходить на самое большое количество шагов - %d\n", turn2);
						player.addPosition(turn2);
						System.out.format("Вы переместились на %d и оказались на клетке №%d\n",turn2,player.getPosition());
						if(!playEvent(eventCont.randomEvent())) break life_cycle;
					}else {
						player.addPosition(turn);
						System.out.format("Вы переместились на %d и оказались на клетеке №%d\n",turn,player.getPosition());
						if(!playEvent(eventCont.randomEvent())) break life_cycle;
					}
					break;
				}
				case 2:{
					System.out.println(player.showStats());
					break;
				}
				case 3:{
					System.out.println(player.heal());				}
				default:{
					System.out.println("Такой команды не предусмотренно.");
					break;
				}
			}
		}
		if(!player.isAlive()) player.died();
		else player.win();
		scan.close();
	}

	private boolean playEvent(Event e) {
		// TODO Auto-generated method stub
		if(e.getType() == Event.ENEMY) {
			System.out.println("О нет! На вас напал(а) - "+e.getEnemy().type);
			System.out.println("Его(ё) статы:" + e.getEnemy().getStats());
			player.setFightingStatus(true);
			while(player.inFight()) {
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.format("Ваш выбор?"
						+ "\n1 - Атаковать"
						+ "\n2 - Бежать"
						+ "\n3 - Захилиться (%d осталось)"
						+ "\n4 - Посмотреть статы\n",player.healAmount);
				byte answer = scan.nextByte();
				if(answer == 1) { //Attack attempt
					int damage = player.attack(e.getEnemy().protection);
					if(damage>0) {
						System.out.format("Вы смогли пробить %s и нанесли %d урона!\n",e.getEnemy().type,damage);
						e.getEnemy().hp -= damage;
						if(e.getEnemy().hp <= 0) {
							System.out.println("Вы победили своего врага! Поздравляем!");
							e.getEnemy().died();
							player.setFightingStatus(false);
							break;
						}
						System.out.println("У вашего врага осталось "+e.getEnemy().hp+" здоровья");
					}else {
						System.out.println("Вы не смогли попасть по "+e.getEnemy().type);
					}
					damage = e.getEnemy().attack(player.protection);
					if(damage>0) {
						System.out.format("%s смог(ла) пробить вас и нанес(ла) %d урона!\n",e.getEnemy().type,damage);
						player.hp -= damage;
						System.out.format("У вас осталось %d из %d хп\n",player.hp,player.maxHP);
						if(player.hp <= 0) break;
					}else {
						System.out.format("%s не смог(ла) попасть по вам\n",e.getEnemy().type);
					}
				}else if(answer == 2) { //Attempting to run away
					if((rnd.nextInt(100)+1)<=player.runAwayChance) {
						System.out.format("А вы круты! Поступили по мужски и сбежали\n");
						player.setFightingStatus(false);
					}else {
						System.out.println("Вас поймали на попытке сбежать, поэтому получаете по заслугам");
						int damage = rnd.nextInt(e.getEnemy().maxDMG)+e.getEnemy().minDMG;
						player.hp -= damage;
						System.out.println("Вы получили - "+damage+" урона");
						if(player.hp <= 0) break;
					}
				}else if(answer == 3) {
					int damage = e.getEnemy().attack(player.protection);
					System.out.println(player.heal());
					System.out.format("%s вас попытался(сь) поймать на ошибке и нанес(ла) - %d урона\n",e.getEnemy().type, damage);
					player.hp -= damage;
					if(player.hp <= 0) break;
				}else if(answer == 4){
					System.out.println(player.showStats());
				}else {
					System.out.println("Такой команды бог-разработчик не придумал");
				}
			}
			}else if(e.getType() == Event.RANDOM_DAMAGE) {
				System.out.println(e.getEventDescription());
				player.hp-=e.getDamageHeal();
			}else if(e.getType() == Event.FREE_LOCATION) {
				System.out.println(e.getLocationDescription());
			}else if(e.getType() == Event.RANDOM_HEAL) {
				System.out.println(e.getEventDescription());
				player.hp+=e.getDamageHeal();
			}
		if(player.isAlive()) return true;
		else return false;
		}
	}
