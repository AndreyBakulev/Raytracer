public class Ray {
    private Point p;
    private Vector v;
    public Ray(Point p, Vector v){
        this.p = p;
        this.v = v.normalize();
    }
    public Point getPosition(){
        return p;
    }
    public Vector getDirection(){
        return v;
    }
    public Point evaluate(double dist){
        Vector omg = v.scale(dist);
        return p.add(omg);
    }
}
