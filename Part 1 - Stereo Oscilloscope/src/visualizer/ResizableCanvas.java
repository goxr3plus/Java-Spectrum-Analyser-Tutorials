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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** A resizable canvas which is resizing on it's parent width without the need of bindings
 * @author GOXR3PLUS
 *
 */
public class ResizableCanvas extends Canvas {

    /** The Graphics Context 2D of the canvas */
    public final GraphicsContext gc = getGraphicsContext2D();

    /**
     * Redraw the Canvas
     */
    @SuppressWarnings("unused")
    private void draw() {

        System.out.println(" Real Canvas Width is:" + getWidth() + " , Real Canvas Height is:" + getHeight() + "\n");

        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, getWidth(), getHeight());
        gc.strokeLine(0, getHeight(), getWidth(), 0);
    }

    @Override
    public double minHeight(double width) {
        return 1;
    }

    @Override
    public double minWidth(double height) {
        return 1;
    }

    @Override
    public double prefWidth(double width) {
        return minWidth(width);
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);

        // This is for testing...
        // draw()

        // System.out.println("Resize method called...")
    }
}
