package duckhunt;

public interface ApplicationConstants {

	int WINDOW_WIDTH = 800;
	int WINDOW_HEIGHT = 600;

	float WORLD_WIDTH = 1.5f;
	
	
	float WORLD_TO_PIXELS_SCALE = WINDOW_WIDTH/WORLD_WIDTH;
	float PIXELS_TO_WORLD_SCALE = 1.0f/WORLD_TO_PIXELS_SCALE;
	
	float WORLD_HEIGHT = WINDOW_HEIGHT*PIXELS_TO_WORLD_SCALE;

	float X_MIN = 0;
	float X_MAX = WORLD_WIDTH;
	float Y_MIN = 0;
	float Y_MAX = Y_MIN + WORLD_HEIGHT;

}
