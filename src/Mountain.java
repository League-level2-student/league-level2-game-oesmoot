import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Mountain {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Mountain(){
		if (needImage) {
			loadImage("mountains.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0,0,100,100, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0,0,100,100);
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
