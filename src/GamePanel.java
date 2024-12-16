import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
final int MENU = 0;
final int GAME = 1;
int currentState = MENU;
Font titleFont;
Font enterFont;

GamePanel(){
	titleFont = new Font("Arial", Font.PLAIN,48);
	enterFont = new Font("Arial", Font.PLAIN,25);
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
 @Override
public void paintComponent(Graphics g) {
if(currentState == MENU) {
	drawMenuState(g);
}
 }
}
