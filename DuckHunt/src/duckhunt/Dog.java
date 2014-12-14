package duckhunt;
import processing.core.PApplet;
import processing.core.PImage;

/***
 * 
 * @author Elizabeth Castro
 * @author Nicholas Moreau
 *
 */
public class Dog implements ApplicationConstants {
	/**
	 * The instance of our applet
	 */
	private static PApplet theApp_;
	/**
	 * The width and height of our object
	 */
	private float width_ = .3f, length_ = .2f;
	/**
	 * Variables used to calculate the changes in time so we know when to draw
	 */
	private float startTimeMove_ = theApp_.millis(), startTimeLaugh_ = theApp_.millis(), currentTime_;
	/**
	 * X and Y, as well as a variable to store the Y we initially start at so we
	 * can get back to it
	 */
	private float x_ = WORLD_WIDTH / 2, y_ = WORLD_HEIGHT / 2 - length_,startY_ = y_;
	/**
	 * The speed at which the dog moves up and down
	 */
	private float changeInY_ = .006f;
	/**
	 * A PImage that contains our dog sprites
	 */
	private PImage dogskin_;
	/**
	 * Which instance of the dog do we want? 1 = Holding one dead ducks 2 =
	 * Holding two dead ducks 3 = Laughing at you because you missed
	 */
	private int whichDog_;
	/**
	 * Amount of time the dog freezes when he reaches his maximum Y position
	 */
private int freezeTime_ = 500;
	/**
	 * How long the dog waits at the bottom of the screen to animate
	 */
	private int initialWaitTime_ = 0;
	/**
	 * Which direction he's heading in 1 = up 2 = Not moving 3 = down
	 */
	private int direction = 1;
	/**
	 * The time between the dog's two "laughing" frames
	 */
	private int timeBetweenLaughs_ = 100;
	/**
	 * Which "laughing frame" are we in?
	 */
	private boolean laughUp_ = false;

	/***
	 * Our parameters are simply the sprite we are using and which dog instance we want
	 * @param x
	 * @param y
	 * @param angle
	 * @param scale
	 */
	public Dog(PImage sprite, int whichDog) {
		dogskin_ = sprite;
		whichDog_ = whichDog;
	}

	void draw() {

		theApp_.pushMatrix();
		theApp_.translate(x_ - (width_ / 2), y_ - (length_ / 2));
		theApp_.noStroke();

		theApp_.beginShape(PApplet.QUADS);
		theApp_.texture(dogskin_);

		/**
		 * If whichDog_ = 1, we need the sprite with him holding one dead duck
		 * If whichDog_ = 2, we need the sprite with him holding two dead ducks
		 * If whichDog_ = 3, we need the animation of him laughing at you
		 */
		if (whichDog_ == 1) {
			// Bottom left
			theApp_.vertex(0, 0, .83f, .15f);
			// Top left
			theApp_.vertex(0, length_, .83f, .02f);
			// Top right
			theApp_.vertex(width_, length_, 1f, .02f);
			// Bottom right
			theApp_.vertex(width_, 0, 1f, .15f);
		} else if (whichDog_ == 2) {
			// Bottom left
			theApp_.vertex(0, 0, .824f, .283f);
			// Top left
			theApp_.vertex(0, length_, .824f, .153f);
			// Top right
			theApp_.vertex(width_, length_, 1.002f, .153f);
			// Bottom right
			theApp_.vertex(width_, 0, 1.002f, .283f);
		} else if (whichDog_ == 3) {
			dogLaugh();
		}

		theApp_.endShape();
		theApp_.popMatrix();

	}

	/**
	 * This function animates the "laughing" animation. It consists of two
	 * frames, indicated by whether "laughUp_" is true or false. After we've
	 * gone longer than "timeBetweenLaughs_", we switch the boolean value so the
	 * opposite frame gets drawn.
	 */
	private void dogLaugh() {
		if (laughUp_ == true) {
			// Bottom left
			theApp_.vertex(0, 0, .623f, .273f);
			// Top left
			theApp_.vertex(0, length_, .623f, .143f);
			// Top right
			theApp_.vertex(width_, length_, .801f, .143f);
			// Bottom right
			theApp_.vertex(width_, 0, .801f, .273f);
		} else {
			theApp_.vertex(0, 0, .463f, .273f);
			// Top left
			theApp_.vertex(0, length_, .463f, .143f);
			// Top right
			theApp_.vertex(width_, length_, .641f, .143f);
			// Bottom right
			theApp_.vertex(width_, 0, .641f, .273f);
		}
		currentTime_ = theApp_.millis();
		if (currentTime_ - startTimeLaugh_ > timeBetweenLaughs_) {
			laughUp_ = !laughUp_;
			startTimeLaugh_ = currentTime_;
		}
	}

	/**
	 * As long as we're past our initial wait time, we need to animate this dog.
	 * If direction = 1, the dog is moving up. Once he hits the high point of
	 * his animation, direction = 2 and he freezes for "freezeTime_". Then
	 * direction_ = 3 and he moves back down.
	 */
	public void animate() {

		currentTime_ = theApp_.millis();

		if (currentTime_ - startTimeMove_ > initialWaitTime_) {
			if (direction == 1) {
				y_ += changeInY_;
				if (y_ - startY_ > length_ - .09) {
					direction = 2;
					startTimeMove_ = theApp_.millis();
				}
			} else if (direction == 2) {
				if (currentTime_ - startTimeMove_ > freezeTime_) {
					direction = 3;
				}
			} else if (direction == 3) {
				if (y_ > startY_) {
					y_ -= changeInY_;
				}
			}
		}
	}

	/***
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
