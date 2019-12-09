package game;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		
		// System objects
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		
		
		// Enemies
		String[] enemies = { "Skeleton", "Zombie", "Goblin", "orc", "troll", "brigand" };
		int maxEnemyHealth = 75;
		int enemyAttackDamage = 25;
		
		// Gold and shop
		int numGold = 0;
		int goldDropChance = 60;
		int swordPrice = 4;
		int swordDamage = 10;
		int armorPrice = 10;
		int armorAmount = 10;
		
		// Boss
		String[] bosses = { "Skeleton King", "King of the mountain", "Dragon", "Zombie Lord" };
		int bossHealth = 200;
		int bossAttackDamage = 20;
		
		
		// Player Variables
		int health = 100;
		int attackDamage = 50;
		int numHealthPotions = 3;
		int healthPotionHealAmount = 30;
		int healthPotionDropChance = 50;// Percentage
		int healthPotionPrice = 2;
		int fleeChance = 20; // Percentage
		int evadeChance = 30;
		
		boolean running = true;
		boolean shopRunning = true;
		System.out.println("Welcome to the Dungeon!");
		
		GAME:
		while(running) {
			System.out.println("-----------------------------------------");
			
			System.out.println("\tPress the different numbers for different actions!\n");
			
			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = enemies[rand.nextInt(enemies.length)];
			String boss = bosses[rand.nextInt(bosses.length)];
			System.out.println("\t# " + enemy + " has appeared! #\n");
			
			
			while(enemyHealth > 0) {
				System.out.println("\tYour HP: " + health);
				System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
				System.out.println("\n\tWhat would you like to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink health potion");
				System.out.println("\t3. Flee");
				System.out.println("\t4. Evade");
				System.out.println("\t5. Shop");
				
				
				String input = in.nextLine();
				if(input.equals("1")) {
					int damageDealt = rand.nextInt(attackDamage);
					int damageTaken = rand.nextInt(enemyAttackDamage);
					
					enemyHealth -= damageDealt;
					health -= damageTaken;
					System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
					System.out.println("\t> You receive " + damageTaken + " in retaliation");
					
					if (health < 1) {
						System.out.println("\t> You have taken too much damage, you are too weak to go on!");
						break;
					}
				} 
				else if(input.equals("2")) {
					if(numHealthPotions > 0) {
						health += healthPotionHealAmount;
						numHealthPotions--;
						System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
										     + "\n\t> You now have " + health + "HP."
										     	+ "\n\t> You have " + numHealthPotions +  " health potions left.\n");
					}
					else {
						System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
					}
				} 
				else if(input.equals("3") && rand.nextInt(100) < fleeChance) {
					System.out.println("\t> You were unable to escape");
				}
				else if(input.equals("3")) {
					System.out.println("\t> You succesfully escaped");
					continue GAME;
				}
				else if(input.equals("4") && rand.nextInt(100) < evadeChance) {
					int damageTaken = rand.nextInt(enemyAttackDamage);
					
					health -= damageTaken;
					
					System.out.println("\t> You were unable to evade and received " + damageTaken + " in retaliation");
					
					if (health < 1) {
						System.out.println("\t> You have taken too much damage, you are too weak to go on!");
						break;
					}
					
				}
				else if(input.equals("4")) {
					System.out.println("\t> You successfully evaded!");
				}else if(input.equals("5") && numGold <= 0) {
					System.out.println("t> You don't have enough money");
					continue GAME;
				 
				}else if(input.equals("5")) {
					SHOP:
					while(shopRunning && numGold > 0) {
						System.out.println("t> Welcome to our humble shop");
						
						System.out.println("\t Sword price: 4 gold");
						System.out.println("\t Health potion price: 2 gold");
						System.out.println("\n What do you want to buy?");
						System.out.println("\t1.Buy sword");
						System.out.println("\t2.Buy health potion");
						System.out.println("\t3.Buy armor");
						System.out.println("\t4.Exit shop");
						
						String input3 = in.nextLine();
						
						if(input3.equals("1")) {
							numGold = numGold - swordPrice;
							attackDamage = attackDamage + swordDamage;
							System.out.println("\t You now have: " + attackDamage + " attack damage!");
							System.out.println("\t You have: " + numGold + " gold left!");
							System.out.println("-----------------------------------------");
							continue SHOP;	
							
						}else if(input3.equals("2")) {
							numGold = numGold - healthPotionPrice;
							numHealthPotions++;
							System.out.println("\t> You now have: " + numHealthPotions + " health potions left!");
							System.out.println("\t> You have: " + numGold + " gold left!");
							System.out.println("-----------------------------------------");
							continue SHOP;
							
						}else if(input3.equals("3")) {
							numGold = numGold - armorPrice;
							enemyAttackDamage = enemyAttackDamage - armorAmount;
							bossAttackDamage = bossAttackDamage - armorAmount;
							System.out.println("\t> All enemies now deal: " + enemyAttackDamage + " damage!");
							System.out.println("\t> You have: " + numGold + " gold left!");
							System.out.println("-----------------------------------------");
							continue SHOP;
							
						}else if(input3.equals("4")) {
							continue GAME;	
						}
						
					}
					
				}
				else {
					System.out.println("\t>Invalid command");
				}
			}	
				
				if(health < 1) {
					System.out.println("\tYou limp out of the dungeon, weak from battle");
					System.out.println("----------GAME OVER----------");
					break;
				}
				
				System.out.println("-----------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have " + health + " HP left #");
				if(rand.nextInt(100) < healthPotionDropChance) {
					numHealthPotions++;
					System.out.println(" # The " + enemy + " dropped a health potion! #");
					System.out.println(" # You now have " + numHealthPotions + " health potion(s). # ");
				}
				if(rand.nextInt(100) < goldDropChance) {
					numGold++;
					System.out.println(" # The " + enemy + " dropped gold! #");
					System.out.println(" # You now have " + numGold + " gold. # ");
				}
				
				System.out.println("-----------------------------------------");
				System.out.println("What would you like to do now?");
				System.out.println("1. Continue fighting");
				System.out.println("2. Exit dungeon");
				System.out.println("3. Fight Boss");
				String input2 = in.nextLine();
				
				while(!input2.equals("1") && !input2.equals("2") && !input2.equals("3") && !input2.equals("4")) {
					System.out.println("Invalid command!");
					input2 = in.nextLine();
				}
				
				if(input2.equals("1")) {
					System.out.println("You continue on your adventure!");
					continue GAME;
				}
				else if(input2.equals("2")) {
					System.out.println("You exit the dungeon, successful from your adventures!");
					break;
				}
				
				
				else if(input2.equals("3")) {
					System.out.println("\t# " + boss + " has appeared! #\n");
					
				}
				
					while(bossHealth > 0) {
						
						if(numHealthPotions >= 10) {
							bossHealth = 300;
						}
			
						System.out.println("\tYour HP: " + health);
						System.out.println("\t" + boss + "'s HP: " + bossHealth);
						System.out.println("\n\tWhat would you like to do?");
						System.out.println("\t1. Attack");
						System.out.println("\t2. Drink health potion");
						System.out.println("\t3. Evade");
						
						
						
						String input3 = in.nextLine();
						if(input3.equals("1")) {
							int damageDealt = rand.nextInt(attackDamage);
							int damageTaken = bossAttackDamage;
							
							bossHealth -= damageDealt;
							health -= damageTaken;
							System.out.println("\t> You strike the " + boss + " for " + damageDealt + " damage.");
							System.out.println("\t> You receive " + damageTaken + " in retaliation");
							
							if (health < 1) {
								System.out.println("\t> You have taken too much damage, you are too weak to go on!");
								break GAME;
							}
							else if(bossHealth <= 0) {
								System.out.println("You defeated the boss!");
								break GAME;
							}
						} 
						else if(input3.equals("2")) {
							if(numHealthPotions > 0) {
								health += healthPotionHealAmount;
								numHealthPotions--;
								System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
												     + "\n\t> You now have " + health + "HP."
												     	+ "\n\t> You have " + numHealthPotions +  " health potions left.\n");
							}
							else {
								System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
							}
						} 
						else if(input3.equals("3") && rand.nextInt(100) < evadeChance) {
							int damageTaken = rand.nextInt(enemyAttackDamage);
							
							health -= damageTaken;
							
							System.out.println("\t> You were unable to evade and received " + damageTaken + " in retaliation");
							
							if (health < 1) {
								System.out.println("\t> You have taken too much damage, you are too weak to go on!");
								break;
							}
							
						}
						else if(input3.equals("3")) {
							System.out.println("\t> You successfully evaded!");
						}
						else {
							System.out.println("\t>Invalid command");
						
					}
				}
					
					
		System.out.println("######################");
		System.out.println("# THANKS FOR PLAYING #");
		System.out.println("######################");
		
	}
		
		
		System.out.println("######################");
		System.out.println("# THANKS FOR PLAYING #");
		System.out.println("######################");
		
	
	
	
	
	
	
	
	
	}
	
}