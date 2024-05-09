import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
/**
 * Driver for the various stages of the image generation and saving process.
 * 
 * @author Andrey Bakulev
 * @version 2023.06.11
 */
public class PercentChecker {
    private int secondCounter;
    private int pixelCounter;
    private double percentDone;
    private boolean isDone;
    public PercentChecker(int interval, ColorImage image) {
        // secondCounter counts the total amount of seconds in the render
        this.secondCounter = interval / 1000;
        this.pixelCounter = 0;
        this.percentDone = 0;
        isDone = false;
        // SOURCE: just found how to use actionListener, programming was mine :D
        Timer timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //loops through the whole image
                if(!isDone){
                for (int i = 0; i < Controller.X_RESOLUTION; i++) {
                    for (int j = 0; j < Controller.Y_RESOLUTION; j++) {
                        if (image.sameColor(image.getColor(i, j), image.getBackgroundColor())) {
                            //pixelcounter counts every uncolored pixel;
                            pixelCounter++;
                        }
                    }
                }
                //percentage gets the percent of the pixels that are unfinished in the whole image
                Double percentage = (double) pixelCounter / (Controller.X_RESOLUTION*Controller.Y_RESOLUTION);
                //percentDone gets the inverse of percentage, giving us the amount of pixels that ARE finished
                percentDone = 100 - (percentage * 100);
                System.out.println("at " + secondCounter + " seconds, the image is " + percentDone + "% finished");
                secondCounter += interval / 1000;
                //set back to 0 so it counts the amount of uncolored pixels every time and doesnt add them together;
                pixelCounter = 0;
                //bc of gui this will keep running so this makes it so once its 100 percent done it doesnt keep printing
                //also this checks if the next "time check" will put it over 100 and if so it stops it.
                //this math is cool AND efficient AND clean so gimme extra points pls
                if((percentDone + ((percentDone/secondCounter)*(interval/1000)) >= 100.0)){
                    isDone = true;
                }
            }
                
            }
        });
        timer.setRepeats(true);
        timer.start(); // Go go go!
    }
}
/*
 * System.out.println("at " + elapsedTime/1000 + " seconds, the render is " +
 * percentDone + "% complete");
 * for(int i = 0; i < Controller.X_RESOLUTION; i++){
 * for(int j = 0; j < Controller.Y_RESOLUTION; j++){
 * if(RaytracerDriver.image2.sameColor(image2.getColor(i, j),
 * image2.getBackgroundColor())){
 * counter++;
 * }
 * }
 * }
 * double percentDone = 100 - ((double)
 * counter/(Controller.X_RESOLUTION*Controller.Y_RESOLUTION) * 100);
 * while(percentDone != 100.0){
 * 
 * }
 */
