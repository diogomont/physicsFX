import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.Random;
import java.util.ArrayList;
import java.text.DecimalFormat;



public class AccelerationTest extends Application {

	private Group root, sliders, labels, buttons;
	private Scene scene;
	private Player player;
	private Settings s;
	private AnimationTimer timer;

	private Rectangle background, menuBar;
	private Slider accelSlider, bounceSlider, frictionSlider;
	private Label accelLabel, accelLabelValue, bounceLabel, bounceLabelValue, frictionLabel, frictionLabelValue;
	private Button resetPlayer, resetAll;

	private Random rand = new Random();
	private DecimalFormat df = new DecimalFormat("#.##");


	@Override
	public void start(Stage stage) {
		gameStart();

		/* =====================Create Control Elements - START===================== */

		background = new Rectangle(0, 0, s.getFrameWidth(), s.getMenuHeight() + s.getFrameHeight());
		background.setFill(Color.rgb(150,150,150));

		menuBar = new Rectangle(0, 0, s.getFrameWidth(), s.getMenuHeight());
		menuBar.setFill(Color.BLACK);

		accelSlider = new Slider(0, 2, Math.sqrt(s.getAccel()));
		accelSlider.setLayoutX(120);
		accelSlider.setLayoutY(10);

		accelLabel = new Label("Acceleration Rate:");
		accelLabel.setLayoutX(10);
		accelLabel.setLayoutY(8);
		accelLabel.setTextFill(Color.WHITE);

		accelLabelValue = new Label(String.valueOf(accelSlider.getValue()*accelSlider.getValue()));
		accelLabelValue.setLayoutX(265);
		accelLabelValue.setLayoutY(8);
		accelLabelValue.setTextFill(Color.WHITE);

		bounceSlider = new Slider(0, 2, s.getBounce());
		bounceSlider.setLayoutX(120);
		bounceSlider.setLayoutY(40);

		bounceLabel = new Label("Bounce Factor:");
		bounceLabel.setLayoutX(10);
		bounceLabel.setLayoutY(38);
		bounceLabel.setTextFill(Color.WHITE);

		bounceLabelValue = new Label(String.valueOf(bounceSlider.getValue()));
		bounceLabelValue.setLayoutX(265);
		bounceLabelValue.setLayoutY(38);
		bounceLabelValue.setTextFill(Color.WHITE);

		frictionSlider = new Slider(0, 0.5, Math.sqrt(s.getFriction()));
		frictionSlider.setLayoutX(120);
		frictionSlider.setLayoutY(70);

		frictionLabel = new Label("Friction Factor:");
		frictionLabel.setLayoutX(10);
		frictionLabel.setLayoutY(68);
		frictionLabel.setTextFill(Color.WHITE);

		frictionLabelValue = new Label(String.valueOf(frictionSlider.getValue()*frictionSlider.getValue()));
		frictionLabelValue.setLayoutX(265);
		frictionLabelValue.setLayoutY(68);
		frictionLabelValue.setTextFill(Color.WHITE);
		
		resetAll = new Button("Reset Player and Settings");
		resetAll.setLayoutX(500);
		resetAll.setLayoutY(55);
		resetAll.setOnAction((event) -> gameStart());

		resetPlayer = new Button("Reset Player");
		resetPlayer.setLayoutX(500);
		resetPlayer.setLayoutY(35);
		resetPlayer.setOnAction((event) -> playerStart());

		sliders = new Group(accelSlider,bounceSlider,frictionSlider);

		labels = new Group(accelLabel,accelLabelValue,bounceLabel,bounceLabelValue,
										frictionLabel, frictionLabelValue);

		buttons = new Group(resetAll, resetPlayer);

		/* =====================Create Control Elements - END======================= */





		root = new Group(background, menuBar, player, sliders, labels, buttons);	
		scene = new Scene(root, s.getFrameWidth(), s.getFrameHeight() + s.getMenuHeight());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Acceleration Test");
		stage.show();



		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update(); 
			}
		};
		timer.start();

		scene.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.UP)		player.setDown(true);
			if(event.getCode() == KeyCode.DOWN)		player.setUp(true);	
			if(event.getCode() == KeyCode.LEFT)		player.setLeft(true);
			if(event.getCode() == KeyCode.RIGHT)	player.setRight(true);
			if(event.getCode() == KeyCode.ESCAPE)	stage.close();
			//KeyLog.add(event.getCode());
			//System.out.println(KeyLog.print());
			System.out.println(event.getCode());
		});

		scene.setOnKeyReleased(event -> {
			if(event.getCode() == KeyCode.UP)		player.setDown(false);	
			if(event.getCode() == KeyCode.LEFT)		player.setLeft(false);
			if(event.getCode() == KeyCode.DOWN)		player.setUp(false);
			if(event.getCode() == KeyCode.RIGHT)	player.setRight(false);
		});
	}




	private void gameStart() {
		s = new Settings();

		playerStart();
		
		if (root!=null) {
			accelSlider.setValue(Math.sqrt(s.getAccel()));
			bounceSlider.setValue(s.getBounce());
			frictionSlider.setValue(Math.sqrt(s.getFriction()));
		}
	}


	private void playerStart() {
		player = new Player((-s.getPlayerSize() >> 1) + (s.getFrameWidth() >> 1), 
								(-s.getPlayerSize() >> 1) + (s.getFrameHeight() >> 1) + s.getMenuHeight(), 
								s.getPlayerSize(), s);

		player.setFill(Color.rgb(25,25,255));

		if (root!=null) {
			root.getChildren().clear();
			root.getChildren().addAll(background, menuBar, player, sliders, labels, buttons);
		}
	}




	private void update() {
		updateUI();
		updateSettings();
		player.setVelX(player.getVelX() / (1 + s.getFriction()) + player.getAccelX());
		player.setVelY(player.getVelY() / (1 + s.getFriction()) + player.getAccelY());
		player.setX(player.getX() + player.getVelX());
		player.setY(player.getY() + player.getVelY());
		player.checkBounds();
	}

	private void updateUI() {
		accelLabelValue.setText(df.format(accelSlider.getValue()*accelSlider.getValue())); 
		frictionLabelValue.setText(df.format(frictionSlider.getValue()*frictionSlider.getValue())); 
		bounceLabelValue.setText(df.format(bounceSlider.getValue())); 
		root.requestFocus();
	}

	private void updateSettings() {
		s.setAccel(accelSlider.getValue()*accelSlider.getValue());
		s.setFriction(frictionSlider.getValue()*frictionSlider.getValue());
		s.setBounce(bounceSlider.getValue());
		player.updateSettings(s);
	}







/*

	static class KeyLog {

		static ArrayList<KeyCode> log = new ArrayList<>();

		public static void add(KeyCode kc) {
			log.add(kc);
		}

		public static KeyCode check() {
			return log.size() == 0 ? null : log.get(log.size()-1);
		}

		public static String print() {
			return log.size() == 0 ? null : log.get(log.size()-1).name();
		}
	}

*/
}

