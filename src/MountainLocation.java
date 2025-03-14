import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class MountainLocation {
int location =1;
House house1 = new House(50,50,100,100);
NPC npc1 = new NPC(250,250,10,10);
boolean dragon = false;
boolean dragonDead = false;
Dragon dragonObj = new Dragon(140, 140, 200, 200, 50, 50, 5, 1000);
boolean key = false;
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
			if(player.y>=0&&player.y<=10) {
				location =4;
				player.y = 480;
			}
		}
		if(location == 3) {
			if(player.x==250&&player.y==250) {
				player.x = 250;
				player.y = 260;
				if(!dragonDead) {
					JOptionPane.showMessageDialog(null, "help! there's a big scary monster at the top of the mountain! can you defeat him?");

				}
				else {
					JOptionPane.showMessageDialog(null, "Oh thank you! I was worried there for a second that you were dead!");

				}
			}
			if(player.y<=500&&player.y>=490) {
				location = 1;
				player.y = 170;
				player.x = 90;
			}
		}
		if(location == 4) {
			if(player.y<=500&&player.y>=490) {
				location = 2;
				player.y = 20;
			}
			if(!dragonDead) {
				if(player.y>=140&&player.y<=340&&player.x>=140&&player.x<=340) {
					player.x = 230;
					player.y = 350;
					JOptionPane.showMessageDialog(null, "raah!");
					dragon = true;
					
				}
			}
			if(dragonDead) {
				key = true;
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
		else if(location == 4) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, RPGRunner.WIDTH, RPGRunner.HEIGHT);
			if(!dragonDead) {
				dragonObj.draw(g);
			}
		}
	}
}
