import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {
    private JLabel label1;
    private JFrame frame;
    private Scene createScene;

    public GUI() {
        // Size of the final image. This will DRAMATICALLY affect the runtime.
        int xResolution = Controller.X_RESOLUTION;
        int yResolution = Controller.Y_RESOLUTION;
        // the time in unix epochs when the program begins running
        int numThreads = Runtime.getRuntime().availableProcessors();
        long startTime = System.currentTimeMillis();
        // which scene to render.
        System.out.println("\n\nUsing "+ numThreads + " threads");
        System.out.println("Creating scene... in GUI");
        // Scene s = SceneCreator.scene1(xResolution, yResolution);
        // Render the scene into a ColorImage
        System.out.println("Rendering image... in GUI");
        // this is the antialiasing
        int numSamples = Controller.AA_PER_PIXEL;
        label1 = new JLabel(new ImageIcon("scene1.png"));
        JFrame frame = new JFrame("render");
        frame.setSize(Controller.X_RESOLUTION, Controller.Y_RESOLUTION);
        createScene = SceneCreator.scene1(Controller.X_RESOLUTION, Controller.Y_RESOLUTION);
        // making the colorimage
        ColorImage image2 = new ColorImage(xResolution, yResolution);
        // amount of available processors on the given computer
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        // splits the image into evenly distributed sections based on the amt of threads
        int chunkSizeX = xResolution / numThreads;
        // counter to check how far away the image is from being finished
        PercentChecker timer = new PercentChecker(Controller.TIME_INTERVAL * 1000, image2);
        // big for loop to make the even threads (based on amt of available processors)
        for (int i = 0; i < numThreads; i++) {
            int startX = i * chunkSizeX;
            int endX = (i + 1) * chunkSizeX;
            int startY = 0;
            int endY = yResolution;
            if (i == numThreads - 1)
                endX = xResolution;
            executor.execute(
                    // run the raytracer using the threads
                    new RaytracingTask(createScene, startX, endX, startY, endY, xResolution, yResolution, numSamples,
                            image2));
        }
        executor.shutdown();
        try {
            // max time the threads can take until it auto-quits
            executor.awaitTermination(Controller.TIMEOUT, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        // when the image is finished rendering, get the current time in unix epochs and
        // subtract them to get total time while rendering
        double elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Saving file...");
        // big string to print out how long the render took, on what res, and with
        // settings
        String dataString = "render took " + elapsedTime / 1000 + " seconds on a " + xResolution + " by " + yResolution
                + " image using " + Controller.AA_PER_PIXEL + " antialiasing, " + Controller.REFLECT_BOUNCES
                + " reflection bounces, " + Controller.AMBIENT_BOUNCES + " ambient bounces, "
                + Controller.AMBIENT_POWER + " ambient power and " + createScene.getAmtOfSurfaces() + " objects";

        System.out.println(dataString);
        // this sends the string that was just printed out into the data.txt file for
        // data storage
        try (PrintWriter out = new PrintWriter(new FileWriter(new File("data.txt"), true))) {
            out.println(dataString);
        } catch (Exception e) {

        }

        //this does both a save and gui so if u want one j comment the other one out
        BufferedImage bufferedImage = RaytracerDriver.GUIImage(image2);
        RaytracerDriver.saveImage("scene1.png", image2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
        frame.pack();
        frame.setVisible(true);
        System.exit(1);
    }

    public static void main(String[] args) throws Exception{
        GUI gui = new GUI();
    }
}
