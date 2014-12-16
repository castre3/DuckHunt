package duckhunt;

import processing.core.PApplet;
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
	private static final float ACROSS_WORLD_TIME = .7f; /* seconds */
	/**
	 * The max speed the duck can go
	 */
	private static final float MAX_SPEED = WORLD_WIDTH / ACROSS_WORLD_TIME;
	/**
	 * the x and y location
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
	 * 	the lenght of the sprite
	 */
	private float length_ = 0.15f;
	/**
	 * this is the file of the image
	 */
	private PImage duckskin_;

	/**
	 * this determines the sprite to use for the duck
	 */
	private int duckWing_ = 1;

	/***
	 * The duck constructor
	 * @param x the x location
	 * @param y the y location
	 * @param angle the starting angle
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

		switch (duckWing_) {
		case 1: //wing down
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);
			if (Vx_ <= 0) {
				// bottom left
				theApp_.vertex(0, 0, .65f, .38f);
				// top left
				theApp_.vertex(0, length_, .65f, .3f);
				// top right
				theApp_.vertex(length_, length_, .55f, .3f);
				// bottom right
				theApp_.vertex(length_, 0, .55f, .38f);

			} else {
				theApp_.vertex(0, 0, .55f, .38f);
				theApp_.vertex(0, length_, .55f, .3f);
				theApp_.vertex(length_, length_, .65f, .3f);
				theApp_.vertex(length_, 0, .65f, .38f);
			}

			theApp_.endShape();
			break;
		case 2: //wing middle
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);

			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);
			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .55f, .38f);
				theApp_.vertex(0, length_, .55f, .3f);
				theApp_.vertex(length_, length_, .45f, .3f);
				theApp_.vertex(length_, 0, .45f, .38f);

			} else {
				theApp_.vertex(0, 0, .45f, .38f);
				theApp_.vertex(0, length_, .45f, .3f);
				theApp_.vertex(length_, length_, .55f, .3f);
				theApp_.vertex(length_, 0, .55f, .38f);
			}

			theApp_.endShape();
			break;
		case 3://wing up
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);

			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .45f, .38f);
				theApp_.vertex(0, length_, .45f, .3f);
				theApp_.vertex(length_, length_, .35f, .3f);
				theApp_.vertex(length_, 0, .35f, .38f);

			} else {
				theApp_.vertex(0, 0, .35f, .38f);
				theApp_.vertex(0, length_, .35f, .3f);
				theApp_.vertex(length_, length_, .45f, .3f);
				theApp_.vertex(length_, 0, .45f, .38f);
			}

			theApp_.endShape();
			break;
		case 4: //wing down
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);
			if (Vx_ <= 0) {
				// bottom left
				theApp_.vertex(0, 0, .65f, .58f);
				// top left
				theApp_.vertex(0, length_, .65f, .5f);
				// top right
				theApp_.vertex(length_, length_, .55f, .5f);
				// bottom right
				theApp_.vertex(length_, 0, .55f, .58f);

			} else {
				theApp_.vertex(0, 0, .55f, .58f);
				theApp_.vertex(0, length_, .55f, .5f);
				theApp_.vertex(length_, length_, .65f, .5f);
				theApp_.vertex(length_, 0, .65f, .58f);
			}

			theApp_.endShape();
			break;
		case 5: //fly away middle
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);

			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);
			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .55f, .58f);
				theApp_.vertex(0, length_, .55f, .5f);
				theApp_.vertex(length_, length_, .45f, .5f);
				theApp_.vertex(length_, 0, .45f, .58f);

			} else {
				theApp_.vertex(0, 0, .45f, .58f);
				theApp_.vertex(0, length_, .45f, .5f);
				theApp_.vertex(length_, length_, .55f, .5f);
				theApp_.vertex(length_, 0, .55f, .58f);
			}

			theApp_.endShape();
			break;
		case 6://wing up fly away
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);

			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .45f, .58f);
				theApp_.vertex(0, length_, .45f, .5f);
				theApp_.vertex(length_, length_, .35f, .5f);
				theApp_.vertex(length_, 0, .35f, .58f);

			} else {
				theApp_.vertex(0, 0, .35f, .58f);
				theApp_.vertex(0, length_, .35f, .5f);
				theApp_.vertex(length_, length_, .45f, .5f);
				theApp_.vertex(length_, 0, .45f, .58f);
			}

			theApp_.endShape();
			break;
		case 7: //down
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);

			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .55f, .68f);
				theApp_.vertex(0, length_, .55f, .6f);
				theApp_.vertex(length_, length_, .45f, .6f);
				theApp_.vertex(length_, 0, .45f, .68f);

			} else {
				theApp_.vertex(0, 0, .45f, .38f);
				theApp_.vertex(0, length_, .45f, .3f);
				theApp_.vertex(length_, length_, .55f, .3f);
				theApp_.vertex(length_, 0, .55f, .38f);
			}

			theApp_.endShape();
			break;
		case 8: // splat
			theApp_.noStroke();
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(duckskin_);
			theApp_.translate(x_, y_);

			if (Vx_ <= 0) {
				theApp_.vertex(0, 0, .45f, .68f);
				theApp_.vertex(0, length_, .45f, .6f);
				theApp_.vertex(length_, length_, .35f, .6f);
				theApp_.vertex(length_, 0, .35f, .68f);

			} else {
				theApp_.vertex(0, 0, .35f, .38f);
				theApp_.vertex(0, length_, .35f, .3f);
				theApp_.vertex(length_, length_, .45f, .3f);
				theApp_.vertex(length_, 0, .45f, .38f);
			}

			theApp_.endShape();
			break;
		}
	}

	/**
	 * moves the ducks making sure it is in bound
	 */
	public void animate() {
		x_ += Vx_ * 0.01f;
		y_ += Vy_ * 0.01f;

		// hit left or right bound bounce
		if ((x_ < X_MIN) || (x_ >= X_MAX-.15f)) {
			Vx_ = -Vx_;
		}

		// hit top or bottom bound
		if (((y_ < (Y_MAX * 1 / 3)) || (y_ >= Y_MAX- .15f)) && !shot && !levelEnded_) {
			Vy_ = -Vy_;
		}
	}

	/***
	 * returns the y location
	 * 
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
	 * this sets which bird flying in play
	 */
	public void switchBirdWingFlying() {
		if (duckWing_ == 3 )
			duckWing_ = 1;
		else
			duckWing_++;
	}
	/***
	 * this sets which bird flying away
	 */
	public void switchBirdWingAway() {
		if (duckWing_ == 6 )
			duckWing_ = 4;
		else
			duckWing_++;
	}
	/***
	 * hard sets which position
	 */
	public void setBirdWing(int position) {
		duckWing_ = position;
	}

	/***
	 * Determines if the click is in the duck
	 * 
	 * @param x the x origin
	 * @param y the y origin
	 * @return return if it is inside
	 */
	public boolean isInside(float xClick, float yClick) {
		float xCenter = x_+ length_;
		float yCenter = y_+ length_;
		float dx = xClick - xCenter, dy = yClick - yCenter;
		return (dx * dx + dy * dy < length_ * length_);
	}

	/**
	 * set that the level is ended
	 */
	public void setLevelEnded() {
		levelEnded_ = true;
	}
	/***
	 * Sets the Main instance of the app
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
