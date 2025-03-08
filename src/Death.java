import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Death {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Death(){
		if (needImage) {
			loadImage("death.png");
		}
	}
	
	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0,0,500,500, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0,0,500,500);
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
