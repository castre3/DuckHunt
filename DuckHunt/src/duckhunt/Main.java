package duckhunt;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Main extends PApplet implements ApplicationConstants {

	private static final long serialVersionUID = 1L;

	private boolean initApp_ = setAppForAllClasses();
	/**
	 * The list containing all of the ducks
	 */
	ArrayList<Duck> duck_;
	/**
	 * instance of the Dog
	 */
	Dog dog_;
	/**
	 * Keeps track of how many times the person has hit a duck this is for when
	 * we implement more that one duck
	 */
	private int shotCount = 0;
	/**
	 * Keeps track of how many times the person has tried and shot
	 */
	private int tryCount = 0;
	/**
	 * The background texture
	 */
	PImage _backgroundImage;
	/**
	 * The sprite containing all of the characters
	 */
	PImage sprite_;
	/**
	 * The sprite for the title screen
	 */
	PImage titlescreen_;
	/**
	 * The sprite for the game over screen
	 */
	PImage gameover_;
	/**
	 * the image on the mouse
	 */
	PImage cursor_;
	/**
	 * the score board sprite
	 */
	PImage scoreSprite_;
	/**
	 * the background instance object
	 */
	Background background_;
	/**
	 * frame counter variable for animation
	 */
	private int frameCounter = 0;
	
	/**
	 * The timer instance (how long the dog animates, how long the gameplay is)
	 */
	Timer time_ = new Timer(2000, 5000);
	/**
	 * the current value when the dog vs duck on screen location current working
	 * on
	 */
	boolean dogAnimate_ = true; // The value of dogAnimate we will be checking
	/**
	 * the current value when the dog vs duck on screen location the value the
	 * timer is returning
	 */
	private boolean dogAnimateValueFromTimer = false;
	/**
	 * determine when to clear the duck
	 */
	boolean offScreen = false;
	/**
	 * determines if a bird got shot
	 */
	static boolean flewAway_= false;
	/**
	 * time to display the start up screen
	 */
	public static boolean startScreen_ = true;
	/** 
	 * if a duck was shot
	 */
	boolean aDuckShot = false;
	/**
	 * the instance of the scorekeeper
	 */
	private ScoreKeeper scorekeeper_;
	

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT, P3D);
		frameRate(2000);

		// set up the cursor
		cursor_ = loadImage("target.gif");
		cursor(cursor_);

		// loading images
		textureMode(NORMAL);
		sprite_ = loadImage("sprite.png");
		_backgroundImage = loadImage("backgroundTransparent.gif");
		scoreSprite_ = loadImage("ScoreSprite.gif");
		titlescreen_ = loadImage("titlescreen.jpg");
		gameover_ = loadImage("gameover.jpg");

		duck_ = new ArrayList<Duck>();
		background_ = new Background();
		scorekeeper_ = new ScoreKeeper(scoreSprite_);
	}

	public void draw() {
		
		/**
		 * If the game hasn't even begun yet...
		 */
		if(startScreen_){
			beginShape();
			noStroke();
			texture(titlescreen_);
			vertex(0, 0, 0, 0);
			vertex(0, WINDOW_HEIGHT, 0, 1);
			vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
			vertex(WINDOW_WIDTH, 0, 1, 0);
			endShape(PConstants.CLOSE);
		}
		
		/**
		 * else if the game is over...
		 */
		else if(scorekeeper_.getCurrentLevel() > 10){
			duck_.clear();
			dog_ = null;
			beginShape();
			noStroke();
			texture(gameover_);
			vertex(0, 0, 0, 0);
			vertex(0, WINDOW_HEIGHT, 0, 1);
			vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
			vertex(WINDOW_WIDTH, 0, 1, 0);
			endShape(PConstants.CLOSE);
		}
		
	
		/**
		 * If we get to here, we're playing the game
		 */
		else {
			//println("Current ducks in play: " + duck_.size());
			background_.draw();
			pushMatrix();
			translate(1, WINDOW_HEIGHT);
			scale(WORLD_TO_PIXELS_SCALE, -WORLD_TO_PIXELS_SCALE);

			//Get the current value of dogAnimate from the timer
			dogAnimateValueFromTimer = time_.getValue(); 
			if (dogAnimateValueFromTimer != dogAnimate_) {
				dogAnimate_ = dogAnimateValueFromTimer;
				if (dogAnimate_ == false) {
					// Destroy dog object
					dog_ = null;
					// Instantiate duck object
					duck_.add(new Duck(sprite_, WORLD_WIDTH / 2, WORLD_HEIGHT / 2, -PI / 4));
					scorekeeper_.resetBullets();
					tryCount = 0;
				} else {
					scorekeeper_.increaseLevelFlash();
					aDuckShot = false;
					// Destroy duck object
					for (Duck obj : duck_) {
						if (obj.getShot() == false) {
							obj.setVx(0); // temporarily make it not move
							obj.setVy(0);
							obj.setVy(1f); // make it fly up screen
							obj.setLevelEnded();
							flewAway_= true;
						}
						else {
							aDuckShot = true;
							flewAway_= false;
						} 
								
					}
					tryCount = 0;
					// Instantiate dog object
					if (aDuckShot) {
						dog_ = new Dog(sprite_, 1);
					}
					else {
						dog_ = new Dog(sprite_, 0);
					}
					aDuckShot = false;
				}
			}
			// draw and animate dog
			if (dog_ != null) {
				dog_.animate();
				dog_.draw();
			}

			// create the wing animation for the duck
			if (frameCounter++ % 6 == 0) {
				scorekeeper_.switchFlash();
				for (Duck obj : duck_) {
					if (obj.getShot()) {
						obj.setBirdWing(7);
					} 
					else if (obj.getLevelEnded() && !obj.getShot()) {
						obj.switchBirdWingAway();
					} 
					else
						obj.switchBirdWingFlying();
				}
			}

			// move the duck
			for (Duck obj : duck_)
				obj.animate();

			// make sure the bird is in range and in play, if not remove
			for (int i = 0; i < duck_.size(); i++) {
				duck_.get(i).draw();
				if ((duck_.get(i).getY() * WORLD_TO_PIXELS_SCALE < 0 && duck_.get(i).getLevelEnded()||duck_.get(i).getY() * WORLD_TO_PIXELS_SCALE < 0 && duck_.get(i).getShot())
						|| (duck_.get(i).getY() * WORLD_TO_PIXELS_SCALE > WINDOW_HEIGHT && duck_.get(i).getLevelEnded()|| duck_.get(i).getY() * WORLD_TO_PIXELS_SCALE > WINDOW_HEIGHT && duck_.get(i).getShot())) {
					duck_.remove(duck_.get(i));
					flewAway_= false;
				}
			}
			popMatrix();
			beginShape();
			noStroke();
			texture(_backgroundImage);
			vertex(0, 0, 0, 0);
			vertex(0, WINDOW_HEIGHT, 0, 1);
			vertex(WINDOW_WIDTH, WINDOW_HEIGHT, 1, 1);
			vertex(WINDOW_WIDTH, 0, 1, 0);
			endShape(PConstants.CLOSE);
			scorekeeper_.draw();
		}
	}

	/**
	 * This controls what happens when you shoot the duck
	 */
	public void mouseReleased() {
		startScreen_ = false; //Click to get past the start screen
		tryCount++;
		if (tryCount == 1) {
			scorekeeper_.setTwoBullet();
		} else if (tryCount == 2) {
			scorekeeper_.setOneBullet();
		}
		float x = xPixelToWorld(mouseX), y = yPixelToWorld(mouseY);
		boolean wasInside = false;
		for (Duck obj : duck_) {
			if (obj.isInside(x, y)) {
				obj.setVx(0); // temporarily make it not move
				obj.setVy(0);
				obj.setVy(-1f); // make it fall off screen
				obj.setShot(); // Set "shot" to "false"
				shotCount++;
				wasInside = true;
				delay(500); // do the "pause" thing
				break;
			}
		}
		println("Try count for click:" + tryCount);
		checkLevelComplete(wasInside);
	}

	/**
	 * this checks if the person has shot all of the ducks in play 1 for now but
	 * added so that can expand to multiple ducks
	 * 
	 * @param wasInside
	 */
	private void checkLevelComplete(boolean wasInside) {
		// if the person has used all of their chances.

		if (tryCount >= 3) {
			flewAway_ = true;
			scorekeeper_.setNoBullets();
			for (Duck obj : duck_) {
				obj.levelEnded();
				if (!wasInside) {
					obj.setVx(0); // temporarily make it not move
					obj.setVy(0);
					delay(500); // do the "pause" thing
					obj.setVy(1f); // make it fly up screen
				}
			}
		}

	}

	private float xPixelToWorld(int ix) {
		return X_MIN + PIXELS_TO_WORLD_SCALE * ix;
	}

	private float yPixelToWorld(int iy) {
		return Y_MIN + PIXELS_TO_WORLD_SCALE * (WINDOW_HEIGHT - iy);
	}

	/**
	 * Set the instances of the application in all of the contingent classes
	 * 
	 * @return if the applications were set
	 */
	private boolean setAppForAllClasses() {
		Duck.setApp(this);
		Dog.setApp(this);
		Background.setApp(this);
		Timer.setApp(this);
		ScoreKeeper.setApp(this);
		return true;
	}
}
