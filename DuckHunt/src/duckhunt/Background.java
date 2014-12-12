package duckhunt;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Background implements ApplicationConstants {

	private PImage ground_;
	private PImage allSprites_;
	private PImage background_;
	private static PApplet theApp_;

	public Background(PImage ground, PImage background, PImage allSprite) {
		ground_ = ground;
		allSprites_ = allSprite;
		background_ = background;
	}

	/**
	 * sets up the background and displays it
	 */
	void draw() {

		theApp_.beginShape();
		theApp_.noStroke();
		theApp_.texture(background_);
		theApp_.vertex(0, 0, 0, 0);
		theApp_.vertex(0, WINDOW_HEIGHT, 0, 1);
		theApp_.vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
		theApp_.vertex(WINDOW_WIDTH, 0, 1, 0);

		theApp_.endShape(PConstants.CLOSE);
		theApp_.noFill();
		theApp_.beginShape();
		theApp_.pushMatrix();
		theApp_.texture(ground_);
		theApp_.vertex(0, 4 * WINDOW_HEIGHT / 6, 0, 0);
		theApp_.vertex(0, WINDOW_HEIGHT, 0, 1);
		theApp_.vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
		theApp_.vertex(WINDOW_WIDTH, 4 * WINDOW_HEIGHT / 6, 1, 0);

		theApp_.endShape(PConstants.CLOSE);
		theApp_.popMatrix();

	}
	
	void drawGround(){}

	/***
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
