import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
final int MENU = 0;
final int GAME = 1;
final int END = 2;
int currentState = MENU;
Font titleFont;
Font enterFont;
Timer frameDraw;

GamePanel(){
	titleFont = new Font("Arial", Font.PLAIN,48);
	enterFont = new Font("Arial", Font.PLAIN,25);
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
}


void updateMenuState() {
	
}

void updateGameState() {
	
}

void updateEndState() {
	
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
	repaint();
}
}
