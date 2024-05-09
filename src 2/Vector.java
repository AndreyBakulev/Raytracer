public class Vector {
    private double dx,dy,dz;
    public Vector(double dx, double dy, double dz){
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    } 
     public double getDx(){
        return dx;
    }
    public double getDy(){
        return dy;
    }
    public double getDz(){
        return dz;
    }
    public Vector scale(double scalar){
        return new Vector(this.dx*scalar, this.dy*scalar, this.dz*scalar);
    }
    public Vector add(Vector v){
        return new Vector(this.dx + v.getDx(), this.dy+ v.getDy(), this.dz+ v.getDz());
    }
    public Vector subtract(Vector v){
        return new Vector(this.dx - v.getDx(),this.dy - v.getDy(),this.dz - v.getDz());
    }
    public double dot(Vector v){
        double wtfisthis = (this.dx*v.getDx()) + (this.dy*v.getDy()) + (this.dz*v.getDz());
        return wtfisthis;
    }
    public Vector cross(Vector v){
        return new Vector(this.dy*v.getDz() - this.dz*v.getDy(),this.dz*v.getDx() - this.dx*v.getDz(), this.dx*v.getDy() - this.dy*v.getDx());
    }
    //getting length
    public double length(){
        return Math.sqrt(this.dot(this));
    }
    //normalizing the vector
    public Vector normalize(){
        return this.scale(1/this.length());
    }
}
