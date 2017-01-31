/*
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

   Also(warning!):
 
  1)You are not allowed to sell this product to third party.
  2)You can't change license and made it like you are the owner,author etc.
  3)All redistributions of source code files must contain all copyright
     notices that are currently in this file, and this list of conditions without
     modification.
 */
package visualizer;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;

/**
 * The Class Visualizer.
 *
 * @author GOXR3PLUS
 */
public class Visualizer extends VisualizerDrawer {

	/** The animation service. */
	public PaintService animationService = new PaintService();

	/**
	 * Constructor
	 * 
	 * @param text
	 */
	public Visualizer(String text) {
		// System.out.println("Visualizer Constructor called...{" + text + "}")

		// if i didn't add the draw to the @Override resize(double width, double
		// height) then it must be into the below listeners

		// Make the magic happen when the width or height changes
		// ----------
		widthProperty().addListener((observable, oldValue, newValue) -> {
			// System.out.println("New Visualizer Width is:" + newValue)

			// Canvas Width
			canvasWidth = (int) widthProperty().get();

			// Compute the Color Scale
			computeColorScale();

		});
		// -------------
		heightProperty().addListener((observable, oldValue, newValue) -> {
			// System.out.println("New Visualizer Height is:" + newValue)

			// Canvas Height
			canvasHeight = (int) heightProperty().get();
			halfCanvasHeight = canvasHeight >> 1;

			// Compute the Color Scale
			computeColorScale();
		});

		// On MouseReleased
		this.setOnMouseReleased(r -> {
			if (displayMode.get() == 0)
				displayMode.set(1);
			else if (displayMode.get() == 1)
				displayMode.set(0);

		});

	}

	/**
	 * Starts the Service that is repainting the Visualizer at 60FPS
	 */
	public void startVisualizerPainter() {
		animationService.start();
	}

	/**
	 * Stops the Service that is repainting the Visualizer
	 */
	public void stopVisualizerPainter() {
		animationService.stop();
		clear();
	}

	/**
	 * @return True if AnimationTimer of Visualizer is Running
	 */
	public boolean isRunning() {
		return animationService.isRunning();
	}

	/*-----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * 
	 * 							      Paint Service
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 */
	/**
	 * This Service is updating the visualizer.
	 *
	 * @author GOXR3PLUS
	 */
	public class PaintService extends AnimationTimer {

		/** The next second. */
		long nextSecond = 0L;

		/** The Constant ONE_SECOND_NANOS. */
		private static final long				ONE_SECOND_NANOS	= 1_000_000_000L;
		/**
		 * When this property is <b>true</b> the AnimationTimer is running
		 */
		private volatile SimpleBooleanProperty	running				= new SimpleBooleanProperty(false);

		@Override
		public void start() {
			// Values must be >0
			if (canvasWidth <= 0 || canvasHeight <= 0) {
				canvasWidth = 1;
				canvasHeight = 1;
			}

			nextSecond = 0L;
			super.start();
			running.set(true);
		}

		@Override
		public void stop() {
			super.stop();
			running.set(false);
		}

		/**
		 * @return True if AnimationTimer is running
		 */
		public boolean isRunning() {
			return running.get();
		}

		/**
		 * @return Running Property
		 */
		public SimpleBooleanProperty runningProperty() {
			return running;
		}

		@Override
		public void handle(long nanos) {

			// Clear Canvas
			clear();

			// Repaint the Canvas
			switch (displayMode.get()) {
			case 0:
				drawOscilloscope(false);
				break;
			case 1:
				drawOscilloscope(true);
				break;
			}

			// -- Show FPS if necessary.
			if (showFPS) {

				framesPerSecond++;

				// Check for 1 second passed
				if (nanos >= nextSecond) {
					fps = framesPerSecond;
					framesPerSecond = 0;
					nextSecond = nanos + ONE_SECOND_NANOS;
				}

				gc.setStroke(Color.YELLOW);
				gc.strokeText("FPS: " + fps + " (FRRH: " + frameRateRatioHint + ")", 0, canvasHeight - 1.00);
			}

		}

	}

}
