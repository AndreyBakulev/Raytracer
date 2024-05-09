public class Camera {
    private Point location;
    private Vector forward,up,right;
    private double xFov,yFov;
    public Camera(Point location, Vector forward, Vector up, double fieldOfView, double aspectRatio){
        this.location = location;
        this.forward = forward.normalize();
        this.up = up.normalize();
        this.right = forward.cross(up);
        this.xFov = Math.toRadians(fieldOfView);
        this.yFov = Math.atan(Math.tan(xFov)/aspectRatio);
    }
    public Point imagePlanePoint(double u, double v){
        return location.add(forward).add(right.scale(2*(u-.5)*Math.tan(xFov))).add(up.scale((2*(v-.5) *Math.tan(yFov))));
    }
    //getting ray given the u and v coordinates
    public Ray generateRay(double u, double v){
      return new Ray(location, this.imagePlanePoint(u, v).subtract(location));
    }
}
