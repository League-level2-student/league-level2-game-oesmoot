import javax.swing.JFrame;

public class RPGRunner {
	JFrame frame;
	public static final int WIDTH = 514;
	public static final int HEIGHT = 537;
	GamePanel gPanel;

	RPGRunner() {
		this.frame = new JFrame();
		this.gPanel = new GamePanel();
	}

	public static void main(String[] args) {
		RPGRunner runner = new RPGRunner();
		runner.setup();
	}

	void setup() {
		frame.add(gPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.addKeyListener(gPanel);
	}
}
