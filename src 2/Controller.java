public class Controller {
    //length and width of the image
    public static final int X_RESOLUTION = 1920;
    public static final int Y_RESOLUTION  = 1080;

    //amt of antialiasing per pixel (heavy performance hit)
    public static final int AA_PER_PIXEL = 400;

    //amt of bounces the mirror ray will tak
    public static final int REFLECT_BOUNCES = 5;

    //amt of bounces ambient ray will take and the strength of the color
    public static final int AMBIENT_BOUNCES = 3;
    public static final int AMBIENT_POWER = 2;

    //camera position
    public static final int CAMERA_X = 0;
    public static final int CAMERA_Y = 0;
    public static final int CAMERA_Z = 0;
    
    //the amount of seconds that you will get updated at 
    public static final int TIME_INTERVAL = 30;  
}
