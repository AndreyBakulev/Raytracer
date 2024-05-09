public class RaytracingTask implements Runnable {
    private final int startX, endX, startY, endY;
    private final Scene scene;
    private final int xResolution, yResolution, numSamples;
    private final ColorImage image;
    //this is my render class
    public RaytracingTask(Scene scene, int startX, int endX, int startY, int endY, int xResolution, int yResolution,
            int numSamples, ColorImage image) {
        this.scene = scene;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.xResolution = xResolution;
        this.yResolution = yResolution;
        this.numSamples = numSamples;
        this.image = image;
    }

    public void run() {
        //gets the square root of the antialiasing number
        int aaRes = (int) Math.sqrt(numSamples);
        //these for loops are split by the amount of threads
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                Color accCol = new Color(0, 0, 0);
                //antialiasing computation
                for (int x = 0; x < aaRes; x++) {
                    for (int y = 0; y < aaRes; y++) {
                        double u = (i + (x + .5) / aaRes) / xResolution;
                        double v = (j + (y + .5) / aaRes) / yResolution;
                        Ray ray = scene.getCamera().generateRay(u, v);
                        Color color = scene.computeVisibleColor(ray, Controller.REFLECT_BOUNCES, Controller.AMBIENT_BOUNCES);
                        accCol = accCol.add(color);
                    }
                }
                Color avgCol = accCol.scale(1.0 / (aaRes * aaRes));
                image.setColor(i, j, avgCol);
            }
        }
    }
}