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
		choosing:
		while(true) {
			String className = cfg.playerTypes[id];
			System.out.println("Выберите класс:");
			System.out.println("Имя: "+className);
			System.out.println("1-Следующий класс\n2-Предыдущий класс\n3-Этот класс\n4-Посмотреть историю персонажа\n5~9-id Классов по списку");
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
						+ "\n1) Дойти до клетки №1000"
						+ "\n2) Сыграть в ящик");
				break choosing;
			}else if(input == 4) {
				System.out.println(cfg.playerDescriptions.get(className));
			}else if(input>4 && input<10){
				className = cfg.playerTypes[input-5];
				System.out.println("Вы уверены, что хотите выбрать класс - "+className);
				System.out.println("1-Да, 2-Нет, 3-Посмотреть историю");
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
							+ "\n1) Дойти до клетки №1000"
							+ "\n2) Сыграть в ящик");
					break choosing;
				}else if(input==3) {
					System.out.println(cfg.playerDescriptions.get(className));
				}
			}else if(input>10){
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
		while(player.isAlive()) {
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.format("Вы находитесь на %d клетке. Ваш следующий ход?\n"
						+ "1-Кинуть кубик или сходить\n"
						+ "2-Посмотреть статы\n"
						+ "3-Захилиться (%d осталось)\n",player.getPosition(), player.healAmount);
				byte answer = scan.nextByte();
				turn:
				switch(answer) {
				case 1:{
					int turn = rnd.nextInt(6)+1;
					System.out.println("На кубике выпало - "+turn);
					e = eventCont.randomEvent();
					if(player.type == "Жрец") {
						if(!cfg.priestSpells.passive(e, player, turn)) {
							if(!playEvent(e)) break life_cycle;
						}
						break turn;
					}else if(player.type == "Варвар"){
						cfg.barbarianSpells.passive(player, turn);
						if(!playEvent(e)) break life_cycle;
						break turn;
					}else {
						player.addPosition(turn);
						System.out.format("Вы переместились на %d клеток и оказались на клетке %d\n",turn, player.getPosition());
						if(!playEvent(e)) break life_cycle;
						break turn;
					}
				}
				case 2:{
					System.out.println(Config.ANSI_GREEN+player.showStats()+Config.ANSI_RESET);
					break;
				}
				case 3:{
					System.out.println(player.heal());	
					break;
				}
				default:{
					System.out.println("Такой команды не предусмотренно.");
					break;
				}
			}
			if(player.getPosition()>=1000) break life_cycle;
		}
		if(!player.isAlive()) player.died();
		else player.win();
		scan.close();
	}

	private boolean playEvent(Event e) {
		// TODO Auto-generated method stub
		if(e.getEventType() == Event.ENEMY) {
			System.out.println("О нет! На вас напал(а) - "+e.getEnemy().type);
			System.out.println("Его(ё) статы:" +Config.ANSI_RED+ e.getEnemy().getStats()+Config.ANSI_RESET);
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
					if(e.getEnemy().type == "Острый еж") {
						if(damage > 0) {
							if(e.getEzh().contrattack()) {
								player.hp -= damage;
								System.out.println("Поздравляю вы напоролись на иголки острого ежа. Весь урон был возвращен вам с полна. Урон = "+damage);
								if(player.type == "Паладин") {
									if(!cfg.paladinSpells.passive(player)) break;
								}else {
									break;
								}
							}else {
								System.out.println("Вы смогли маневрировать между иголками и втащили этому ежу");
								System.out.format("Вы смогли пробить %s и нанесли %d урона!\n",e.getEnemy().type,damage);
								e.getEnemy().hp -= damage;
								if(e.getEnemy().hp <= 0) {
									System.out.println("Вы победили своего врага! Поздравляем!");
									e.getEnemy().died();
									player.setFightingStatus(false);
									break;
								}
								System.out.println("У вашего врага осталось "+e.getEnemy().hp+" здоровья");
							}
						}else {
							System.out.println("Вы не смогли попасть по "+e.getEnemy().type);
						}
					}else {
						if(damage > 0) {
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
							if(player.hp <= 0) {
								if(player.type == "Паладин") {
									if(!cfg.paladinSpells.passive(player)) break;
								}else {
									break;
								}
							}
						}else {
							System.out.format("%s не смог(ла) попасть по вам\n",e.getEnemy().type);
						}
					}
				}else if(answer == 2) { //Attempting to run away
					if(e.getEnemy().type != "Острый еж") {
						if(player.type == "Паладин") {
							System.out.println("Вы воин света, а воинам света не ведан страх.");
						}else {
							if((rnd.nextInt(100)+1)<=player.runAwayChance) {
								System.out.format("А вы круты! Поступили по мужски и сбежали\n");
								player.setFightingStatus(false);
							}else {
								System.out.println("Вас поймали на попытке сбежать, поэтому получаете по заслугам");
								int damage = rnd.nextInt(e.getEnemy().maxDMG)+e.getEnemy().minDMG;
								player.hp -= damage;
								System.out.println("Вы получили - "+damage+" урона");
								if(player.hp <= 0) {
									if(player.type == "Паладин") {
										if(!cfg.paladinSpells.passive(player)) break;
									}else {
										break;
									}
								}
							}
						}
					}else {
						System.out.println("Вы зациклены в пространстве у вас нет другого выхода...");
					}
				}else if(answer == 3) {
					if(e.getEnemy().type == "Острый еж") {
						System.out.println("Ай-ай-ай! На глазах бедного ежа вы кушаете восстанавливающий хлебец.\nКак вам не стыдно!");
					}else {
						int damage = e.getEnemy().attack(player.protection);
						System.out.println(player.heal());
						System.out.format("%s вас попытался(сь) поймать на ошибке и нанес(ла) - %d урона\n",e.getEnemy().type, damage);
						player.hp -= damage;
						if(player.hp <= 0) {
							if(player.type == "Паладин") {
								if(!cfg.paladinSpells.passive(player)) break;
							}else {
								break;
							}
						}
					}
				}else if(answer == 4){
					System.out.println(Config.ANSI_GREEN+player.showStats()+Config.ANSI_RESET);
				}else {
					System.out.println("Такой команды бог-разработчик не придумал");
				}
			}
			}else{
				System.out.println(e.getEventInterface().execute(player));
				if(player.hp <= 0 && player.type == "Паладин") cfg.paladinSpells.passive(player);
				if(player.hp > player.maxHP) player.hp = player.maxHP;
			}
		if(player.isAlive()) return true;
		else return false;
		}
	}
