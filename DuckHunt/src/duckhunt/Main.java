package duckhunt;

import java.util.ArrayList;

import processing.core.PApplet;


public class Main extends PApplet implements ApplicationConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean initApp_ = setAppForAllClasses();
	private int frameCounter_ = 0;
	private int lastDrawTime_ = 0;
	
	private int selectedIndex = 0;
	
	ArrayList<Duck> duck_;
	
	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frameRate(2000);

		duck_ = new ArrayList<Duck>();
		
		duck_.add(new Duck(WORLD_WIDTH/4, WORLD_HEIGHT/4, -PI/4, PIXELS_TO_WORLD_SCALE/4));
		duck_.add(new Duck(WORLD_WIDTH/4, 3*WORLD_HEIGHT/4, PI/4, PIXELS_TO_WORLD_SCALE/4));
		duck_.add(new Duck(-WORLD_WIDTH/4, WORLD_HEIGHT/4, 3*PI/4, PIXELS_TO_WORLD_SCALE/4));
		duck_.add(new Duck(-WORLD_WIDTH/4, 3*WORLD_HEIGHT/4, 3*PI/4, PIXELS_TO_WORLD_SCALE/4));
		
	}

	public void draw() {
		
		
		//Version 2 with ArrayList (new style)
		for (Duck obj : duck_)
			obj.animate();


		//if (frameCounter_++ % 10 == 0)
		//{ 
			background(127);

	 		//	We are still in pixel units.
	 		translate(WINDOW_WIDTH/2, WINDOW_HEIGHT);
	 		
	 		//	change to world units
	 		scale(WORLD_TO_PIXELS_SCALE, -WORLD_TO_PIXELS_SCALE);
	 		
	 		stroke(255);
	 		strokeWeight(1*PIXELS_TO_WORLD_SCALE);
	 		line(0, 0, 0, WORLD_HEIGHT);

	 		pushMatrix();
			
			for (Duck obj : duck_){
				obj.draw();
				//if (obj.getY() < -WORLD_HEIGHT){ <- We gotta remove this duck somehow but this code throws an error
					//duck_.remove(obj);
				//}
			}

			translate(-width, 0);
			for (Duck obj : duck_)
				obj.draw();

			translate(2*width, 0);
			for (Duck obj : duck_)
				obj.draw();

			popMatrix();
		//}
	}

	public void keyReleased() {
		switch (key) {
		case '0':
		case '1':
		case '2':
		case '3':
		selectedIndex = Integer.parseInt(""+key);
		break;
		}
		
		
	}
	public void mouseReleased() {
//		face_.get(selectedIndex).setPosition(xPixelToWorld(mouseX), yPixelToWorld(mouseY));
		
		float x = xPixelToWorld(mouseX), y = yPixelToWorld(mouseY);

		for (Duck obj : duck_)
			if (obj.isInside(x, y)) {
				obj.setVx(0);		//temporarily make it not move
				obj.setVy(0);
				delay(1000);		//do the "pause" thing
				obj.setVy(-1f);		//make it fall off screen
				obj.setShot();		//Set "shot" to "false"
				//duck_.remove(obj);
				break;
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
