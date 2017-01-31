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

import javafx.scene.paint.Color;

/**
 * The Class Oscilloscope.
 * 
 * -----------------------------------------------------------------------
 * 
 * -----------------------------------------------------------------------
 * 
 * 
 * Oscilloscope
 * 
 * -----------------------------------------------------------------------
 * 
 * -----------------------------------------------------------------------
 *
 * 
 * @author GOXR3PLUS
 */
public class Oscilloscope {

	// ---------------Oscilloscope-------------------

	/** The color size. */
	private final int colorSize = 360;

	/** The color index. */
	private int colorIndex = 0;

	/** The band width. */
	private float bandWidth;

	/** The x. */
	private int x = 0;

	/** The y. */
	private int y = 0;

	/** The x old. */
	private int xOld = 0;

	/** The y old. */
	@SuppressWarnings("unused")
	private int yOld = 0;

	/** VisualizerDrawer instance. */
	private VisualizerDrawer visualizerDrawer;

	// ---------------------------------------------------------------------------------------------------

	/**
	 * Constructor.
	 *
	 * @param visualizerDrawer
	 *            the visualizer drawer
	 */
	public Oscilloscope(VisualizerDrawer visualizerDrawer) {
		this.visualizerDrawer = visualizerDrawer;
	}

	/*-----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * 
	 * 			        Oscilloscope
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Draws an Oscilloscope.
	 *
	 * @param stereo
	 *            The Oscilloscope with have 2 lines->stereo or 1 line->merge
	 *            left and right audio
	 */
	public void drawOscilloscope(boolean stereo) {
		float[] pSample1;

		// It will be stereo?
		if (stereo)
			pSample1 = visualizerDrawer.pLeftChannel;
		else // not?Then merge the array
			pSample1 = visualizerDrawer.stereoMerge(visualizerDrawer.pLeftChannel, visualizerDrawer.pRightChannel);

		visualizerDrawer.gc.setStroke(visualizerDrawer.oscilloscopeColor);
		 System.out.println(pSample1.length);

		int yLast1 = (int) (pSample1[0] * (float) visualizerDrawer.halfCanvasHeight)
				+ visualizerDrawer.halfCanvasHeight;
		int samIncrement1 = 1;
		for (int a = samIncrement1, c = 0; c < visualizerDrawer.canvasWidth; a += samIncrement1, c++) {
			int yNow = (int) (pSample1[a] * (float) visualizerDrawer.halfCanvasHeight)
					+ visualizerDrawer.halfCanvasHeight;
			visualizerDrawer.gc.strokeLine(c, yLast1, c + 1.00, yNow);
			yLast1 = yNow;
		}

		// Oscilloscope will be stereo
		if (stereo) {
			colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
			visualizerDrawer.gc.setStroke(Color.hsb(colorIndex, 1.0f, 1.0f));

			float[] pSample2 = visualizerDrawer.pRightChannel;

			int yLast2 = (int) (pSample2[0] * (float) visualizerDrawer.halfCanvasHeight)
					+ visualizerDrawer.halfCanvasHeight;
			int samIncrement2 = 1;
			for (int a = samIncrement2, c = 0; c < visualizerDrawer.canvasWidth; a += samIncrement2, c++) {
				int yNow = (int) (pSample2[a] * (float) visualizerDrawer.halfCanvasHeight)
						+ visualizerDrawer.halfCanvasHeight;
				visualizerDrawer.gc.strokeLine(c, yLast2, c + 1.00, yNow);
				yLast2 = yNow;
			}

		}

	}

}
