package duckhunt;

import processing.core.PApplet;
import processing.core.PConstants;

public class Background implements ApplicationConstants {
	/**
	 * The local instance of the application
	 */
	private static PApplet theApp_;
	/**
	 * the constructor
	 */
	public Background() {}

	/**
	 * sets up the background and displays it
	 */
	void draw() {
		//fill with blue
		theApp_.beginShape();
		theApp_.noStroke();

		if (Main.flewAway_) {
			//pink
			theApp_.fill(255,170,153);
		}
		
		else {
			theApp_.fill(63,191,255);
		}
		
		theApp_.vertex(0, 0, 0, 0);
		theApp_.vertex(0, WINDOW_HEIGHT, 0, 1);
		theApp_.vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
		theApp_.vertex(WINDOW_WIDTH, 0, 1, 0);
		theApp_.endShape(PConstants.CLOSE);
	}
	/***
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
