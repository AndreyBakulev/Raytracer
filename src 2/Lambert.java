public class Lambert extends Material {
    private Color diffuse;

    public Lambert(Color diffuse){
        this.diffuse = diffuse;
    }
    //get base color
    public Color getDiffuse(){
        return diffuse;
    }
    //computing the lighting for the lambert
    public Color computeLighting(Intersection i, Ray viewingRay, Light li){
        //directional vector that gets the direction from the light towards the intersection
        Vector killme = li.computeLightDirection(i.getPosition());
        double dot = i.getNormal().dot(killme);
        //if there is no light hitting that pixel
        if(dot < 0) return new Color(0, 0, 0);
        else{
            //shade by the diffuse color scaled by the dot factor (gives us an opacity type thing)
            Color c = diffuse.scale(dot);
            return c.shade(li.computeLightColor(i.getPosition()));
        }
    }
}
