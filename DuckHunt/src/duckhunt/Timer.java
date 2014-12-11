package duckhunt;

import processing.core.PApplet;


public class Timer implements Runnable {
	
	/**
	 * Our instance variables.  
	 * timeForDogAnimation is the amount of time we will allow the dog to animate
	 * timeForGameplay is the amount of time we will allow to shoot at the duck
	 * currentTime is the current time
	 * endTime is the time we are waiting for until we switch
	 */
	private static PApplet theApp_;
	int timeForDogAnimation_;
	int timeForGameplay_;		
	int currentTime = theApp_.millis();
	int endTime;

	
	public Timer(int timeForDogAnimation, int timeForGameplay){
		
		/**
		 * Accept our values and set them
		 */
		timeForDogAnimation_ = timeForDogAnimation;
		timeForGameplay_ = timeForGameplay;
	
	}
	
	
	/**
	 * Run an infinite loop inside a thread.  
	 * Check the static value dogAnimateValueFromThread and set the new endTime depending
	 * on what point in the game we are at.  Then call "startTimer" to get the timer going
	 * until we need to switch again.
	 */
	public void run(){
	
		while(true){
			//reset the switch value to false after a switch time sequence has occurred
			Main.resetSwitchTimerValue(); 
			if(Main.getDogAnimateValueFromThread()){
				endTime = currentTime + timeForDogAnimation_;
			}
			else{
				endTime = currentTime + timeForGameplay_;
			}
			startTimer();	
		}	
		
	}
	
	/**
	 * Until we get to the endTime, continue looping, then switch the static value
	 */
	private void startTimer(){
		while(currentTime < endTime && !Main.getSwitchTimerValue()){
			currentTime = theApp_.millis();
		}
		Main.setDogAnimateValueFromThread(!Main.getDogAnimateValueFromThread());
	}
	
	/**
	 * Set the app
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
}
