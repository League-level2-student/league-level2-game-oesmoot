import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class Underworld {
int location = 2;
Color darkRed = new Color(179, 12, 0);
HellBG hell = new HellBG();
Chest chest1 = new Chest();
Chest chest2 = new Chest();
boolean itemGot = false;

Underworld(){
	
}

void checkLocation(Player player) {
	if(location == 1) {
		if(player.y<=10) {
			player.y+=10;
		}
		if(player.x>=480) {
			player.x-=10;
		}
		if(player.x<=10) {
			player.x+=10;
		}
		if(player.y>=490) {
			location = 2;
			player.y = 20;
		}
	}
	if(location == 2) {
		if(player.y>=300) {
			player.y-=10;
		}
		if(player.x>=490) {
			location = 3;
			player.x = 20;
		}
		if(player.x<=0) {
			location = 4;
			player.x =480;
		}
	}
	if(location == 3) {
		if(player.x<=0) {
			location = 2;
			player.x =480;
		}
		if(player.x>=250&&player.x<=300&&player.y>=250&&player.y<=300) {
			player.x = 270;
			player.y = 310;
			itemGot = true;
			JOptionPane.showMessageDialog(null, "you got a new spell!");
			player.damage += 10;
		}
	}
	if(location ==4) {
		if(player.x>=490) {
			location = 2;
			player.x = 20;
		}
		if(player.x>=250&&player.x<=300&&player.y>=250&&player.y<=300) {
			player.x = 270;
			player.y = 310;
			itemGot = true;
			JOptionPane.showMessageDialog(null, "Damage up!");
			player.damage += 10;
		}
	}
}

void drawLocation (Graphics g) {
	if(location == 1) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		g.setColor(darkRed);
		g.fillRect(0, 0, 500, 20);
		g.fillRect(0, 0, 20, 500);
		g.fillRect(480, 0, 20, 500);
	}
	if(location == 2) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		hell.draw(g);
		
		g.setColor(darkRed);
		g.fillRect(0, 300, 500, 20);
	}
	if(location == 3) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		if(!itemGot) {
			chest2.draw(g);
		}
	}
	if(location ==4) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		if(!itemGot) {
			chest1.draw(g);
		}
		
	}
}
}
