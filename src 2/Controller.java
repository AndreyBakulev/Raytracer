public class Controller {
    //length and width of the image
    public static final int X_RESOLUTION = 480;
    public static final int Y_RESOLUTION  = 270;

    //amt of antialiasing per pixel (heavy performance hit)
    public static final int AA_PER_PIXEL = 100;
    //amt of bounces the mirror ray will take
    public static final int REFLECT_BOUNCES = 2;

    //amt of bounces ambient ray will take (medium performance hit)
    public static final int AMBIENT_BOUNCES = 3;
    //strength of color (doesn't affect performance)
    public static final int AMBIENT_POWER = 2;

    //camera position
    public static final int CAMERA_X = 0;
    public static final int CAMERA_Y = 0;
    public static final int CAMERA_Z = 0;
    
    //the amount of seconds that you will get updated at (seconds)
    public static final int TIME_INTERVAL = 2;  
    //timeout time (seconds)
    public static final int TIMEOUT = 60 * 60 * 100;
}
