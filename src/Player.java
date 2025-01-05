import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
int maxHealth = 20;
int health = maxHealth;
int damage = 5;
int maxMP = 10;
int MP = maxMP;

	
	Player(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	

}
