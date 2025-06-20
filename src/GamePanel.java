import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 3;
	final int FIGHT = 2;
	int currentState = MENU;
	Player player = new Player(250, 250, 10, 10, 10);
	Font titleFont;
	Font enterFont;
	Font combatFont;
	Timer frameDraw;
	Random ran = new Random();
	int rand;
	Enemy enemy;
	int roll;
	int enemyChoice;
	final int ATTACK = 4;
	final int DEFEND = 5;
	final int MAGIC = 6;
	final int RUN = 7;
	int battleChoice = ATTACK;
	boolean isDoingMagic = false;
	Spell spell1 = new MagicMissile((player.health/2), 25, 2);
	Spell spell2 = new Fireball(2, 99, 1);
	Spell spell3 = new Fireball(2, 99, 1);
	Spell spell4 = new Fireball(2, 99, 1);
	String firstSpell = "MagMissl";
	String secondSpell = "fireball";
	String thirdSpell = "fireball";
	String fourthSpell = "fireball";
	Menu menu = new Menu();
	Mountain mountain = new Mountain();
	Dungeon dungeon = new Dungeon();
	final int OVERWORLD = 1;
	final int MOUNTAIN = 2;
	final int DUNGEON = 3;
	final int GAMEFINISH = 5;
	final int UNDERWORLD = 4;
	int location = OVERWORLD;
	MountainLocation mtl = new MountainLocation();
	DungeonLocation dunLoc = new DungeonLocation();
	Grass grass = new Grass();
	Death death = new Death();
	EndScreen end = new EndScreen();
	Underworld hell = new Underworld();

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterFont = new Font("Arial", Font.PLAIN, 25);
		combatFont = new Font("Arial", Font.PLAIN, 27);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}
	void drawFinishState(Graphics g) {
		end.draw(g);
	}
	void drawMenuState(Graphics g) {
		menu.draw(g);
	}

	void drawEndState(Graphics g) {
		death.draw(g);
	}

	void drawGameState(Graphics g) {
		if (location == OVERWORLD) {
			grass.draw(g);
			mountain.draw(g);
			player.draw(g);
			dungeon.draw(g);
			if (player.x >= 0 && player.x <= 100 && player.y >= 0 && player.y <= 100) {
				location = MOUNTAIN;
				player.x = 250;
				player.y = 250;
			}
			if(player.x>=400&&player.x<=500&&player.y>=100&&player.y<=100) {
				if(mtl.key) {
					location = DUNGEON;
					player.x = 250;
					player.y = 250;
				}
				else {
					player.x-=10;
					player.y+=10;
					JOptionPane.showMessageDialog(null, "it's locked.");
				}
			}
		}
		if (location == MOUNTAIN) {
			// g.setColor(Color.gray);
			// g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
			mtl.checkLocation(player);
			mtl.drawLocation(g);
			player.draw(g);
			if (player.y <= 500 && player.y >= 490) {
				location = OVERWORLD;
				player.x = 50;
				player.y = 110;
			}
			if (mtl.dragon) {
				currentState = FIGHT;
				changeToFight();
			}
		}
		if(location == DUNGEON) {
			dunLoc.CheckLocation(player);
			dunLoc.DrawLocation(g);
			player.draw(g);
			
			if(dunLoc.trollActive) {
				currentState = FIGHT;
				changeToFight();
			}
			if(dunLoc.gameBeaten) {
				currentState = GAMEFINISH;
			}
		}
		if(location == UNDERWORLD) {
			hell.drawLocation(g);
			hell.checkLocation(player);
			player.draw(g);
			
			if(hell.satanActive) {
				currentState = FIGHT;
				changeToFight();
			}
			if(hell.GameBeaten) {
				currentState = GAMEFINISH;
			}
		}
	}

	void drawFightState(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.white);
		g.fillRect(0, 401, 500, 100);
		enemy.draw(g);
		g.setColor(Color.black);
		g.fillRect(10, 410, 110, 50);
		g.fillRect(130, 410, 110, 50);
		g.fillRect(250, 410, 110, 50);
		g.fillRect(370, 410, 110, 50);
		g.setFont(combatFont);
		g.setColor(Color.white);
		g.drawString("HP: " + String.valueOf(player.health) + "/" + String.valueOf(player.maxHealth), 0, 25);
		g.drawString("MP: " + String.valueOf(player.MP) + "/" + String.valueOf(player.maxMP), 0, 50);
		g.drawString("enemy: " + String.valueOf(enemy.health) + "/" + String.valueOf(enemy.maxHealth), 0, 75);
		if (!isDoingMagic) {
			g.drawString("ATTACK", 10, 445);
			g.drawString("DEFEND", 130, 445);
			g.drawString("MAGIC", 250, 445);
			g.drawString("RUN", 370, 445);
			g.setColor(Color.red);
			if (battleChoice == 4) {
				g.fillRect(0, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString("ATTACK", 10, 445);
			}
			if (battleChoice == 5) {
				g.fillRect(120, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString("DEFEND", 130, 445);
			}
			if (battleChoice == 6) {
				g.fillRect(240, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString("MAGIC", 250, 445);
			}
			if (battleChoice == 7) {
				g.fillRect(360, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString("RUN", 370, 445);
			}
		}
		if (isDoingMagic) {
			g.drawString(firstSpell, 10, 445);
			g.drawString(secondSpell, 130, 445);
			g.drawString(thirdSpell, 250, 445);
			g.drawString(fourthSpell, 370, 445);
			g.setColor(Color.red);
			if (battleChoice == 4) {
				g.fillRect(0, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString(firstSpell, 10, 445);
			}
			if (battleChoice == 5) {
				g.fillRect(120, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString(secondSpell, 130, 445);
			}
			if (battleChoice == 6) {
				g.fillRect(240, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString(thirdSpell, 250, 445);
			}
			if (battleChoice == 7) {
				g.fillRect(360, 410, 10, 50);
				g.setColor(Color.yellow);
				g.drawString(fourthSpell, 370, 445);
			}
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void updateFightState() {

	}
	void updateFinishState() {
		
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		}
		if (currentState == GAME) {
			drawGameState(g);
		}
		if (currentState == END) {
			drawEndState(g);
		}
		if (currentState == FIGHT) {
			drawFightState(g);
		}
		if(currentState == GAMEFINISH) {
			drawFinishState(g);
		}
	}
	
	public void changeToFight() {
		if (currentState == FIGHT) {
			if (mtl.dragon == false&&dunLoc.trollActive == false&&hell.satanActive ==false) {
				enemyChoice = ran.nextInt(2);
				if (enemyChoice == 0) {
					enemy = new Skeleton(170, 150, 150, 150, 10, 10, 2, 50);
				} else if (enemyChoice == 1) {
					enemy = new Zombie(170, 150, 150, 150, 10, 10, 1, 20);
				}
			}
			if(mtl.dragon) {
				enemy = new Dragon(170, 150, 150, 150, 50, 45, 3, 1000);
			}
			if(dunLoc.trollActive) {
				enemy = new Troll(170, 150, 150, 150, 60, 60, 7, 2000);
			}
			if(hell.satanActive) {
				enemy = new Satan(170,150,150,150,70,70,10,3000);
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("keypressed: " + e.getKeyCode());
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				currentState = GAME;
			}
			if (currentState == END) {
				currentState = MENU;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() == KeyEvent.VK_W) {
				if (player.y > 0) {
					player.y -= player.speed;
				}
				rand = ran.nextInt(25);
				if (rand == 1) {
					currentState = FIGHT;
					changeToFight();

				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() == KeyEvent.VK_S) {
				if (player.y < 490) {
					player.y += player.speed;
				}
				rand = ran.nextInt(25);
				if (rand == 1) {
					currentState = FIGHT;
					changeToFight();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_A) {
				if (player.x > 0) {
					player.x -= player.speed;
				}
				rand = ran.nextInt(25);
				if (rand == 1) {
					currentState = FIGHT;
					changeToFight();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_D) {
				if (player.x < 490) {
					player.x += player.speed;
				}
				rand = ran.nextInt(25);
				if (rand == 1) {
					currentState = FIGHT;
					changeToFight();
				}
			}
//TODO:   this code doesn't get reached when current state gets set to FIGHT in the MountainLocation class.
// 		  move this out of the if currentState==GAME section, and only let it run once. it should run as soon as
//        the currentState gets set to FIGHT
		
		}
		
		
		if (currentState == FIGHT) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (battleChoice > 4) {
					battleChoice--;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (battleChoice < 7) {
					battleChoice++;
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (isDoingMagic) {
					isDoingMagic = false;
				}
			}
		}

	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if (currentState == FIGHT) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
				System.out.println("space");
				if (!isDoingMagic) {
					if (battleChoice == 4) {
						roll = ran.nextInt(2);
						if (roll == 1) {
							enemy.health -= player.damage;
						}
						roll = ran.nextInt(2);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
						if (enemy.health <= 0) {
							if (mtl.dragon) {
								mtl.dragon = false;
								mtl.dragonDead = true;
								JOptionPane.showMessageDialog(null, "you found a key!");
							}
							if(dunLoc.trollActive) {
								dunLoc.trollActive = false;
								dunLoc.trollDead = true;
							}
							if(hell.satanActive) {
								hell.satanActive = false;
								hell.satanDead = true;
							}
							currentState = GAME;
							enemy.health = enemy.maxHealth;
							player.XPNeed -= enemy.XP;
							player.health += player.damage;
							if (player.health > player.maxHealth) {
								player.health = player.maxHealth;
							}
							if (player.XPNeed <= 0) {
								player.maxHealth += 5;
								player.health = player.maxHealth;
								player.maxMP += 5;
								player.MP = player.maxMP;
								player.damage++;
								player.XPNeedTotal += 50;
								player.XPNeed = player.XPNeedTotal;
								JOptionPane.showMessageDialog(null, "Congrats! You leveled up!");
							}
						}
					}
					if (battleChoice == 5) {
						roll = ran.nextInt(5);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
					}
					if (battleChoice == 6) {
						isDoingMagic = true;
						return;
						
					}
					if (battleChoice == 7) {
						roll = ran.nextInt(3);
						enemy.health = enemy.maxHealth;
						currentState = GAME;

					}
					if (player.health <= 0) {
						currentState = END;
						player.health = player.maxHealth;
						player.MP = player.maxMP;
						mtl.dragon = false;
						mtl.dragonDead = false;
						mtl.location = 1;
						dunLoc.location = 1;
						hell.location = 1;
						//location = OVERWORLD;
						player.x = 250;
						player.y = 250;
						enemy.health = 0;
						player.XPNeedTotal = 100;
						player.XPNeed = player.XPNeedTotal;
						dunLoc.trollActive = false;
						dunLoc.trollDead = false;
						dunLoc.hasBomb = false;
						dunLoc.blockageDestroyed = false;
						dunLoc.bombYes = false;
						dunLoc.hasKey1 = false;
						hell.satanActive = false;
						hell.satanDead = false;
					}
				}
				if (isDoingMagic) {
					if (battleChoice == 4) {
						player.castSpell(enemy, spell1, ran);
						roll = ran.nextInt(2);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
						if (enemy.health <= 0) {
							if (mtl.dragon) {
								mtl.dragon = false;
								mtl.dragonDead = true;
								JOptionPane.showMessageDialog(null, "you found a key!");
							}
							if(dunLoc.trollActive) {
								dunLoc.trollActive = false;
								dunLoc.trollDead = true;
							}
							if(hell.satanActive) {
								hell.satanActive = false;
								hell.satanDead = true;
							}
							currentState = GAME;
							enemy.health = enemy.maxHealth;
							player.XPNeed -= enemy.XP;
							player.health += player.damage;
							if (player.health > player.maxHealth) {
								player.health = player.maxHealth;
							}
							if (player.XPNeed <= 0) {
								player.maxHealth += 5;
								player.health = player.maxHealth;
								player.maxMP += 5;
								player.MP = player.maxMP;
								player.damage++;
								player.XPNeedTotal += 50;
								player.XPNeed = player.XPNeedTotal;
								JOptionPane.showMessageDialog(null, "Congrats! You leveled up!");
							}
						}
					}
					if (battleChoice == 5) {
						player.castSpell(enemy, spell2, ran);
						roll = ran.nextInt(2);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
						if (enemy.health <= 0) {
							if (mtl.dragon) {
								mtl.dragon = false;
								mtl.dragonDead = true;
								JOptionPane.showMessageDialog(null, "you found a key!");
							}
							if(dunLoc.trollActive) {
								dunLoc.trollActive = false;
								dunLoc.trollDead = true;
							}
							currentState = GAME;
							enemy.health = enemy.maxHealth;
							player.XPNeed -= enemy.XP;
							player.health += player.damage;
							if (player.health > player.maxHealth) {
								player.health = player.maxHealth;
							}
							if (player.XPNeed <= 0) {
								player.maxHealth += 5;
								player.health = player.maxHealth;
								player.maxMP += 5;
								player.MP = player.maxMP;
								player.damage++;
								player.XPNeedTotal += 50;
								player.XPNeed = player.XPNeedTotal;
								JOptionPane.showMessageDialog(null, "Congrats! You leveled up!");
							}
						}
					}
					if (battleChoice == 6) {
						player.castSpell(enemy, spell3, ran);
						roll = ran.nextInt(2);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
						if (enemy.health <= 0) {
							if (mtl.dragon) {
								mtl.dragon = false;
								mtl.dragonDead = true;
								JOptionPane.showMessageDialog(null, "you found a key!");
							}
							if(dunLoc.trollActive) {
								dunLoc.trollActive = false;
								dunLoc.trollDead = true;
							}
							currentState = GAME;
							enemy.health = enemy.maxHealth;
						}
					}
					if (battleChoice == 7) {
						player.castSpell(enemy, spell4, ran);
						roll = ran.nextInt(2);
						if (roll == 1) {
							player.health -= enemy.damage;
						}
						if (enemy.health <= 0) {
							if (mtl.dragon) {
								mtl.dragon = false;
								mtl.dragonDead = true;
								JOptionPane.showMessageDialog(null, "you found a key!");
							}
							if(dunLoc.trollActive) {
								dunLoc.trollActive = false;
								dunLoc.trollDead = true;
							}
							currentState = GAME;
							enemy.health = enemy.maxHealth;
							player.XPNeed -= enemy.XP;
							player.health += player.damage;
							if (player.health > player.maxHealth) {
								player.health = player.maxHealth;
							}
							if (player.XPNeed <= 0) {
								player.maxHealth += 5;
								player.health = player.maxHealth;
								player.maxMP += 5;
								player.MP = player.maxMP;
								player.damage++;
								player.XPNeedTotal += 50;
								player.XPNeed = player.XPNeedTotal;
								JOptionPane.showMessageDialog(null, "Congrats! You leveled up!");
							}
						}
					}
					if (player.health <= 0) {
						currentState = END;
						player.health = player.maxHealth;
						player.MP = player.maxMP;
						mtl.dragon = false;
						mtl.dragonDead = false;
						dunLoc.trollActive = false;
						dunLoc.trollDead = false;
						hell.satanActive = false;
						hell.satanDead = false;
						mtl.location = 1;
						location = OVERWORLD;
						player.x = 250;
						player.y = 250;
						enemy.health = 0;
						player.XPNeedTotal = 100;
						player.XPNeed = player.XPNeedTotal;
						dunLoc.hasBomb = false;
						dunLoc.blockageDestroyed = false;
						dunLoc.bombYes = false;
						dunLoc.hasKey1 = false;
					}
				}
			}
		}

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		} else if (currentState == FIGHT) {
			updateFightState();
		}
		else if(currentState == GAMEFINISH) {
			updateFinishState();
		}
		repaint();

	}
}
