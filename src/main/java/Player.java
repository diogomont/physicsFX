import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {

	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private double accelX;
	private double accelY;
	private double velX;
	private double velY;
	private Settings s;


	public Player(int x, int y, int size, Settings s) {
		super(x, y, size, size);
		accelX = 0;
		accelY = 0;
		velX = 0;
		velY = 0;
		this.s = s;
	}




	public void cruise() {
		accelX = 0;
		accelY = 0;
		right = false;
		left = false;
		up = false;
		down = false;
	}




	public void setRight(boolean right) {
		this.right = right;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public void updateSettings(Settings s) {
		this.s = s;
	}


	public double getAccelX() {
		int xPlus = right ? 1 : 0;
		int xMinus = left ? -1 : 0;
		return s.getAccel() * (xPlus + xMinus);
	}
	public double getAccelY() {
		int yPlus = up ? 1 : 0;
		int yMinus = down ? -1 : 0;
		return s.getAccel() * (yPlus + yMinus);
	}

	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}


	public void checkBounds() {
		if (getX() < 0) {
			setX(0); 
			velX = -velX * s.getBounce();
		}

		if (getX() > s.getFrameWidth() - s.getPlayerSize()) {
			setX(s.getFrameWidth() - s.getPlayerSize());
			velX = -velX * s.getBounce();
		}

		if (getY() < s.getMenuHeight()) {
			setY(s.getMenuHeight()); 
			velY = -velY * s.getBounce();
		}

		if (getY() > s.getFrameHeight() + s.getMenuHeight() - s.getPlayerSize()) {
			setY(s.getFrameHeight() + s.getMenuHeight() - s.getPlayerSize()); 
			velY = -velY * s.getBounce();
		}
	}
}