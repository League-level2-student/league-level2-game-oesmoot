import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class MountainLocation {
int location =1;
House house1 = new House(50,50,100,100);
NPC npc1 = new NPC(250,250,10,10);
MountainLocation(){
	
}

	void checkLocation(Player player) {
		if(location == 1) {
		if(player.y>=0&&player.y<=10) {
			location =2;
			player.y = 480;
		}
		if(player.x>=50&&player.x<=150&&player.y>=50&&player.y<=150) {
			location = 3;
			player.x = 250;
			player.y = 480;
		}
		}
		if(location == 2) {
			if(player.y<=500&&player.y>=490) {
				location = 1;
				player.y = 20;
			}
		}
		if(location == 3) {
			if(player.x==250&&player.y==250) {
				player.x = 250;
				player.y = 260;
				JOptionPane.showMessageDialog(null, "help! there's a big scary monster at the top of the mountain! can you defeat him?");
			}
			if(player.y<=500&&player.y>=490) {
				location = 1;
				player.y = 170;
				player.x = 90;
			}
		}
	}
	
	void drawLocation(Graphics g) {
		if(location == 1) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
			house1.draw(g);
		}
		else if(location == 2) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
		}
		else if(location == 3) {
			g.setColor(Color.orange);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
			npc1.draw(g);
		}
	}
}
