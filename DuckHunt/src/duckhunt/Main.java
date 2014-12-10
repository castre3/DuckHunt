package duckhunt;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements ApplicationConstants {

	private static final long serialVersionUID = 1L;

	private boolean initApp_ = setAppForAllClasses();
	
	private static int frameCounter_ = 0;


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
	/**
	 * The background.
	 */
	PImage _backgroundImage;
	
	Background background_;
	private int frameCounter = 0;
	
	Timer time_;
	boolean dogAnimate_;  //The value of dogAnimate we will be checking

	public static boolean dogAnimateValueFromThread = false; //A static variable that our thread

	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT,P3D);
		
		Timer time_ = new Timer(1000,2000);
		Thread timerThread = new Thread(time_);
		timerThread.start();
		
		frameRate(2000);	
		
		duck_ = new ArrayList<Duck>();
		
		//load image 0-1
		textureMode(NORMAL);
		
		//Image loading section
		PImage sprite_=loadImage("sprite.png");
		_backgroundImage=loadImage("background.jpg");
		PImage ground_ = loadImage("ground.jpg");
		PImage allSprite_ = loadImage("duckhunt_various_sheet.png");
		
		background_ = new Background(ground_,_backgroundImage, allSprite_);
		// the y parameter needs to be above WORLD_HEIGHT/3
		duck_.add(new Duck(sprite_,WORLD_WIDTH /2, WORLD_HEIGHT / 2, -PI / 4,PIXELS_TO_WORLD_SCALE / 4));
	}

	public void draw() {;
		//Background world setup - etc
		background_.draw();

		if(getStatic() != dogAnimate_) {
			dogAnimate_ = getStatic();
			if(dogAnimate_== false) {
				//Destroy dog object
				//Instantiate duck object
			}
			else {
				//Destroy duck object
				//Instantiate dog object
			}
		}
		if(dogAnimate_ = false) {
			//draw duck
			//animate duck
		}
		else {
			//draw dog
			//animate dog
		}
		
		if (frameCounter++ % 10 == 0) 
		{
			for (Duck obj : duck_)
				obj.switchBirdWing();
		}
		for (Duck obj : duck_)
			obj.animate();

		// We are still in pixel units.
		translate(1, WINDOW_HEIGHT);
		
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
	


	/**
	 * 
	 */
	public void keyReleased() {
		/*switch (key) {
		case '0':
		case '1':
		case '2':
		case '3':
			int selectedIndex = Integer.parseInt("" + key);
			break;
		}*/
		if (key=='1'){
			camera(width/2.0f, height/2.0f, (height/2.0f) / tan(PI*30.0f / 180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0); 
			
		} else if (key=='2'){
			camera(width/2.0f, height/1.0f, (height/1.5f) / tan(PI*30.0f / 180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0);
		
		} else if (key=='3'){
			camera(width/2.0f, height*2.5f, (height/3.0f) / tan(PI*30.0f / 180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0); 
			
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
				obj.setVy(1f); // make it fly up screen
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
		Background.setApp(this);
		Timer.setApp(this);
		return true;
	}
	
	public static void setStatic(boolean value){
		dogAnimateValueFromThread = value;
	}
	
	public static boolean getStatic(){
		return dogAnimateValueFromThread;
	}

}
