import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Troll extends Enemy{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	

	Troll(int x, int y, int width, int height, int maxHealth, int health, int damage, int XP) {
		super(x, y, width, height, maxHealth, health, damage, XP);
		// TODO Auto-generated constructor stub
		if (needImage) {
			loadImage("troll.png");
		}
	}

	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.GREEN);
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

}
