public abstract class Material {
    public abstract Color computeLighting(Intersection i, Ray viewingRay, Light li);
    public double getReflectiveness(){ return 0; }
    public Color getDiffuse(){ return new Color(0, 0, 0);};
    public Vector getDeviance(){ return new Vector(0, 0 ,0);}
}
