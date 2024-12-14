import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
final int MENU = 0;
final int GAME = 1;
int currentState = MENU;

void drawMenuState(Graphics g) {
	g.setColor(Color.blue);
	g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
}
 @Override
public void paintComponent(Graphics g) {

 }
}
