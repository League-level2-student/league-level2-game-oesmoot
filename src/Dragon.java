import java.awt.Color;
import java.awt.Graphics;

public class Dragon extends Enemy{

	Dragon(int x, int y, int width, int height, int maxHealth, int health, int damage, int XP) {
		super(x, y, width, height, maxHealth, health, damage, XP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}

}
