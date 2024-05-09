public class LightBulb extends Light{
    private Color intensity;
    private Point position;
    public LightBulb(Color intensity, Point position){
        this.intensity = intensity;
        this.position = position;
    }
    //finds a random point within a certain range 
    public Point findTargetPoint(){
        //randomness of the position: change the multiplier to get tighter/wider soft shadows.
        Point newPos = new Point(position.getX()+Math.random()*1.5-.75, position.getY()+Math.random()*1.5-.75, position.getZ()+Math.random()*1.5-.75);
        return newPos;
    }
    public Vector computeLightDirection(Point surfacePoint){
        return (this.findTargetPoint().subtract(surfacePoint)).normalize();
    }
     public Color computeLightColor(Point surfacePoint){
         return this.intensity;
    }
     public double computeLightDistance(Point surfacePoint){
         return (this.position.subtract(surfacePoint)).length();
    }
 
}

//SOURCES: Basically just crying over email to mr farrar and a bunch of youtube videos that led nowhere :D