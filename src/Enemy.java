import java.awt.Graphics;

public abstract class Enemy {
	int x;
	int y;
	int width;
	int height;
	int maxHealth;
	int health;
	int damage;
	int XP;

	Enemy(int x, int y, int width, int height, int maxHealth, int health, int damage, int XP) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.maxHealth = maxHealth;
		this.health = health;
		this.damage = damage;
		this.XP = XP;
	}

	public abstract void draw(Graphics g);
}
