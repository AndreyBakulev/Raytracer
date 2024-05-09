import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Driver for the various stages of the image generation and saving process.
 * 
 * @author Andrey Bakulev
 * @version 2023.06.07
 */
public class RaytracerDriver extends JFrame {
    //renders out to a gui
    public static BufferedImage GUIImage(ColorImage image) {
        try {
            BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    // This line reverses the y axis. Use the following line instead if your image
                    // is upside down.
                    bi.setRGB(x, image.getHeight() - 1 - y, image.getColor(x, y).toARGB());
                    // bi.setRGB(x,y,image.getColor(x,y).toARGB());
                }
            }
            return bi;

        } catch (Exception e) {
            System.out.println("Problem saving image");
            System.out.println(e);
            System.exit(1);
            return null;
        }
    }
    //saves the image
    public static void saveImage(String filename, ColorImage image){
        try {
            
            BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    //This line reverses the y axis. Use the following line instead if your image is upside down.
                    bi.setRGB(x,image.getHeight()-1-y,image.getColor(x,y).toARGB());
                    //bi.setRGB(x,y,image.getColor(x,y).toARGB());
                }
            }
            ImageIO.write(bi, "PNG", new File(filename));
            
        } catch(Exception e) {
            System.out.println("Problem saving image: " + filename);
            System.out.println(e);
            System.exit(1);
        }
    }
}