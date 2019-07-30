# About

Are you curious on how to make spectrum analysers in Java? Well the below tutorials plus the above code are the solution :)

## Java Audio Tutorials and API's by GOXR3PLUS STUDIO
 - **Spectrum Analyzers**
   - [Java-Audio-Wave-Spectrum-API](https://github.com/goxr3plus/Java-Audio-Wave-Spectrum-API)
    ![image](https://github.com/goxr3plus/Java-Audio-Wave-Spectrum-API/raw/master/images/Screenshot_2.jpg?raw=true)
   - [Jave Spectrum Analyzers from Audio](https://github.com/goxr3plus/Java-Spectrum-Analyser-Tutorials)
   - [Capture Audio from Microphone and make complex spectrum analyzers](https://github.com/goxr3plus/Java-Microphone-Audio-Spectrum-Analyzers-Tutorial)
  
 - **Java multiple audio formats player**
   - [Java-stream-player](https://github.com/goxr3plus/java-stream-player)
  
 - **Speech Recognition/Translation/Synthenizers**
   - [Java Speech Recognition/Translation/Synthesizer based on Google Cloud Services](https://github.com/goxr3plus/java-google-speech-api)
   - [Java-Speech-Recognizer-Tutorial--Calculator](https://github.com/goxr3plus/Java-Speech-Recognizer-Tutorial--Calculator)
   - [Java+MaryTTS=Java Text To Speech](https://github.com/goxr3plus/Java-Text-To-Speech-Tutorial)
   - [Java Speech Recognition Program based on Google Cloud Services ](https://github.com/goxr3plus/Java-Google-Speech-Recognizer)
   - [Java Google Text To Speech](https://github.com/goxr3plus/Java-Google-Text-To-Speech)
   - [Full Google Translate Support using Java](https://github.com/goxr3plus/java-google-translator)
   - [Professional Java Google Desktop Translator](https://github.com/goxr3plus/Java-Google-Desktop-Translator)


### Example Usage ( Hey check [here](https://github.com/goxr3plus/Java-Spectrum-Analyser-Tutorials/tree/master/Part%201%20-%20Stereo%20Oscilloscope/src/application) ):

``` JAVA
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

			// Selection of song to play
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jFileChooser.setFileFilter(new FileNameExtensionFilter("audio","mp3","wav"));
			jFileChooser.setAcceptAllFileFilterUsed(false);

			while(true){
				if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					playerExample.playSong(jFileChooser.getSelectedFile());
					break;
				} else{
					JOptionPane.showMessageDialog(null,"Please choose audio file","Select audio",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
```


I hope you enjoy these tutorials and actually you can watch the youtube videos about them below:


[![Java Spectrum Analyser Tutorial](http://img.youtube.com/vi/lwlioga8Row/0.jpg)](https://www.youtube.com/watch?v=lwlioga8Row)
