import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject {
int distX;
	Zombie(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		// TODO Auto-generated constructor stub
	}
	
void draw(Graphics g) {
	g.setColor(Color.yellow);
	g.fillRect(x, y, width, height);
}

void update() {
	
}

void getPlayerX(Player player) {
	
}
}
