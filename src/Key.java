import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Key {
int x;
int y;
public static BufferedImage image;
public static boolean needImage = true;
public static boolean gotImage = false;

Key(int x, int y){
	this.x = x;
	this.y =y;
	
	if (needImage) {
		loadImage("key.png");
	}
}

public void draw(Graphics g) {
	if (gotImage) {
		g.drawImage(image, x, y, 10,10, null);
	} else {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 10,10);
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
