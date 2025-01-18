import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
final int MENU = 0;
final int GAME = 1;
final int END = 3;
final int FIGHT= 2;
int currentState = MENU;
Player player = new Player(250,250,10,10,10);
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
Spell spell1 = new Fireball(2,1,99);
Spell spell2 = new Fireball(2,1,99);
Spell spell3 = new Fireball(2,1,99);
Spell spell4 = new Fireball(2,1,99);
String firstSpell = "fireball";
String secondSpell = "fireball";
String thirdSpell = "fireball";
String fourthSpell = "fireball";

GamePanel(){
	titleFont = new Font("Arial", Font.PLAIN,48);
	enterFont = new Font("Arial", Font.PLAIN,25);
	combatFont = new Font("Arial", Font.PLAIN, 27);
	frameDraw = new Timer(1000/60, this);
	frameDraw.start();
}

void drawMenuState(Graphics g) {
	g.setColor(Color.blue);
	g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
	g.setFont(titleFont);
	g.setColor(Color.black);
	g.drawString("Zajef 37", 10, 35);
	g.setFont(enterFont);
	g.drawString("press enter", 220, 235);
}

void drawEndState(Graphics g) {
	g.setColor(Color.red);
	g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
	g.setFont(enterFont);
	g.setColor(Color.black);
	g.drawString("Zajef'd your last 37?", 50, 235);
}

void drawGameState(Graphics g) {
	g.setColor(Color.black);
	g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
	player.draw(g);
	
}

