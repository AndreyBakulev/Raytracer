public class PointLight extends Light{
    private Color intensity;
    private Point position;
    public PointLight(Color intensity, Point position){
        this.intensity = intensity;
        this.position = position;
    }
    public Vector computeLightDirection(Point surfacePoint){
       return (this.position.subtract(surfacePoint)).normalize();
    }
    public Color computeLightColor(Point surfacePoint){
        //falloff here
        return this.intensity;
    }
    public double computeLightDistance(Point surfacePoint){
        return (this.position.subtract(surfacePoint)).length();
    }
    @Override
    public Point findTargetPoint(){ return position;}
    
}
