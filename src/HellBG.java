import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HellBG {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	HellBG(){
		if (needImage) {
			loadImage("hell.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0,300,500,200, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0,300,500,200);
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
