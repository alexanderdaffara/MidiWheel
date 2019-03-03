import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Receiver;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import javafx.animation.*;

public class WheelGUI extends Application{
	public static MidiInputHandler handler;
	public static MidiInputReceiver receiver;
	public static Map<Pair,Line> linesMap;
	public static boolean prevNotes[] = new boolean[12];
	
	public static void main(String[] args) {
		handler = new MidiInputHandler();
		receiver = (MidiInputReceiver)handler.trans.getReceiver();
		linesMap = new HashMap<Pair,Line>();
		for (int i = 0; i < 12; i++) {
			prevNotes[i] = false;
		}
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
								Color.MAROON);
			nodes[i].setTranslateX((radius-1)*Math.cos(theta));
			nodes[i].setTranslateY(-(radius-1)*Math.sin(theta));
			nodes[i].setCenterX((radius-1)*Math.cos(theta));
			nodes[i].setCenterY(-(radius-1)*Math.sin(theta));
			theta -= (Math.PI / 6);
		}
		
		innerCircle.setFill(Color.WHITE);
		
		for (int i = 0; i < nodes.length; i++) {
			for (int j = i + 1; j < nodes.length; j++) {
				//draw line from i to j
				Line line = new Line(nodes[i].getCenterX(),nodes[i].getCenterY(),
			 			 nodes[j].getCenterX(),nodes[j].getCenterY());
				line.setTranslateX((nodes[i].getCenterX() + nodes[j].getCenterX())/2);
				line.setTranslateY((nodes[i].getCenterY() + nodes[j].getCenterY())/2);
				Pair<Integer,Integer> pair = new Pair<Integer,Integer>(i,j);
				line.setStroke(Color.TRANSPARENT);
				linesMap.put(pair, line);
				root.getChildren().add(line);
			}
			root.getChildren().add(nodes[i]);
		}
		
		stage0.setScene(scene);
		stage0.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				boolean currNotes[] = receiver.notesOn;
				boolean lineUpdateNeeded = false;
				for (int i = 0; i < nodes.length; i++) {
					if(currNotes[i]) {
						nodes[i].setFill(Color.GREEN);
					} else {
						nodes[i].setFill(Color.MAROON);
					}
					if (prevNotes[i] != currNotes[i]) {
						System.out.println("update");
						lineUpdateNeeded = true;
					}
				}
				

				//if (lineUpdateNeeded) {
					for (int i = 0; i < nodes.length; i++) {
						for (int j = i+1; j < nodes.length; j++) {
							linesMap.get(new Pair<Integer,Integer>(i,j)).setStroke(Color.TRANSPARENT);
						}
					}
				//}
				
				int first = -1;
				int prev = first;
				for (int i = 0; i < nodes.length; i++) {
					if (currNotes[i]) {
						if (first == -1) {
							first = i;
						} else {
							linesMap.get(new Pair<Integer,Integer>(prev,i)).setStroke(Color.BLACK);
						}
						prev = i;
					}
				}
				if (first != -1 && first != prev) {
					linesMap.get(new Pair<Integer,Integer>(first,prev)).setStroke(Color.BLACK);
				}
				prevNotes = currNotes;
			}
		}.start();
	}
}