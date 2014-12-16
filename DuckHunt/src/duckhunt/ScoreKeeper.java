package duckhunt;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class ScoreKeeper implements ApplicationConstants {
	/**
	 * the local instance of the application
	 */
	private static PApplet theApp_;
	private PImage scoreSprite_;
	private boolean twoBullets_ = false;
	private boolean oneBullet_ = false;
	private boolean noBullet_ = false;
	private boolean flashRound_ = true;
	private int addition_ = 0;
	private int currentLevel_= 1;

	/**
	 * the constructor for the scorekeeper
	 * 
	 * @param scoreSprite_
	 */
	public ScoreKeeper(PImage scoreSprite) {
		scoreSprite_ = scoreSprite;
	}

	/**
	 * sets up the background and displays it
	 */
	void draw() {
		// the bullets
		theApp_.beginShape();
		theApp_.noStroke();
		theApp_.texture(scoreSprite_);
		// Bottom left
		theApp_.vertex(WINDOW_WIDTH / 10, 39* WINDOW_HEIGHT / 40, .035f, .8f);
		// Top left
		theApp_.vertex(WINDOW_WIDTH / 10, 39* WINDOW_HEIGHT / 40 - 64, .035f,.725f);
		// Top right
		theApp_.vertex(2 * WINDOW_WIDTH / 10, 39* WINDOW_HEIGHT / 40 - 64,.089f, .725f);
		// Bottom right
		theApp_.vertex(2 * WINDOW_WIDTH / 10, 39* WINDOW_HEIGHT / 40, .089f,.8f);
		theApp_.endShape(PConstants.CLOSE);

		//the level board
		theApp_.beginShape();
		theApp_.noStroke();
		theApp_.texture(scoreSprite_);
		// Bottom left
		theApp_.vertex(WINDOW_WIDTH / 4, 39 * WINDOW_HEIGHT / 40, .105f, .8f);
		// Top left
		theApp_.vertex(WINDOW_WIDTH / 4, 39 * WINDOW_HEIGHT / 40 - 64, .105f,.725f);
		// Top right
		theApp_.vertex(3 * WINDOW_WIDTH / 4-10, 39 * WINDOW_HEIGHT / 40 - 64,.32f, .725f);
		// Bottom right
		theApp_.vertex(3 * WINDOW_WIDTH / 4-10, 39 * WINDOW_HEIGHT / 40, .32f, .8f);
		theApp_.endShape(PConstants.CLOSE);
		
		theApp_.beginShape();
		theApp_.noStroke();
		theApp_.noFill();
		theApp_.stroke(255);
		//theApp_.texture(scoreSprite_);
		// Bottom left
		theApp_.vertex(310, 560, .2f, .97f);
		// Top left
		theApp_.vertex(310, 530, .2f,.93f);
		// Top right
		theApp_.vertex(565, 530,.34f, .93f);
		// Bottom right
		theApp_.vertex(565, 560, .34f, .97f);
		
		theApp_.endShape(PConstants.CLOSE);
		
		
		if (flashRound_) {
			// making only 5 rounds
			theApp_.fill(0);
			theApp_.rect(312 + addition_, 530, 25, 28);
		}

		// changing the number of bullets
		if (twoBullets_) {
			theApp_.fill(0);
			theApp_.rect(128, 530, 18, 25);
		}
		if (oneBullet_) {
			theApp_.fill(0);
			theApp_.rect(112, 530, 34, 25);

		}
		if (noBullet_) {
			theApp_.fill(0);
			theApp_.rect(90, 530, 56, 25);
		}
		if(Main.flewAway_){
			theApp_.fill(255);
			//the level board
			theApp_.beginShape();
			theApp_.noStroke();
			theApp_.texture(scoreSprite_);
			// Bottom left
			theApp_.vertex(350, 150, .542f, .92f);
			// Top left
			theApp_.vertex(350, 100, .542f,.855f);
			// Top right
			theApp_.vertex(500, 100,.672f, .855f);
			// Bottom right
			theApp_.vertex(500, 150, .672f, .92f);
			
			theApp_.endShape(PConstants.CLOSE);
		}
	}
	/**
	 * switch flashing
	 */
	public void switchFlash(){
		flashRound_= !flashRound_;
	}

	/**
	 * increases the flashing of the level on
	 */
	public void increaseLevelFlash() {
		addition_ += 25;
		currentLevel_++;
	}

	/**
	 * reset the flashing of the level on
	 */
	public void resetAddition() {
		addition_ = 0;
	}

	/**
	 * setting one bullet
	 */
	public void setOneBullet() {
		oneBullet_ = true;
	}

	/**
	 * setting two bullets
	 */
	public void setTwoBullet() {
		twoBullets_ = true;
	}

	/**
	 * setting no bullets
	 */
	public void setNoBullets() {
		noBullet_ = true;
	}

	/**
	 * resetting to 3 bullets
	 */
	public void resetBullets() {
		oneBullet_ = false;
		twoBullets_ = false;
		noBullet_ = false;
	}

	/***
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}

	public int getCurrentLevel() {
		return currentLevel_;
	}
}
