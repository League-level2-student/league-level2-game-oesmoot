import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Dungeon {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Dungeon(){
		if (needImage) {
			loadImage("dungeon.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 400,0,100,100, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(400,0,100,100);
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
