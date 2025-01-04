import java.awt.Color;
import java.awt.Graphics;

public class Skeleton extends Enemy{
Skeleton(int x, int y, int width, int height, int maxHealth, int health, int damage, int XP) {
		super(x, y, width, height, maxHealth, health, damage, XP);
		// TODO Auto-generated constructor stub
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

}
