public abstract class Light {
    //lol funny class
    public abstract Vector computeLightDirection(Point surfacePoint);
    public abstract Color computeLightColor(Point surfacePoint);
    public abstract double computeLightDistance(Point surfacePoint);
    public abstract Point findTargetPoint();

}
