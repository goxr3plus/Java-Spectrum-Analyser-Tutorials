package application;

import java.io.File;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import streamplayer.*;
import visualizer.Visualizer;

public class PlayerExample extends StackPane implements StreamPlayerListener {

	Visualizer		visualizer		= new Visualizer("Example Visualizer");
	StreamPlayer	streamPlayer	= new StreamPlayer();

	/**
	 * Constructor
	 */
	public PlayerExample() {
		setStyle("-fx-background-color:black;");
		getChildren().add(visualizer);

		// Add the Listener to the Player
		streamPlayer.addStreamPlayerListener(this);

	}

	/**
	 * An example method of how to play a song with StreamPlayer
	 * 
	 * @param path
	 */
	public void playSong(File file) {
		try {
			// ----------------------Open the Media
			System.out.println("Opening ...");
			streamPlayer.open(file);

			// ---------------------- Play the Media
			System.out.println("Starting to play ...");
			streamPlayer.play();
			
			visualizer.setShowFPS(true);

		} catch (StreamPlayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void opened(Object dataSource, Map<String, Object> properties) {

	}

	@Override
	public void progress(int nEncodedBytes, long microsecondPosition, byte[] pcmData, Map<String, Object> properties) {
		// write the data to the visualizer
		visualizer.writeDSP(pcmData);

	}

	@Override
	public void statusUpdated(StreamPlayerEvent event) {
		System.out.println("Player Status is:" + streamPlayer.getStatus());

		// player is opened
		if (event.getPlayerStatus() == Status.OPENED && streamPlayer.getSourceDataLine() != null) {

			visualizer.setupDSP(streamPlayer.getSourceDataLine());
			visualizer.startDSP(streamPlayer.getSourceDataLine());

			Platform.runLater(() -> visualizer.startVisualizerPainter());
			
			// player is stopped
		} else if (event.getPlayerStatus() == Status.STOPPED) {

			visualizer.stopDSP();

			Platform.runLater(() -> visualizer.stopVisualizerPainter());

		}
	}

}
