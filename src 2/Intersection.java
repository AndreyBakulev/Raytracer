public class Intersection {
    private Point position;
    private Vector normal;
    private double distance,imageX,imageY;
    private Material surfaceMaterial;
    //one of two constructors
    public Intersection(Point position, Vector normal, double distance, Material surfaceMaterial,double imageX, double imageY){
        this.position = position;
        this.normal = normal;
        this.distance = distance;
        this.surfaceMaterial = surfaceMaterial;
        this.imageX = imageX;
        this.imageY = imageY;
    }
    //second of two constructors, this time with imagex and imagey being 0
    public Intersection(Point position, Vector normal, double dist, Material m){
         this(position, normal,dist,m, 0.0,0.0);
    }
    public Point getPosition(){
        return position;
    }
    public double getImageX(){
        return imageX;
    }
    public double getImageY(){
        return imageY;
    }
    public Vector getNormal(){
        return normal;
    }
    public double getDistance(){
        return distance;
    }
    public Material getMaterial(){
        return surfaceMaterial;
    }
    
}
