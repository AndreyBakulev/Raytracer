public class MirrorPhong extends Phong {
    private double refPwr;
    private double deviance;

    public MirrorPhong(Color diff, Color spec, double exp, double refPwr, double deviance) {
        super(diff, spec, exp);
        this.refPwr = refPwr;
        this.deviance = deviance;
    }

    public Vector getDeviance() {
        return new Vector(getRandomness(),getRandomness(),getRandomness());
    }
    public double getRandomness(){
        return Math.random() * (2 * deviance) - deviance;
    }

    public double getReflectiveness() {
        return refPwr;
    }

}
