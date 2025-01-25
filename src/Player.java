import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Player extends GameObject {
	int maxHealth = 20;
	int health = maxHealth;
	int damage = 5;
	int maxMP = 10;
	int MP = maxMP;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	Player(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		// TODO Auto-generated constructor stub
		if (needImage) {
			loadImage("player@.png");
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void castSpell(Enemy enemy, Spell spell, Random ran) {
		if(MP>= spell.cost) {
			int cast = ran.nextInt(99);
			if (cast <= spell.hitChance) {
				enemy.health -= spell.damage;
			}
			MP -= spell.cost;
		}
	}

}
