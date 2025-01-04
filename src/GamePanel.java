import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
Zombie zombie = new Zombie(220,200,50,50,10,10,1, 5);
Skeleton skeleton = new Skeleton(220,200,50,50,10,10,2,5);
Enemy enemy;
int roll;
int enemyChoice;

GamePanel(){
	titleFont = new Font("Arial", Font.PLAIN,48);
	enterFont = new Font("Arial", Font.PLAIN,25);
	combatFont = new Font("Arial", Font.PLAIN, 30);
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
	enemy.draw(g);
	g.setColor(Color.black);
	g.fillRect(10, 410, 200, 60);
	g.setFont(combatFont);
	g.setColor(Color.white);
	g.drawString("attack", 10, 450);
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
		if(currentState == END) {
			currentState = MENU;
		}
		else {
			currentState++;
		}
	}
	if(currentState == GAME) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.y-=player.speed;
			rand = ran.nextInt(20);
			if(rand == 1) {
				currentState = FIGHT;
				
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.y+=player.speed;
			rand = ran.nextInt(20);
			if(rand == 1) {
				currentState = FIGHT;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.x-=player.speed;
			rand = ran.nextInt(20);
			if(rand == 1) {
				currentState = FIGHT;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.x+=player.speed;
			rand = ran.nextInt(20);
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
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			roll = ran.nextInt(2);
			if(roll == 1) {
				zombie.health -= player.damage;
			}
			roll = ran.nextInt(2);
			if(roll == 1) {
				player.health -= zombie.damage;
			}
			if(zombie.health<=0) {
				currentState=GAME;
				zombie.health = zombie.maxHealth;
			}
			if(player.health<=0) {
				currentState = END;
				player.health = player.maxHealth;
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
