import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class DungeonLocation {
int location = 1;
boolean hasKey1 = false;
Color brown = new Color(122,73,7);
Key key = new Key(250,250);
Button1 button1 = new Button1(250,250);
Button2 button2 = new Button2(220,250);
Button3 button3 = new Button3(280,250);
boolean button1On = false;
boolean button2On = false;
boolean button3On = false;
boolean sequenceComplete = false;

DungeonLocation(){
	
}

void CheckLocation (Player player) {
	if(location == 1) {
		if(player.y>=0&&player.y<=10&&player.x>=0&&player.x<=225) {
			player.y+=10;
		}
		if(player.y>=0&&player.y<=10&&player.x>=265&&player.x<=500) {
			player.y+=10;
		}
		if(player.y==0&&player.x>=225&&player.x<=265) {
			location = 2;
			player.y =480;
		}
		if(player.x>=480&&player.x<=500&&player.y>=0&&player.y<=225) {
			player.x-=10;
		}
		if(player.x>=480&&player.x<=500&&player.y>=265&&player.y<=500) {
			player.x-=10;
		}
		if(!hasKey1) {
			if(player.x>=480&&player.x<=500&&player.y>=225&&player.y<=265) {
				player.x-=10;
				JOptionPane.showMessageDialog(null, "It's locked.");
			}
		}
			
		}
	if(location == 2) {
		if(!hasKey1) {
			if(player.y>=480&&player.y<=500&&player.x>=0&&player.x<=225) {
				player.y-=10;
			}
			if(player.y>=480&&player.y<=500&&player.x>=265&&player.x<=500) {
				player.y-=10;
			}
			if(player.y>=490&&player.y<=500&&player.x>=225&&player.x<=265){
				location = 1;
				player.y = 10;
			}
			
			
		}
		if(hasKey1) {
			if(player.y>=480) {
				player.y -=10;
			}
		}
		if(!hasKey1) {
			if(player.x== key.x&&player.y == key.y) {
				hasKey1= true;
				JOptionPane.showMessageDialog(null, "You grab the key, but as you turn around to leave, the way back caves in!");
			}
		}
		if(player.y>=0&&player.y<=10&&player.x>=0&&player.x<=225) {
			player.y+=10;
		}
		if(player.y>=0&&player.y<=10&&player.x>=265&&player.x<=500) {
			player.y+=10;
		}
		if(player.y==0&&player.x>=225&&player.x<=265) {
			location = 3;
			player.y =480;
		}
	}
	if(location == 3) {
		if(player.y>=480&&player.y<=500&&player.x>=0&&player.x<=225) {
			player.y-=10;
		}
		if(player.y>=480&&player.y<=500&&player.x>=265&&player.x<=500) {
			player.y-=10;
		}
		if(player.y>=490&&player.y<=500&&player.x>=225&&player.x<=265){
			location = 2;
			player.y = 10;
		}
		
		if(player.x == button1.x&&player.y == button1.y) {
			button1On = true;
			button2On = false;
			button3On = false;
		}
		if(button1On) {
			if(player.x == button2.x&&player.y == button2.y) {
				button2On = true;
				button3On = false;
			}
		}
		if(button2On&&button1On) {
			if(player.x == button3.x&&player.y == button3.y) {
				button3On = true;
			}
		}
		if(button1On&&button2On&&button3On) {
			sequenceComplete = true;
			button1On = false;
			button2On = false;
			button3On = false;
			JOptionPane.showMessageDialog(null, "Sequence Complete!");
		}
	}
	}
	


void DrawLocation (Graphics g) {
	if(location == 1) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.gray);
		g.fillRect(0, 0, 225, 20);
		g.fillRect(275, 0, 225, 20);
		g.fillRect(480, 0, 20, 225);
		g.fillRect(480, 275, 20, 225);
		if(!hasKey1) {
			g.setColor(brown);
			g.fillRect(480,225,20,50);
		}
		
	}
	if(location == 2) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.gray);
		g.fillRect(0, 480, 225, 20);
		g.fillRect(275, 480, 225, 20);
		g.fillRect(0, 0, 225, 20);
		g.fillRect(275, 0, 225, 20);
		if(hasKey1) {
			g.fillRect(225, 480, 50, 20);
		}
		if(!hasKey1) {
			key.draw(g);
		}
	}
	if(location ==3) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		button1.draw(g);
		button2.draw(g);
		button3.draw(g);
	}
}
}
