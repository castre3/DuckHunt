package duckhunt;

import processing.core.PApplet;


public class Timer{
	
	/**
	 * Our instance variables.  
	 * timeForDogAnimation is the amount of time we will allow the dog to animate
	 * timeForGameplay is the amount of time we will allow to shoot at the duck
	 * startTime is the time we start the current "clock cycle" at (used to determined when the
	 * 	"cycle" is over
	 * currentTime is the current time
	 * dogAnimate_ holds the current timer value of "dogAnimate_"
	 * endTime is the time we are waiting for until we switch
	 */
	private static PApplet theApp_;
	int timeForDogAnimation_;
	int timeForGameplay_;		
	int startTime_ = theApp_.millis();
	int currentTime = startTime_;
	boolean dogAnimate_ = false;
	int endTime;

	
	public Timer(int timeForDogAnimation, int timeForGameplay){
		/**
		 * Accept our values and set them
		 */
		timeForDogAnimation_ = timeForDogAnimation;
		timeForGameplay_ = timeForGameplay;
	
	}
	
	/**
	 * If the dog is animating (dogAnimate_ is true), check if we're done the current cycle.
	 * 		--If so, switch dogAnimate_ to false and reset the "startTime_"
	 * If the ducks are animating (dogAnimate_ is false), check if we're done the current cycle.
	 * 		--If so, switch dogAnimate_ to true and reset the "startTime_"
	 * Then return the value of dogAnimate_ after the calculation
	 */
	public boolean getValue(){
	
		currentTime = theApp_.millis();
		
		if(dogAnimate_){
			if (currentTime - startTime_ > timeForDogAnimation_){
				dogAnimate_ = false;
				startTime_ = theApp_.millis();
			}
		}
		else{
			if (currentTime - startTime_ > timeForGameplay_){
				dogAnimate_ = true;
				startTime_ = theApp_.millis();
			}
		}
		return dogAnimate_;
		
	}
	
	/**
	 * Used if we need to quickly end the "clock cycle" we're currently in
	 */
	public void switchValue(){
		dogAnimate_ = !dogAnimate_;
		startTime_ = theApp_.millis();
	}

	
	/**
	 * Set the app
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
