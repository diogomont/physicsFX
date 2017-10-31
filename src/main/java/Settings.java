public class Settings {

	private int frameWidth;
	private int frameHeight;
    private int menuHeight;
	private int playerSize;
	private double accel;
	private double bounce;
	private double friction;


    private static final int DEF_FRAME_WIDTH = 900;
    private static final int DEF_FRAME_HEIGHT = 900;
    private static final int DEF_MENU_HEIGHT = 100;
    private static final int DEF_PLAYER_SIZE = 30;
    private static final double DEF_ACCEL_RATE = 1;
	private static final double DEF_BOUNCE = 1;
	private static final double DEF_FRICTION = 0.025;




	public Settings() {
		frameWidth = DEF_FRAME_WIDTH;
		frameHeight = DEF_FRAME_HEIGHT;
        menuHeight = DEF_MENU_HEIGHT;
		playerSize = DEF_PLAYER_SIZE;
		accel = DEF_ACCEL_RATE;
		bounce = DEF_BOUNCE;
		friction = DEF_FRICTION;
	}

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getMenuHeight() {
        return menuHeight;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public double getAccel() {
        return accel;
    }

    public double getBounce() {
        return bounce;
    }

    public double getFriction() {
        return friction;
    }



    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void setMenuHeight(int menuHeight) {
        this.menuHeight = menuHeight;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public void setAccel(double accel) {
        this.accel = accel;
    }

    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }
}