void drawFightState(Graphics g) {
	g.setColor(Color.black);
	g.fillRect(0, 0, 500, 400);
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
	g.drawString("HP: "+ String.valueOf(player.health)+"/"+ String.valueOf(player.maxHealth), 0, 25);
	g.drawString("MP: " + String.valueOf(player.MP)+"/"+ String.valueOf(player.maxMP), 0, 50);
	if(!isDoingMagic) {
	g.drawString("ATTACK", 10, 445);
	g.drawString("DEFEND", 130, 445);
	g.drawString("MAGIC", 250, 445);
	g.drawString("RUN", 370, 445);
	g.setColor(Color.red);
	if(battleChoice == 4) {
		g.fillRect(0, 410, 10, 50);
		g.setColor(Color.yellow);
		g.drawString("ATTACK", 10, 445);
	}
	if(battleChoice == 5) {
		g.fillRect(120, 410, 10, 50);
		g.setColor(Color.yellow);
		g.drawString("DEFEND", 130, 445);
	}
	if(battleChoice == 6) {
		g.fillRect(240, 410, 10, 50);
		g.setColor(Color.yellow);
		g.drawString("MAGIC", 250, 445);
	}
	if(battleChoice == 7) {
		g.fillRect(360, 410, 10, 50);
		g.setColor(Color.yellow);
		g.drawString("RUN", 370, 445);
	}
	}
	if(isDoingMagic) {
		g.drawString(firstSpell, 10, 445);
		g.drawString(secondSpell, 130, 445);
		g.drawString(thirdSpell, 250, 445);
		g.drawString(fourthSpell, 370, 445);
		g.setColor(Color.red);
		if(battleChoice == 4) {
			g.fillRect(0, 410, 10, 50);
			g.setColor(Color.yellow);
			g.drawString(firstSpell, 10, 445);
		}
		if(battleChoice == 5) {
			g.fillRect(120, 410, 10, 50);
			g.setColor(Color.yellow);
			g.drawString(secondSpell, 130, 445);
		}
		if(battleChoice == 6) {
			g.fillRect(240, 410, 10, 50);
			g.setColor(Color.yellow);
			g.drawString(thirdSpell, 250, 445);
		}
		if(battleChoice == 7) {
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
 @Override
public void paintComponent(Graphics g) {
if(currentState == MENU) {
	drawMenuState(g);
}
if(currentState == GAME) {
	drawGameState(g);
}
if(currentState == END) {
	drawEndState(g);
}
if(currentState==FIGHT) {
	drawFightState(g);
}
 }

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		if(currentState == MENU) {
			currentState = GAME;
		}
		if(currentState == END) {
			currentState = MENU;
		}
	}
	if(currentState == GAME) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			if(player.y>0) {
				player.y-=player.speed;
			}
			rand = ran.nextInt(100);
			if(rand == 1) {
				currentState = FIGHT;
				
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(player.y<490) {
				player.y+=player.speed;
			}
			rand = ran.nextInt(100);
			if(rand == 1) {
				currentState = FIGHT;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(player.x>0) {
				player.x-=player.speed;
			}
			rand = ran.nextInt(100);
			if(rand == 1) {
				currentState = FIGHT;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(player.x<490) {
				player.x+=player.speed;
			}
			rand = ran.nextInt(100);
			if(rand == 1) {
				currentState = FIGHT;
			}
		}

if(currentState == FIGHT) {
	enemyChoice = ran.nextInt(2);
	if(enemyChoice == 0) {
		enemy = new Skeleton(220,200,50,50,10,10,2,5);
	}
	else if(enemyChoice == 1) {
		enemy = new Zombie(220,200,50,50,10,10,1, 5);
	}
}

	}
	if(currentState == FIGHT) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(battleChoice>4) {
				battleChoice--;
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(battleChoice<7) {
				battleChoice++;
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(!isDoingMagic) {
			if(battleChoice==4) {
				roll = ran.nextInt(2);
				if(roll == 1) {
					enemy.health -= player.damage;
				}
				roll = ran.nextInt(2);
				if(roll == 1) {
					player.health -= enemy.damage;
				}
				if(enemy.health<=0) {
					currentState=GAME;
					enemy.health = enemy.maxHealth;
				}
			}
			if(battleChoice==5) {
				roll = ran.nextInt(5);
				if(roll == 1) {
					player.health-= enemy.damage;
				}
			}
			if(battleChoice==6) {
				isDoingMagic = true;
			}
			if(battleChoice==7) {
				roll = ran.nextInt(3);
				enemy.health = enemy.maxHealth;
				currentState = GAME;
				
			}
			if(player.health<=0) {
				currentState = END;
				player.health = player.maxHealth;
			}
		}
			if(isDoingMagic) {
				if(battleChoice == 4) {
					player.castSpell(enemy, spell1, ran);
					roll = ran.nextInt(2);
					if(roll == 1) {
						player.health -= enemy.damage;
					}
					if(enemy.health<=0) {
						currentState=GAME;
						enemy.health = enemy.maxHealth;
					}
				}
				if(battleChoice == 5) {
					player.castSpell(enemy, spell2, ran);
					roll = ran.nextInt(2);
					if(roll == 1) {
						player.health -= enemy.damage;
					}
					if(enemy.health<=0) {
						currentState=GAME;
						enemy.health = enemy.maxHealth;
					}
				}
				if(battleChoice == 6) {
					player.castSpell(enemy, spell3, ran);
					roll = ran.nextInt(2);
					if(roll == 1) {
						player.health -= enemy.damage;
					}
					if(enemy.health<=0) {
						currentState=GAME;
						enemy.health = enemy.maxHealth;
					}
				}
				if(battleChoice == 7) {
					player.castSpell(enemy, spell4, ran);
					roll = ran.nextInt(2);
					if(roll == 1) {
						player.health -= enemy.damage;
					}
					if(enemy.health<=0) {
						currentState=GAME;
						enemy.health = enemy.maxHealth;
					}
				}
				if(player.health<=0) {
					currentState = END;
					player.health = player.maxHealth;
					player.MP = player.maxMP;
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			if(isDoingMagic) {
				isDoingMagic = false;
			}
		}
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(currentState==MENU) {
		updateMenuState();
	}
	else if(currentState==GAME) {
		updateGameState();
	}
	else if(currentState==END) {
		updateEndState();
	}
	else if(currentState==FIGHT) {
		updateFightState();
	}
	repaint();

}
}
