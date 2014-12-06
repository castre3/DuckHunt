package duckhunt;

import processing.core.PApplet;

/***
 * 
 * @author castre3
 *
 */
public class Duck implements ApplicationConstants {
	/*
	 * 
	 */
	private static PApplet theApp_;
	/**
	 * 
	 */
	private static final float ACROSS_WORLD_TIME = 2.5f;  /* seconds*/
	/*
	 * 
	 */
	private static final float MAX_SPEED = WORLD_WIDTH / ACROSS_WORLD_TIME;
	/*
	 * 
	 */
	private float x_, y_, scale_;
	/*
	 * 
	 */
	private float Vx_, Vy_;
	/*
	 * 
	 */
	private boolean shot = false;
	/*
	 * 	
	 */
	private float radius_ = 0.15f;
	
	
	/***
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param scale
	 */
	public Duck(float x, float y, float angle, 
			float scale) {
		x_ = x;
		y_ = y;
		scale_ = scale;
		
		// generate speed
		float theta = (float) (Math.PI * Math.random());
		float v = (float) (MAX_SPEED);
		Vx_ = v * theApp_.cos(theta);
		Vy_ = v * theApp_.sin(theta);
	}
	
	/*
	 * 
	 */
	void draw() {

		theApp_.pushMatrix();
			
		theApp_.translate(x_, y_);
	
		theApp_.strokeWeight(0.01f);
		theApp_.stroke(0, 0, 0);
		theApp_.ellipse(0, 0, radius_, radius_);
			
		theApp_.scale(scale_);
		
		theApp_.noStroke();
		theApp_.fill(120, 174, 198);
	
		theApp_.ellipse(-50, +0, 140, 130);  //  right ear
		theApp_.ellipse(+50, +0, 140, 130);  //  left ear
		theApp_.ellipse(+0, +10, 150, 280);
	
		theApp_.fill(255);
		theApp_.ellipse(-30, -40, 40, 60);
		theApp_.fill(20, 90, 130);
		theApp_.ellipse(-28, -45, 25, 28);
	
		theApp_.fill(255);
		theApp_.ellipse(+30, -40, 40, 60);
		theApp_.fill(20, 90, 130);
		theApp_.ellipse(+32, -45, 25, 28);
		

		theApp_.popMatrix();
	}
	/**
	 * 
	 */
	public void animate(){
		
			x_ += Vx_ * 0.01f;
			y_ += Vy_ * 0.01f;
			
			// hit left or right  bound bounce
			if ((x_ < X_MIN) || (x_ >= X_MAX )) {
				Vx_ = -Vx_;
			}
			
			// hit top or bottom  bound
			if (((y_ < Y_MIN) || (y_ >= Y_MAX )) && !shot) {
				Vy_ = -Vy_;
			}
		
	}
	/***
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		x_ = x;
		y_ = y;
	}
	/***
	 * 
	 * @return
	 */
	public float getX(){
		return x_;
	}
	/***
	 * 
	 * @return
	 */
	public float getY(){
		return y_;
	}
	/***
	 * 
	 * @param Vx
	 */
	public void setVx(float Vx){
		Vx_ = Vx;
	}
	/***
	 * 
	 * @param Vy
	 */
	public void setVy(float Vy){
		Vy_ = Vy;
	}
	/***
	 * 
	 */
	public void setShot(){
		shot = true;
	}
	/***
	 * 
	 * @param theApp
	 */
	public static void setApp(PApplet theApp) {
		theApp_ = theApp;
	}
	/***
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInside(float x, float y) {
	
		float dx = x - x_, dy = y - y_;
		
		return (dx*dx + dy*dy < radius_*radius_);
	}

}
