public class Phong extends Material {
    private Color diffuse, specular;
    private double exponent;
    public Phong(Color diffuse, Color specular, double exponent){
        this.exponent = exponent;
        this.specular = specular;
        this.diffuse = diffuse;
    }
    public Color getDiffuse(){
        return diffuse;
    }
    //similar to lambert except now with specular highlights
    public Color computeLighting(Intersection i, Ray viewingRay, Light li) {
        //getting direction and color of the light
        Vector lightDirection = li.computeLightDirection(i.getPosition());
        Color lightColor = li.computeLightColor(i.getPosition());
        double dot = i.getNormal().dot(lightDirection);
        //if the light does not hit the pixel
        if(dot < 0) return new Color(0, 0, 0);
        //scale the diffuse color by the dot product and shade it by the light color
        Color c = diffuse.scale(dot).shade(lightColor);
        //mirror vector huge equation courtesy of Monsiuer Farrar
        Vector mirrorVector = (i.getNormal().scale(2*(i.getNormal().dot(lightDirection))).subtract(lightDirection)).normalize();
        double specularAngle = (viewingRay.getDirection().scale(-1)).dot(mirrorVector);
        //if there are no specular highlights to that pixel
        if(specularAngle < 0.0){
            return c;
        } else{
            //multiply the lightcolor by the coefficient (how bright it is)
            double coefficient = (Math.pow(specularAngle, exponent));
            Color highlightColor = lightColor.scale(coefficient).shade(specular);
            return c.tint(highlightColor);

        }

    }

}
