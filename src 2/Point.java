public class Point {
    private double x,y,z;

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public void setX(double x){
        this.x =x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setZ(double z){
        this.z = z;
    }

    //adds the two points together to make a vector
    public Point add(Vector v){
        Point newPoint = new Point(this.x + v.getDx(), this.y + v.getDy(), this.z + v.getDz());
        return newPoint;
    }
    //subtracts the two points
    public Vector subtract(Point v){
        Vector newVect = new Vector(this.x - v.getX(), this.y - v.getY(), this.z - v.getZ());
        return newVect;
    }
}
