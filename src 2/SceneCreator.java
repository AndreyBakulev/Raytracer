import java.beans.beancontext.BeanContextChildSupport;
//NUMBER 1 IMPORT!!!!!!!!!

/**
 * Details the static methods to creating various scenes for use in the
 * raytracer.
 * scene1() is included as an example. You can add more static methods (for
 * example scene2(),
 * scene3(), etc.) to create different scenes without affecting scene1.
 *
 * @author Ben Farrar
 * @version 2019.05.22
 */
public class SceneCreator {
    //makes the new scene
    public static Scene scene1(double xResolution, double yResolution) {
        Camera cam = new Camera(new Point(Controller.CAMERA_X, Controller.CAMERA_Y, Controller.CAMERA_Z), // camera location
                new Vector(0, 0, -1), // forward vector/view direction
                new Vector(0, 1, 0), // up vector
                25, // field of view
                xResolution / yResolution); // aspect ratio
        Scene s = new Scene(cam);
        //all the colors I used
        Lambert white = new Lambert(Color.White());
        Lambert green = new Lambert(Color.Green());
        Lambert blue = new Lambert(Color.Blue());
        Lambert red = new Lambert(Color.Red());
        Lambert black = new Lambert(Color.Black());
        Phong pRed = new Phong(Color.Red(), Color.White(), 20);
        Phong pGray = new Phong(new Color(.4, .4, .4), Color.White(), 20);
        Phong pGreen = new Phong(Color.Green(), Color.White(), 20);
        Phong pCyan = new Phong(Color.Cyan(), Color.White(), 20);
        Phong pMagenta = new Phong(Color.Magenta(), Color.White(), 20);
        Phong pMarineBlue = new Phong(Color.MarineBlue(), Color.White(), 20);
        MirrorPhong mCyan = new MirrorPhong(Color.Cyan(), Color.White(), 20, .25,.01);
        MirrorPhong mMagenta = new MirrorPhong(Color.Magenta(), Color.White(), 20, .25,.01);
        MirrorPhong mGreen = new MirrorPhong(Color.Green(), Color.White(), 20, .25,.01);
        MirrorPhong mGray = new MirrorPhong(Color.Dark(), Color.White(), 20, .25,.01);
        MirrorPhong mRed = new MirrorPhong(Color.Red(), Color.White(), 20, .25,.01);
        MirrorPhong mirror = new MirrorPhong(Color.Dark(), Color.White(), 20, 1,.001);
        MirrorPhong halfMirror = new MirrorPhong(Color.Dark(), Color.White(), 20, .7,.05);
        //Texture checkers = new Texture("checkers.jpeg");
        //Texture earth = new Texture("earth.jpg");

        //making all the objects
        Surface s1 = new Sphere(new Point(0, 0, -25), 3, white, new Vector(0, 0,-1), new Vector(0, 1, 0));
        s.addSurface(s1);
        //Surface s3 = new Sphere(new Point(4, -1.5, -30), 1.5, checkers,new Vector(0, 0,1), new Vector(0, 1, 0));
        //s.addSurface(s3);
        //Surface s7 = new Sphere(new Point(-2,-1.5,-15), 1.5, earth, new Vector(0, 0, 1), new Vector(0, 1,0));
        //s.addSurface(s7);
        Surface s8 = new Cone(new Point(2, 0, -10), new Point(2, 5, -10), 2, pCyan);
        //s.addSurface(s8);
        //all the lights
        PointLight l1 = new PointLight(Color.White(), new Point(0, 15, 5));
        //s.addLight(l1);
        PointLight l2 = new PointLight(Color.White(), new Point(0, .1, 0));
        //s.addLight(l2);     
        LightBulb l3 = new LightBulb(Color.White(), new Point(0, 15, 5));  
        s.addLight(l3);


        //walls made of 2 right triangles
        //using these to make the creation of the walls easier
        int leftBound = -6;
        int rightBound = 6;
        int topWall = 12;
        int bottomWall = -3;
        //making walls
        Surface rightWall1 = new Triangle(new Point(rightBound, bottomWall, -40), new Point(rightBound, topWall, -40), new Point(rightBound, bottomWall, -2), pCyan);
        s.addSurface(rightWall1);
        Surface rightWall2 = new Triangle(new Point(rightBound, topWall, -40), new Point(rightBound, topWall, -2), new Point(rightBound, bottomWall, -2), pCyan);
        s.addSurface(rightWall2);
        Surface leftWall1 = new Triangle(new Point(leftBound, bottomWall, -40), new Point(leftBound, topWall, -40), new Point(leftBound, topWall, -2), red);
        s.addSurface(leftWall1);
        Surface leftWall2 = new Triangle(new Point(leftBound, bottomWall, -40), new Point(leftBound, topWall, -2), new Point(leftBound, bottomWall, -2), red);
        s.addSurface(leftWall2);
        Surface backwall1 = new Triangle(new Point(leftBound, bottomWall, -40), new Point(leftBound, topWall, -40), new Point(rightBound, bottomWall, -40), pGray);
        s.addSurface(backwall1);
        Surface backwall2 = new Triangle(new Point(rightBound, bottomWall, -40), new Point(rightBound, topWall, -40), new Point(leftBound,  topWall, -40), pGray);
        s.addSurface(backwall2);
        Surface floor = new Triangle(new Point(0, -3, 0), new Point(3000, -3, -1000), new Point(-3000, -3, -1000), green);
        s.addSurface(floor);

       
        return s;
    }
}

/*
 * NOTES
 * different patterns on the floor
 * mirror blur
 * interactive scene???
 * make mirror blur based on how far the object is from the mirror (.shade(computelightdistance))???
 * 
 * FIXES FOR FARRAR:
 * I need to find a way to flip the image horizontally when I am using the texture (show earth problem)
 * 
 */
