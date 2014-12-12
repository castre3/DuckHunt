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
public class Dog implements ApplicationConstants {
	/**
	 * The local instance of the application
	 */
	
	/**
	 * The max speed the duck can go
	 */
	private static PApplet theApp_;
	private float width_ = .3f, length_ = .55f;
	private float startTime_ = theApp_.millis(), currentTime_;
	private float x_ = WORLD_WIDTH /2, y_ = WORLD_HEIGHT / 2 - length_, startY_ = y_;
	

	private float Vy_ = .6f;
	private PImage dogskin_;
	private int whichDog_;
	private int freezeTime_ = 2000;
	private int initialWaitTime_ = 2000;
	private int direction = 1;
	

	/***
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param scale
	 */
	public Dog(PImage sprite, int whichDog) {
		dogskin_ = sprite;
		whichDog_ = whichDog;
	}

	/**
	 * 
	 */
	void draw() {
		theApp_.noStroke();
		theApp_.beginShape(PApplet.QUADS);
		theApp_.texture(dogskin_);
		theApp_.translate(x_, y_);
		//bottom left
		theApp_.vertex(0, 0, .88f, .1f);
		//top left
		theApp_.vertex(0, .3f, .88f, 0f);
		//top right
		theApp_.vertex(.3f, .3f, 1f, 0f);
		//bottom right
		theApp_.vertex(.3f, 0, 1f, .1f);
		theApp_.endShape();
		/*
			theApp_.pushMatrix();
			theApp_.translate(x_ - (width_/2), y_ - (length_/2));
			theApp_.noStroke(); 
			theApp_.beginShape(PApplet.QUADS);
			theApp_.texture(dogskin_);
			
			//Top left
			theApp_.vertex(0, length_, .88f, .03f); 
			//Top right
			theApp_.vertex(width_, length_, 1f, .03f);
			//Bottom left
			theApp_.vertex(0, 0, .88f, .1f); 
			//Bottom right
			theApp_.vertex(width_, 0, 1f, .1f);
			  
			theApp_.endShape();
			theApp_.popMatrix();
			*/
	}

	/**
	 * moves the ducks making sure it is in bound
	 */
	public void animate() {
	
		currentTime_ = theApp_.millis();
		
		if(currentTime_ - startTime_ > initialWaitTime_){
			
			if (direction == 1){
				y_ += Vy_ * 0.001f;
				if(y_ - startY_ > length_){
					direction = 2;
					startTime_ = theApp_.millis();
				}
			}
			
			else if (direction == 2){
				if (currentTime_ - startTime_ > freezeTime_){
					direction = 3;
				}
			}
			
			else if (direction == 3){
				if(y_ > startY_){
					y_ -= Vy_ * 0.001f;
				}
			}	
		}
	}

	/***
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}


}
