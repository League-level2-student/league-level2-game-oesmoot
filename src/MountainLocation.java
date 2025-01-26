import java.awt.Color;
import java.awt.Graphics;

public class MountainLocation {
int location =1;
House house1 = new House();

MountainLocation(){
	
}

	void checkLocation(Player player) {
		if(location == 1) {
		if(player.y>=0&&player.y<=10) {
			location =2;
			player.y = 480;
		}
		}
	}
	
	void drawLocation(Graphics g) {
		if(location == 1) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
		}
		else if(location == 2) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
		}
	}
}
