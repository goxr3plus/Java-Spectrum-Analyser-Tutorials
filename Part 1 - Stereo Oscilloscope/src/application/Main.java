package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	PlayerExample playerExample = new PlayerExample();

	@Override
	public void start(Stage primaryStage) {
		try {

			// Scene
			Scene scene = new Scene(playerExample, 600, 600);
			scene.setCursor(Cursor.HAND);
			primaryStage.setScene(scene);

			// Show
			primaryStage.setOnCloseRequest(c -> System.exit(0));
			primaryStage.show();

			// Let's play this song[Replace with your song file path]
			playerExample.playSong(new File("C:/Users/GOXR3PLUS/Desktop/song.mp3"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
