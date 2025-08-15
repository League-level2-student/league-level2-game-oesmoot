import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class Underworld {
int location = 7;
Color darkRed = new Color(179, 12, 0);
HellBG hell = new HellBG();
Chest chest1 = new Chest();
Chest chest2 = new Chest();
Portal portal = new Portal();
Satan satan = new Satan(150, 150, 200, 200, 0, 0, 0, 0);
boolean itemGot = false;
boolean satanActive = false;
boolean satanDead = false;
boolean GameBeaten = false;
EndScreen fin = new EndScreen();

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
			JOptionPane.showMessageDialog(null, "Fists of steel!(damage up!)");
			player.damage += 10;
		}
	}
	if(location ==4) {
		if(player.x>=490) {
			location = 2;
			player.x = 20;
		}
		if(player.y>=490) {
			location = 5;
			player.y = 20;
		}
		if(player.x>=250&&player.x<=300&&player.y>=250&&player.y<=300) {
			player.x = 270;
			player.y = 310;
			itemGot = true;
			JOptionPane.showMessageDialog(null, "A shiny new sword!(damage up!)");
			player.damage += 10;
		}
	}
	if(location == 5) {
		if(player.x>= 200&&player.x<= 300&&player.y>=200&&player.y<=300) {
			location = 6;
			player.y = 20;
		}
	}
	if(location == 6) {
		if(!satanDead) {
		if(player.x>=150&&player.x<=350&&player.y>=150&&player.y<=350) {
			satanActive = true;
		}
		}
		if(satanDead) {
			if(player.x>= 200&&player.x<= 300&&player.y>=200&&player.y<=300) {
				player.x = 0;
				player.y = 0;
				JOptionPane.showMessageDialog(null, "with the destruction of satan, you have cleansed the world of all it's evil!");
				location = 7;
				GameBeaten = true;
				
			}
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
	if(location ==5) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		portal.draw(g);
		
	}
	if(location == 6) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 500, 500);
		if(!satanDead) {
			satan.draw(g);
		}
		if(satanDead) {
			portal.draw(g);
		}
	}
	if(location == 7) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
	}
}
}
