import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class WheelGUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage0) throws Exception {
		stage0.setTitle("MidiWheel");
		StackPane root = new StackPane();
		
		Circle outerCircle = new Circle(80);
		Circle innerCircle = new Circle(77);
		
		innerCircle.setFill(Color.WHITE);
		
		root.getChildren().add(outerCircle);
		root.getChildren().add(innerCircle);
		
		outerCircle.setCenterX(root.getWidth()/2);
		outerCircle.setCenterY(root.getHeight()/2);
		innerCircle.setCenterX(root.getWidth()/2);
		innerCircle.setCenterY(root.getHeight()/2);
		
		stage0.setScene(new Scene(root, 200, 200));
		stage0.show();
	}

}