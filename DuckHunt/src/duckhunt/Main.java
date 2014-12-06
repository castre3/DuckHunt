package duckhunt;

import java.util.ArrayList;

import processing.core.PApplet;

public class Main extends PApplet implements ApplicationConstants {

	private static final long serialVersionUID = 1L;

	private boolean initApp_ = setAppForAllClasses();
	private int frameCounter_ = 0;
	private int lastDrawTime_ = 0;
	private int selectedIndex = 0;

	/**
	 * The list containing all of the ducks
	 */
	ArrayList<Duck> duck_;
	/**
	 * Keeps track of how many times the person has hit a duck
	 * this is for when we implement more that one duck 
	 */
	private int shotCount = 0;
	/**
	 * Keeps track of how many times the person has tried and shot
	 */
	private int tryCount = 0;

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		// this is for debugging
		// frameRate(2000);
		frameRate(100);
		duck_ = new ArrayList<Duck>();

		duck_.add(new Duck(WORLD_WIDTH / 4, WORLD_HEIGHT / 4, -PI / 4,PIXELS_TO_WORLD_SCALE / 4));
		// duck_.add(new Duck(WORLD_WIDTH/4, 3*WORLD_HEIGHT/4, PI/4,PIXELS_TO_WORLD_SCALE/4));
		// duck_.add(new Duck(-WORLD_WIDTH/4, WORLD_HEIGHT/4, 3*PI/4,PIXELS_TO_WORLD_SCALE/4));
		// duck_.add(new Duck(-WORLD_WIDTH/4, 3*WORLD_HEIGHT/4, 3*PI/4,PIXELS_TO_WORLD_SCALE/4));
	}

	public void draw() {
		background(127);
		
		for (Duck obj : duck_)
			obj.animate();

		// We are still in pixel units.
		translate(WINDOW_WIDTH / 2, WINDOW_HEIGHT);
		// change to world units
		scale(WORLD_TO_PIXELS_SCALE, -WORLD_TO_PIXELS_SCALE);
				
		pushMatrix();
		for (int i = 0; i < duck_.size(); i++) {
			duck_.get(i).draw();
			
			if ((duck_.get(i).getY() < -WORLD_HEIGHT  && duck_.get(i).getLevelEnded()) 
					|| (duck_.get(i).getY() > WORLD_HEIGHT  && duck_.get(i).getLevelEnded())) { // this random amount is bc it was going out the top
				duck_.remove(duck_.get(i));
			}
		}
		popMatrix();

	}
	public void keyReleased() {
		switch (key) {
		case '0':
		case '1':
		case '2':
		case '3':
			selectedIndex = Integer.parseInt("" + key);
			break;
		}
	}

	/**
	 * This controls what happens when you shoot the duck
	 */
	public void mouseReleased() {
		float x = xPixelToWorld(mouseX), y = yPixelToWorld(mouseY);

		for (Duck obj : duck_) {
			if (obj.isInside(x, y)) {
				obj.setVx(0); // temporarily make it not move
				obj.setVy(0);
				delay(500); // do the "pause" thing
				obj.setVy(-1f); // make it fall off screen
				obj.setShot(); // Set "shot" to "false"
				shotCount++;
				tryCount++;
				checkLevelComplete();
				break; 
			}	
		}
		tryCount++;
		println("Try count for click:" + tryCount);
		checkLevelComplete();
	}
	/**
	 * this checks if the person has shot all of the ducks in play
	 * 1 for now but added so that can expand to multiple ducks
	 */
	private void checkLevelComplete() {
		//if the person has used all of their chances. 
		if (tryCount == 3) {
			
			for (Duck obj : duck_) {
				obj.levelEnded();
				obj.setVx(0); // temporarily make it not move
				obj.setVy(0);
				delay(500); // do the "pause" thing
				obj.setVy(1f); // make it fall off screen
				break; 
			}
		}
		
	}

	private float xPixelToWorld(int ix) {
		return X_MIN + PIXELS_TO_WORLD_SCALE * ix;
	}

	private float yPixelToWorld(int iy) {
		return Y_MIN + PIXELS_TO_WORLD_SCALE * (WINDOW_HEIGHT - iy);
	}

	private boolean setAppForAllClasses() {
		Duck.setApp(this);
		return true;
	}
}
