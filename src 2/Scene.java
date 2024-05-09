import java.util.ArrayList;

public class Scene {
    private Camera cam;
    private ArrayList<Light> lightList;
    private ArrayList<Surface> surfaceList;

    public Scene(Camera cam) {
        this.cam = cam;
        lightList = new ArrayList<Light>();
        surfaceList = new ArrayList<Surface>();
    }
    //making a new camera
    public void setCamera(Camera newCam) {
        this.cam = newCam;
    }
    //adding lights to scene
    public void addLight(Light li) {
        lightList.add(li);
    }
    //adding surfaces to scene
    public void addSurface(Surface s) {
        surfaceList.add(s);
    }
    //returning total amt of objects in the room
    public int getAmtOfSurfaces(){
        return surfaceList.size();
    }
    //checks if a pixel is shadowed
    public boolean isShadowed(Point p, Light li) {
        //shadow ray is the ray from the light point to the direction of the ground
        Ray shadowRay = new Ray(p, li.computeLightDirection(p));
        Intersection intersect = null;
        for (int i = 0; i < surfaceList.size(); i++) {
            //loops thru the whole surfaceList (which is j the amt of objects) to check if it collides
            intersect = surfaceList.get(i).intersect(shadowRay);
            if (intersect != null && intersect.getDistance() < li.computeLightDistance(p)) {
                //if a collision happens... 
                return true;

            }
        }
        return false;
    }
    //getting the colors of everything
    public Color computeVisibleColor(Ray r, int bouncesLeft, int ambientBounces) {
        Intersection closestIntersect = null;
        double closest = Double.MAX_VALUE;
        Intersection intersect = null;
        //runs through the amount of objects
        for (int i = 0; i < surfaceList.size(); i++) {
            intersect = surfaceList.get(i).intersect(r);
            if (intersect != null) {
                //if there is an intersection/the light hit something
                if (intersect.getDistance() < closest) {
                    closestIntersect = intersect;
                    closest = closestIntersect.getDistance();
                }
            }
        }
        //if nothing got hit
        if (closestIntersect == null)
            return new Color(0, 0, 0);
        //if something did get hit
        else {
            Color c = new Color(0, 0, 0);
            for (int j = 0; j < lightList.size(); j++) {
                //if this light hits an object that is not shadowed
                if (!isShadowed(closestIntersect.getPosition(), lightList.get(j))) {
                    //this tint gets us the individual color of every object
                    Color tint = closestIntersect.getMaterial().computeLighting(closestIntersect, r, lightList.get(j));
                    c = c.tint(tint);
                }
            }
            //mirror tint
            if (bouncesLeft > 0 && closestIntersect.getMaterial().getReflectiveness() > 0) {
                //this is the direction vector
                Vector dasdlkasddsfishdfosdnfoi = closestIntersect.getNormal()
                        .scale(2 * (closestIntersect.getNormal().dot(r.getDirection().scale(-1))))
                        .subtract(r.getDirection().scale(-1)).add(closestIntersect.getMaterial().getDeviance()).normalize();
                //making a new ray at the hit on the mirror with the direction vector
                Ray newR = new Ray(closestIntersect.getPosition(), dasdlkasddsfishdfosdnfoi);
                //recursively run through this based on the amount of bounces given
                Color mirrors = computeVisibleColor(newR, bouncesLeft - 1, ambientBounces)
                        .scale(closestIntersect.getMaterial().getReflectiveness());
                c = c.tint(mirrors);
            }
            //ambient light
            //Source: https://www.youtube.com/watch?v=Qz0KTGYJtUk
            if (ambientBounces > 0) {
                //make a vector that is slightly randomized
                Vector ambientVect = new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1)
                        .normalize();
                //if the ambientVector and the dot product of the normal of the closest object is less than 0
                if (ambientVect.dot(closestIntersect.getNormal()) < 0) {
                    //invert it because it is facing inwards
                    ambientVect = ambientVect.scale(-1);
                }
                //this is v similar to the mirrors except with randomness added
                Ray ambientRay = new Ray(closestIntersect.getPosition(), ambientVect);
                Color ambient = computeVisibleColor(ambientRay, bouncesLeft, ambientBounces - 1).shade(closestIntersect.getMaterial().getDiffuse());
                c = c.tint(ambient.scale(closestIntersect.getNormal().dot(ambientRay.getDirection())).scale(Controller.AMBIENT_POWER));
            }
            return c;
        }
    }
    //getting the camera
    public Camera getCamera() {
        return this.cam;
    }
} 