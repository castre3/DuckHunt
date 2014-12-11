package duckhunt;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/***
 * 
 * @author Elizabeth Castro
 * @author Nicholas Moreau
 *
 */
public class Duck implements ApplicationConstants {
	/**
	 * The local instance of the application
	 */
	private static PApplet theApp_;
	/**
	 * how long it takes the duck to fly across screen
	 */
	private static final float ACROSS_WORLD_TIME = 1f; /* seconds */
	/**
	 * The max speed the duck can go
	 */
	private static final float MAX_SPEED = WORLD_WIDTH / ACROSS_WORLD_TIME;
	/**
	 * the x and y location as well as the scale
	 */
	private float x_, y_;
	/**
	 * x and y velocity
	 */
	private float Vx_, Vy_;
	/**
	 * Parameter that records if duck is shot
	 */
	private boolean shot = false;
	/**
	 * this keeps track of when the level is over
	 */
	private boolean levelEnded_ = false;
	/**
	 * 	
	 */
	private float radius_ = 0.15f;
	/**
	 * this is the file of the image
	 */
	private PImage duckskin_;

	/**
	 * this will determine what sprite to draw
	 */
	private boolean duckWingUp_ = true;

	/***
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param scale
	 */
	public Duck(PImage sprite, float x, float y, float angle) {
		x_ = x;
		y_ = y;
		duckskin_ = sprite;

		// generate speed
		float theta = (float) (Math.PI * Math.random());
		float v = (float) (MAX_SPEED);
		Vx_ = v * PApplet.cos(theta);
		Vy_ = v * PApplet.sin(theta);
	}

	/**
	 * draws the duck
	 */
	void draw() {

		if (duckWingUp_) {
			  theApp_.noStroke(); theApp_.beginShape(PApplet.QUADS);
			  
			  theApp_.texture(duckskin_);
			  
			  theApp_.vertex(0, 0, .65f, .38f); 
			  theApp_.vertex(0, .15f, .65f, .3f);
			  theApp_.vertex(.15f, .15f, .55f, .3f); 
			  theApp_.vertex(.15f, 0, .55f,
			  .38f);
			  
			  theApp_.endShape();
			/*
			theApp_.pushMatrix();

			theApp_.translate(x_, y_);

			theApp_.strokeWeight(0.01f);
			theApp_.stroke(0, 0, 0);
			theApp_.fill(120, 174, 198);
			theApp_.ellipse(0, 0, radius_, radius_);

			theApp_.popMatrix();*/

		} else {
			  theApp_.noStroke(); theApp_.beginShape(PApplet.QUADS);
			  
			  theApp_.fill(120, 174, 198);
			  
			  theApp_.vertex(0, 0, .65f, .38f); 
			  theApp_.vertex(0, .15f, .65f, .3f);
			  theApp_.vertex(.15f, .15f, .55f, .3f); 
			  theApp_.vertex(.15f, 0, .55f,
			  .38f);
			  
			  theApp_.endShape();
/*
			theApp_.pushMatrix();

			theApp_.translate(x_, y_);

			theApp_.strokeWeight(0.01f);
			theApp_.stroke(0, 0, 0);
			theApp_.fill(124, 42, 206);
			theApp_.ellipse(0, 0, radius_, radius_);

			
			theApp_.popMatrix();
			*/
			
		}
	}

	/**
	 * moves the ducks making sure it is in bound
	 */
	public void animate() {
		x_ += Vx_ * 0.01f;
		y_ += Vy_ * 0.01f;

		// hit left or right bound bounce
		if ((x_ < X_MIN) || (x_ >= X_MAX)) {
			Vx_ = -Vx_;
		}

		// hit top or bottom bound
		if (((y_ < (Y_MAX * 1 / 3)) || (y_>= Y_MAX)) && !shot) {
			Vy_ = -Vy_;
		}
	}

	/***
	 * changed the location of the duck
	 * @param x float the x location
	 * @param y float the y location
	 */
	public void setPosition(float x, float y) {
		x_ = x;
		y_ = y;
	}

	/***
	 * returns the x location
	 * @return x
	 */
	public float getX() {
		return x_;
	}

	/***
	 * returns the y location
	 * @return y
	 */
	public float getY() {
		return y_;
	}

	/***
	 * 
	 * @param Vx
	 */
	public void setVx(float Vx) {
		Vx_ = Vx;
	}

	/***
	 * 
	 * @param Vy
	 */
	public void setVy(float Vy) {
		Vy_ = Vy;
	}

	/***
	 * sets the fact that the duck has been shot
	 */
	public void setShot() {
		shot = true;
		levelEnded();
	}

	/***
	 * sets the fact that the level has ended
	 */
	public void levelEnded() {
		levelEnded_ = true;
	}

	/***
	 * determines if the level has ended
	 */
	public boolean getLevelEnded() {
		return levelEnded_;
	}

	/***
	 * reports if the duck is shot.
	 */
	public boolean getShot() {
		return shot;
	}

	/***
	 * this sets which bird view will be displayed
	 */
	public void switchBirdWing() {
		duckWingUp_ = !duckWingUp_;
	}

	/***
	 * Determines if the click is in the duck
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInside(float x, float y) {
		float dx = x - x_, dy = y - y_;
		return (dx * dx + dy * dy < radius_ * radius_);
	}
	/***
	 * Sets the Main instance of the app
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
