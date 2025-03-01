import java.awt.Color;
import java.awt.Graphics;

public class DungeonLocation {
int location = 1;

DungeonLocation(){
	
}

void CheckLocation (Player player) {
	if(player.y>=0&&player.y<=10&&player.x>=0&&player.x<=225) {
		player.y+=10;
	}
	if(player.y>=0&&player.y<=10&&player.x>=265&&player.x<=500) {
		player.y+=10;
	}
}

void DrawLocation (Graphics g) {
	if(location == 1) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.gray);
		g.fillRect(0, 0, 225, 20);
		g.fillRect(275, 0, 225, 20);
	}
}
}
