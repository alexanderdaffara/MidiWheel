import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javax.sound.midi.Receiver;

public class WheelGUI extends Application{
	
	public static void main(String[] args) {
		MidiInputHandler handler = new MidiInputHandler();
		MidiInputReceiver receiver = (MidiInputReceiver)handler.trans.getReceiver();
		boolean notesOn[] = receiver.notesOn;
		
		launch(args);
	}

	@Override
	public void start(Stage stage0) throws Exception {
		stage0.setTitle("MidiWheel");
		StackPane root = new StackPane();
		int radius = 80;
		double theta = Math.PI / 2;
		
		Circle outerCircle = new Circle(80);
		Circle innerCircle = new Circle(78);
		Circle[] nodes = new Circle[12];
		Scene scene = new Scene(root, 200, 200);
		root.getChildren().add(outerCircle);
		root.getChildren().add(innerCircle);
		
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Circle((scene.getWidth()/2) + (radius)*Math.cos(theta),
								(scene.getHeight()/2) - (radius)*Math.sin(theta),
								5,
								Color.GREEN);
			nodes[i].setTranslateX((radius-1)*Math.cos(theta));
			nodes[i].setTranslateY((radius-1)*Math.sin(theta));
			root.getChildren().add(nodes[i]);
			theta -= (Math.PI / 6);
		}
		
		innerCircle.setFill(Color.WHITE);
	
		
		stage0.setScene(scene);
		stage0.show();
	}
	

}